---Quy dinh 1

DECLARE c_DocGia CURSOR FOR SELECT DISTINCT dbo.DocGia.MaDocGia, dbo.DocGia.Ho, dbo.DocGia.TenLot, dbo.DocGia.Ten, dbo.NguoiLon.SoNha, dbo.NguoiLon.Duong, dbo.NguoiLon.Quan, dbo.NguoiLon.DienThoai, dbo.NguoiLon.HanSuDung FROM dbo.DocGia INNER JOIN dbo.NguoiLon ON dbo.DocGia.MaDocGia = dbo.NguoiLon.ma_DocGia
OPEN c_DocGia
FETCH NEXT FROM c_DocGia INTO @MaDG, @HoDG, @Tenlot, @TenDG

WHILE
	@@FETCH_STATUS =0
BEGIN
	PRINT CAST(@MaDG AS NVARCHAR(10)) + ': ' + @HoDG + ' '+ @Tenlot + ' ' + @TenDG
	FETCH NEXT FROM c_DocGia INTO @MaDG, @HoDG, @Tenlot, @TenDG
END
CLOSE c_DocGia
DEALLOCATE c_DocGia



CREATE PROC sp_ThongTinDocGia (@maDG INT)
AS
BEGIN
	DECLARE @temp INT =0
	 SELECT @temp = dbo.NguoiLon.ma_DocGia FROM dbo.NguoiLon WHERE ma_DocGia = @maDG
	IF(@temp = @maDG)
		BEGIN
		DECLARE @sonha INT, @duong NVARCHAR(50), @quan NVARCHAR(50), @dienthoai NCHAR(10), @hansudung SMALLDATETIME 
		DECLARE @ho NVARCHAR(10), @tenlot NVARCHAR(50), @ten NVARCHAR(10), @ngaysinh SMALLDATETIME
		SELECT @ho = dbo.DocGia.Ho, @tenlot = dbo.DocGia.TenLot, @ten = dbo.DocGia.Ten, @ngaysinh = dbo.DocGia.NgaySinh FROM dbo.DocGia WHERE MaDocGia = @temp
		SELECT @sonha = dbo.NguoiLon.SoNha, @duong = dbo.NguoiLon.Duong, @quan = dbo.NguoiLon.Quan, @dienthoai = dbo.NguoiLon.DienThoai, @hansudung = dbo.NguoiLon.HanSuDung FROM dbo.NguoiLon WHERE ma_DocGia = @temp
	
		PRINT CAST(@maDG AS NVARCHAR(10)) + ' : ' + @ho + ' '+ @tenlot + ' '+@ten +' ngay sinh: '+ CAST(@ngaysinh AS NVARCHAR(20))
		PRINT CAST(@sonha AS NVARCHAR(10)) + ', '+ @duong + ', ' + @quan+ ', '+ @dienthoai + ', '+ CAST(@hansudung AS NCHAR(10))
		END
        
END
DROP PROC dbo.sp_ThongTinDocGia
EXEC dbo.sp_ThongTinDocGia 
 @maDG = 1 
