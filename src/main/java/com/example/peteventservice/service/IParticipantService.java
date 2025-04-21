package com.example.peteventservice.service;

import com.example.peteventservice.dto.CreateParticipantRequest;
import com.example.peteventservice.dto.UpdateParticipantRequest;
import com.example.peteventservice.model.Participant;

import java.util.List;

public interface IParticipantService {
    List<Participant> getParticipants();
    Participant getParticipantById(Long id);
    Participant getParticipantByRut(String rut);
    Participant createParticipant(CreateParticipantRequest request);
    Participant updateParticipant(UpdateParticipantRequest request);
    void deleteParticipant(Long id);
}
