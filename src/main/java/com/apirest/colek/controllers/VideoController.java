package com.apirest.colek.controllers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.apirest.colek.entity.Video;
import com.apirest.colek.services.Implementation.VideoService;

import ws.schild.jave.MultimediaInfo;
import ws.schild.jave.MultimediaObject;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

    private final VideoService videoService;
    private final Path root = Paths.get("src/main/resources/static/uploads/videos");


    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @PostMapping()
    public ResponseEntity<Object> crearVideo(@RequestParam("video") MultipartFile video) {
          Video nuevoVideo = new Video();
            try {
                String nombreDocumento = video.getOriginalFilename().replaceAll(" ","_");
                String fileExtension = FilenameUtils.getExtension(nombreDocumento);

                String nombreRutaGuardado = UUID.randomUUID().toString() + "-" + nombreDocumento;
                //String idRootPath = usuario.getAvatar().split("/")[5];
               // Files.deleteIfExists(this.root.resolve(idRootPath));
                Files.copy(video.getInputStream(), this.root.resolve( nombreRutaGuardado));

               // Path rutaArchivo = this.root.resolve(nombreRutaGuardado).normalize();
                String rutaArchivo = "http://localhost:8080/uploads/videos/"+nombreRutaGuardado;
                File input = new File("src/main/resources/static/uploads/videos/"+nombreRutaGuardado);
                MultimediaObject instance = new MultimediaObject(input);
                MultimediaInfo result = instance.getInfo();
                long videoTime = result.getDuration() / 1000;
                System.out.println(videoTime);
                nuevoVideo.setExtension(fileExtension);
                nuevoVideo.setTitulo(nombreDocumento);
                nuevoVideo.setUrl(rutaArchivo);
                nuevoVideo.setDuracion(String.valueOf(videoTime));
                this.videoService.guardarVideo(nuevoVideo);

            } catch (Exception e) {
                return new ResponseEntity<> ("Error: No existe ningún cliente con ese id "+e.getMessage(), HttpStatus.BAD_REQUEST);
            }
          
        return new ResponseEntity<>("El video se a creado correctamente", HttpStatus.CREATED);
    }



}
