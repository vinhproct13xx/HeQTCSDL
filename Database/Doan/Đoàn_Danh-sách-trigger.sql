--PROCEDURE--
CREATE PROC USP_DeleteHopDong
@maHD NVARCHAR(6)
AS
BEGIN

	DELETE dbo.ThongTinThanhToan WHERE MaHopDong= @maHD
	DELETE dbo.HopDong WHERE MaHopDong= @maHD
END
GO

--TRIGGER--

--Cập nhật trạng thái hợp đồng khi xóa thông tin thanh toán
CREATE TRIGGER UTG_CapNhatTrangThai
ON dbo.ThongTinThanhToan AFTER DELETE
AS
BEGIN
	DECLARE @maHD NVARCHAR(6)
	DECLARE @sum FLOAT
	DECLARE @triGiaHD FLOAT
	SELECT @maHD=Deleted.MaHopDong FROM Deleted
	SELECT @sum=SUM(SoTien) FROM dbo.ThongTinThanhToan WHERE MaHopDong=@maHD
	SELECT @triGiaHD = TriGia FROM dbo.HopDong WHERE MaHopDong = @maHD
	IF(@sum >= @triGiaHD)
		UPDATE dbo.HopDong SET TrangThai = N'Đã thanh toán' WHERE MaHopDong=@maHD
	ELSE 
		UPDATE dbo.HopDong SET TrangThai = N'Chưa thanh toán' WHERE MaHopDong=@maHD
END
--Cập nhật trạng thái hợp đồng khi thêm, sửa thông tin thanh toán
 CREATE TRIGGER UTG_CapNhatTrangThai1
ON dbo.ThongTinThanhToan AFTER INSERT,UPDATE
AS
BEGIN
	DECLARE @maHD NVARCHAR(6)
	DECLARE @sum FLOAT
	DECLARE @triGiaHD FLOAT
	SELECT @maHD=Inserted.MaHopDong FROM Inserted
	SELECT @sum=SUM(SoTien) FROM dbo.ThongTinThanhToan WHERE MaHopDong=@maHD
	SELECT @triGiaHD = TriGia FROM dbo.HopDong WHERE MaHopDong = @maHD
	IF(@sum >= @triGiaHD)
		UPDATE dbo.HopDong SET TrangThai = N'Đã thanh toán' WHERE MaHopDong=@maHD
	ELSE 
		UPDATE dbo.HopDong SET TrangThai = N'Chưa thanh toán' WHERE MaHopDong=@maHD
END
-- Cập nhật trạng thái hợp đồng khi sửa trị giá hợp đồng
CREATE TRIGGER UTG_CapNhatTriGiaHopDong
ON dbo.HopDong AFTER UPDATE
AS
BEGIN
	DECLARE @maHD NVARCHAR(6)
	DECLARE @sum FLOAT
	DECLARE @triGiaHD FLOAT
	SELECT @maHD=Inserted.MaHopDong FROM Inserted
	SELECT @sum=SUM(SoTien) FROM dbo.ThongTinThanhToan WHERE MaHopDong=@maHD
	SELECT @triGiaHD = TriGia FROM dbo.HopDong WHERE MaHopDong = @maHD
	IF(@sum >= @triGiaHD)
		UPDATE dbo.HopDong SET TrangThai = N'Đã thanh toán' WHERE MaHopDong=@maHD
	ELSE
		UPDATE dbo.HopDong SET TrangThai = N'Chưa thanh toán' WHERE MaHopDong=@maHD
END
--Ngày ký hợp đồng phải nhỏ hơn ngày khởi hành.
CREATE TRIGGER UTG_ngayKy ON dbo.HopDong
FOR INSERT,UPDATE
AS
BEGIN
	DECLARE @ngayKhoiHanh DATE
	DECLARE @ngayKy DATE
	SELECT @ngayKhoiHanh=dbo.ChuyenDi.NgayKhoiHanh FROM Inserted,dbo.ChuyenDi WHERE Inserted.MaChuyenDi = ChuyenDi.MaChuyenDi
	SELECT @ngayKy=Inserted.NgayKy FROM Inserted
	IF(@ngayKy >= @ngayKhoiHanh)
	BEGIN
		RAISERROR (N'Ngày ký hợp đồng phải nhỏ hơn ngày khởi hành!',16,2)
		ROLLBACK
	END
END
GO
--Ngày ký hợp đồng phải nhỏ hơn ngày khởi hành mới cập nhật
CREATE TRIGGER UTG_UpdateNgayKhoiHanh ON dbo.ChuyenDi
FOR UPDATE
AS
BEGIN
	DECLARE @ngayKhoiHanh DATE
	DECLARE @ngayKy DATE
	SELECT @ngayKhoiHanh=Inserted.NgayKhoiHanh FROM Inserted
	SELECT @ngayKy=NgayKy FROM Inserted,dbo.HopDong WHERE Inserted.MaChuyenDi=HopDong.MaChuyenDi
	IF(@ngayKy >= @ngayKhoiHanh)
	BEGIN
		RAISERROR (N'Ngày khởi hành phải lớn ngày ký hợp đồng!',16,2)
		ROLLBACK
	END
END

INSERT INTO dbo.HopDong
        ( MaHopDong ,
          MaChuyenDi ,
          MaCongTy ,
          TriGia ,
          TrangThai ,
          NgayKy
        )
VALUES  ( N'HD4' , -- MaHopDong - nvarchar(6)
          N'CD5' , -- MaChuyenDi - nvarchar(6)
          N'CT3' , -- MaCongTy - nvarchar(6)
          10000.0 , -- TriGia - float
          N'Chua Thanh Toan' , -- TrangThai - nvarchar(20)
          GETDATE()  -- NgayKy - date
        )
--Ngày thanh toán phải lớn hơn hoặc bằng ngày ký hợp đồng.
GO
CREATE TRIGGER UTG_ngayThanhToan ON dbo.ThongTinThanhToan
FOR INSERT,UPDATE
AS
BEGIN
	DECLARE @ngayThanhToan DATE
	DECLARE @ngayKy DATE
	SELECT @ngayThanhToan=Inserted.NgayThanhToan FROM Inserted
	SELECT @ngayKy=NgayKy FROM dbo.HopDong,Inserted WHERE HopDong.MaHopDong = Inserted.MaHopDong
	IF(@ngayThanhToan<@ngayKy)
	BEGIN
		RAISERROR (N'Ngày thanh toán phải lớn hơn hoặc bằng ngày ký hợp đồng.',16,1)
		ROLLBACK
	END
END
GO

CREATE TRIGGER UTG_updateNgayKy ON dbo.HopDong
FOR UPDATE
AS
BEGIN
	DECLARE @maHopDong NVARCHAR(6)
	DECLARE @ngayKy DATE

	SELECT @maHopDong=Inserted.MaHopDong FROM Inserted
	SELECT @ngayKy = Inserted.NgayKy FROM Inserted
	DECLARE cs_ThanhToan CURSOR FOR SELECT NgayThanhToan FROM dbo.ThongTinThanhToan WHERE MaHopDong=@maHopDong
	OPEN cs_ThanhToan
	DECLARE @ngayThanhToan DATE

	FETCH NEXT FROM cs_ThanhToan INTO @ngayThanhToan
	WHILE @@FETCH_STATUS=0
	BEGIN
			IF(@ngayThanhToan<@ngayKy)
			BEGIN
				RAISERROR (N'Ngày ký hợp đồng phải nhỏ hơn hoặc bằng ngày thanh toán!',16,1)
				ROLLBACK
			END
			FETCH NEXT FROM cs_ThanhToan INTO @ngayThanhToan
	END
	CLOSE cs_ThanhToan
	DEALLOCATE cs_ThanhToan
END
GO
--Tên công ty không được trùng!

CREATE TRIGGER UTG_congTy_name ON dbo.CongTyDuLich
FOR INSERT,UPDATE
AS
BEGIN
	
	DECLARE @tenCongTy1 NVARCHAR(50)
	SELECT @tenCongTy1=Inserted.TenCongTy FROM Inserted
	DECLARE cs_congTy CURSOR FOR SELECT TenCongTy FROM dbo.CongTyDuLich
	OPEN cs_congTy
	DECLARE @tenCongTy2 NVARCHAR(50)
	DECLARE @count INT
	SET @count=0
	FETCH NEXT FROM cs_congTy INTO @tenCongTy2
	WHILE @@FETCH_STATUS=0
	BEGIN
		IF(@tenCongTy1=@tenCongTy2)
		BEGIN
			SET @count=@count+1
		END	
		IF(@count>1) 
		BEGIN
			RAISERROR(N'Không được trùng tên công ty!',16,1)
			ROLLBACK
		END
		FETCH NEXT FROM cs_congTy INTO @tenCongTy2
	END
	CLOSE cs_congTy
	DEALLOCATE cs_congTy
END
GO

--SDT của công ty ko đc trùng 
CREATE TRIGGER UTG_congTy_SDT ON dbo.CongTyDuLich
FOR INSERT,UPDATE
AS
BEGIN
	
	DECLARE @sdt1 NVARCHAR(11)
	SELECT @sdt1=Inserted.SDT FROM Inserted
	DECLARE cs_congTy CURSOR FOR SELECT SDT FROM dbo.CongTyDuLich
	OPEN cs_congTy
	DECLARE @sdt2 NVARCHAR(50)
	DECLARE @count INT
	SET @count=0
	FETCH NEXT FROM cs_congTy INTO @sdt2
	WHILE @@FETCH_STATUS=0
	BEGIN
		IF(@sdt1=@sdt2)
		BEGIN
			SET @count=@count+1
		END	
		IF(@count>1) 
		BEGIN
			RAISERROR(N'Không được trùng SDT!',16,1)
			ROLLBACK
		END
		FETCH NEXT FROM cs_congTy INTO @sdt2
	END
	CLOSE cs_congTy
	DEALLOCATE cs_congTy
END
GO
--CMND của giáo viên không được trùng
CREATE TRIGGER UTG_GiaoVien_CMND ON dbo.GiaoVien
FOR INSERT,UPDATE
AS
BEGIN
	
	DECLARE @cmnd1 NVARCHAR(12)
	SELECT @cmnd1=Inserted.CMND FROM Inserted
	DECLARE cs_GiaoVien CURSOR FOR SELECT CMND FROM dbo.GiaoVien
	OPEN cs_GiaoVien
	DECLARE @cmnd2 NVARCHAR(12)
	DECLARE @count INT
	SET @count=0
	FETCH NEXT FROM cs_GiaoVien INTO @cmnd2
	WHILE @@FETCH_STATUS=0
	BEGIN
		IF(@cmnd1=@cmnd2)
		BEGIN
			SET @count=@count+1
		END	
		IF(@count>1) 
		BEGIN
			RAISERROR(N'Không được trùng CMND!',16,1)
			ROLLBACK
		END
		FETCH NEXT FROM cs_GiaoVien INTO @cmnd2
	END
	CLOSE cs_GiaoVien
	DEALLOCATE cs_GiaoVien
END
GO
--SDT của giáo viên không đc trung nhau
CREATE TRIGGER UTG_GiaoVien_SDT ON dbo.GiaoVien
FOR INSERT,UPDATE
AS
BEGIN
	
	DECLARE @sdt1 NVARCHAR(11)
	SELECT @sdt1=Inserted.SDT FROM Inserted
	DECLARE cs_GiaoVien CURSOR FOR SELECT SDT FROM dbo.GiaoVien
	OPEN cs_GiaoVien
	DECLARE @sdt2 NVARCHAR(12)
	DECLARE @count INT
	SET @count=0
	FETCH NEXT FROM cs_GiaoVien INTO @sdt2
	WHILE @@FETCH_STATUS=0
	BEGIN
		IF(@sdt1=@sdt2)
		BEGIN
			SET @count=@count+1
		END	
		IF(@count>1) 
		BEGIN
			RAISERROR(N'Không được trùng số điện thoại!',16,1)
			ROLLBACK
		END
		FETCH NEXT FROM cs_GiaoVien INTO @sdt2
	END
	CLOSE cs_GiaoVien
	DEALLOCATE cs_GiaoVien
END
GO
--Mã chuyến đi trong hợp đồng không được trùng nhau
CREATE TRIGGER UTG_HopDong_MaChuyenDi ON dbo.HopDong
FOR INSERT,UPDATE
AS
BEGIN
	
	DECLARE @maChuyenDi1 NVARCHAR(6)
	SELECT @maChuyenDi1=Inserted.MaChuyenDi FROM Inserted
	DECLARE cs_HopDong CURSOR FOR SELECT MaChuyenDi FROM dbo.HopDong
	OPEN cs_HopDong
	DECLARE @maChuyenDi2 NVARCHAR(6)
	DECLARE @count INT
	SET @count=0
	FETCH NEXT FROM cs_HopDong INTO @maChuyenDi2
	WHILE @@FETCH_STATUS=0
	BEGIN
		IF(@maChuyenDi1=@maChuyenDi2)
		BEGIN
			SET @count=@count+1
		END	
		IF(@count>1) 
		BEGIN
			RAISERROR(N'Không được trùng chuyến đi!',16,1)
			ROLLBACK
		END
		FETCH NEXT FROM cs_HopDong INTO @maChuyenDi2
	END
	CLOSE cs_HopDong
	DEALLOCATE cs_HopDong
END
GO