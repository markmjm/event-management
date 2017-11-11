package com.mjm.eventmanagment.controllers;

import com.mjm.eventmanagment.entities.Participant;
import com.mjm.eventmanagment.repos.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RepositoryRestController
@RequestMapping("/events")
public class CheckOutController {

    @Autowired
    private ParticipantRepository participantRepository;

    @PutMapping("/checkout/{id}")
    public ResponseEntity<PersistentEntityResource> checkIn(@PathVariable Long id, PersistentEntityResourceAssembler assembler)  {

        //PersistentEntityResourceAssembler is to convert entity into HAL resouce
        Participant participant = participantRepository.findOne(id);

        if (participant != null) {
            if (participant.getCheckedIn()) {
                throw new AlreadyCheckedInException();
            }
            participant.setCheckedIn(false);
            participantRepository.save(participant);
        }
        return ResponseEntity.ok( assembler.toResource(participant));
    }
}


