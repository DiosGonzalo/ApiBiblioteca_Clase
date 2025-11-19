package com.salesianos.dam.apibiblioteca.models.Dtos;

import com.salesianos.dam.apibiblioteca.models.Library;

import java.util.List;

public record CreateLibraryCmd(
        String city,
        String name,
        double foundedYear,
        List<String> books,
        String description,
        String url
) {
    public Library toEntity() {
        return Library.builder()
                .books(this.books())
                .name(this.name())
                .description(this.description())
                .foundedYear(this.foundedYear())
                .city(this.city())
                .url(this.url())
                .build();

    }
}
