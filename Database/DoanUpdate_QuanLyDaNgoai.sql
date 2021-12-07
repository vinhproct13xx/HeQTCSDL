----CÁC HÀM TẠO MÃ TỰ TĂNG--------
CREATE FUNCTION AUTO_MaChuyenDi()
RETURNS VARCHAR(5)
AS
BEGIN
	DECLARE @ID VARCHAR(5)
	IF (SELECT COUNT(MaChuyenDi) FROM dbo.ChuyenDi) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(MaChuyenDi, 3)) FROM dbo.ChuyenDi
		SELECT @ID = CASE
			WHEN @ID >= 0 and @ID < 9 THEN 'CD00' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 9 THEN 'CD0' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
		END
	RETURN @ID
END
GO
-------------------
CREATE FUNCTION AUTO_MaCongTyDuLich()
RETURNS VARCHAR(5)
AS
BEGIN
	DECLARE @ID VARCHAR(5)
	IF (SELECT COUNT(MaCongTy) FROM dbo.CongTyDuLich) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(MaCongTy, 3)) FROM dbo.CongTyDuLich
		SELECT @ID = CASE
			WHEN @ID >= 0 and @ID < 9 THEN 'CT00' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 9 THEN 'CT0' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
		END
	RETURN @ID
END

-------------------
CREATE FUNCTION AUTO_MaDiaDiem()
RETURNS VARCHAR(5)
AS
BEGIN
	DECLARE @ID VARCHAR(5)
	IF (SELECT COUNT(MaDiaDiem) FROM dbo.DiaDiem) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(MaDiaDiem, 3)) FROM dbo.DiaDiem
		SELECT @ID = CASE
			WHEN @ID >= 0 and @ID < 9 THEN 'DD00' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 9 THEN 'DD0' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
		END
	RETURN @ID
END
GO
------

----------------

------------------
CREATE FUNCTION AUTO_MaNamHoc()
RETURNS INT
AS
BEGIN
	DECLARE @ID VARCHAR(5)
	IF (SELECT COUNT(MaNH) FROM dbo.NamHoc) = 0
		SET @ID = 0
	ELSE
		SELECT @ID = MAX(MaNH)+1 FROM dbo.NamHoc
		
		
	RETURN @ID
END
GO-
---------------

-------------------
------------
CREATE FUNCTION AUTO_MaHopDong()
RETURNS VARCHAR(5)
AS
BEGIN
	DECLARE @ID VARCHAR(5)
	IF (SELECT COUNT(MaHopDong) FROM dbo.HopDong) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(MaHopDong, 3)) FROM dbo.HopDong
		SELECT @ID = CASE
			WHEN @ID >= 0 and @ID < 9 THEN 'HD00' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 9 THEN 'HD0' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
		END
	RETURN @ID
END
GO
------------///////////////////////////////////////////////////////-----------





CREATE DATABASE QuanLyDaNgoai
USE QuanLyDaNgoai
SET DATEFORMAT DMY

CREATE TABLE HocSinh
(
	MaHS INT PRIMARY KEY CONSTRAINT idHocSinh DEFAULT dbo.AUTO_MaHocSinh(),
	TenHS NVARCHAR(50),
	NgaySinh SMALLDATETIME,
	DiaChi NVARCHAR(50),
	TenCha NVARCHAR(50),
	TenMe NVARCHAR(50),
	TenNguoiGiamHo NVARCHAR(50),
	SDT NVARCHAR(11),
	TrangThai bit
)

CREATE TABLE NamHoc(
	MaNH INT PRIMARY KEY CONSTRAINT idNamHoc DEFAULT dbo.AUTO_MaNamHoc(),
	TenNH NVARCHAR(20)
)


CREATE TABLE LopHoc
(

	MaLop NVARCHAR(6) PRIMARY KEY  CONSTRAINT idLop DEFAULT dbo.AUTO_MaLop(),
	TenLop NVARCHAR(20),

)

CREATE TABLE CTLop
(
	MaHS INT FOREIGN KEY REFERENCES dbo.HocSinh(MaHS) not null,
	MaLop NVARCHAR(6) FOREIGN KEY REFERENCES dbo.LopHoc(MaLop) not null, 
	MaNH INT FOREIGN KEY REFERENCES dbo.NamHoc(MaNH) not null,
	CONSTRAINT pk_CTLop PRIMARY KEY(MaHS,MaLop, MaNH)
)
GO
CREATE TABLE GiaoVien(
	MaGV INT PRIMARY KEY ,
	TenGV NVARCHAR(50),
	NgaySinh SMALLDATETIME,
	DiaChi NVARCHAR(50),
	SDT NVARCHAR(11),
	CMND NVARCHAR(12),
	TrangThai bit
)


CREATE TABLE CongTyDuLich(
	MaCongTy NVARCHAR(6) PRIMARY KEY CONSTRAINT idCongTY DEFAULT dbo.AUTO_MaCongTyDuLich(),
	TenCongTy NVARCHAR(50) ,
	DiaChi NVARCHAR(255),
	SDT NVARCHAR(11)
)


create TABLE DiaDiem
(
-- dia diem 
	MaDiaDiem NVARCHAR(6) PRIMARY KEY CONSTRAINT idDiaDiem DEFAULT dbo.AUTO_MaDiaDiem() ,
	TenDiaDiem NVARCHAR(50) ,
	DiaChi  NVARCHAR(255),
)

CREATE TABLE ChuyenDi
(
	MaChuyenDi  NVARCHAR(6) PRIMARY KEY CONSTRAINT idChuyenDi DEFAULT dbo.AUTO_MaChuyenDi(),
	MaCongTyDuLich NVARCHAR(6) FOREIGN KEY REFERENCES dbo.CongTyDuLich(MaCongTy),
	MaDiaDiem NVARCHAR(6) FOREIGN KEY REFERENCES dbo.DiaDiem(MaDiaDiem),
	NgayKhoiHanh DATE
)


CREATE TABLE ChiTietChuyenDi(
	MaChuyenDi NVARCHAR(6) FOREIGN KEY REFERENCES dbo.ChuyenDi(MaChuyenDi) not null,
	ThoiGian time(5),
	HoatDong nvarchar(255),
	GhiChu  NVARCHAR(255)
	constraint pk_MaCD primary key (MaChuyenDi)
)


CREATE TABLE ChiPhi
(
	
	MaChuyenDi NVARCHAR(6) FOREIGN KEY REFERENCES dbo.ChuyenDi(MaChuyenDi) not null,
	VeCong FLOAT,
	TienXe FLOAT,
	TienAnTrua FLOAT,
	PhiHuongDanVien FLOAT,
	NuocUong FLOAT,
	TienAnXe FLOAT,
	LinhTinh float,
	GhiChu Nvarchar(255),
	Tong float
	CONSTRAINT pk_ChiPhi PRIMARY KEY (MaChuyenDi)
)

CREATE TABLE HopDong
(
	MaHopDong NVARCHAR(6) PRIMARY KEY CONSTRAINT idHopDong DEFAULT dbo.AUTO_MaHopDong(),
	MaChuyenDi NVARCHAR(6) FOREIGN KEY REFERENCES dbo.ChuyenDi(MaChuyenDi),
	MaCongTy NVARCHAR(6) FOREIGN KEY REFERENCES dbo.CongTyDuLich(MaCongTy),
	TriGia FLOAT,
	TrangThai nvarchar(20),
	NgayKy date
	
)

CREATE TABLE ThongTinThanhToan -- chi tiet hop dong
(
	MaHopDong NVARCHAR(6) FOREIGN KEY REFERENCES dbo.HopDong(MaHopDong) not null,
	LanThanhToan INT not null,
	NgayThanhToan SMALLDATETIME,
	SoTien float,
	CONSTRAINT pk_ThongTinThanhToan PRIMARY KEY (MaHopDong,LanThanhToan)
)

CREATE TABLE HocSinhThamGia
(
	MaHS INT FOREIGN KEY REFERENCES dbo.HocSinh(MaHS) not null,
	MaLop NVARCHAR(6) FOREIGN KEY REFERENCES dbo.LopHoc(MaLop) not null,
	MaChuyenDi NVARCHAR(6) FOREIGN KEY REFERENCES dbo.ChuyenDi not null,
	ThamGia BIT,
	IsDongTien BIT,
	CONSTRAINT pk_HocSinhThamGia PRIMARY KEY (MaHS, MaLop, MaChuyenDi)
)

CREATE TABLE GiaoVienThamGia
(
	MaGiaoVien INT FOREIGN KEY REFERENCES dbo.GiaoVien(MaGV) not null,
	MaLop NVARCHAR(6) FOREIGN KEY REFERENCES dbo.LopHoc(MaLop) not null,
	MaChuyenDi NVARCHAR(6) FOREIGN KEY REFERENCES dbo.ChuyenDi not null,
	ThamGia BIT,
	CONSTRAINT pk_GiaoVienThamGia PRIMARY KEY (MaGiaoVien, MaLop, MaChuyenDi)
)

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[User](
	[ID] [int] NOT NULL,
	[Email] [nvarchar](50) NULL,
	[Password] [nvarchar](50) NULL,
	[Level] [int] NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


CREATE TABLE CT_GV_Lop
(
		 MaGV INT FOREIGN KEY REFERENCES dbo.GiaoVien(MaGV) not null,
		 MaLop NVARCHAR(6) FOREIGN KEY REFERENCES dbo.LopHoc(MaLop) not null,
		 MaNH INT FOREIGN KEY REFERENCES dbo.NamHoc(MaNH) not null,
		 CONSTRAINT pk PRIMARY KEY (MaGV, MaLop, MaNH)
)
		

INSERT INTO dbo.LopHoc
        ( TenLop )
VALUES  (
          N'Chồi'  -- TenLop - nvarchar(20)
          )
INSERT INTO dbo.NamHoc
        (  TenNH )
VALUES  (
          N'2018'  -- TenNH - nvarchar(20)
          )

		  
