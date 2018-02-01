package aleksey.stepanyuk.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    String store(MultipartFile multipartFile);

    String storeScaledImg(MultipartFile multipartFile);

    Resource loadAsResource(String filename);
}