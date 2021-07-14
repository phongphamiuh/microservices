package com.hotel.project.service;

import com.hotel.project.domain.ServiceHotel;
import com.hotel.project.dto.ServiceHotelRequest;
import com.hotel.project.dto.ServiceHotelResponse;
import com.hotel.project.dto.ServiceUpdateRequest;
import com.hotel.project.dto.UpdateServiceRequest;

import java.util.List;

public interface ServiceHotelService {
    ServiceHotel save(ServiceHotelRequest serviceHotelRequest);
    void saveAll(Long hotelId);
    List<ServiceHotelResponse> findAllSeriveHotelByHotelId(Long hotelId);
    List<ServiceHotelResponse> findAllSeriveHotelByHotelIdAndCheckAlbe(Long hotelId,boolean checkAlbe);
    ServiceHotelResponse getServiceHotelById(Long serviceHotelId);
    void updateServiceHotel(Long id, ServiceUpdateRequest serviceUpdateRequest);
    void updateCheckAbleByNameService(Long hotelId, List<UpdateServiceRequest> updateServiceRequests);
    void delete(Long id);
}
