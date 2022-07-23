package br.com.iteris.universidade.AnimalsAPI.controller;


import br.com.iteris.universidade.AnimalsAPI.domain.dto.*;
import br.com.iteris.universidade.AnimalsAPI.service.AnimalsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AnimalsController {
    private final AnimalsService service;

    public AnimalsController(final AnimalsService service){
        this.service = service;
    }

    @GetMapping(value = "api/animals/{id}")
    public ResponseEntity<AnimalResponse> findAnimalById(@PathVariable Integer id){
        var foundAnimal = service.findAnimalById(id);
        return ResponseEntity.ok(foundAnimal);
    }

    @GetMapping(value = "api/animals/name/{nameParam}")
    public ResponseEntity<AnimalResponse> findAnimalByName(@PathVariable String nameParam) {
        var animalResponse = service.findAnimalByName(nameParam);
        return ResponseEntity.ok(animalResponse);
    }

    @GetMapping(value = "api/animals")
    public ResponseEntity<List<AnimalResponse>> listAnimals(AnimalFilterRequest filter) {
        var listAnimals = service.listAnimals(filter);
        return ResponseEntity.ok(listAnimals);
    }

    @PostMapping(value = "api/animals")
    public ResponseEntity<AnimalResponse> createAnimal(@RequestBody @Valid AnimalCreateRequest animal) {
        var create = service.createAnimal(animal);
        return ResponseEntity.ok(create);
    }

    @PutMapping(value = "api/animals/{id}")
    public ResponseEntity<AnimalResponse> updateAnimal(@PathVariable Integer id, @RequestBody @Valid AnimalUpdateRequest animalUpdateRequest) {
        var update = service.updateAnimal(id, animalUpdateRequest);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping(value = "api/animals/{id}")
    public ResponseEntity<AnimalResponse> deleteAnimal(@PathVariable Integer id) {
        var delete = service.deleteAnimal(id);
        return ResponseEntity.ok(delete);
    }

}
