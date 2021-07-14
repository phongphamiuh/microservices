package com.hotel.project.model.specs;

import com.hotel.project.model.entity.Hotel;
import com.hotel.project.model.entity.StarType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class HotelSpecs {

    public static Specification<Hotel> withCategory(String categoryName){
        return (root,query,cb) ->{
            if(categoryName==null){
                return cb.isTrue(cb.literal(true));
            }
            return cb.equal(root.join("category").get("name"),categoryName);
        };
    }

    public static Specification<Hotel> withCity(String city){
        return (root,query,cb) ->{
            if(city==null){
                return cb.isTrue(cb.literal(true));
            }
            return cb.equal(root.get("city"),city);
        };
    }

    public static Specification<Hotel> withStar(List<StarType> stars){
        return (root,query,cb) ->{
            if(stars==null){
                return cb.isTrue(cb.literal(true));
            }
            return cb.in(root.get("star")).value(stars);
        //    return cb.equal(root.get("star"),stars);
        };
    }

    public static Specification<Hotel> maxPrice(Double displayPrice) {
        return (root, query, cb) -> {
            if (displayPrice == null) {
                return cb.isTrue(cb.literal(true));
            }
            return cb.lessThan(root.get("displayPrice"), displayPrice);
        };
    }

    public static Specification<Hotel> minPrice(Double displayPrice) {
        return (root, query, cb) -> {
            if (displayPrice == null) {
                return cb.isTrue(cb.literal(true));
            }
            return cb.greaterThanOrEqualTo(root.get("displayPrice"), displayPrice);
        };
    }

}
