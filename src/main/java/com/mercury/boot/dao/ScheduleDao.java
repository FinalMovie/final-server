package com.mercury.boot.dao;

import com.mercury.boot.bean.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleDao extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByMovieId(long movieId);
}
