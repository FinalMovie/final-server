package com.mercury.boot.service;


import com.mercury.boot.dao.RoomDao;
import org.springframework.stereotype.Component;

@Component
public class RoomService {

    final RoomDao roomDao;

    public RoomService(RoomDao roomDao) {
        this.roomDao = roomDao;
    }


}
