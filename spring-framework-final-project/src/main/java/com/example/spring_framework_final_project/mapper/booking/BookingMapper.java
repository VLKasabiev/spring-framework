package com.example.spring_framework_final_project.mapper.booking;

import com.example.spring_framework_final_project.entities.Booking;
import com.example.spring_framework_final_project.kafka.event.BookingEvent;
import com.example.spring_framework_final_project.model.booking.BookingResponse;
import com.example.spring_framework_final_project.model.booking.UpsertBookingRequest;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.security.core.userdetails.UserDetails;

@DecoratedWith(BookingMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookingMapper {

    Booking requestToBooking(UpsertBookingRequest request, UserDetails userDetails);

    @Mapping(source = "room.id", target = "roomId")
    @Mapping(source = "user.id", target = "userId")
    BookingResponse bookingToResponse(Booking booking);

    @Mapping(source = "room.id", target = "roomId")
    @Mapping(source = "user.id", target = "userId")
    BookingEvent bookingToEvent(Booking booking);
}
