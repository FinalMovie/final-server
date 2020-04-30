package com.mercury.boot.dao;

import com.mercury.boot.bean.Movies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieDao extends JpaRepository<Movies,Long> {

}
