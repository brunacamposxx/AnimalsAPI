package br.com.iteris.universidade.AnimalsAPI.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class AnimalResponse {
    private int idAnimal;
    private String name;
    private Integer age;
    private String species;
    private Date birthDate;
    private Integer cutenessLevel;
    private Integer affectionLevel;
    private String email;

}
