package com.apirest.colek.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apirest.colek.entity.Video;

public interface VideoRepository extends JpaRepository<Video,Long>{
    
}
