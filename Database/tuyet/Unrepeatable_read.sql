

SET TRANSACTION ISOLATION LEVEL REPEATABLE READ

BEGIN TRAN
	SELECT dbo.HocSinh.DiaChi FROM dbo.HocSinh WHERE MaHS = 1
	WAITFOR DELAY '00:00:05'
	SELECT dbo.HocSinh.DiaChi FROM dbo.HocSinh WHERE MaHS = 1
	COMMIT

--T2

SET TRANSACTION ISOLATION LEVEL REPEATABLE READ
BEGIN TRAN

	UPDATE dbo.HocSinh SET DiaChi = 'Lam Dong' WHERE MaHS = 1
		SELECT dbo.HocSinh.DiaChi FROM dbo.HocSinh WHERE MaHS = 1
	COMMIT
	