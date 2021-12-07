--CREATE DATABASE QuanLyDaNgoai
--DROP DATABASE QuanLyDaNgoai
--USE QuanLyDaNgoai
--SET DATEFORMAT DMY


CREATE TABLE HocSinh
(
	MaHS INT PRIMARY KEY not null,
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
	MaNH INT PRIMARY KEY not null,
	TenNH NVARCHAR(20)
)
CREATE TABLE LopHoc
(

	MaLop NVARCHAR(6) PRIMARY KEY not null,
	TenLop NVARCHAR(20),

)


CREATE TABLE CTLop
(
	MaHS INT FOREIGN KEY REFERENCES dbo.HocSinh(MaHS) not null,
	MaLop NVARCHAR(6) FOREIGN KEY REFERENCES dbo.LopHoc(MaLop) not null, 
	MaNH INT FOREIGN KEY REFERENCES dbo.NamHoc(MaNH) not null,
	CONSTRAINT pk_CTLop PRIMARY KEY(MaHS,MaLop, MaNH)
)

CREATE TABLE GiaoVien(
	MaGV INT PRIMARY KEY not null,
	TenGV NVARCHAR(50),
	NgaySinh SMALLDATETIME,
	DiaChi NVARCHAR(50),
	SDT NVARCHAR(11),
	CMND NVARCHAR(12),
	
)


CREATE TABLE CongTyDuLich(
	MaCongTy NVARCHAR(6) PRIMARY KEY not null,
	TenCongTy NVARCHAR(50) ,
	DiaChi NVARCHAR(255),
	SDT NVARCHAR(11)
)

CREATE TABLE DiaDiem
(
-- dia diem 
	MaDiaDiem NVARCHAR(6) PRIMARY KEY not null,
	TenDiaDiem NVARCHAR(50) ,
	DiaChi  NVARCHAR(255),
)

CREATE TABLE ChuyenDi
(
	MaChuyenDi  NVARCHAR(6) PRIMARY KEY not null,
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
	MaHopDong NVARCHAR(6) PRIMARY KEY not null,
	MaChuyenDi NVARCHAR(6) FOREIGN KEY REFERENCES dbo.ChuyenDi(MaChuyenDi),
	MaCongTy NVARCHAR(6) FOREIGN KEY REFERENCES dbo.CongTyDuLich(MaCongTy),
	TriGia FLOAT,
	NgayKy date,
	TrangThai nvarchar(20)
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
		