--Lost update
--T2

--Lost update
--T2

SET TRAN ISOLATION LEVEL READ COMMITTED
BEGIN TRAN 
DECLARE @triGiaHD FLOAT
SELECT @triGiaHD = TriGia FROM dbo.HopDong  WHERE MaHopDong = 'HD1'
UPDATE dbo.HopDong SET TriGia = @triGiaHD + 200 WHERE MaHopDong = 'HD1'
COMMIT

SELECT TriGia FROM dbo.HopDong WHERE MaHopDong = 'HD1'
