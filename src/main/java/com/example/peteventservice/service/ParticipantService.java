package com.example.peteventservice.service;

import com.example.peteventservice.dto.CreateParticipantRequest;
import com.example.peteventservice.dto.UpdateParticipantRequest;
import com.example.peteventservice.exception.ParticipantNotFoundException;
import com.example.peteventservice.model.Participant;
import com.example.peteventservice.repository.IEventRepository;
import com.example.peteventservice.repository.IParticipantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ParticipantService implements IParticipantService {
    @Autowired
    private IParticipantRepository participantRepository;
    @Autowired
    private IEventRepository eventRepository;

    @Override
    public List<Participant> getParticipants() {
        log.debug("Servicio: getParticipants()");
        return participantRepository.findAll();
    }

    @Override
    public Participant getParticipantById(Long id) {
        log.debug("Servicio: getParticipantById({})", id);
        return participantRepository.findById(id).orElseThrow(() -> new ParticipantNotFoundException("No se encontro el participante con el id: " + id));
    }

    @Override
    public Participant getParticipantByRut(String rut) {
        log.debug("Servicio: getParticipantByRut({})", rut);
        return participantRepository.findByRut(rut).orElseThrow(() -> new ParticipantNotFoundException("No se encontro el participante con el rut: " + rut));
    }

    @Override
    public Participant createParticipant(CreateParticipantRequest request) {
        log.debug("Servicio: createParticipant({})", request.getRut());
        Participant participant = new Participant();
        participant.setRut(request.getRut());
        participant.setName(request.getName());
        participant.setEmail(request.getEmail());
        participant.setPhone(request.getPhone());
        participant.setAddress(request.getAddress());
        participant.setLastName(request.getLastName());
        participant.setEvent(eventRepository.findById(request.getEventId()).orElseThrow(() -> new ParticipantNotFoundException("No se encontro el evento con el id: " + request.getEventId())));

        return participantRepository.save(participant);
    }

    @Override
    public Participant updateParticipant(UpdateParticipantRequest request) {
        log.debug("Servicio: updateParticipant({})", request.getRut());
        Optional<Participant> foundParticipant = participantRepository.findById(request.getId());
        return foundParticipant.map(participant -> {
            participant.setRut(request.getRut() != null ? request.getRut() : participant.getRut());
            participant.setName(request.getName() != null ? request.getName() : participant.getName());
            participant.setEmail(request.getEmail() != null ? request.getEmail() : participant.getEmail());
            participant.setPhone(request.getPhone() != null ? request.getPhone() : participant.getPhone());
            participant.setAddress(request.getAddress() != null ? request.getAddress() : participant.getAddress());
            participant.setLastName(request.getLastName() != null ? request.getLastName() : participant.getLastName());
            participant.setEvent(request.getEventId() != null ? eventRepository.findById(request.getEventId()).orElseThrow(() -> new ParticipantNotFoundException("No se encontro el evento con el id: " + request.getEventId())) : participant.getEvent());
            return participantRepository.save(participant);
        }).orElseThrow(() -> new ParticipantNotFoundException("No se encontro el participante con el id: " + request.getId()));
    }

    @Override
    public void deleteParticipant(Long id) {
        log.debug("Servicio: deleteParticipant({})", id);
        Participant foundParticipant = participantRepository.findById(id).orElseThrow(() -> new ParticipantNotFoundException("No se encontro el participante con el id: " + id));
        participantRepository.delete(foundParticipant);
    }
}
