package com.example.peteventservice.controller;

import com.example.peteventservice.dto.CreateParticipantRequest;
import com.example.peteventservice.dto.ResponseWrapper;
import com.example.peteventservice.dto.UpdateParticipantRequest;
import com.example.peteventservice.hateoas.ParticipantModelAssembler;
import com.example.peteventservice.model.Participant;
import com.example.peteventservice.service.IParticipantService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@RestController
@RequestMapping("/participant")
public class ParticipantController {
    private final IParticipantService participantService;
    private final ParticipantModelAssembler participantModelAssembler;

    public ParticipantController(IParticipantService participantService, ParticipantModelAssembler participantModelAssembler) {
        this.participantService = participantService;
        this.participantModelAssembler = participantModelAssembler;
    }

    @GetMapping
    public ResponseEntity<?> getParticipants() {
        log.info("GET /participant - Obteniendo todos los participantes");
        List<EntityModel<Participant>> participants = participantService.getParticipants().stream().map(participantModelAssembler::toModel).collect(Collectors.toList());
        return ResponseEntity.ok(CollectionModel.of(participants, linkTo(methodOn(ParticipantController.class).getParticipants()).withSelfRel()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getParticipantById(@PathVariable Long id) {
        log.info("GET /participant/{} - Obteniendo el participante con id: {}", id);
        return ResponseEntity.ok(participantModelAssembler.toModel(participantService.getParticipantById(id)));
    }

    @GetMapping("/rut/{rut}")
    public ResponseEntity<?> getParticipantByRut(@PathVariable String rut) {
        log.info("GET /participant/rut/{} - Obteniendo el participante con rut: {}", rut);
        return ResponseEntity.ok(participantModelAssembler.toModel(participantService.getParticipantByRut(rut)));
    }

    @PostMapping
    public ResponseEntity<?> createParticipant(@Valid @RequestBody CreateParticipantRequest request) {
        log.info("POST / participant - Creando el participante: {}", request.getRut());
        return ResponseEntity.status(HttpStatus.CREATED).body(participantModelAssembler.toModel(participantService.createParticipant(request)));
    }

    @PutMapping
    public ResponseEntity<?> updateParticipant(@Valid @RequestBody UpdateParticipantRequest request) {
        log.info("PUT /participant - Actualizando el participante: {}", request.getRut());
        return ResponseEntity.ok(participantModelAssembler.toModel(participantService.updateParticipant(request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteParticipant(@PathVariable Long id) {
        log.info("DELETE /participant/{} - Eliminando el participante con id: {}", id);
        participantService.deleteParticipant(id);
        return ResponseEntity.ok(new ResponseWrapper<>(HttpStatus.OK.value(), "Participante eliminado exitosamente", 1, null));
    }
}
