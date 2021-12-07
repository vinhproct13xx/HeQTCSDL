begin tran
set transaction isolation level repeatable read
select DiaChi from DiaDiem where MaDiaDiem='DD5'
update DiaDiem
set DiaChi = N'28 Trần Văn Ơn'
where MaDiaDiem='DD5'
select DiaChi from DiaDiem where MaDiaDiem='DD5'
commit