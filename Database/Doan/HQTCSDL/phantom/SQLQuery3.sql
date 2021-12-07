set transaction isolation level serializable
begin tran
select count(MaChuyenDi) from ChuyenDi
where year(NgayKhoiHanh)=2018
waitfor delay '00:00:05'
select count(MaChuyenDi) from ChuyenDi
where year(NgayKhoiHanh)=2018
commit


