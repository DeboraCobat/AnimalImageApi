package com.example.midtermFDS0007;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DogService {
    private RestTemplate restTemplate;

    public DogService() {
        this.restTemplate = new RestTemplate();
    }

    public Dog getRandomDogImage() {
        String url = "https://dog.ceo/api/breeds/image/random";
        Dog dog = restTemplate.getForObject(url, Dog.class);
        return dog;
    }
}
