package com.apirest.colek.services.Interfaces;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.apirest.colek.entity.Video;

public interface IVideoService {

    public CompletableFuture<Video> guardarVideo(Video usuario);

    public CompletableFuture<Video> obtenerVideoPorId(Long id);

    public CompletableFuture<List<Video>> obtenerVideos();
}
