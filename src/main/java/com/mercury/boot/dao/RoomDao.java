package com.mercury.boot.dao;

import com.mercury.boot.bean.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomDao extends JpaRepository<Room, Long> {
}
