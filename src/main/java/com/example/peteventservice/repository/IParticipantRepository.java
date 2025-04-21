package com.example.peteventservice.repository;

import com.example.peteventservice.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IParticipantRepository extends JpaRepository<Participant, Long> {
    Optional<Participant> findByRut(String rut);
    List<Participant> findByEvent_Id(Long eventId);
}
