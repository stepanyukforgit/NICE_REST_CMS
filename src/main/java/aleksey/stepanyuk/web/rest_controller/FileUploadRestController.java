package aleksey.stepanyuk.web.rest_controller;

import aleksey.stepanyuk.domain.entity.Photo;
import aleksey.stepanyuk.service.photo.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class FileUploadRestController {

    private PhotoService photoService;

    @Autowired
    FileUploadRestController(PhotoService photoService){
        this.photoService = photoService;
    }

    @PostMapping("/upload_photo")
    public ResponseEntity<Photo> uploadImg(@RequestParam("file") MultipartFile file){

        Photo savedPhoto = photoService.save(file);
        if (savedPhoto != null)
            return ResponseEntity.ok(savedPhoto);
        else
            return ResponseEntity.unprocessableEntity().build();
    }

    //todo amount constraint (maybe set not required param)
    @PostMapping("/upload_photo_list")
    public ResponseEntity<List<Photo>> uploadImgMulti(@RequestParam("file") MultipartFile[] file){
        return ResponseEntity.ok(photoService.saveAll(file));
    }

    @GetMapping("/get_photo/{id}")
    public ResponseEntity<Resource> servePhoto(@PathVariable Long id) {

        Resource file = photoService.read(id);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("/get_prev_photo/{id}")
    public ResponseEntity<Resource> servePrevPhoto(@PathVariable Long id) {

        Resource file = photoService.readPrev(id);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/delete_photo")
    public void deletePhoto(@RequestParam Long id){

        photoService.delete(id);
    }
}