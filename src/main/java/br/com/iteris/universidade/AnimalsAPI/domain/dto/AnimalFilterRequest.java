package br.com.iteris.universidade.AnimalsAPI.domain.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AnimalFilterRequest {
    private String name;
    private Integer age;
    private String species;
    private Date birthDate;
    private Integer cutenessLevel;
    private Integer affectionLevel;
    private String email;

}
