package com.hotel.project.repository;

import com.hotel.project.entity.BedType;
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BEncoderStream;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BedTypeRepository extends JpaRepository<BedType,Long>{
    Optional<BedType> findByName(String name);
}
