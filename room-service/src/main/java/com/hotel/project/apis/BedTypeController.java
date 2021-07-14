package com.hotel.project.apis;

import com.hotel.project.dto.BedTypeResponse;
import com.hotel.project.service.BedTypeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bed/type")
@AllArgsConstructor
public class BedTypeController {
    private final BedTypeService bedTypeService;
    @GetMapping("/all")
    public List<BedTypeResponse> getAllsBedType(){
        return bedTypeService.getAllsBedType();
    }
}
