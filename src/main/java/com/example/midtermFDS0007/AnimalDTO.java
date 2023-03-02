package com.example.midtermFDS0007;

import org.springframework.web.multipart.MultipartFile;

public class AnimalDTO {
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
