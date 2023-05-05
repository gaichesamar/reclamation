package com.covoiturage.reclamation.commandsApi.events;

import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class BaseEvent<T> {
    @TargetAggregateIdentifier
    @Getter
    private T id;
    public BaseEvent(T id)
    {
        this.id= id;
    }
}
