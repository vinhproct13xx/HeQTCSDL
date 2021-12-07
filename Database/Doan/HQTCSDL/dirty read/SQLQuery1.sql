set transaction isolation level read uncommitted 
select count(MaChuyenDi) from ChuyenDi with(updlock)
where year(NgayKhoiHanh)=2018

