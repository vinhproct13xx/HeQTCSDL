USE AnToanGiaoThong
SET DATEFORMAT DMY

CREATE TABLE Loi
(
	MaLoi INT PRIMARY KEY,
	NoiDung NVARCHAR(255),
	MucTienPhat FLOAT
)


CREATE TABLE DoiTuong
(
	MaDT INT PRIMARY KEY IDENTITY,
	HoTen NVARCHAR(50),
	CMND NVARCHAR(12),
	DiaChi NVARCHAR(50),
	BienKS NVARCHAR(10),
	TongTienPhat FLOAT
)

CREATE TABLE ViPham
(
	MaLoi INT,
	MaDT INT FOREIGN KEY REFERENCES dbo.DoiTuong(MaDT),
	ThoiDiemVP SMALLDATETIME,
	NgayHen SMALLDATETIME
	CONSTRAINT pk PRIMARY KEY (MaLoi, MaDT)
)
--- cau a
CREATE TRIGGER ViPhamLoi
ON dbo.ViPham
after INSERT, UPDATE
AS
BEGIN
	DECLARE @muctien float, @ngayhen SMALLDATETIME, @thoidiemvp SMALLDATETIME, @temp SMALLDATETIME
	SELECT @ngayhen = Inserted.NgayHen, @thoidiemvp = Inserted.ThoiDiemVP, @muctien = dbo.Loi.MucTienPhat 
		FROM Inserted INNER JOIN dbo.Loi ON Loi.MaLoi = Inserted.MaLoi
		IF(@muctien > 300000)
			BEGIN
				SET @temp = @thoidiemvp +10
				IF( @ngayhen < @temp)
				PRINT 'Ngay hen phai lon hon 10 ngay! '
				ROLLBACK TRANSACTION
			END
END 

-- cau b
CREATE PROC c_Caub @thang INT, @nam INT, @cursor CURSOR VARYING OUT
AS
BEGIN
	
		SET @cursor = CURSOR FORWARD_ONLY
		STATIC
		FOR
		SELECT DISTINCT dbo.DoiTuong.* FROM (dbo.DoiTuong INNER JOIN dbo.ViPham ON ViPham.MaDT = DoiTuong.MaDT) INNER JOIN dbo.Loi WHERE Loi.MaLoi = dbo.ViPham.MaLoi
		WHERE MONTH(dbo.ViPham.ThoiDiemVP) = @thang AND year(dbo.ViPham.ThoiDiemVP) = @nam
		OPEN @cursor
END

CREATE PROC Caub @thang int , @nam INT 
AS
BEGIN 
	DECLARE @cursor CURSOR
	DECLARE @madt INT, @hoten NVARCHAR(50), @cmnd NVARCHAR(12), @diachi NVARCHAR(50), @bienks NVARCHAR(10), @tongtien FLOAT, @count INT = 0
		IF(@thang > 12 OR @thang < 1)
		BEGIN
			PRINT 'Thang chua hop le!'
		END
		ELSE
		BEGIN
		EXEC dbo.c_Caub @thang,@nam, @cursor OUT
		FETCH NEXT FROM @cursor INTO @madt, @hoten, @cmnd, @diachi, @bienks, @tongtien
			WHILE @@FETCH_STATUS = 0
			BEGIN
				SET @count += 1
				PRINT 'Ho ten: ' + @hoten
				FETCH NEXT FROM @cursor INTO @madt, @hoten, @cmnd, @diachi, @bienks, @tongtien
			END
		
			IF(@count =  0)
				PRINT 'Khong co du lieu'
	CLOSE @cursor
			DEALLOCATE @cursor
		END 
END


EXEC Caub 3, 2018

--- cau c
CREATE PROC Cauc @madt int, @thang int, @nam int 
AS
BEGIN
	DECLARE @count INT 
	SELECT @count = COUNT(MaLoi) FROM dbo.ViPham 
	WHERE MaDT = @madt AND MONTH(ThoiDiemVP) = @thang AND YEAR(ThoiDiemVP) = @nam
	PRINT 'Tong so loi: ' + CAST(@count AS NVARCHAR(20))
END
