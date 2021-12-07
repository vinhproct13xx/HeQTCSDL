set transaction isolation level repeatable read
begin tran	
declare @ghichu nvarchar(255)
select @ghichu = GhiChu from ChiPhi with(updlock)
where MaChuyenDi='CD1'
update ChiPhi set GhiChu=@ghichu + ' Ghi chú 2'
where MaChuyenDi='CD1'
select GhiChu from ChiPhi where MaChuyenDi = 'CD1' 
commit	