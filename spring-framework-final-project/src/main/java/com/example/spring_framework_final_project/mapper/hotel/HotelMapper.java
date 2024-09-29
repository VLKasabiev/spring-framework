package com.example.spring_framework_final_project.mapper.hotel;

import com.example.spring_framework_final_project.entities.Hotel;
import com.example.spring_framework_final_project.model.hotel.HotelResponse;
import com.example.spring_framework_final_project.model.hotel.UpsertHotelRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HotelMapper {
    Hotel requestToHotel(UpsertHotelRequest request);

    @Mapping(source = "hotelId", target = "id")
    Hotel requestToHotel(Long hotelId, UpsertHotelRequest request);

    HotelResponse hotelToResponse(Hotel hotel);

    default Hotel toHotel(UpsertHotelRequest request) {
        Hotel hotel = requestToHotel(request);
        hotel.setNumberOfRatings(0L);
        hotel.setRating(0.0);
        return hotel;
    }

    default Hotel toHotel(Long hotelId, UpsertHotelRequest request) {
        Hotel hotel = toHotel(request);
        hotel.setId(hotelId);
        return hotel;
    }

    default HotelResponse toResponse(Hotel hotel) {
        HotelResponse response = hotelToResponse(hotel);
        if (response.getNumberOfRatings() >= 2) {
            response.setNumberOfRatings(response.getNumberOfRatings() - 1);
        }

        return response;
    }
}
