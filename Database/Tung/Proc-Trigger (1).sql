-- Lưu chi phí chuyến đi trong bảng ChiPhi, khi có yêu cầu lưu thông tin chi phí từ form ChiPhi
-- Các thông tin trong bảng ChiPhi sẽ được lưu, riêng thuộc tính Tong được cập nhật theo tổng các thuộc tính còn lại trong bảng ChiPhi
create proc LuuChiPhi (
@MaChuyenDi nvarchar(6),
@VeCong float,
@TienXe float,
@TienAnTrua float,
@PhiHDV float,
@NuocUong float,
@TienAnXe float,
@LinhTinh float,
@GhiChu ntext )
as
begin
	begin
	update ChiPhi
	set VeCong=@VeCong, TienXe=@TienXe, TienAnTrua=@TienAnTrua,PhiHuongDanVien=@PhiHDV,NuocUong=@NuocUong,TienAnXe=@TienAnXe,LinhTinh=@LinhTinh,GhiChu=@GhiChu
	where MaChuyenDi=@MaChuyenDi
	end
	begin
	update ChiPhi
	set Tong=VeCong+TienXe+TienAnTrua+TienAnXe+PhiHuongDanVien+NuocUong+LinhTinh
	where MaChuyenDi=@MaChuyenDi
	end
end


-- Thống kê tổng chi phi trong 1 năm học bất kì, lấy thông tin từ bảng Chi phí, thống kê rồi bỏ vào Barchart trong form ThongKe
create proc chartTongChiPhi( @nam int)
as 
begin 
	select sum(Tong) as TongChiPhi 
	from ChiPhi,ChuyenDi
	where ChiPhi.MaChuyenDi=ChuyenDi.MaChuyenDi and year(ChuyenDi.NgayKhoiHanh)=@nam
end


-- Thống kê chi phí trung bình cho 1 chuyến đi trong 1 năm học bất kì, lấy thông tin từ bảng ChiPhi, thống kê rồi bỏ vào Barchart trong form ThongKe
create proc chartChiPhiTB( @nam int)
as
begin
	select round(avg(Tong),0) as ChiPhiTB
	from ChiPhi,ChuyenDi
	where ChiPhi.MaChuyenDi=ChuyenDi.MaChuyenDi and year(ChuyenDi.NgayKhoiHanh)=@nam
end


-- Thống kê số học sinh tham gia trung bình trong 1 chuyến đi, lấy thông tin từ bảng HocSinhThamGia, thống kê rồi bỏ vào Barchart trong form ThongKe
create proc chartHSTB (@nam int)
as
begin
	declare @TB float
	declare @HS float
	declare @CD float
	set @HS = (select count(MaHS) from ChuyenDi,HocSinhThamGia where ChuyenDi.MaChuyenDi=HocSinhThamGia.MaChuyenDi and year(ChuyenDi.NgayKhoiHanh) = @nam and HocSinhThamGia.ThamGia=1)
	set @CD = (select count(distinct(ChuyenDi.MaChuyenDi)) from ChuyenDi,HocSinhThamGia where ChuyenDi.MaChuyenDi=HocSinhThamGia.MaChuyenDi and year(ChuyenDi.NgayKhoiHanh) = @nam and HocSinhThamGia.ThamGia=1)
	set @TB = @HS / @CD
	select ROUND(@TB,0)
end


-- Thống kê thổng số chuyến đi trong 1 năm bất kì, lấy thông tin từ bảng ChuyenDi, thống kê rồi bỏ vào Barchart trong form ChuyenDi
create proc chartSoChuyenDi (@nam int)
as
begin
	select count(MaChuyenDi) from ChuyenDi where year(NgayKhoiHanh) = @nam
end 


-- Check xem tên địa điểm trước khi lưu có bị trùng lắp với tên các địa điểm đã có trong bảng DiaDiem hay không, nếu có thì thống báo lỗi và rollback 
create trigger checkTenDiaDiem_DiaDiem 
on DiaDiem
for insert, update
as
begin
	declare @tendd nvarchar(50)
	declare @tam nvarchar(50)
	declare @count int
	set @count=0
	select @tendd=TenDiaDiem from inserted
	declare csCheck cursor for
	select TenDiaDiem from DiaDiem
	open csCheck
	fetch next from csCheck
	into @tam
	while @@FETCH_STATUS = 0
	begin
	if @tendd=@tam
		begin
			set @count+=1
		end
	fetch next from csCheck
	into @tam
	end
	close csCheck
	deallocate cscheck	
	if (@count >1)
	begin
		print N'Tên địa điểm không được trùng!!'
		rollback tran
	end
end


-- Check địa chỉ địa điểm trước khi lưu có bị trùng với các địa chỉ có sẵn trong bảng DiaDiem hay không, nếu có thì thống báo lỗi và rollback 
create trigger checkDiaChi_DiaDiem 
on DiaDiem
for insert, update
as
begin
	declare @diachi nvarchar(255)
	declare @tam nvarchar(255)
	declare @count int
	set @count=0
	select @diachi=DiaChi from inserted
	declare csCheck cursor for
	select DiaChi from DiaDiem
	open csCheck
	fetch next from csCheck
	into @tam
	while @@FETCH_STATUS = 0
	begin
	if @diachi=@tam
		begin
			set @count+=1
		end
	fetch next from csCheck
	into @tam
	end
	close csCheck
	deallocate cscheck	
	if (@count >1)
	begin
		print N'Địa chỉ không được trùng!!'
		rollback tran
	end
end


-- Check xem tổng chi phí có cao hơn 100 triệu đồng không, nếu có thì thông báo lỗi và rollback
create trigger checkTongTien_ChiPhi
on ChiPhi
for insert,update
as
begin
	declare @tong float
	select @tong=Tong from inserted
	if(@tong>100000000)
	begin
	print N'Tổng chi phí không được lớn hơn 100 triệu đồng!!'
	rollback tran
	end
end

