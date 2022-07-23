package br.com.iteris.universidade.AnimalsAPI.repository;

import br.com.iteris.universidade.AnimalsAPI.domain.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// ref: https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html
@Repository
public interface AnimalsRepository extends JpaRepository<Animal, Integer> {
    Optional<Animal> findByNameContaining(String name);


    // ref: https://www.baeldung.com/spring-data-derived-queries
    @Query(
            nativeQuery = true,
            value = "SELECT * FROM animal WHERE (:name IS NULL OR name = :name)"
    )
    List<Animal> listarComFiltroNativo(@Param("name") String name);
}
// 4. Dentro desse package crie uma nova interface chamada AnimalRepository.:
// a. Repare na notação @Repository, ela diz que nossa classe faz parte do contexto de injeção de
// dependencia do Spring
// b. Estendendo a classe JpaRepository recebemos diversos métodos úteis já implementados pelo JPA