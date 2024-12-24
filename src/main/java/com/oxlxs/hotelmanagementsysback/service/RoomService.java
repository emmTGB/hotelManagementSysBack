package com.oxlxs.hotelmanagementsysback.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oxlxs.hotelmanagementsysback.dto.request.NewRoomTypeRequest;
import com.oxlxs.hotelmanagementsysback.dto.request.RoomCreateRequest;
import com.oxlxs.hotelmanagementsysback.dto.response.AvailableRoomResponse;
import com.oxlxs.hotelmanagementsysback.dto.response.RoomInfoResponse;
import com.oxlxs.hotelmanagementsysback.dto.response.RoomTypeResponse;
import com.oxlxs.hotelmanagementsysback.entity.AvailableRoom;
import com.oxlxs.hotelmanagementsysback.entity.Room;
import com.oxlxs.hotelmanagementsysback.entity.RoomStatus;
import com.oxlxs.hotelmanagementsysback.entity.RoomType;
import com.oxlxs.hotelmanagementsysback.exception.room.RoomAlreadyExistsException;
import com.oxlxs.hotelmanagementsysback.exception.room.RoomNotFoundException;
import com.oxlxs.hotelmanagementsysback.exception.room.RoomTypeAlreadyExistsException;
import com.oxlxs.hotelmanagementsysback.exception.room.RoomTypeNotFoundException;
import com.oxlxs.hotelmanagementsysback.repository.AvailableRoomDAO;
import com.oxlxs.hotelmanagementsysback.repository.RoomDAO;
import com.oxlxs.hotelmanagementsysback.repository.RoomTypeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String TYPE_CACHE_KEY = "room_type";


    public void create(RoomCreateRequest request) throws RuntimeException {
        if (roomDAO.existsById(request.getRoomNumber()))
            throw new RoomAlreadyExistsException();

        RoomType type = roomTypeDAO.findById(request.getTypeName())
                .orElseThrow(RoomTypeNotFoundException::new);

        Room room = new Room();
        room.setId(request.getRoomNumber());
        room.setType(type);

        roomDAO.save(room);
    }

    public void delete(Long id) throws RuntimeException {
        Room room = roomDAO.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
        room.setStatus(RoomStatus.DELETED);

        roomDAO.save(room);
    }

    public List<RoomInfoResponse> getRooms() throws RuntimeException{
        List<Room> rooms = roomDAO.findAll();
        return rooms.stream().map(RoomInfoResponse::new).toList();
    }

    public void createRoomType(NewRoomTypeRequest request) throws RuntimeException {
        RoomType type = new RoomType(request);
        if(roomTypeDAO.existsById(request.getName()) || roomTypeDAO.existsById(type.getFullName()))
            throw new RoomTypeAlreadyExistsException();
        roomTypeDAO.save(type);
        redisTemplate.delete(TYPE_CACHE_KEY);
    }

    public void deleteRoomType(String name) throws RuntimeException {
        RoomType type = roomTypeDAO.findById(name).orElseThrow(RoomTypeNotFoundException::new);
        roomTypeDAO.delete(type);
        redisTemplate.delete(TYPE_CACHE_KEY);
    }

    public List<RoomTypeResponse> getRoomTypes() throws RuntimeException{
        List<RoomType> roomTypes = (List<RoomType>) redisTemplate.opsForValue().get(TYPE_CACHE_KEY);

        if(roomTypes == null){
            roomTypes = roomTypeDAO.findAll();
            redisTemplate.opsForValue().set(TYPE_CACHE_KEY, roomTypes);
        }else{
            ObjectMapper objectMapper = new ObjectMapper();
            roomTypes = objectMapper.convertValue(roomTypes, new TypeReference<List<RoomType>>() {});
        }

        return roomTypes.stream().map(RoomTypeResponse::new).toList();
    }

    public List<AvailableRoomResponse> getAvailable() throws RuntimeException {
        List<AvailableRoom> rooms = availableRoomDAO.findAll();
        return rooms.stream().map(AvailableRoomResponse::new).toList();
    }

}
