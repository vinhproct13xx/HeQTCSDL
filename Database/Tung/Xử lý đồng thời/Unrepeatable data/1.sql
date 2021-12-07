--Unrepeatable data
--T1
BEGIN TRAN
SELECT SDT FROM dbo.GiaoVien WHERE TenGV=N'Nguyễn Phạm Hương'
WAITFOR DELAY '00:00:10'
SELECT SDT FROM dbo.GiaoVien WHERE TenGV=N'Nguyễn Phạm Hương'
COMMIT

