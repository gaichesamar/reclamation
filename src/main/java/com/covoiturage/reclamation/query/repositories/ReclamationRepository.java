package com.covoiturage.reclamation.query.repositories;

import com.covoiturage.reclamation.query.entities.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReclamationRepository  extends JpaRepository<Reclamation,String> {
}
