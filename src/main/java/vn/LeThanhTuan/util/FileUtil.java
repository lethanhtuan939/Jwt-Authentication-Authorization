package vn.LeThanhTuan.util;

import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.util.stream.Stream;

public class FileUtil {

    private static final String UPLOAD_DIR = "images";
    private static final Path storageFolder = Paths.get(UPLOAD_DIR);

    public static String generatedFileName(MultipartFile file) {
        String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
        String fileName = UUID.randomUUID().toString().replace("-", "");
        fileName = fileName + "." + fileExtension;

        return fileName;
    }
    public static void saveFile(String fileName, MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(UPLOAD_DIR);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }

    public static byte[] readFileContent(String fileName) {
        try {
            Path uploadPath = Paths.get(UPLOAD_DIR);
            Path file = uploadPath.resolve(fileName);

            Resource resource = new UrlResource(file.toUri());

            if(resource.exists() || resource.isReadable()) {
                byte[] bytes = StreamUtils.copyToByteArray(resource.getInputStream());

                return bytes;
            } else {
                throw new RuntimeException("Coud not read file: " + fileName);
            }
        } catch (IOException ioe) {
            throw new RuntimeException("Coud not read file: " + fileName, ioe);
        }
    }

    public static Stream<Path> loadAll() {
        try {
            return Files.walk(storageFolder, 1)
                        .filter(path -> !path.equals(storageFolder))
                        .map(storageFolder::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load stored files", e);
        }
    }
}
