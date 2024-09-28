package com.example.spring_framework_final_project.mapper.room;

import com.example.spring_framework_final_project.entities.Hotel;
import com.example.spring_framework_final_project.entities.Room;
import com.example.spring_framework_final_project.mapper.hotel.HotelMapper;
import com.example.spring_framework_final_project.model.room.RoomResponse;
import com.example.spring_framework_final_project.model.room.UpsertRoomRequest;
import com.example.spring_framework_final_project.repositories.HotelRepository;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
//@DecoratedWith(RoomMapperDelegate.class)
//@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoomMapper {

//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "hotel", source = "hotelId")
//    Room requestToRoom(UpsertRoomRequest request);
//
////    @Mapping(source = "roomId", target = "id")
//    Room requestToRoom(Long roomId, UpsertRoomRequest request);
//
////    @Mapping(source = "hotel.id", target = "hotelId")
//    RoomResponse roomToResponse(Room room);
}
