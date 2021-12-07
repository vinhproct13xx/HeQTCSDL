set transaction isolation level repeatable read
begin tran
declare @ghichu nvarchar(255)
select @ghichu = GhiChu from ChiPhi with(updlock)
where MaChuyenDi='CD1'
waitfor delay '00:00:10'
update ChiPhi set GhiChu=@ghichu + ' Ghi chú 1'
where MaChuyenDi='CD1'
select GhiChu from ChiPhi where MaChuyenDi = 'CD1' 
commit	
--update ChiPhi set GhiChu=N'Ghi chú  0' where MaChuyenDi='CD1'


