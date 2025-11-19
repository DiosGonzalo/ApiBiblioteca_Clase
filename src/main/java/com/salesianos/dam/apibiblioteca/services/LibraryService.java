package com.salesianos.dam.apibiblioteca.services;

import com.salesianos.dam.apibiblioteca.models.Dtos.CreateLibraryCmd;
import com.salesianos.dam.apibiblioteca.models.Dtos.LibraryRequesDto;
import com.salesianos.dam.apibiblioteca.models.Dtos.LibraryResponseDto;
import com.salesianos.dam.apibiblioteca.models.Library;
import com.salesianos.dam.apibiblioteca.repositorios.LibraryRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.LibraryNotFoundError;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class LibraryService {

    LibraryRepository repo;



    public List<Library> findAll(){
        List<Library> result = repo.findAll();
        if(result.isEmpty()){
            throw new LibraryNotFoundException("There are no Libraries in the list")
        }
        return repo.findAll();
    }

    public Library findById(Long id){
        return repo.findById(id).orElse(null);
    }

    public Library save(CreateLibraryCmd cmd) {

        if (!StringUtils.hasText(cmd.name())) {
            throw new IllegalArgumentException("Error al crear un monumento");
        }

        return repo.save(cmd.toEntity());
    }

    public LibraryResponseDto edit(Long id, CreateLibraryCmd cmd) {

        return repo.findById(id)
                .map(library -> {

                    library.setCity(cmd.city());
                    library.setName(cmd.name());
                    library.setFoundedYear(cmd.foundedYear());
                    library.setBooks(cmd.books());
                    library.setDescription(cmd.description());
                    library.setUrl(cmd.url());

                    Library updated = repo.save(library);

                    return repo.save(cmd);
                })
                .orElseThrow(() -> new RuntimeException("Biblioteca con id " + id + " no encontrada"));
    }

    public void delete(Library library) {
        deleteById(library.getId());
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }


}
