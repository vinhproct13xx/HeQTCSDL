---database
CREATE DATABASE BUUCUC
USE BUUCUC

USE [BUUCUC]
GO

/****** Object:  Table [dbo].[BUUCUC]    Script Date: 10/3/2018 5:49:12 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[BUUCUC](
	[SoHieuBC] [int] NOT NULL,
	[TenBC] [nvarchar](50) NULL,
	[DiaChi] [nvarchar](100) NULL,
	[DienThoai] [nchar](10) NULL,
	[TinhTP] [nvarchar](20) NULL,
 CONSTRAINT [PK_BUUCUC] PRIMARY KEY CLUSTERED 
(
	[SoHieuBC] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

USE [BUUCUC]
GO

/****** Object:  Table [dbo].[TAIKHOAN]    Script Date: 10/3/2018 5:49:27 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[TAIKHOAN](
	[MaTK] [int] NOT NULL,
	[HoTenKH] [nvarchar](50) NULL,
	[DiaChi] [nvarchar](50) NULL,
	[CMND] [nvarchar](12) NULL,
	[SoHieuBC] [int] NULL,
	[NgayMoTK] [smalldatetime] NULL,
 CONSTRAINT [PK_TAIKHOAN] PRIMARY KEY CLUSTERED 
(
	[MaTK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

USE [BUUCUC]
GO

/****** Object:  Table [dbo].[GIAODICH]    Script Date: 10/3/2018 5:49:36 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[GIAODICH](
	[STT] [int] IDENTITY(1,1) NOT NULL,
	[MaTK] [int] NULL,
	[SoHieuBC] [int] NULL,
	[NgayGD] [smalldatetime] NULL,
	[SoTien] [float] NULL,
	[HinhThucGD] [nvarchar](10) NULL,
 CONSTRAINT [PK_GIAODICH] PRIMARY KEY CLUSTERED 
(
	[STT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

ALTER TABLE [dbo].[GIAODICH]  WITH CHECK ADD  CONSTRAINT [FK_GIAODICH_BUUCUC] FOREIGN KEY([SoHieuBC])
REFERENCES [dbo].[BUUCUC] ([SoHieuBC])
GO

ALTER TABLE [dbo].[GIAODICH] CHECK CONSTRAINT [FK_GIAODICH_BUUCUC]
GO

ALTER TABLE [dbo].[GIAODICH]  WITH CHECK ADD  CONSTRAINT [FK_GIAODICH_TAIKHOAN] FOREIGN KEY([MaTK])
REFERENCES [dbo].[TAIKHOAN] ([MaTK])
GO

ALTER TABLE [dbo].[GIAODICH] CHECK CONSTRAINT [FK_GIAODICH_TAIKHOAN]
GO





--1. Tạo stored procedure với tham số vào là năm thực hiện giao dịch và mã tài
--khoản của khách hàng. Với khách hàng có mã tài khoản đó, hãy cho biết tổng
--tiền gửi theo từng tháng của năm này. Viết đoạn chương trình chạy và hiển thị
--kết quả, thông tin hiển thị gồm có: mã tài khoản, tháng, tổng tiền gửi. 

CREATE PROC Bai1 @nam INT , @matk INT 
AS
BEGIN
	DECLARE @thang INT = 0, @tongtien FLOAT = 0
	WHILE(@thang <=12)
	BEGIN
		SELECT @tongtien = SUM(dbo.GIAODICH.SoTien) FROM dbo.GIAODICH 
		WHERE MaTK = @matk AND YEAR(NgayGD) = @nam AND month(NgayGD) = @thang AND HinhThucGD = 'gui'
		IF(@tongtien > 0)
			PRINT 'Tong Tien thang ' + CAST(@thang AS NVARCHAR(2)) + ' la: '+ CAST(@tongtien AS NVARCHAR(50))
		SET @thang +=1
	END 

END

DECLARE @matk INT = 2
DECLARE @nam INT = 2018
PRINT 'Ma Tk : ' + CAST(@matk AS NVARCHAR(10))
EXEC dbo.Bai1 @nam, @matk
DROP PROC dbo.Bai1

--Tạo Function với tham số vào là năm thực hiện giao dịch và mã tài khoản của
--khách hàng. Hàm trả về tổng tiền rút của khách hàng có mã tài khoản và năm đó.
--Viết đoạn chương trình chạy và hiển thị kết quả, thông tin hiển thị gồm có: mã
--tài khoản, năm, tổng tiền rút


CREATE FUNCTION TongRut (@nam int , @matk int )
returns float
AS
BEGIN
	DECLARE @tongtien float 
	SELECT @tongtien = SUM(dbo.GIAODICH.SoTien) FROM dbo.GIAODICH
	 WHERE MaTK = @matk AND YEAR(NgayGD) = @nam
	RETURN @tongtien
END

DECLARE @matk INT = 3
DECLARE @tongtien FLOAT
DECLARE @nam INT = 2019
PRINT 'ma tk ' + CAST(@matk AS NVARCHAR(10)) + ' da rut trong nam ' + CAST(@nam AS NVARCHAR(10)) 
SELECT @tongtien = DBO.TongRut(2019, 3)
PRINT 'So tien: '+ CAST(@tongtien AS NVARCHAR(50))

--Tạo stored procedure với tham số vào là tên tỉnh/thành phố (TinhTP) và tham
--số ra là cursor chứa danh sách các khách hàng (họ tên khách hàng, địa chỉ,
--CMND) đã mở tài khoản giao dịch ở các bưu cục của tỉnh/thành phố này. Viết
--đoạn chương trình chạy và hiển thị kết quả.


CREATE PROC c_TinhTP  @tinhtp NVARCHAR(20), @cursor CURSOR VARYING OUT
AS
BEGIN
	
	SET @cursor = CURSOR FORWARD_ONLY
	STATIC 
	FOR
	 SELECT DISTINCT dbo.TAIKHOAN.HoTenKH, dbo.TAIKHOAN.DiaChi, dbo.TAIKHOAN.CMND
	 FROM dbo.TAIKHOAN INNER JOIN dbo.BUUCUC ON BUUCUC.SoHieuBC = TAIKHOAN.SoHieuBC
	 WHERE dbo.BUUCUC.TinhTP = @tinhtp
	OPEN @cursor				
	
END

DECLARE @cursor CURSOR
DECLARE @hoten NVARCHAR(50), @diachi NVARCHAR(50), @cmnd NVARCHAR(12)
EXEC dbo.c_TinhTP  'hcm', @cursor OUT 
FETCH NEXT FROM @cursor INTO @hoten, @diachi, @cmnd
	WHILE
		@@FETCH_STATUS = 0
	BEGIN
		PRINT 'Khach hang:  '+ @hoten 
		PRINT 'Dia Chi:  ' + @diachi
		PRINT 'CMND: ' + @cmnd
		FETCH NEXT FROM @cursor INTO  @hoten, @diachi, @cmnd

	END
	CLOSE @cursor
	DEALLOCATE @cursor



--Tạo stored procedure với tham số vào là tên bưu cục (TenBC) và tham số ra là
--cursor chứa danh sách các khách hàng (họ tên khách hàng, địa chỉ, CMND) đã
--mở tài khoản giao dịch ở bưu cục này. Viết đoạn chương trình chạy và hiển thị
--kết quả.
CREATE PROC c_KhachHang  @tenbc NVARCHAR(20), @cursor CURSOR VARYING OUT
AS
BEGIN
	
	SET @cursor = CURSOR FORWARD_ONLY
	STATIC 
	FOR
	 SELECT DISTINCT dbo.TAIKHOAN.HoTenKH, dbo.TAIKHOAN.DiaChi, dbo.TAIKHOAN.CMND
	 FROM dbo.TAIKHOAN INNER JOIN dbo.BUUCUC ON BUUCUC.SoHieuBC = TAIKHOAN.SoHieuBC
	 WHERE dbo.BUUCUC.TenBC = @tenbc
	OPEN @cursor			
	
END

DECLARE @cursor CURSOR
DECLARE @hoten NVARCHAR(50), @diachi NVARCHAR(50), @cmnd NVARCHAR(12)
EXEC dbo.c_KhachHang'acb', @cursor OUT 
FETCH NEXT FROM @cursor INTO @hoten, @diachi, @cmnd
	WHILE
		@@FETCH_STATUS = 0
	BEGIN
		PRINT 'Khach hang:  '+ @hoten 
		PRINT 'Dia Chi:  ' + @diachi
		PRINT 'CMND: ' + @cmnd
		FETCH NEXT FROM @cursor INTO  @hoten, @diachi, @cmnd

	END
	CLOSE @cursor
	DEALLOCATE @cursor




-- Bai e
CREATE TRIGGER RutTien ON GiaoDich
AFTER INSERT, UPDATE, DELETE
AS 
BEGIN 
	DECLARE @matk INT, @ngaygd SMALLDATETIME
	SELECT @matk = Inserted.MaTK, @ngaygd = inserted.NgayGD FROM inserted
	IF((SELECT SUM(dbo.GIAODICH.SoTien) FROM dbo.GIAODICH WHERE MaTK = @matk AND NgayGD = @ngaygd AND HinhThucGD = 'rut') > 50000000)
	BEGIN
	PRINT 'Khach hang khong the rut vi da hon 50.000.000'
		ROLLBACK TRANSACTION	
	END
END 
DROP TRIGGER RutTien


-- Bai f
CREATE TRIGGER t_GiaoDich ON GiaoDich
AFTER INSERT, UPDATE, DELETE
AS 
BEGIN 
	DECLARE @matk INT, @ngaygd SMALLDATETIME
	SELECT @matk = Inserted.MaTK, @ngaygd = inserted.NgayGD FROM inserted
	IF((SELECT count(*) FROM dbo.GIAODICH WHERE MaTK = @matk AND NgayGD = @ngaygd AND HinhThucGD = 'rut') >10)
	BEGIN
	PRINT 'Khach hang khong the rut vi khach hang da rut >10 lan'
		ROLLBACK TRANSACTION	
	END
END 
DROP TRIGGER t_GiaoDich