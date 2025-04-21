package com.example.peteventservice.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateParticipantRequest {
    @NotNull(message = "El ID no puede ser nulo")
    @Positive(message = "El ID debe ser un número positivo")
    private Long id;
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 1, max = 100, message = "El título debe tener entre 1 y 100 caracteres")
    private String name;
    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(min = 1, max = 100, message = "El apellido debe tener entre 1 y 100 caracteres")
    private String lastName;
    @NotBlank(message = "El RUT no puede estar vacío")
    @Size(min = 1, max = 12, message = "El RUT debe tener entre 1 y 12 caracteres")
    private String rut;
    @Email
    @NotBlank(message = "El correo no puede estar vacío")
    @Size(min = 1, max = 100, message = "El correo debe tener entre 1 y 100 caracteres")
    private String email;
    @NotNull(message = "El telefono no puede ser nulo")
    @Positive(message = "El telefono debe ser un número positivo")
    private Integer phone;
    @NotBlank(message = "La direccion no puede estar vacío")
    @Size(min = 1, max = 150, message = "La direccion debe tener entre 1 y 100 caracteres")
    private String address;
    @NotNull(message = "El evento no puede ser nulo")
    @Positive(message = "El evento debe ser un número positivo")
    private Long eventId;
}
