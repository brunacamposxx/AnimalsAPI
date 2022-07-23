package br.com.iteris.universidade.AnimalsAPI.service;

import br.com.iteris.universidade.AnimalsAPI.domain.dto.*;
import br.com.iteris.universidade.AnimalsAPI.repository.AnimalsRepository;
import br.com.iteris.universidade.AnimalsAPI.domain.entity.Animal;
import br.com.iteris.universidade.AnimalsAPI.exception.InvalidAnimalException;
import br.com.iteris.universidade.AnimalsAPI.exception.AnimalNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimalsService {

    private final AnimalsRepository repository;

    public AnimalsService(final AnimalsRepository repository) {
        this.repository = repository;
    }

    public List<AnimalResponse> listAnimals(AnimalFilterRequest filter) {
        var result = repository.listarComFiltroNativo(filter.getName());

        return result.stream().map(animal -> new AnimalResponse(
                animal.getIdAnimal(),
                animal.getName(),
                animal.getAge(),
                animal.getSpecies(),
                animal.getBirthDate(),
                animal.getCutenessLevel(),
                animal.getAffectionLevel(),
                animal.getEmail()
        )).collect(Collectors.toList());
    }

    public AnimalResponse createAnimal(AnimalCreateRequest animalCreateRequest) {

        List<String> acceptedSpecies = new ArrayList<>();
        acceptedSpecies.add("Cachorro");
        acceptedSpecies.add("Gato");
        acceptedSpecies.add("Coelho");
        acceptedSpecies.add("Capivara");

        if (!acceptedSpecies.contains(animalCreateRequest.getSpecies())) {
            throw new InvalidAnimalException("Especie invalida");
        }

        var newAnimal = new Animal();
        newAnimal.setName(animalCreateRequest.getName());
        newAnimal.setAge(animalCreateRequest.getAge());
        newAnimal.setSpecies(animalCreateRequest.getSpecies());
        newAnimal.setBirthDate(animalCreateRequest.getBirthDate());
        newAnimal.setCutenessLevel(animalCreateRequest.getCutenessLevel());
        newAnimal.setAffectionLevel(animalCreateRequest.getAffectionLevel());
        newAnimal.setEmail(animalCreateRequest.getEmail());

        var saveAnimal = repository.save(newAnimal);

        return new AnimalResponse(
                saveAnimal.getIdAnimal(),
                saveAnimal.getName(),
                saveAnimal.getAge(),
                saveAnimal.getSpecies(),
                saveAnimal.getBirthDate(),
                saveAnimal.getCutenessLevel(),
                saveAnimal.getAffectionLevel(),
                saveAnimal.getEmail()
        );

    }

    public AnimalResponse findAnimalById(Integer idAnimal) {
        var foundAnimal = repository.findById(idAnimal);

        if (foundAnimal.isEmpty()) {
            throw new AnimalNotFoundException();
        }
        var saveAnimal = foundAnimal.get();

        return new AnimalResponse(
                saveAnimal.getIdAnimal(),
                saveAnimal.getName(),
                saveAnimal.getAge(),
                saveAnimal.getSpecies(),
                saveAnimal.getBirthDate(),
                saveAnimal.getCutenessLevel(),
                saveAnimal.getAffectionLevel(),
                saveAnimal.getEmail()
        );
    }

    public AnimalResponse findAnimalByName(String name) {
        var foundAnimal = repository.findByNameContaining(name);

        if (foundAnimal.isEmpty()) {
            throw new AnimalNotFoundException();
        }
        var saveAnimal = foundAnimal.get();

        return new AnimalResponse(
                saveAnimal.getIdAnimal(),
                saveAnimal.getName(),
                saveAnimal.getAge(),
                saveAnimal.getSpecies(),
                saveAnimal.getBirthDate(),
                saveAnimal.getCutenessLevel(),
                saveAnimal.getAffectionLevel(),
                saveAnimal.getEmail()
        );
    }

    public AnimalResponse updateAnimal(Integer idAnimal, AnimalUpdateRequest animalUpdateRequest) {
        var foundAnimal = repository.findById(idAnimal);

        if (foundAnimal.isEmpty()) {
            throw new AnimalNotFoundException();
        }
        var updatedAnimal = foundAnimal.get();
        updatedAnimal.setName(animalUpdateRequest.getName());

        var saveAnimal = repository.save(updatedAnimal);

        return new AnimalResponse(
                saveAnimal.getIdAnimal(),
                saveAnimal.getName(),
                saveAnimal.getAge(),
                saveAnimal.getSpecies(),
                saveAnimal.getBirthDate(),
                saveAnimal.getCutenessLevel(),
                saveAnimal.getAffectionLevel(),
                saveAnimal.getEmail()
        );
    }

    public AnimalResponse deleteAnimal(Integer idAnimal) {
        var foundAnimal = repository.findById(idAnimal);

        if (foundAnimal.isEmpty()) {
            throw new AnimalNotFoundException();
        }

        var deleteAnimal = foundAnimal.get();
        repository.delete(deleteAnimal);

        return new AnimalResponse(
                deleteAnimal.getIdAnimal(),
                deleteAnimal.getName(),
                deleteAnimal.getAge(),
                deleteAnimal.getSpecies(),
                deleteAnimal.getBirthDate(),
                deleteAnimal.getCutenessLevel(),
                deleteAnimal.getAffectionLevel(),
                deleteAnimal.getEmail()
        );
    }
}
