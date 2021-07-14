//package com.hotel.project.apis;
//
//import com.hotel.project.dto.HotelFacilitiesRequest;
//import com.hotel.project.service.HotelFacilitiesService;
//import lombok.AllArgsConstructor;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//@AllArgsConstructor
//public class DateLoader implements ApplicationRunner {
//    private final HotelFacilitiesService hotelFacilitiesService;
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        hotelFacilitiesService.saveHotelFacilities(new HotelFacilitiesRequest("Giặt là","1"));
//        hotelFacilitiesService.saveHotelFacilities(new HotelFacilitiesRequest("Thang máy","2"));
//        hotelFacilitiesService.saveHotelFacilities(new HotelFacilitiesRequest("Nhà hàng","2"));
//        hotelFacilitiesService.saveHotelFacilities(new HotelFacilitiesRequest("Cho thuê xe","2"));
//        hotelFacilitiesService.saveHotelFacilities(new HotelFacilitiesRequest("Phòng thể dục","2"));
//        hotelFacilitiesService.saveHotelFacilities(new HotelFacilitiesRequest("Dọn phòng hằng ngày","2"));
//        hotelFacilitiesService.saveHotelFacilities(new HotelFacilitiesRequest("Thang máy","2"));
//        hotelFacilitiesService.saveHotelFacilities(new HotelFacilitiesRequest("Đưa đón sân bay","2"));
//    }
//}
