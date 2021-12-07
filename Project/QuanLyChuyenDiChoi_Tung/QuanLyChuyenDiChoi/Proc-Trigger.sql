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
	set @HS = (select count(MaHS) from ChuyenDi,HocSinhThamGia where ChuyenDi.MaChuyenDi=HocSinhThamGia.MaChuyenDi and year(ChuyenDi.NgayKhoiHanh) = @nam )
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

