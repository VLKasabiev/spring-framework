package com.example.spring_framework_final_project.mapper.room;

import com.example.spring_framework_final_project.entities.Room;
import com.example.spring_framework_final_project.model.room.RoomResponse;
import com.example.spring_framework_final_project.model.room.UpsertRoomRequest;
import com.example.spring_framework_final_project.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RoomMapper {
    @Autowired
    private HotelService hotelService;


    public Room requestToRoom(UpsertRoomRequest request) {
        Room room = new Room();

        room.setHotel(hotelService.findById(request.getHotelId()));
        room.setCost(request.getCost());
        room.setTitle(request.getTitle());
        room.setNumber(request.getNumber());
        room.setDescription(request.getDescription());
        room.setMaxOccupancy(request.getMaxOccupancy());

        return room;
    }

    public Room requestToRoom(Long roomId, UpsertRoomRequest request) {
        Room room = requestToRoom(request);
        room.setId(roomId);

        return room;
    }

    public RoomResponse roomToResponse(Room room) {
        RoomResponse response = new RoomResponse();
        response.setId(room.getId());
        response.setTitle(room.getTitle());
        response.setCost(room.getCost());
        response.setNumber(room.getNumber());
        response.setDescription(room.getDescription());
        response.setMaxOccupancy(room.getMaxOccupancy());
        response.setUnavailableDates(room.getUnavailableDates()
                .stream()
                .map(resp -> resp.getArrivalDate() + " - " + resp.getDepartureDate())
                .collect(Collectors.toList()));

        response.setHotelId(room.getHotel().getId());
        return response;
    }
}
