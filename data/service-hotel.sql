SELECT * FROM servicehotelservice.hotel_facilities;

insert into servicehotelservice.hotel_facilities values(1,'local_laundry_service','Giặt là');
insert into servicehotelservice.hotel_facilities values(2,'elevator','Thang máy');
insert into servicehotelservice.hotel_facilities values(3,'local_restaurant','Nhà hàng');
insert into servicehotelservice.hotel_facilities values(4,'motorcycle','Cho thuê xe');
insert into servicehotelservice.hotel_facilities values(5,'sports_handball','Phòng thể dục');
insert into servicehotelservice.hotel_facilities values(6,'cleaning_services','Dọn phòng hằng ngày');
insert into servicehotelservice.hotel_facilities values(7,'airplanemode_active','Đưa đón sân bay');

SELECT * FROM servicehotelservice.service_hotel;

update servicehotelservice.service_hotel
set check_able=true where service_hotel_id=1;

insert into servicehotelservice.service_hotel values(1,false,1,'2021-05-11','',true,0,'',1);
insert into servicehotelservice.service_hotel values(2,false,1,'2021-05-11','',true,0,'',2);
insert into servicehotelservice.service_hotel values(3,false,1,'2021-05-11','',true,0,'',3);
insert into servicehotelservice.service_hotel values(4,false,1,'2021-05-11','',true,0,'',4);
insert into servicehotelservice.service_hotel values(5,false,1,'2021-05-11','',true,0,'',5);
insert into servicehotelservice.service_hotel values(6,false,1,'2021-05-11','',true,0,'',6);
insert into servicehotelservice.service_hotel values(7,false,1,'2021-05-11','',true,0,'',7);


