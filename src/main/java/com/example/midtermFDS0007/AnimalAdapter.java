package com.example.midtermFDS0007;

public class AnimalAdapter {
    public static Dog fromDTO(AnimalDTO animalDTO) {
        // We don't need to map from DTO to domain object in this case
        return null;
    }

    public static AnimalDTO toDTO(Dog dog) {
        AnimalDTO animalDTO = new AnimalDTO();
        // Map the Dog object to AnimalDTO object
        return animalDTO;
    }
}
