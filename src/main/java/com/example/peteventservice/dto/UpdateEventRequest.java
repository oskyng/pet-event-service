package com.example.peteventservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UpdateEventRequest {
    @NotNull(message = "El ID no puede ser nulo")
    @Positive(message = "El ID debe ser un número positivo")
    private Long id;
    @NotBlank(message = "El nombre del evento no puede estar vacío")
    @Size(min = 1, max = 100, message = "El título debe tener entre 1 y 100 caracteres")
    private String name;
    @NotBlank(message = "La description no puede estar vacío")
    @Size(max = 255, message = "La description no debe superar los 255 caracteres")
    private String description;
    @NotNull(message = "La frcha de evento no puede estar vacío")
    private LocalDateTime dateEvent;
}
