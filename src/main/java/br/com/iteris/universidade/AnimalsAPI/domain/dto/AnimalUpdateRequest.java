package br.com.iteris.universidade.AnimalsAPI.domain.dto;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.Date;

@Data
public class AnimalUpdateRequest {
    @NotEmpty(message = "Nome é obrigatório")
    private String name;

}
