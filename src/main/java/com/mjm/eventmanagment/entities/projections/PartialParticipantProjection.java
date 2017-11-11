package com.mjm.eventmanagment.entities.projections;

import com.mjm.eventmanagment.entities.Event;
import org.springframework.data.rest.core.config.Projection;


@Projection(name = "partial", types = { Event.class })
public interface PartialParticipantProjection {

	String getName();

	Boolean getCheckedIn();
}
