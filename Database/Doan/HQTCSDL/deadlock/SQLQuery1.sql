begin tran
set transaction isolation level repeatable read
select DiaChi from DiaDiem where MaDiaDiem='DD5'
waitfor delay '00:00:05'
update DiaDiem
set DiaChi = N'28 Võ Thị Sáu'
where MaDiaDiem='DD5'
select DiaChi from DiaDiem where MaDiaDiem='DD5'
commit

