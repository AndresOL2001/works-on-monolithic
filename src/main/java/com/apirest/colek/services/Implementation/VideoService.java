package com.apirest.colek.services.Implementation;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.colek.entity.Video;
import com.apirest.colek.repositories.VideoRepository;
import com.apirest.colek.services.Interfaces.IVideoService;

@Service
@Transactional
public class VideoService implements IVideoService{

    @Autowired
    VideoRepository videoRepository;

    @Override
    public CompletableFuture<Video> guardarVideo(Video video) {
        return CompletableFuture.completedFuture(this.videoRepository.save(video));
    }

    @Override
    public CompletableFuture<Video> obtenerVideoPorId(Long id) {
        return CompletableFuture.completedFuture(this.videoRepository.findById(id).get());
    }

    @Override
    public CompletableFuture<List<Video>> obtenerVideos() {
        return CompletableFuture.completedFuture(this.videoRepository.findAll());
    }
    
}
