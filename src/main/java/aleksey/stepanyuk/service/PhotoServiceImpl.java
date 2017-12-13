package aleksey.stepanyuk.service;

import aleksey.stepanyuk.domain.entity.Photo;
import aleksey.stepanyuk.domain.repo.PhotoRepository;
import aleksey.stepanyuk.util.NiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Value(value = "classpath:static/img/noimagefound.jpeg")
    private Resource noimagefound;

    private StorageService storageService;
    private PhotoRepository photoRepository;

    @Autowired
    PhotoServiceImpl(StorageService storageService, PhotoRepository photoRepository) {
        this.storageService = storageService;
        this.photoRepository = photoRepository;
    }

    @Override
    public Photo save(MultipartFile multipartFile) {
        Photo savedPhoto = new Photo();

        if (new NiceValidator().imageValidate(multipartFile.getOriginalFilename())) {
            String link = storageService.store(multipartFile);
            String prevLink = storageService.storeScaledImg(multipartFile);

            if (!link.isEmpty()) {
                savedPhoto.setLink(link);
                savedPhoto.setPrevLink(prevLink);
            }
        }
        return savedPhoto;
    }

    @Override
    public List<Photo> saveAll(MultipartFile[] multipartFiles) {

        List<Photo> photosList = new ArrayList<>();
        Arrays.stream(multipartFiles).forEach(img -> photosList.add(save(img)));

        return photosList;
    }

    @Override
    public Resource read(Long id) {
        Photo photo = photoRepository.findOne(id);
        Resource resource;
        if (photo != null) {
            resource = storageService.loadAsResource(photo.getLink());
        } else {
            resource = noimagefound;
        }
        return resource;
    }

    @Override
    public Resource readPrev(Long id) {
        Photo photo = photoRepository.findOne(id);
        Resource resource;
        if (photo != null) {
            resource = storageService.loadAsResource(photo.getPrevLink());
        } else {
            resource = noimagefound;
        }
        return resource;
    }

    @Override
    public void delete(Long id) {

        photoRepository.delete(id);
    }
}
