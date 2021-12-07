--Giải quyết dirty reads
--Chuyển mức cô lập của T2 về Read Committed
SET TRANSACTION ISOLATION LEVEL READ COMMITTED
SELECT * FROM dbo.GiaoVien WHERE TrangThai = 1
