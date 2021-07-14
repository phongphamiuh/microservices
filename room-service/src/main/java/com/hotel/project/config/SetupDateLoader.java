package com.hotel.project.config;

import com.hotel.project.entity.Facilities;
import com.hotel.project.repository.FacilitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private FacilitiesRepository facilitiesRepository;

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        createFacilitiesIfNotFound("Áo Choàng Tắm","1");
        createFacilitiesIfNotFound("Vòi Hoa Sen","2");
        createFacilitiesIfNotFound("Bàn","3");
        createFacilitiesIfNotFound("Điều Hòa Nhiệt Độ","4");
        createFacilitiesIfNotFound("Máy Sấy Tóc","5");
        createFacilitiesIfNotFound("Tivi","6");
        createFacilitiesIfNotFound("Bồn Tắm","6");
    }

    @Transactional
    private final Facilities createFacilitiesIfNotFound(final String name,final String icon) {
        Facilities facilities = null;
        facilities = facilitiesRepository.findByName(name).orElseGet(()->{
            return facilitiesRepository.save(new Facilities(name,icon));
        });
        return facilities;
    }
}