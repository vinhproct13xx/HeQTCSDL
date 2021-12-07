set transaction isolation level read committed
begin tran
select TenNguoiGiamHo from HocSinh
where MaHS=1
update HocSinh set TenNguoiGiamHo=(TenNguoiGiamHo+N' Cha') 
where MaHS=1
select TenNguoiGiamHo from HocSinh where MaHS=1
commit
