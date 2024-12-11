package com.oxlxs.hotelmanagementsysback.service;

import com.oxlxs.hotelmanagementsysback.dto.request.RoomCreateRequest;
import com.oxlxs.hotelmanagementsysback.dto.response.AvailableRoomResponse;
import com.oxlxs.hotelmanagementsysback.dto.response.RoomInfoResponse;
import com.oxlxs.hotelmanagementsysback.dto.response.RoomTypeResponse;
import com.oxlxs.hotelmanagementsysback.entity.AvailableRoom;
import com.oxlxs.hotelmanagementsysback.entity.Room;
import com.oxlxs.hotelmanagementsysback.entity.RoomType;
import com.oxlxs.hotelmanagementsysback.repository.AvailableRoomDAO;
import com.oxlxs.hotelmanagementsysback.repository.RoomDAO;
import com.oxlxs.hotelmanagementsysback.repository.RoomTypeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomDAO roomDAO;

    @Autowired
    private RoomTypeDAO roomTypeDAO;

    @Autowired
    private AvailableRoomDAO availableRoomDAO;

    public void create(RoomCreateRequest request) throws RuntimeException {
        if (roomDAO.existsById(request.getRoomNumber()))
            throw new RuntimeException("Room number already exists");

        RoomType type = roomTypeDAO.findById(request.getTypeName())
                .orElseThrow(() -> new RuntimeException("Room type not found"));

        Room room = new Room();
        room.setId(request.getRoomNumber());
        room.setType(type);

        roomDAO.save(room);
    }

    public void delete(Long id) throws RuntimeException {
        if (!roomDAO.existsById(id))
            throw new RuntimeException("Room not found");

        roomDAO.deleteById(id);
    }

    public List<RoomInfoResponse> getRooms() throws RuntimeException{
        List<Room> rooms = roomDAO.findAll();
        return rooms.stream().map(RoomInfoResponse::new).toList();
    }

    public List<RoomTypeResponse> getRoomTypes() throws RuntimeException{
        List<RoomType> roomTypes = roomTypeDAO.findAll();
        return roomTypes.stream().map(RoomTypeResponse::new).toList();
    }

    public List<AvailableRoomResponse> getAvailable() throws RuntimeException {
        List<AvailableRoom> rooms = availableRoomDAO.findAll();
        return rooms.stream().map(AvailableRoomResponse::new).toList();
    }

}
