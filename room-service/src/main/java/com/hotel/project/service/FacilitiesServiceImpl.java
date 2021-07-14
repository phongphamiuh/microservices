package com.hotel.project.service;

import com.hotel.project.dto.FacilitiesResponse;
import com.hotel.project.mapper.FacilitiesMapper;
import com.hotel.project.repository.FacilitiesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FacilitiesServiceImpl implements FacilitiesService{
    private final FacilitiesRepository facilitiesRepository;
    private final FacilitiesMapper facilitiesMapper;
    @Override
    public List<FacilitiesResponse> getAlls() {
        return facilitiesRepository.findAll()
                .stream()
                .map(facilitiesMapper :: mapToDto)
                .collect(Collectors.toList());
    }
}
