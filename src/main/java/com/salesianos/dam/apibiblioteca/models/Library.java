package com.salesianos.dam.apibiblioteca.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Library {

    @Id
    private Long id;
    private String city;
    private String name;
    private double foundedYear;
    private List<String> books;
    private String description;
    private String url;
}
