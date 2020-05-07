package com.mercury.boot.service;


import com.mercury.boot.bean.Schedule;
import com.mercury.boot.dao.ScheduleDao;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduleService {

    final ScheduleDao scheduleDao;

    public ScheduleService(ScheduleDao scheduleDao) {
        this.scheduleDao = scheduleDao;
    }

    public List<Schedule> getScheduleByMovieId(long movieId) {
        return scheduleDao.findAllByMovieId(movieId);
    }

}
