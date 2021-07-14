package com.hotel.project.service;

import com.hotel.project.dto.RoomRequest;
import com.hotel.project.dto.RoomResponse;
import com.hotel.project.entity.BedType;
import com.hotel.project.entity.Facilities;
import com.hotel.project.entity.Room;
import com.hotel.project.exception.BadRequestException;
import com.hotel.project.mapper.RoomMapper;
import com.hotel.project.repository.BedTypeRepository;
import com.hotel.project.repository.FacilitiesRepository;
import com.hotel.project.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService{

    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private FacilitiesRepository facilitiesRepository;
    @Autowired
    private BedTypeRepository bedTypeRepository;
    public RoomResponse save(RoomRequest roomRequest){
        Room room=roomMapper.map(roomRequest);
        List<Facilities> facilities=getFicilitiesIfFound(roomRequest);
        room.setFacilities(facilities);
        BedType bedType= bedTypeRepository.findByName(roomRequest.getBedTypeName())
                .orElseThrow(()->new BadRequestException("Bed is not found"));
        room.setBedType(bedType);
        roomRepository.save(room);
        roomRepository.flush();
        return roomMapper.mapToRoomDto(room);
    }

    public List<Facilities> getFicilitiesIfFound(RoomRequest roomRequest){
        List<Facilities> list=new ArrayList<>();
        if(Objects.isNull(roomRequest.getName())){
            return list;
        }
        roomRequest.getName().stream().forEach((name)->{
           Facilities facilities=facilitiesRepository.findByName(name)
                   .orElseThrow(()->new BadRequestException("Facilities not found"));
           list.add(facilities);
        });
        return list;
    }

    @Override
    public RoomResponse getRoom(Long roomId) {
       Room room = roomRepository.findById(roomId)
               .orElseThrow(()-> new BadRequestException("Room is not found"));
       return roomMapper.mapToRoomDto(room);
    }

    @Override
    public List<RoomResponse> getAll() {
        return roomRepository.findAll()
                .stream()
                .map(roomMapper::mapToRoomDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomResponse> getAllsByHotelId(Long hotelId) {
        List<Room> list=roomRepository.findAllByHotelId(hotelId);
        return list.stream().map(roomMapper::mapToRoomDto).collect(Collectors.toList());
    }

    @Override
    public void deleteRoom(Long roomId) {
        Room room=roomRepository.findById(roomId).orElseThrow(()->new BadRequestException("Room is not found"));
        roomRepository.deleteById(roomId);
    }

    @Override
    public void updateRoom(Long id, RoomRequest roomRequest) {
        Room room=roomRepository.findById(id).orElseThrow(()->new BadRequestException("Room is not found"));
        roomMapper.updateRoom(roomRequest,room);
        room=roomRepository.save(room);
    }

}
