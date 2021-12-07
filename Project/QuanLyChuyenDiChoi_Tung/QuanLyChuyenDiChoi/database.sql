USE QuanLyDaNgoai
set dateformat dmy

insert into HocSinh(TenHS,NgaySinh,DiaChi,TenCha,TenMe,TenNguoiDamHo,SDT) values (N'Nguyễn Văn A','1/1/2013',N'TpHCM',N'Nguyễn Tiến A',N'Nguyễn Thảo A',N'Không','01322655865')
insert into HocSinh(TenHS,NgaySinh,DiaChi,TenCha,TenMe,TenNguoiDamHo,SDT) values (N'Nguyễn Thị B','2/5/2014',N'Đồng Nai',N'Nguyễn Văn B',N'Nguyễn Trần B',N'Không','01234567895')
insert into HocSinh(TenHS,NgaySinh,DiaChi,TenCha,TenMe,TenNguoiDamHo,SDT) values (N'Nguyễn Bá C','5/7/2015',N'Vũng Tàu',N'Nguyễn Vũ C',N'Nguyễn Trần Thiên C',N'Không','02564875325')
insert into HocSinh(TenHS,NgaySinh,DiaChi,TenCha,TenMe,TenNguoiDamHo,SDT) values (N'Nguyễn Hùng D','12/5/2013',N'Tây Ninh',N'Không',N'Không',N'Nguyễn Trần Nhật D','02564857955')
insert into HocSinh(TenHS,NgaySinh,DiaChi,TenCha,TenMe,TenNguoiDamHo,SDT) values (N'Nguyễn Quá E','5/8/2014',N'Long An',N'Nguyễn Quảng E',N'Nguyễn Ngọc E',N'Không','01475896235')
insert into HocSinh(TenHS,NgaySinh,DiaChi,TenCha,TenMe,TenNguoiDamHo,SDT) values (N'Lê Sáng F','6/12/2015',N'Bình Dương',N'Lê Vũ F',N'Trần Thị F',N'Không','0582296593')
insert into HocSinh(TenHS,NgaySinh,DiaChi,TenCha,TenMe,TenNguoiDamHo,SDT) values (N'Trần Lâm G','12/9/2013',N'Cần Thơ',N'Trần Quang G',N'Vũ Thị G',N'Không','01586547785')
insert into HocSinh(TenHS,NgaySinh,DiaChi,TenCha,TenMe,TenNguoiDamHo,SDT) values (N'Đào Bá H','25/12/2014',N'An Giang',N'Đào Văn H',N'Trần Thị Vân H',N'Không','0586589825')
insert into HocSinh(TenHS,NgaySinh,DiaChi,TenCha,TenMe,TenNguoiDamHo,SDT) values (N'Vũ Văn I','1/7/2015',N'Bến Tre',N'Vũ Minh I',N'Nguyễn Tâm I',N'Không','0895878465')
insert into HocSinh(TenHS,NgaySinh,DiaChi,TenCha,TenMe,TenNguoiDamHo,SDT) values (N'Trần Thế K','2/2/2013',N'Bình Thuận',N'Trần Bá K',N'Lê Thị K',N'Không','01246355855')
update HocSinh
set TrangThai=1

insert into NamHoc values (2018,'2018-2019')
insert into NamHoc values (2019,'2019-2020')
insert into NamHoc values (2020,'2020-2021')
insert into NamHoc values (2021,'2021-2022')
insert into NamHoc values (2022,'2022-2023')

insert into LopHoc values ('Mam1',N'Mầm 1')
insert into LopHoc values ('Mam2',N'Mầm 2')
insert into LopHoc values ('Mam3',N'Mầm 3')
insert into LopHoc values ('Choi1',N'Chồi 1')
insert into LopHoc values ('Choi2',N'Chồi 2')
insert into LopHoc values ('Choi3',N'Chồi 3')
insert into LopHoc values ('La1',N'Lá 1')
insert into LopHoc values ('La2',N'Lá 2')
insert into LopHoc values ('La3',N'Lá 3')

insert into CTLop values (3,'Mam1',2018)
insert into CTLop values (6,'Mam1',2018)
insert into CTLop values (9,'Mam1',2018)
insert into CTLop values (2,'Choi1',2018)
insert into CTLop values (5,'Choi1',2018)
insert into CTLop values (8,'Choi1',2018)
insert into CTLop values (1,'La1',2018)
insert into CTLop values (4,'La1',2018)
insert into CTLop values (7,'La1',2018)
insert into CTLop values (10,'La1',2018)

insert into GiaoVien(TenGV,NgaySinh,DiaChi,SDT,CMND,MaLop) values (N'Nguyễn Thị Thanh Thảo','1/1/1995',N'TpHCM','01234567899','123456178921','Mam1')
insert into GiaoVien(TenGV,NgaySinh,DiaChi,SDT,CMND,MaLop) values (N'Trần Ngọc Linh Chi','2/2/1996',N'TpHCM','0125645655','001256256584','Mam2')
insert into GiaoVien(TenGV,NgaySinh,DiaChi,SDT,CMND,MaLop) values (N'Nguyễn Ngọc Phương Mai','5/5/1994',N'TpHCM','05689556225','002565852215','Mam3')
insert into GiaoVien(TenGV,NgaySinh,DiaChi,SDT,CMND,MaLop) values (N'Lê Thị Ánh tuyết','8/7/1995',N'TpHCM','0158657455','005223655855','Choi1')
insert into GiaoVien(TenGV,NgaySinh,DiaChi,SDT,CMND,MaLop) values (N'Hồ Mai Thủy','6/6/1995',N'TpHCM','0369265685','009865325625','Choi2')
insert into GiaoVien(TenGV,NgaySinh,DiaChi,SDT,CMND,MaLop) values (N'Lê Minh Hà','2/5/1992',N'TpHCM','0375685652','006598562563','Choi3')
insert into GiaoVien(TenGV,NgaySinh,DiaChi,SDT,CMND,MaLop) values (N'Lý Thị Huệ','30/3/1993',N'TpHCM','0352562354','012025625698','La1')
insert into GiaoVien(TenGV,NgaySinh,DiaChi,SDT,CMND,MaLop) values (N'Hồ Ngọc Hà','20/11/1995',N'TpHCM','0125658253','012563255874','La2')
insert into GiaoVien(TenGV,NgaySinh,DiaChi,SDT,CMND,MaLop) values (N'Lê Minh Hằng','7/1/1994',N'TpHCM','0369391813','000025482280','La3')

insert into CongTyDuLich values ('CT1',N'VietTravel',N'190 Pasteur, Quận 3, Tp. Hồ Chí Minh, Việt Nam','19001839')
insert into CongTyDuLich values ('CT2',N'Saigontourist',N'45 Lê Thánh Tôn, Phường Bến Nghé, Quận 1, TP.HCM','19001808')

insert into DiaDiem values ('DD1',N'CVVH Đầm Sen',N'Số 3 Hòa Bình, Phường 3, Quận 11, Hồ Chí Minh')
insert into DiaDiem values ('DD2',N'Thảo cầm viên Sài Gòn',N'2 Nguyễn Bỉnh Khiêm, Bến Nghé, Quận 1, Hồ Chí Minh')