package com.covoiturage.reclamation.query.service;

import com.covoiturage.reclamation.commandsApi.events.ReclamationCreatedEvent;
import com.covoiturage.reclamation.commandsApi.events.ReclamationWaitingEvent;
import com.covoiturage.reclamation.commandsApi.queries.GetAllReclamationQuery;
import com.covoiturage.reclamation.commandsApi.queries.GetReclamationByIdQuery;
import com.covoiturage.reclamation.query.entities.Reclamation;
import com.covoiturage.reclamation.query.repositories.ReclamationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Slf4j
@Transactional
@Service
public class ReclamationServiceHandler {
    private ReclamationRepository reclamationRepository;

    @EventHandler
    public void on( ReclamationCreatedEvent event){
        log.info("************************");
        log.info("ReclamationCreatedEvent received");
        Reclamation reclamation = new Reclamation();
        reclamation.setId(event.getId());
        reclamation.setSujet(event.getSujet());
        reclamation.setDescription(event.getDescription());
        reclamation.setStatus(event.getStatus());
        reclamationRepository.save(reclamation);
    }

    @EventHandler
    public void on(ReclamationWaitingEvent event){
        log.info("************************");
        log.info("ReclamationWaitingEvent received");
        Reclamation  reclamation = reclamationRepository.findById(event.getId()).get();
        reclamation.setId(event.getId());
        reclamation.setStatus(event.getStatus());
        reclamationRepository.save(reclamation);


    }
    @QueryHandler
    public List<Reclamation> on (GetAllReclamationQuery query) {
        return reclamationRepository.findAll();
    }
    @QueryHandler
    public Reclamation on (GetReclamationByIdQuery query) {
        return  reclamationRepository.findById(query.getId()).get();
    }

}