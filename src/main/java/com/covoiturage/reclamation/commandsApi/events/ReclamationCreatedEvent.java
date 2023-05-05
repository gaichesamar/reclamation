package com.covoiturage.reclamation.commandsApi.events;

import com.covoiturage.reclamation.commandsApi.enums.ReclamationStatus;
import lombok.Getter;

public class ReclamationCreatedEvent extends BaseEvent<String>{
    @Getter
    private String sujet;
    @Getter
    private String description;
    @Getter
    private ReclamationStatus status;

    public ReclamationCreatedEvent(String id,String sujet,String description,ReclamationStatus status){
        super(id);
        this.sujet=sujet;
        this.description=description;
        this.status=status;
    }
}
