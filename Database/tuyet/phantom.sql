SET TRAN ISOLATION LEVEL SERIALIZABLE

BEGIN TRAN
	SELECT COUNT(dbo.HocSinh.MaHS) FROM dbo.HocSinh WHERE TrangThai = 1
	WAITFOR DELAY '00:00:10'
COMMIT



--T2
BEGIN TRAN 
	INSERT INTO dbo.HocSinh VALUES  ( 12, 'Mai thuy anh tuyet', NULL, 'Dong Nai', 'Nguyen Van Troi', 'Nguyen Thi Minh ', NULL, '0987654321', 1 )
