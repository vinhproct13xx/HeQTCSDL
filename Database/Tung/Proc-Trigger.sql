-- proc LuuChiPhi dung de luu chi phi chuyen di
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


-- proc charTongChiPhi de thong ke tong chi phi theo tung nam
create proc chartTongChiPhi( @nam int)
as 
begin 
	select sum(Tong) as TongChiPhi 
	from ChiPhi,ChuyenDi
	where ChiPhi.MaChuyenDi=ChuyenDi.MaChuyenDi and year(ChuyenDi.NgayKhoiHanh)=@nam
end


--proc chartChiPhiTB de thong ke chi phi trung binh theo tung nam
create proc chartChiPhiTB( @nam int)
as
begin
	select round(avg(Tong),0) as ChiPhiTB
	from ChiPhi,ChuyenDi
	where ChiPhi.MaChuyenDi=ChuyenDi.MaChuyenDi and year(ChuyenDi.NgayKhoiHanh)=@nam
end


-- proc chartHSTB de thong ke so hoc sinh di da ngoai trung binh theo tung nam
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


-- proc chartSoChuyenDi de thong ke so chuyen di trong 1 nam
create proc chartSoChuyenDi (@nam int)
as
begin
	select count(MaChuyenDi) from ChuyenDi where year(NgayKhoiHanh) = @nam
end 


-- trigger checkTenDiaDiem_DiaDiem để check xem tên địa điểm lưu có bị trùng hay không
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


-- trigger checkDiaChi_DiaDiem để check xem địa chỉ địa điểm lưu có bị trùng hay không
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


-- trigger checkTongTien_ChiPhi để check xem tổng chi phí có cao hơn 100 triệu đồng không
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

