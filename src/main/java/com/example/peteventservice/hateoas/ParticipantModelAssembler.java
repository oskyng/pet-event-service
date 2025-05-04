package com.example.peteventservice.hateoas;

import com.example.peteventservice.controller.ParticipantController;
import com.example.peteventservice.model.Participant;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ParticipantModelAssembler implements RepresentationModelAssembler<Participant, EntityModel<Participant>> {
    @Override
    public EntityModel<Participant> toModel(Participant entity) {
        return EntityModel.of(
                entity,
                linkTo(methodOn(ParticipantController.class).getParticipantById(entity.getId())).withSelfRel(),
                linkTo(methodOn(ParticipantController.class).getParticipants()).withRel("participants"),
                linkTo(methodOn(ParticipantController.class).updateParticipant(null)).withRel("Update participant"),
                linkTo(methodOn(ParticipantController.class).deleteParticipant(entity.getId())).withRel("Delete participant")
        );
    }
}
