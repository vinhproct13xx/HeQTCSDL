CREATE DATABASE GiaoVien
GO
USE GiaoVien
SET DATEFORMAT DMY
GO

CREATE TABLE GiangVien
(
	MAGV INT IDENTITY PRIMARY KEY,
	TEN NVARCHAR(100) ,
	NOISINH NVARCHAR(100),
	NGAYSINH DATETIME,
	LUONG INT
)
GO
INSERT INTO dbo.GiangVien
        (  TEN, NOISINH, NGAYSINH, LUONG )
VALUES  ( 
          N'Nguy?n V?n C', -- TEN - nvarchar(100)
          N'B�nh D??ng', -- NOISINH - nvarchar(100)
          '15/02/1995', -- NGAYSINH - datetime
          12000  -- LUONG - int
          )
GO
----- update nh?ng gi?ng vi�n >30 tu?i c� l??ng =50000
DECLARE cs_GIANGVIEN CURSOR FOR SELECT MAGV, (YEAR(GETDATE())-YEAR(NGAYSINH)) FROM dbo.GiangVien
OPEN cs_GIANGVIEN
DECLARE @maGV INT
DECLARE @tuoi INT

FETCH NEXT FROM cs_GIANGVIEN INTO @maGV,@tuoi
WHILE @@FETCH_STATUS=0
BEGIN
	IF @tuoi>30
	BEGIN
		UPDATE dbo.GiangVien SET LUONG=50000 WHERE MAGV=@maGV
	END
	FETCH NEXT FROM cs_GIANGVIEN INTO @maGV, @tuoi	
END
CLOSE cs_GIANGVIEN
DEALLOCATE cs_GIANGVIEN
GO
SELECT * FROM dbo.GiangVien
----------Demo su dug tham chieu cursor tra ve
---X�y d?ng th? t?c truy?n v�o NOISINH v� tham s? d?u ra l� cursor ch?a danh s�ch c�c GV theo NOISINH truy?n v�o
GO
CREATE PROC USP_Demo
@cursorGV CURSOR VARYING OUTPUT, @noisinh NVARCHAR(100)
AS
SET @cursorGV = CURSOR FOR SELECT * FROM dbo.GiangVien WHERE NOISINH = @noisinh
OPEN @cursorGV
GO

DECLARE @ResultCS CURSOR 
GO
EXEC dbo.USP_Demo @noisinh = N'B�nh Duong', @cursorGV=@ResultCS OUTPUT -- nvarchar(100), 
WHILE (@@FETCH_STATUS = 0)
BEGIN
FETCH NEXT FROM @ResultCS
END
CLOSE @ResultCS
DEALLOCATE @ResultCS
