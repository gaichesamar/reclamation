package com.covoiturage.reclamation.query.entities;

import com.covoiturage.reclamation.commandsApi.enums.ReclamationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Rclamations")

public class Reclamation {
    @Id
    private String id;
    @Column(length = 500)
    private String sujet;
    @Column(length = 500)
    private String description;
    @Enumerated(EnumType.STRING)
    private ReclamationStatus status;
}
