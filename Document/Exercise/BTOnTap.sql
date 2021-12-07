
create proc TimMaTuaSach (@stt INT OUT)
as
begin
	declare @mamax int
	select @mamax = MAX(Ma_TuaSach) from dbo.TuaSach

	DECLARE @i INT
	SET @i = 1
	set @stt=0
	WHILE @i <= @mamax
		BEGIN
			if (@i = some(select Ma_TuaSach FROM dbo.TuaSach))
				
					set @i=@i+1
				
			else
				begin 
					set @stt=@i	
					break
				end
		END
	IF(@stt=0)
		begin
		set @stt = @mamax+1
		end
END
--DROP PROC TimMaTuaSach

--DECLARE @a INT
--EXEC TimMaTuaSach  @a OUT
--PRINT CAST(@a AS NVARCHAR(10))

--- main
CREATE PROC sp_ThemTuaSach @tuasach nvarchar(50), @tacgia NVARCHAR(50), @tomtat NVARCHAR(255)
AS
BEGIN
	DECLARE @MaTuaSach INT, @temp INT =0
	EXEC TimMaTuaSach @MaTuaSach OUT
	IF(@tuasach = SOME(SELECT TuaSach FROM TuaSach))
		SET @temp +=1
	ELSE IF @tacgia = SOME(SELECT TacGia FROM dbo.TuaSach)
		SET @temp += 1
	ELSE IF @tomtat = SOME(SELECT TomTat FROM dbo.TuaSach)
		SET @temp +=1

	IF(@temp !=0)
		PRINT 'khong the them csdl'
	ELSE
		waitfor delay '00:00:10'
		INSERT INTO dbo.TuaSach
		VALUES  (@MaTuaSach, @tuasach, @tacgia, @tomtat )

END
--DROP PROC sp_ThemTuaSach

begin TRAN
set tran isolation LEVEL SERIALIZABLE
EXEC sp_ThemTuaSach 'a', 'b', 'c'
COMMIT

