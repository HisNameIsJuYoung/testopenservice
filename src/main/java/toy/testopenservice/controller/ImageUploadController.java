package toy.testopenservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import toy.testopenservice.dto.ResponseDTO;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class ImageUploadController {

    private static final String UPLOAD_DIR = "/Users/ysh/workspace/testopenservice/src/main/resources/static/image/";

    @PostMapping("/imageUpload")
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            // Ensure the upload directory exists
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Save the file to the local disk
            String fileName = file.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);
            file.transferTo(filePath.toFile());
            System.out.println("File uploaded successfully: " + fileName);

            return new ResponseEntity<>(new ResponseDTO<>(HttpStatus.OK.value(), fileName), HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to upload image"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}