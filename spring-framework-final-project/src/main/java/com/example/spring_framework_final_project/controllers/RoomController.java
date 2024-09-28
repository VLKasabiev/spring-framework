package com.example.spring_framework_final_project.controllers;

import com.example.spring_framework_final_project.entities.Room;
import com.example.spring_framework_final_project.mapper.room.RoomMapperDelegate;
import com.example.spring_framework_final_project.filters.RoomFilter;
import com.example.spring_framework_final_project.model.room.RoomResponse;
import com.example.spring_framework_final_project.model.room.UpsertRoomRequest;
import com.example.spring_framework_final_project.services.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/room")
@RequiredArgsConstructor
@Slf4j
public class RoomController {
    private final RoomService roomService;
    private final RoomMapperDelegate roomMapper;

    @GetMapping("/filter")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<RoomResponse>> filter(@Valid RoomFilter filter) {
        return ResponseEntity.ok(roomService.filterBy(filter)
                .stream()
                .map(roomMapper::roomToResponse)
                .toList());
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<RoomResponse>> findAll() {
        return ResponseEntity.ok(
                roomService.findAll()
                        .stream()
                        .map(roomMapper::roomToResponse)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<RoomResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                roomMapper.roomToResponse(roomService.findById(id))
        );
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<RoomResponse> create(@RequestBody @Valid UpsertRoomRequest request) {
        Room newRoom = roomService.createRoom(roomMapper.requestToRoom(request));

        log.info("newHotel ID: " + newRoom.getHotel().getId());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(roomMapper.roomToResponse(newRoom));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<RoomResponse> update(@PathVariable Long id,
                                               @RequestBody @Valid UpsertRoomRequest request) {
        Room updatedRoom = roomService.updateRoom(roomMapper.requestToRoom(id, request));

        return ResponseEntity.ok(roomMapper.roomToResponse(updatedRoom));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        roomService.deleteRoomById(id);

        return ResponseEntity.noContent().build();
    }
}
