begin tran
insert into ChuyenDi(MaChuyenDi,NgayKhoiHanh)
values ('CD10','1/1/2018')
commit
select count(MaChuyenDi) from ChuyenDi
where year(NgayKhoiHanh)=2018


--delete from ChuyenDi where MaChuyenDi='CD10'