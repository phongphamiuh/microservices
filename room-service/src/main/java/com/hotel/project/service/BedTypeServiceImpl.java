package com.hotel.project.service;

import com.hotel.project.dto.BedTypeResponse;
import com.hotel.project.mapper.BedTypeMapper;
import com.hotel.project.repository.BedTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BedTypeServiceImpl implements BedTypeService{
    private final BedTypeRepository bedTypeRepository;
    private final BedTypeMapper bedTypeMapper;
    @Override
    public List<BedTypeResponse> getAllsBedType() {
        return bedTypeRepository.findAll()
                .stream()
                .map(bedTypeMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
