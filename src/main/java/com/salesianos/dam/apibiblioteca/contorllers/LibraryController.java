package com.salesianos.dam.apibiblioteca.contorllers;


import com.salesianos.dam.apibiblioteca.models.Dtos.CreateLibraryCmd;
import com.salesianos.dam.apibiblioteca.models.Dtos.LibraryRequesDto;
import com.salesianos.dam.apibiblioteca.models.Dtos.LibraryResponseDto;
import com.salesianos.dam.apibiblioteca.models.Library;
import com.salesianos.dam.apibiblioteca.services.LibraryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library/")
@RequiredArgsConstructor
@Tag(name = "Library", description = "Para realizar las operaciones de gestion de la library")
public class LibraryController {
    private final LibraryService service;

    @GetMapping
    @Operation(summary = "Endpoint para todas las librerias")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Se han encontrado bibliotecas",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = LibraryResponseDto.class)),
                            examples = @ExampleObject(value = """
                                [
                                    {
                                        "id": 1,
                                        "city": "Madrid",
                                        "name": "Biblioteca Nacional de España",
                                        "foundedYear": 1712,
                                        "books": 12,
                                        "description": "La biblioteca más importante de España",
                                        "url": "https://www.bibliotecamadrid.com"
                                    }
                                ]
                                """)
                    )
            )
    })
    public List<LibraryResponseDto> getAll(){
        return service.findAll()
                .stream()
                .map(librari -> LibraryResponseDto.of(librari))
                .toList();
    }

    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Biblioteca encontrada",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = LibraryResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Biblioteca no encontrada",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ProblemDetail.class),
                                    examples = @ExampleObject(
                                            value = """
                                                {
                                                  "type": "library-not-found",
                                                  "title": "Biblioteca no encontrada",
                                                  "status": 404,
                                                  "detail": "No se ha encontrado la biblioteca con id 1",
                                                  "instance": "/library/1"
                                                }
                                                """
                                    )
                            )
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<LibraryResponseDto> getById(@PathVariable Long id) {

        return ResponseEntity.ok(
                LibraryResponseDto.of(service.findById(id))
        );
    }


    @PostMapping
    public ResponseEntity<LibraryResponseDto> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos necesarios para crear una nueva biblioteca",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = CreateLibraryCmd.class),
                            examples = @ExampleObject(
                                    value = """
                                        {
                                            "city": "Madrid",
                                            "name": "Biblioteca Central de Madrid",
                                            "foundedYear": 1901,
                                            "books": 2000,
                                            "description": "Biblioteca histórica situada en el centro.",
                                            "url": "https://biblioteca-madrid.example.com"
                                        }
                                        """
                            )
                    )
            )
            @RequestBody CreateLibraryCmd cmd
    ) {
        Library nueva = service.save(cmd);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(LibraryResponseDto.of(nueva));
    }


    @Operation(summary = "Editar una biblioteca existente")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Biblioteca editada correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LibraryResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Biblioteca no encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class),
                            examples = @ExampleObject(
                                    value = """
                                        {
                                          "type": "library-not-found",
                                          "title": "Biblioteca no encontrada",
                                          "status": 404,
                                          "detail": "No se ha encontrado la biblioteca con id 1",
                                          "instance": "/library/1"
                                        }
                                        """
                            )
                    )
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<LibraryResponseDto> edit(
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos para editar una biblioteca existente",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = CreateLibraryCmd.class),
                            examples = @ExampleObject(
                                    value = """
                                        {
                                            "city": "Barcelona",
                                            "name": "Biblioteca Central Actualizada",
                                            "foundedYear": 1850,
                                            "books": 3500,
                                            "description": "Descripción actualizada de la biblioteca.",
                                            "url": "https://biblioteca-barcelona.example.com"
                                        }
                                        """
                            )
                    )
            )
            @RequestBody CreateLibraryCmd cmd
    ) {

        Library updated = service.edit(id, cmd);

        return ResponseEntity.ok(
                LibraryResponseDto.of(updated)
        );
    }


    @Operation(summary = "Eliminar una biblioteca por su ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "La biblioteca se eliminó correctamente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Biblioteca no encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class),
                            examples = @ExampleObject(
                                    value = """
                                        {
                                          "type": "library-not-found",
                                          "title": "Biblioteca no encontrada",
                                          "status": 404,
                                          "detail": "No se ha encontrado la biblioteca con id 5",
                                          "instance": "/library/5"
                                        }
                                        """
                            )
                    )
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        service.deleteById(id);

        return ResponseEntity.noContent().build();
    }



}
