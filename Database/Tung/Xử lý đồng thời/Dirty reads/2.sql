--Dirty reads

--T1
BEGIN TRAN
	UPDATE dbo.GiaoVien SET TrangThai =0 WHERE MaGV=2
	WAITFOR DELAY '00:00:05'
	ROLLBACK

SELECT * FROM dbo.GiaoVien WHERE TrangThai = 1
