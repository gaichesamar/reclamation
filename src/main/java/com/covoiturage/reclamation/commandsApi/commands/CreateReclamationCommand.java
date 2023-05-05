package com.covoiturage.reclamation.commandsApi.commands;

import lombok.Getter;

public class CreateReclamationCommand extends baseCommand<String> {

    @Getter
    private String sujet;
    @Getter
    private String description;


    public CreateReclamationCommand(String id,String sujet,String description) {
        super(id);
        this.sujet=sujet;
        this.description=description;
    }
}
