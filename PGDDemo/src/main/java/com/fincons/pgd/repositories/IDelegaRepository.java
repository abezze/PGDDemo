package com.fincons.pgd.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fincons.pgd.models.Delega;
import com.fincons.pgd.models.Utente;

@Repository
public interface IDelegaRepository extends JpaRepository<Delega, Long>{
	
	List<Delega> findByUtenteDelegatoOrUtenteDelegante(Utente utenteDelegato, Utente utenteDelegante);

}
