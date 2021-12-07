--phantom
--T2

BEGIN TRAN
	INSERT INTO dbo.ThongTinThanhToan
	        ( MaHopDong ,
	          LanThanhToan ,
	          NgayThanhToan ,
	          SoTien
	        )
	VALUES  ( N'HD1' , -- MaHopDong - nvarchar(6)
	          5 , -- LanThanhToan - int
	          GETDATE() , -- NgayThanhToan - date
	          10000  -- SoTien - float
	        )
COMMIT
