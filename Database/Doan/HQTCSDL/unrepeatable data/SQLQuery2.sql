set transaction isolation level read committed
begin tran
select TenNguoiGiamHo from HocSinh  with(updlock) 
where MaHS=1 
waitfor delay '00:00:10'
select TenNguoiGiamHo from HocSinh where MaHS=1
commit



--update HocSinh set TenNguoiGiamHo=N'Không' where MaHS=1

 