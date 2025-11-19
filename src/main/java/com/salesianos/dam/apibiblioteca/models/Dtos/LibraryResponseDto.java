package com.salesianos.dam.apibiblioteca.models.Dtos;

import com.salesianos.dam.apibiblioteca.models.Library;

public record LibraryResponseDto (
        Integer id,
        String city,
        String name,
        Integer foundedYear,
        Integer books,
        String description,
        String url
) {

    public static LibraryResponseDto of(Library library) {
        return new LibraryResponseDto(
                library.getId(),
                library.getCity(),
                library.getName(),
                library.getFoundedYear(),
                library.getBooks(),
                library.getDescription(),
                library.getUrl()
        );
    }
}
