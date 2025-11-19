package com.salesianos.dam.apibiblioteca.models.Dtos;

import java.util.List;



public record LibraryRequesDto(

        String city,
        String name,
        double foundedYear,
        List<String> books,
        String description,
        String url
) {
}
