package com.hotel.project.service;

import com.hotel.project.domain.HotelFacilities;
import com.hotel.project.domain.ServiceHotel;
import com.hotel.project.dto.ServiceHotelRequest;
import com.hotel.project.dto.ServiceHotelResponse;
import com.hotel.project.dto.ServiceUpdateRequest;
import com.hotel.project.dto.UpdateServiceRequest;
import com.hotel.project.exception.BadRequestException;
import com.hotel.project.mapper.ServiceHotelMapper;
import com.hotel.project.repository.HotelFacilitiesRepository;
import com.hotel.project.repository.ServiceHotelRepository;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ServiceHotelServiceImpl implements ServiceHotelService{

    private final ServiceHotelRepository serviceHotelRepository;
    private final HotelFacilitiesRepository hotelFacilitiesRepository;
    private final ServiceHotelMapper serviceHotelMapper;

    @Override
    public ServiceHotel save(ServiceHotelRequest serviceHotelRequest) {
        ServiceHotel serviceHotel1=serviceHotelRepository.findByNameAndHotelId(serviceHotelRequest.getName(),serviceHotelRequest.getHotelId());
        ServiceHotel serviceHotel=null;
        if(serviceHotel1==null){
            HotelFacilities hotelFacilities=hotelFacilitiesRepository.findByName(serviceHotelRequest.getName())
                    .orElseThrow(()->new BadRequestException("Hotel Facilities not found"));
            serviceHotel=serviceHotelMapper.map(serviceHotelRequest,hotelFacilities);
            serviceHotelRepository.save(serviceHotel);
        }
        return serviceHotel;
    }

    @Override
    public void saveAll(Long hotelId) {
        List<HotelFacilities> hotelFacilities=hotelFacilitiesRepository.findAll();
        hotelFacilities.forEach(facility->{
            ServiceHotel serviceHotel=new ServiceHotel();
            serviceHotel.setHotelId(hotelId);
            serviceHotel.setCheckAble(false);
            serviceHotel.setPrice(0);
            serviceHotel.setPriceAble(false);
            serviceHotel.setTax(0);
            serviceHotel.setTaxName("");
            serviceHotel.setModifiedDate(Instant.now());
            serviceHotel.setHotelFacilities(facility);
            serviceHotelRepository.save(serviceHotel);
        });
    }

    @Override
    public List<ServiceHotelResponse> findAllSeriveHotelByHotelIdAndCheckAlbe(Long hotelId,boolean checkAlbe) {
        List<ServiceHotel> list=serviceHotelRepository.findAllByHotelIdAndCheckAble(hotelId,checkAlbe);
        return list.stream().map(serviceHotelMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<ServiceHotelResponse> findAllSeriveHotelByHotelId(Long hotelId) {
        List<ServiceHotel> list=serviceHotelRepository.findAllByHotelId(hotelId);
        return list.stream().map(serviceHotelMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public ServiceHotelResponse getServiceHotelById(Long serviceHotelId) {
        return serviceHotelMapper.mapToDto(serviceHotelRepository.findById(serviceHotelId).orElseThrow(()->new BadRequestException("Hotel Facilities not found")));
    }

    @Override
    public void updateServiceHotel(Long id, ServiceUpdateRequest serviceUpdateRequest) {
       ServiceHotel serviceHotel= serviceHotelRepository.findById(id)
               .orElseThrow(()->new BadRequestException("Hotel Facilities not found"));
       serviceHotelMapper.update(serviceUpdateRequest,serviceHotel);
       serviceHotel=serviceHotelRepository.save(serviceHotel);
    }

    @Override
    public void updateCheckAbleByNameService(Long hotelId, List<UpdateServiceRequest> updateServiceRequests) {

        updateServiceRequests.forEach(updateRequest->{
            ServiceHotel serviceHotel = serviceHotelRepository.findByNameAndHotelId(updateRequest.getName(),hotelId);
            serviceHotel.setCheckAble(updateRequest.isCheckAble());
            serviceHotelRepository.save(serviceHotel);
        });
    }

    @Override
    public void delete(Long id) {
        ServiceHotel serviceHotel= serviceHotelRepository.findById(id).orElseThrow(()->new BadRequestException("Hotel Facilities not found"));
        serviceHotelRepository.delete(serviceHotel);
    }
}
