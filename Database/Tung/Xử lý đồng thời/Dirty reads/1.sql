--Dirty reads

-- T2
SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED
SELECT * FROM dbo.GiaoVien WHERE TrangThai = 1



