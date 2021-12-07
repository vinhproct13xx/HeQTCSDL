--Unrepeatable data
--T2
BEGIN TRAN
UPDATE dbo.GiaoVien SET SDT = N'11111' WHERE TenGV=N'Nguyễn Phạm Hương'
COMMIT