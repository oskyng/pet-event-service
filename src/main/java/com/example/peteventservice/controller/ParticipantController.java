package com.example.peteventservice.controller;

import com.example.peteventservice.dto.CreateParticipantRequest;
import com.example.peteventservice.dto.ResponseWrapper;
import com.example.peteventservice.dto.UpdateParticipantRequest;
import com.example.peteventservice.model.Participant;
import com.example.peteventservice.service.IParticipantService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/participant")
public class ParticipantController {
    private final IParticipantService participantService;

    public ParticipantController(IParticipantService participantService) {
        this.participantService = participantService;
    }

    @GetMapping
    public ResponseEntity<?> getParticipants() {
        log.info("GET /participant - Obteniendo todos los participantes");
        return ResponseEntity.ok(new ResponseWrapper<>(HttpStatus.OK.value(), "OK", participantService.getParticipants().size(), participantService.getParticipants()));
    }

    @GetMapping("/{id}")
    public Participant getParticipantById(@PathVariable Long id) {
        log.info("GET /participant/{} - Obteniendo el participante con id: {}", id);
        return participantService.getParticipantById(id);
    }

    @GetMapping("/rut/{rut}")
    public Participant getParticipantByRut(@PathVariable String rut) {
        log.info("GET /participant/rut/{} - Obteniendo el participante con rut: {}", rut);
        return participantService.getParticipantByRut(rut);
    }

    @PostMapping
    public ResponseEntity<?> createParticipant(@Valid @RequestBody CreateParticipantRequest request) {
        log.info("POST / participant - Creando el participante: {}", request.getRut());
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper<>(HttpStatus.CREATED.value(), "Participante creado exitosamente", 1, List.of(participantService.createParticipant(request))));
    }

    @PutMapping
    public ResponseEntity<?> updateParticipant(@Valid @RequestBody UpdateParticipantRequest request) {
        log.info("PUT /participant - Actualizando el participante: {}", request.getRut());
        return ResponseEntity.ok(new ResponseWrapper<>(HttpStatus.OK.value(), "Participante actualizado exitosamente", 1, List.of(participantService.updateParticipant(request))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteParticipant(@PathVariable Long id) {
        log.info("DELETE /participant/{} - Eliminando el participante con id: {}", id);
        participantService.deleteParticipant(id);
        return ResponseEntity.ok(new ResponseWrapper<>(HttpStatus.OK.value(), "Participante eliminado exitosamente", 1, null));
    }
}
