-- Bai tap thuc hanh ngay 21/09/2018
--a,  print hello
CREATE PROC In_Hello
AS
BEGIN
	PRINT 'Hello'
END
EXEC In_Hello
--b,  print Xin chao
CREATE PROC In_XinChao
AS
BEGIN
	PRINT N'Xin chào'
END

EXEC In_XinChao

--c,  In xin chao + ten sv
CREATE PROC In_XinChao_Ten(@Ten nvarchar(40))
AS
BEGIN
	PRINT N'Xin chào bạn '+ @Ten
END

DECLARE @Ten NVARCHAR(40)
SET @Ten = N'Mai Thụy Ánh Tuyết!'
EXEC In_XinChao_Ten @Ten
--2e. tong 2 so s1, s2

CREATE PROC Tong(@s1 int, @s2 int)
AS
BEGIN
	DECLARE @Tong FLOAT
	SET @Tong = @s1 + @s2
	PRINT @Tong
END

EXEC Tong 3,4

--2f. tong 2 so s1, s2
CREATE PROC Tong_2(@s1 int, @s2 int)
AS
BEGIN
	DECLARE @Tong FLOAT
	SET @Tong = @s1 + @s2
	PRINT 'Tong la: ' + CAST(@Tong AS NVARCHAR(30))
END
DROP PROC Tong_2
EXEC Tong_2 3, 4

--2g. Viết stored-procedure Nhập vào 2 số @s1, @s2. Xuất tổng @s1+@s2 ra
--tham số @tong. Cho thực thi và in giá trị của tham số này để kiểm tra.
CREATE PROC Tong_Out(@s1 int, @s2 int, @Tong float OUT)
AS
BEGIN
	SET @Tong = @s1 + @s2
	--PRINT @Tong
END

DECLARE @Tong FLOAT
EXEC Tong_Out 3, 4, @Tong OUTPUT
BEGIN
	PRINT @Tong
END

--2h. Viết stored-procedure Nhập vào 2 số @s1, @s2. Xuất tổng @s1+@s2 ra
--tham số @tong. Cho thực thi và in giá trị của tham số này để kiểm tra dưới
--dạng ‘Tổng là: @tg’ với @tg =@s1+@s2.
CREATE PROC Tong_Out_2(@s1 int, @s2 int, @Tong float OUT)
AS
BEGIN
	SET @Tong = @s1 + @s2
END

DECLARE @Tong FLOAT
EXEC Tong_Out_2 3, 4, @Tong OUT
BEGIN
	PRINT 'Tong la: '+ CAST(@Tong AS NVARCHAR(30))
END

-- 2i. Viết stored-procedure Nhập vào 2 số @s1, @s2. In ra max của chúng.
CREATE PROC Tim_Max(@s1 int, @s2 int)
AS
	IF(@s1 <@s2)
	BEGIN
		PRINT @s2
	END
	ELSE PRINT @s1
EXEC Tim_Max 3, 4

-- 2j. Viết stored-procedure Nhập vào 2 số @s1, @s2. In ra câu ‘Số lớn nhất của
--@s1 và @s2 là @max’ với @s1, @s2, max là các giá trị tương ứng
CREATE PROC Tim_Max_2(@s1 int , @s2 int )
AS
	IF(@s1 <@s2)
	BEGIN
		PRINT N'Số lớn nhất của hai số '+CAST(@s1 AS NVARCHAR(30))+ N' và '+CAST(@s2 AS NVARCHAR(30))+N' là: '+ CAST(@s2 AS NVARCHAR(100))
	END
	ELSE 
	PRINT N'Số lớn nhất của hai số '+CAST(@s1 AS NVARCHAR(30))+ N' và '+CAST(@s2 AS NVARCHAR(30))+N' là: '+ CAST(@s1 AS NVARCHAR(100))

EXEC Tim_Max_2 3, 4

--Viết stored-procedure Nhập vào 2 số @s1, @s2. Xuất min và max của
--chúng ra tham số @max, @min. Cho thực thi và in giá trị của các tham số này để kiểm tra.

CREATE PROC In_Max_Min(@s1 int, @s2 int, @Max int OUT, @Min INT OUT)
AS
IF(@s1 < @s2)
	BEGIN
		SET @Max = @s2
		SET @Min = @s1
	END
ELSE
	BEGIN
    SET @Max = @s1
	SET @Min = @s2
	END
 
 DECLARE @Max INT
 DECLARE @Min INT
 EXEC In_Max_Min 3, 4, @Max OUT, @Min OUT
BEGIN
	PRINT N'Max = '+ CAST(@Max AS NVARCHAR(30))
	PRINT N'Min = '+ CAST(@Min AS NVARCHAR(30))
END

-- 2l. Viết stored-procedure Nhập vào số nguyên @n. In ra các số từ 1 đến @n

CREATE  PROC In_1_n(@n int)
AS
DECLARE @i INT
SET @i=1;
WHILE (@i != @n +1)
	BEGIN
		PRINT @i
		SET @i = @i +1 
	END
    
EXEC In_1_n 5

-- 2m. Viết stored-procedure Nhập vào số nguyên @n. In ra tổng các số chẵn từ 1 đến @n
CREATE  PROC Tong_1_n(@n int)
AS
DECLARE @i INT
DECLARE @Tong float
SET @i=1;
SET @Tong =0;
WHILE (@i != @n +1)
	BEGIN
	
		SET @Tong = @tong + @i
		SET @i = @i +1 
	END
PRINT @Tong
    
EXEC Tong_1_n 5
-- 2n. Viết stored-procedure Nhập vào số nguyên @n. In ra tổng, và số lượng các số chẵn từ 1 đến @n
CREATE  PROC TongVaSoChan(@n int)
AS
DECLARE @i INT
DECLARE @Tong FLOAT
DECLARE @SoChan int
SET @i=1;
SET @Tong =0;
SET @SoChan = 0;
WHILE (@i != @n +1)
	BEGIN
		SET @Tong = @tong + @i
		IF((@i % 2) = 0)
		SET @SoChan = @SoChan +1
		SET @i = @i +1 
	END
BEGIN
PRINT 'Tong la: ' + CAST(@Tong AS NVARCHAR(50))
PRINT 'So luong cac so chan la: ' + CAST(@SoChan AS NVARCHAR(30))
END

DROP PROC TongVaSoChan
EXEC TongVaSoChan 5

-- 2o. Viết stored-procedure Nhập vào 2 số. In ra ước chung lớn nhất của chúng
CREATE PROC UocChungLonNhat(@a int, @b int)
AS
DECLARE @UCLN int
DECLARE @max INT
DECLARE @min INT
DECLARE @temp INT
IF(@a < @b)
	BEGIN
		WHILE( @b % @a != 0)
		BEGIN
		SET @temp = @b % @a
		 SET @b = @a
		SET  @a = @temp
        END
		PRINT @a
	END
ELSE 
	BEGIN
		WHILE( @a % @b != 0)
		BEGIN
		SET @temp = @a% @b
		SET  @a = @b
		SET  @b = @temp
        END
		PRINT @b
	END

DROP PROC UocChungLonNhat
EXECUTE UocChungLonNhat 24, 20
--
CREATE PROC UocChungLonNhatOut(@a int, @b int, @UCLN int out)
AS
DECLARE @max INT
DECLARE @min INT
DECLARE @temp INT
IF(@a < @b)
	BEGIN
		WHILE( @b % @a != 0)
		BEGIN
		SET @temp = @b % @a
		 SET @b = @a
		SET  @a = @temp
        END
		SET @UCLN = @a
	END
ELSE 
	BEGIN
		WHILE( @a % @b != 0)
		BEGIN
		SET @temp = @a% @b
		SET  @a = @b
		SET  @b = @temp
        END
		SET @UCLN = @b
	END

DECLARE @UCLN INT
DECLARE @a INT = 20
DECLARE @b INT = 24

EXEC UocChungLonNhatOut @a, @b, @UCLN OUT
BEGIN
	PRINT N'Kết quả: ucln('+  CAST(@a AS NVARCHAR(40)) + ', '+ CAST(@b AS NVARCHAR(40)) + ') = ' + CAST(@UCLN AS NVARCHAR(40))
END

-- q Viết stored-procedure Cài đặt có dùng đệ quy, thuật toán Euler tìm ước chung lớn nhất (a, A)


CREATE PROC UocchungLonNhatDeQuy( @a int, @b int)
AS
DECLARE @UCLN INT
DECLARE @temp INT
BEGIN
	if(@a >= @b)
	BEGIN
		IF(@b = 0)
		BEGIN
			PRINT @a
			return
		END
		SET @temp =  @a % @b
		EXEC UocChungLonNhatDeQuy @b,@temp
	END
    else
	BEGIN
		IF(@a = 0)
		BEGIN
			PRINT @a
			RETURN 
		END
		SET @temp =  @b % @a
		EXEC UocChungLonNhatDeQuy @a, @temp
	END
END

DROP PROC dbo.UocchungLonNhatDeQuy

EXEC UocChungLonNhatDeQuy 24, 20

--Viết stored-procedure Nhập vào số nguyên @n <= 5. In ra tất cả các số nhị phân có @n bit.
CREATE PROC In_Binary(@string varchar(100), @n int)
AS
BEGIN
DECLARE @temp VARCHAR(100)
	IF(@n = 0)
	BEGIN
		PRINT @string
	END
	ELSE
    BEGIN
		SET @n -=1
		SET @temp = @string + '0'
    	EXEC In_Binary @temp, @n 
		SET @temp = @string + '1'
		EXEC In_Binary @temp, @n
    END

END

DROP PROC In_Binary
EXEC In_Binary '', 5

--Viết một stored proc có nội dung: Dùng lệnh print để in ra danh sách mã các tựa sách.
CREATE TABLE TuaSach
(
	MaTuaSach INT PRIMARY KEY,
	TuaSach VARCHAR(100),
	TacGia VARCHAR(50),
	TomTat TEXT
)
DECLARE @MaTuasach int
DECLARE c_MaTuaSach CURSOR FOR SELECT DISTINCT MaTuaSach FROM TuaSach
OPEN c_MaTuaSach
FETCH NEXT FROM c_MaTuaSach INTO @MaTuasach
WHILE
	@@FETCH_STATUS =0
BEGIN
	PRINT @MaTuasach
	FETCH NEXT FROM c_MaTuaSach INTO @MaTuasach
END
CLOSE c_MaTuaSach
DEALLOCATE c_MaTuaSach



--Viết một stored proc có nội dung: Dùng lệnh print để in ra danh sách mã và họ tên các độc giả.
CREATE TABLE DocGia
(
	MaDG INT PRIMARY KEY,
	HoDG nVARCHAR(10),
	TenLot nVARCHAR(50),
	TenDG NVARCHAR(10),
	NgSinh SMALLDATETIME
)

DECLARE @MaDG INT
DECLARE @HoDG NVARCHAR(10)
DECLARE @Tenlot NVARCHAR(50)
DECLARE @TenDG NVARCHAR(10)

DECLARE c_DocGia CURSOR FOR SELECT DISTINCT MaDG, HoDG, TenLot, TenDG FROM dbo.DocGia
OPEN c_DocGia
FETCH NEXT FROM c_DocGia INTO @MaDG, @HoDG, @Tenlot, @TenDG

WHILE
	@@FETCH_STATUS =0
BEGIN
	PRINT CAST(@MaDG AS NVARCHAR(10)) + ': ' + @HoDG + ' '+ @Tenlot + ' ' + @TenDG
	FETCH NEXT FROM c_DocGia INTO @MaDG, @HoDG, @Tenlot, @TenDG
END
CLOSE c_DocGia
DEALLOCATE c_DocGia

-- bài tập quản lý thư viện. 
--4-1  sp_ThongtinDocGia

CREATE PROC sp_ThongTinDocGia (@maDG INT)
AS
BEGIN
	DECLARE @temp INT =0
	 SELECT @temp = dbo.NguoiLon.ma_DocGia FROM dbo.NguoiLon WHERE ma_DocGia = @maDG
	IF(@temp = @maDG)
		BEGIN
		DECLARE @sonha INT, @duong NVARCHAR(50), @quan NVARCHAR(50), @dienthoai NCHAR(10), @hansudung SMALLDATETIME 
		DECLARE @ho NVARCHAR(10), @tenlot NVARCHAR(50), @ten NVARCHAR(10), @ngaysinh SMALLDATETIME
		SELECT @ho = dbo.DocGia.Ho, @tenlot = dbo.DocGia.TenLot, @ten = dbo.DocGia.Ten, @ngaysinh = dbo.DocGia.NgaySinh FROM dbo.DocGia WHERE MaDocGia = @temp
		SELECT @sonha = dbo.NguoiLon.SoNha, @duong = dbo.NguoiLon.Duong, @quan = dbo.NguoiLon.Quan, @dienthoai = dbo.NguoiLon.DienThoai, @hansudung = dbo.NguoiLon.HanSuDung FROM dbo.NguoiLon WHERE ma_DocGia = @temp
	
		PRINT CAST(@maDG AS NVARCHAR(10)) + ' : ' + @ho + ' '+ @tenlot + ' '+@ten +' ngay sinh: '+ CAST(@ngaysinh AS NVARCHAR(20))
		PRINT CAST(@sonha AS NVARCHAR(10)) + ', '+ @duong + ', ' + @quan+ ', '+ @dienthoai + ', '+ CAST(@hansudung AS NCHAR(10))
		END
        
END
DROP PROC dbo.sp_ThongTinDocGia
EXEC dbo.sp_ThongTinDocGia 
 @maDG = 1 

--4.2 sp_ThongtinDausach

CREATE PROC sp_ThongTinDauSach (@isbn INT)
AS
BEGIN
	DECLARE @temp INT =0
	 SELECT @temp = dbo.DauSach.isbn FROM dbo.DauSach WHERE isbn = @isbn
	IF(@temp = @isbn)
		BEGIN
		DECLARE @matuasach INT, @tuasach NVARCHAR(50), @tacgia NVARCHAR(50), 
			@tomtat NVARCHAR(255), @ngonngu NVARCHAR(15), @bia NVARCHAR(15), 
			@trangthai NVARCHAR(1), @SLSach INT

		
		SELECT @matuasach = dbo.TuaSach.MaTuaSach, @tuasach = dbo.TuaSach.TuaSach, @tacgia = dbo.TuaSach.TacGia, @tomtat = dbo.TuaSach.TomTat, 
			@ngonngu = dbo.DauSach.NgonNgu, @bia = dbo.DauSach.Bia, @trangthai = dbo.DauSach.TrangThai 
		FROM dbo.TuaSach INNER JOIN dbo.DauSach ON DauSach.MaTuaSach = TuaSach.MaTuaSach 
		WHERE dbo.DauSach.isbn = @isbn
		SELECT @SLSach = COUNT(dbo.DauSach.MaTuaSach) FROM dbo.DauSach WHERE MaTuaSach = @matuasach AND dbo.DauSach.TrangThai = 'N'

		PRINT CAST(@isbn AS NVARCHAR(10)) + ' : ' + CAST(@matuasach AS NVARCHAR(10)) + 
			' '+ @tuasach + ', tac gia: '+@tacgia +', tom tat: '+ @tomtat
		PRINT 'Ngon ngu: '+ @ngonngu + ', bia:'+ @bia + ', trang thai: ' + @trangthai+ ', so luong con lai = '+ CAST(@SLSach AS NCHAR(10))
		END
        -- trạng thái của sách sẽ là 1 nếu dc mượn rồi, là 0 nếu chưa có ai mượn
END
DROP PROC sp_ThongTinDauSach
EXEC dbo.sp_ThongTinDauSach @isbn = 1 -- int

--4.3 sp_ThongtinNguoilonDangmuon

DECLARE  @maDG INT, @ho NVARCHAR(10), @tenlot NVARCHAR(50), @ten NVARCHAR(10), @ngaysinh SMALLDATETIME, 
	@sonha INT, @duong NVARCHAR(50), @quan NVARCHAR(50), @dienthoai NCHAR(10), 
	@hansudung SMALLDATETIME
DECLARE c_NguoiLonDangMuonSach 
CURSOR FOR SELECT DISTINCT dbo.DocGia.MaDocGia, dbo.DocGia.Ho,  
				dbo.DocGia.TenLot, dbo.DocGia.Ten, dbo.DocGia.NgaySinh,
				dbo.NguoiLon.SoNha, dbo.NguoiLon.Duong, dbo.NguoiLon.Quan, dbo.NguoiLon.DienThoai, 
				dbo.NguoiLon.HanSuDung 
				FROM dbo.Muon INNER JOIN (dbo.DocGia INNER JOIN dbo.NguoiLon ON NguoiLon.ma_DocGia = DocGia.MaDocGia) 
				ON DocGia.MaDocGia = Muon.MaDocGia
OPEN c_NguoiLonDangMuonSach
FETCH NEXT FROM c_NguoiLonDangMuonSach INTO @maDG, @ho, @tenlot, @ten, @ngaysinh,
				@sonha, @duong, @quan, @dienthoai, @hansudung

WHILE
	@@FETCH_STATUS =0
BEGIN

	PRINT CAST(@maDG AS NVARCHAR(10)) +  ' : ' + @ho + ' '+ @tenlot + ' '+@ten +
			' ngay sinh: '+ CAST(@ngaysinh AS NVARCHAR(20))
	PRINT CAST(@sonha AS NVARCHAR(10)) + ', '+ @duong + ', ' + @quan+ ', '
	PRINT 'Dien thoai: ' + @dienthoai 
	PRINT 'Han su dung: ' + CAST(@hansudung AS NCHAR(10))
	
	FETCH NEXT FROM c_NguoiLonDangMuonSach 
	INTO @MaDG, @ho, @tenlot, @ten, @ngaysinh, @sonha, @duong, @quan, @dienthoai, @hansudung

END
CLOSE c_NguoiLonDangMuonSach
DEALLOCATE c_NguoiLonDangMuonSach

-- 4.4 Liệt kê những độc giả người lớn đang mượn sách quá hạn

DECLARE @now SMALLDATETIME
SELECT @now = GETDATE()
DECLARE  @maDG INT, @ho NVARCHAR(10), @tenlot NVARCHAR(50), @ten NVARCHAR(10), @ngaysinh SMALLDATETIME, 
	@sonha INT, @duong NVARCHAR(50), @quan NVARCHAR(50), @dienthoai NCHAR(10), 
	@hansudung SMALLDATETIME
DECLARE c_NguoiLonMuonSachQuaHan 
CURSOR FOR SELECT DISTINCT dbo.DocGia.MaDocGia, dbo.DocGia.Ho,  
				dbo.DocGia.TenLot, dbo.DocGia.Ten, dbo.DocGia.NgaySinh,
				dbo.NguoiLon.SoNha, dbo.NguoiLon.Duong, dbo.NguoiLon.Quan, dbo.NguoiLon.DienThoai, 
				dbo.NguoiLon.HanSuDung 
				FROM dbo.Muon INNER JOIN (dbo.DocGia INNER JOIN dbo.NguoiLon ON NguoiLon.ma_DocGia = DocGia.MaDocGia) 
				ON DocGia.MaDocGia = Muon.MaDocGia WHERE dbo.Muon.NgayHetHan < @now
OPEN c_NguoiLonMuonSachQuaHan
FETCH NEXT FROM c_NguoiLonMuonSachQuaHan INTO @maDG, @ho, @tenlot, @ten, @ngaysinh,
				@sonha, @duong, @quan, @dienthoai, @hansudung

WHILE
	@@FETCH_STATUS =0
BEGIN

	PRINT CAST(@maDG AS NVARCHAR(10)) +  ' : ' + @ho + ' '+ @tenlot + ' '+@ten +
			' ngay sinh: '+ CAST(@ngaysinh AS NVARCHAR(20))
	PRINT CAST(@sonha AS NVARCHAR(10)) + ', '+ @duong + ', ' + @quan+ ', '
	PRINT 'Dien thoai: ' + @dienthoai 
	PRINT 'Han su dung: ' + CAST(@hansudung AS NCHAR(10))
	
	FETCH NEXT FROM c_NguoiLonMuonSachQuaHan
	INTO @MaDG, @ho, @tenlot, @ten, @ngaysinh, @sonha, @duong, @quan, @dienthoai, @hansudung

END
CLOSE c_NguoiLonMuonSachQuaHan
DEALLOCATE c_NguoiLonMuonSachQuaHan


-- 4.5 Liệt kê những độc giả người lớn đang mượn sách có trẻ em cũng đang mượn sách

DECLARE  @maDG INT, @ho NVARCHAR(10), @tenlot NVARCHAR(50), @ten NVARCHAR(10), @ngaysinh SMALLDATETIME, 
	@sonha INT, @duong NVARCHAR(50), @quan NVARCHAR(50), @dienthoai NCHAR(10), 
	@hansudung SMALLDATETIME
DECLARE c_DocGiaCoTreEmMuon 
CURSOR FOR SELECT DISTINCT dbo.DocGia.MaDocGia, dbo.DocGia.Ho,  
				dbo.DocGia.TenLot, dbo.DocGia.Ten, dbo.DocGia.NgaySinh,
				dbo.NguoiLon.SoNha, dbo.NguoiLon.Duong, dbo.NguoiLon.Quan, dbo.NguoiLon.DienThoai, 
				dbo.NguoiLon.HanSuDung 
				FROM dbo.Muon INNER JOIN (dbo.DocGia INNER JOIN (dbo.NguoiLon INNER JOIN dbo.TreEm ON TreEm.ma_DocGia_NguoiLon = NguoiLon.ma_DocGia) ON NguoiLon.ma_DocGia = DocGia.MaDocGia) 
				ON DocGia.MaDocGia = Muon.MaDocGia
OPEN c_DocGiaCoTreEmMuon
FETCH NEXT FROM c_DocGiaCoTreEmMuon INTO @maDG, @ho, @tenlot, @ten, @ngaysinh,
				@sonha, @duong, @quan, @dienthoai, @hansudung

WHILE
	@@FETCH_STATUS =0
BEGIN

	PRINT CAST(@maDG AS NVARCHAR(10)) +  ' : ' + @ho + ' '+ @tenlot + ' '+@ten +
			' ngay sinh: '+ CAST(@ngaysinh AS NVARCHAR(20))
	PRINT CAST(@sonha AS NVARCHAR(10)) + ', '+ @duong + ', ' + @quan+ ', '
	PRINT 'Dien thoai: ' + @dienthoai 
	PRINT 'Han su dung: ' + CAST(@hansudung AS NCHAR(10))
	
	FETCH NEXT FROM c_DocGiaCoTreEmMuon
	INTO @MaDG, @ho, @tenlot, @ten, @ngaysinh, @sonha, @duong, @quan, @dienthoai, @hansudung

END
CLOSE c_DocGiaCoTreEmMuon
DEALLOCATE c_DocGiaCoTreEmMuon

--4.6 Cập nhật trạng thái của đầu sách

create  PROC sp_CapNhatTrangThaiDauSach @isbn int
AS
BEGIN
	DECLARE @count INT =0
	SELECT @count = COUNT(*) FROM dbo.DauSach WHERE isbn = @isbn
	IF(@count >0)
		UPDATE dbo.DauSach SET TrangThai = 'Y' WHERE isbn = @isbn
	ELSE 
		UPDATE dbo.DauSach SET TrangThai = 'N' WHERE isbn = @isbn
END

EXEC sp_CapNhatTrangThaiDauSach 1
--4.7 Thêm tựa sách mới
-- ham tim MaTuaSach
create proc TimMaTuaSach (@stt INT OUT)
as
begin
	declare @mamax int
	select @mamax = MAX(MaTuaSach) from dbo.TuaSach

	DECLARE @i INT
	SET @i = 1
	set @stt=0
	WHILE @i <= @mamax
		BEGIN
			if (@i = some(select MaTuaSach FROM dbo.TuaSach))
				
					set @i=@i+1
				
			else
				begin 
					set @stt=@i	
					break
				end
		END
	IF(@stt=0)
		begin
		set @stt = @mamax+1
		end
END
DROP PROC TimMaTuaSach

DECLARE @a INT
EXEC TimMaTuaSach  @a OUT
PRINT CAST(@a AS NVARCHAR(10))

--- main
CREATE PROC sp_ThemTuaSach @tuasach nvarchar(50), @tacgia NVARCHAR(50), @tomtat NVARCHAR(255)
AS
BEGIN
	DECLARE @MaTuaSach INT, @temp INT =0
	EXEC TimMaTuaSach @MaTuaSach OUT
	IF(@tuasach = SOME(SELECT TuaSach FROM TuaSach))
		SET @temp +=1
	ELSE IF @tacgia = SOME(SELECT TacGia FROM dbo.TuaSach)
		SET @temp += 1
	ELSE IF @tomtat = SOME(SELECT TomTat FROM dbo.TuaSach)
		SET @temp +=1

	IF(@temp !=0)
		PRINT 'khong the them csdl'
	ELSE
		INSERT INTO dbo.TuaSach
		VALUES  (@MaTuaSach, @tuasach, @tacgia, @tomtat )

END
DROP PROC sp_ThemTuaSach

EXEC sp_ThemTuaSach 'a', 'b', 'c'
--------------Thank for watching! ----------------





