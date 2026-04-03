package com.fincons.pgd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fincons.pgd.models.Utente;

@Repository
public interface IUtenteRepository extends JpaRepository<Utente, Long>{

}
