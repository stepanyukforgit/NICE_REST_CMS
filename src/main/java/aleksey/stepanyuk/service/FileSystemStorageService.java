package aleksey.stepanyuk.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class FileSystemStorageService implements StorageService {

    @Value(value = "classpath:static/img/noimagefound.jpeg")
    private Resource noimagefound;
    private final Path rootLocation = Paths.get("../upload-dir");
    private Path todaysDir;

    public void init() {
        try {
            todaysDir = rootLocation.resolve(Paths.get(LocalDate.now().toString()));
            Files.createDirectories(todaysDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String store(MultipartFile multipartFile) {
        String link = "";

        if (!multipartFile.isEmpty()) {
            try (InputStream inputStream = multipartFile.getInputStream()) {
                link = storeFile(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return link;
    }

    @Override
    public String storeScaledImg(MultipartFile multipartFile) {
        String link = "";

        try (InputStream inputStreamImg = multipartFile.getInputStream()) {

            BufferedImage img = ImageIO.read(inputStreamImg);
            img = scaledImage(img);

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(img, "jpeg", os);
            InputStream inputStreamImgResized = new ByteArrayInputStream(os.toByteArray());
            link = storeFile(inputStreamImgResized);
            inputStreamImgResized.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return link;
    }

    @Override
    public Resource loadAsResource(String pathToFile) {
        try {
            Resource resource = new UrlResource(Paths.get(pathToFile).toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                System.err.println("Could not read: " + pathToFile);
            }
        } catch (MalformedURLException e) {
            System.err.println("Could not read: " + pathToFile);
            e.printStackTrace();
        }
        return noimagefound;
    }

    private String storeFile(InputStream inputStream) {
        String link = "";

        try {

            init();

            String randomString = UUID.randomUUID().toString().replace("-", "");
            Path path = todaysDir.resolve(randomString + ".jpeg");
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
            link = path.toString();

        } catch (IOException e) {
            System.err.println("Failed to store file.");
            e.printStackTrace();
        }
        return link;
    }

    private BufferedImage scaledImage(BufferedImage originalImage) {
        int IMG_WIDTH = 200;
        int IMG_HEIGHT = 200;

        double thumbRatio = (double) IMG_WIDTH / (double) IMG_HEIGHT;
        int imageWidth = originalImage.getWidth(null);
        int imageHeight = originalImage.getHeight(null);
        double aspectRatio = (double) imageWidth / (double) imageHeight;

        if (thumbRatio < aspectRatio) {
            IMG_HEIGHT = (int) (IMG_WIDTH / aspectRatio);
        } else {
            IMG_WIDTH = (int) (IMG_HEIGHT * aspectRatio);
        }

        BufferedImage newImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT,
                originalImage.getType());
        Graphics2D graphics2D = newImage.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);

        return newImage;
    }
}