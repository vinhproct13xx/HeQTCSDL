set transaction isolation level read uncommitted 
begin tran
delete from ChuyenDi where MaChuyenDi='CD10'
waitfor delay '00:00:05'
rollback

select count(MaChuyenDi) from ChuyenDi
where year(NgayKhoiHanh)=2018



--insert into ChuyenDi(MaChuyenDi,NgayKhoiHanh) values ('CD10','1/1/2018')

