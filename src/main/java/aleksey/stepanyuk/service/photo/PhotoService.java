package aleksey.stepanyuk.service.photo;

import aleksey.stepanyuk.domain.entity.Photo;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PhotoService {

    Photo save(MultipartFile multipartFile);

    List<Photo> saveAll(MultipartFile[] multipartFile);

    Resource read(Long id);

    Resource readPrev(Long id);

    void delete(Long id);
}