package com.example.peteventservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventRequest {
    @NotBlank(message = "El nombre del evento no puede estar vacío")
    @Size(min = 1, max = 100, message = "El título debe tener entre 1 y 100 caracteres")
    private String name;
    @NotBlank(message = "La description no puede estar vacío")
    @Size(max = 255, message = "La description no debe superar los 255 caracteres")
    private String description;
    @NotNull(message = "La frcha de evento no puede estar vacío")
    private LocalDateTime dateEvent;
}
