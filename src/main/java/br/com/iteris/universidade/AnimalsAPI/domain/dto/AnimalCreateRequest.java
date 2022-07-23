package br.com.iteris.universidade.AnimalsAPI.domain.dto;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.Date;

@Data
public class AnimalCreateRequest {

    @NotEmpty(message = "Nome é obrigatório")
    private String name;

    @NotNull(message = "Idade é obrigatório")
    private Integer age;

    @NotEmpty(message = "Especie é obrigatório")
    private String species;

    @Past(message = "Data de nascimento deve ser anterior a data atual")
    private Date birthDate;

    @Min(value = 1, message = "Nível de fofura deve ser maior que 1")
    @Max(value = 5, message = "Nível de fofura deve ser menor que 5")
    private Integer cutenessLevel;

    @Min(value = 1, message = "Nível de fofura deve ser maior que 1")
    @Max(value = 5, message = "Nível de fofura deve ser menor que 5")
    private Integer affectionLevel;

    @Email(message = "Email invalido")
    private String email;

}
