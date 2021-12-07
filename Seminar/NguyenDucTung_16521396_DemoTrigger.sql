create database Demo
use Demo
CREATE TABLE tbl_KhoHang
(
	id int identity not null,
	MaHang int not null primary key,
	TenHang nvarchar(50) not null,
	SoLuongTon int not null
)
CREATE TABLE tbl_DatHang
(
	id int identity not null,
	MaHang int not null foreign key references tbl_KhoHang(MaHang),
	SoLuongDat int not null
)


/* Add dữ liệu vào bảng tbl_KhoHang */
insert into tbl_KhoHang(MaHang,TenHang,SoLuongTon) values (1,N'Rau mùng tơi',17)
insert into tbl_KhoHang(MaHang,TenHang,SoLuongTon) values (2,N'Rau muống',5)
insert into tbl_KhoHang(MaHang,TenHang,SoLuongTon) values (3,N'Lam bô gi ni',8)


/* Chạy dữ liệu 2 bảng KhoHang và DatHang */
select * from tbl_KhoHang
select * from tbl_DatHang


/* cập nhật hàng trong kho sau khi đặt hàng hoặc cập nhật */
CREATE TRIGGER trg_DatHang ON tbl_DatHang 
AFTER INSERT 
AS 
BEGIN
	UPDATE tbl_KhoHang
	SET SoLuongTon = SoLuongTon - (
		SELECT SoLuongDat
		FROM inserted
		WHERE MaHang = tbl_KhoHang.MaHang
	)
	FROM tbl_KhoHang
	JOIN inserted ON tbl_KhoHang.MaHang = inserted.MaHang
END
GO
/* cập nhật hàng trong kho sau khi cập nhật đặt hàng */
CREATE TRIGGER trg_CapNhatDatHang on tbl_DatHang 
after update 
AS
BEGIN
   UPDATE tbl_KhoHang SET SoLuongTon = SoLuongTon -
	   (SELECT SoLuongDat FROM inserted WHERE MaHang = tbl_KhoHang.MaHang) +
	   (SELECT SoLuongDat FROM deleted WHERE MaHang = tbl_KhoHang.MaHang)
   FROM tbl_KhoHang 
   JOIN deleted ON tbl_KhoHang.MaHang = deleted.MaHang
end
GO
/* cập nhật hàng trong kho sau khi hủy đặt hàng */
create TRIGGER trg_HuyDatHang ON tbl_DatHang 
FOR DELETE 
AS 
BEGIN
	UPDATE tbl_KhoHang
	SET SoLuongTon = SoLuongTon + (SELECT SoLuongDat FROM deleted WHERE MaHang = tbl_KhoHang.MaHang)
	FROM tbl_KhoHang 
	JOIN deleted ON tbl_KhoHang.MaHang = deleted.MaHang
END


/* Một hôm muốn ăn rau mùng tơi nên ta đặt 5 mớ ăn chơi */
INSERT INTO tbl_DatHang VALUES(1,5)
select * from tbl_KhoHang
select * from tbl_DatHang


/* Ta thấy 5 mớ chưa oai nên đặt hẳn thành 10 mớ */
update tbl_DatHang set SoLuongDat=10 where id = 1
select * from tbl_KhoHang
select * from tbl_DatHang


/* Ta thấy nhiều quá ăn không hết nên đặt lại về 3 mớ */
update tbl_DatHang set SoLuongDat=3 where id = 1
select * from tbl_KhoHang
select * from tbl_DatHang


/* Nhưng phát hiện ra không có tiền nên hủy đơn */
delete tbl_DatHang where id =16
delete tbl_DatHang where id =20

select * from tbl_KhoHang
select * from tbl_DatHang


/* Bonus check số lượng đặt có lớn hơn số lượng tồn hay không */
CREATE TRIGGER DIEUKIENDAT on tbl_DatHang
for insert, update
as 
	declare @SoLuongDat int
	declare @MaHang int
	declare @SoLuongTon int
	select @MaHang=MaHang, @SoLuongDat=SoLuongDat from inserted
	select @SoLuongTon=SoLuongTon from tbl_KhoHang where tbl_KhoHang.MaHang=@MaHang
	if (@SoLuongDat>@SoLuongTon)
	begin 
		print N'Só lượng đặt phải nhỏ hơn hoặc bằng số lượng tồn!!'
		rollback tran
	end

CREATE TRIGGER DIEUKIENTON on tbl_KhoHang
for update
as
if UPDATE(SoLuongTon)
	begin 
		declare @SoLuongTon int
		declare @MaHang int
		declare @SoLuongDat int
		select @MaHang=MaHang, @SoLuongTon=SoLuongTon from inserted
		if (select count(*) from tbl_DatHang where MaHang=@MaHang and SoLuongDat>@SoLuongTon) > 0
		begin	
			print N'Số lượng tồn phải lớn hơn số lượng đặt!!'
			rollback tran
		end
	end


/* test trigger DIEUKIENDAT và DIEUKIENTON */
drop trigger DIEUKIENDAT
DROP TRIGGER DIEUKIENTON
drop trigger trg_DatHang
drop trigger trg_HuyDatHang
drop trigger trg_CapNhatDatHang
insert into tbl_DatHang values (1,11)
insert into tbl_KhoHang values (1,6)

update tbl_KhoHang set SoLuongTon=6 where MaHang=1
update tbl_DatHang set SoLuongDat=10 where MaHang=1

select * from tbl_KhoHang
select * from tbl_DatHang