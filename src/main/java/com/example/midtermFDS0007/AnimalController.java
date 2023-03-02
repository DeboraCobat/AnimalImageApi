package com.example.midtermFDS0007;
import org.springframework.http.*;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class AnimalController {

    private final DogService dogService;

    public AnimalController(DogService dogService) {
        this.dogService = dogService;
    }

    @GetMapping("/animal")
    public ResponseEntity<?> getAnimal() throws IOException {
        Dog dog = dogService.getRandomDogImage();
        String url = dog.getMessage();
        String[] parts = url.split("/");
        String fileName = parts[parts.length - 1];
        String fileLocation = "src/main/resources/download/" + fileName;
        Path path = Paths.get(fileLocation);
        Files.createDirectories(path.getParent());
        Files.write(path, new RestTemplate().getForObject(url, byte[].class));
        return ResponseEntity.ok(dog);
    }

    @PostMapping("/animal")
    public ResponseEntity<String> uploadAnimalImage(@RequestParam("file") MultipartFile file) {
        if (file == null) {
            return ResponseEntity.badRequest().body("File is null.");
        }
        File uploadDir = new File("src/main/resources/upload");
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path uploadPath = Paths.get(uploadDir.getAbsolutePath(), fileName);
        try {
            Files.write(uploadPath, file.getBytes());
            return ResponseEntity.ok("File uploaded successfully!");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}