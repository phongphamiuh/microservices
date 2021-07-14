SELECT * FROM roomservice.bed_type;

insert into roomservice.bed_type values(1,"Giường đơn");
insert into roomservice.bed_type values(2,"Giường đôi");
insert into roomservice.bed_type values(3,"Giường ba");
insert into roomservice.bed_type values(4,"1 giường đôi, 2 giường đơn");
insert into roomservice.bed_type values(5,"2 giường đôi, 1 giường đơn");

select * from roomservice.room;
insert into roomservice.room values(1,500,true,1,"Beutiful",2,1,'2021-05-09','hinh.com','photo.com','great','room is pretty',1);
insert into roomservice.room values(2,800,true,2,"Beutiful",4,1,'2021-05-09','hinh1.com','photo1.com','great1','Delux',2);
insert into roomservice.room values(3,600,true,1,"Beutiful",6,1,'2021-05-09','hinh2.com','photo2.com','great2','Family',3);
insert into roomservice.room values(4,700,true,1,"Beutiful",2,1,'2021-05-09','hinh3.com','photo3.com','great3','Deluxe King',1);
insert into roomservice.room values(5,900,true,4,"Beutiful",6,1,'2021-05-09','hinh4.com','photo4.com','great4','room is pretty',4);

select * from roomservice.room_facilities;

insert into roomservice.room_facilities values(1,1);
insert into roomservice.room_facilities values(1,2);
insert into roomservice.room_facilities values(1,3);
insert into roomservice.room_facilities values(2,4);
insert into roomservice.room_facilities values(2,5);
insert into roomservice.room_facilities values(2,6);
insert into roomservice.room_facilities values(3,1);
insert into roomservice.room_facilities values(4,2);
insert into roomservice.room_facilities values(5,3);