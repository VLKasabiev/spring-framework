package com.example.spring_framework_final_project.services;

import com.example.spring_framework_final_project.entities.Room;
import com.example.spring_framework_final_project.exceptions.EntityNotFoundException;
import com.example.spring_framework_final_project.filters.RoomFilter;
import com.example.spring_framework_final_project.repositories.RoomRepository;
import com.example.spring_framework_final_project.specification.RoomSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class RoomService {
    private final RoomRepository roomRepository;

    public List<Room> filterBy(RoomFilter filter) {
        return roomRepository.findAll(RoomSpecification.withFilter(filter));
    }

    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    public Room findById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(
                "Комната с ID {0} не найдена!", id
        )));
    }

    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room updateRoom(Room room) {
        return roomRepository.save(room);
    }

    public void deleteRoomById(Long id) {
        roomRepository.deleteById(id);
    }
}
