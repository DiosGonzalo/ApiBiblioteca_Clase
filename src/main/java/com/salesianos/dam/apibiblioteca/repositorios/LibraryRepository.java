package com.salesianos.dam.apibiblioteca.repositorios;

import com.salesianos.dam.apibiblioteca.models.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LibraryRepository extends JpaRepository<Library,Long> {
}
