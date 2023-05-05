package com.covoiturage.reclamation.commands.controllers;
import com.covoiturage.reclamation.commandsApi.commands.CreateReclamationCommand;
import com.covoiturage.reclamation.commandsApi.dtos.CreateReclamtiontRequestDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
@RestController
@RequestMapping(path = "/commands/Reclamation")
public class ReclamtionController {
    private CommandGateway commandGateway;
    private EventStore eventStore;
    private final QueryGateway queryGateway;

    public ReclamtionController(CommandGateway commandGateway, EventStore eventStore, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.eventStore = eventStore;
        this.queryGateway = queryGateway;
    }
    @PostMapping(path = "/create")
    public ResponseEntity<String> createReclamation(@RequestBody CreateReclamtiontRequestDTO request) {
        String reclamationId = UUID.randomUUID().toString();
        CreateReclamationCommand command = new CreateReclamationCommand(reclamationId, request.getSujet(), request.getDescription());
        commandGateway.sendAndWait(command);
        String message = String.format("Votre réclamation avec l'identifiant %s a été reçue et est en cours de traitement.", reclamationId);
        return ResponseEntity.ok(message);
    }
    @ExceptionHandler()
    public ResponseEntity<String> exceptionHandler(Exception exception)
    {
        ResponseEntity<String> entity= new ResponseEntity<>(
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
        return entity;
    }
    @GetMapping("/eventStore/{ReclamationId}")
    public Stream eventStore(@PathVariable String ReclamationId){
        return (Stream)eventStore.readEvents(ReclamationId).asStream();
    }
}
