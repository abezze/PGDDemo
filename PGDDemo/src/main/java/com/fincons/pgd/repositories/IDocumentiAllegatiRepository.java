package com.fincons.pgd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fincons.pgd.models.DocumentiAllegati;

@Repository
public interface IDocumentiAllegatiRepository extends JpaRepository<DocumentiAllegati, Long>{
	
boolean existsByNomeFileIgnoreCase(String filename);
	
	default boolean safeExistsByNomeFile(String filename) {
        return filename != null && existsByNomeFileIgnoreCase(filename);
    }

}
