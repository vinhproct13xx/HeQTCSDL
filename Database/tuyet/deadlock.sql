
	
SET TRANSACTION ISOLATION LEVEL READ COMMITTED
BEGIN TRAN

		UPDATE dbo.HocSinh SET DiaChi = 'TPHCM' WHERE MaHS = 1
		WAITFOR DELAY '00:00:10'
		UPDATE dbo.GiaoVien SET DiaChi = 'Thai Binh' WHERE MaGV = 1

COMMIT

SELECT * FROM dbo.HocSinh WHERE MaHS = 1
SELECT * FROM dbo.GiaoVien WHERE MaGV = 1


--T2
SET TRANSACTION ISOLATION LEVEL  READ COMMITTED
BEGIN TRAN
		UPDATE dbo.GiaoVien SET DiaChi = 'Thai Binh' WHERE MaGV = 1
		WAITFOR DELAY '00:00:10'
		UPDATE dbo.HocSinh SET DiaChi = 'TPHCM' WHERE MaHS = 1
		
		

		COMMIT