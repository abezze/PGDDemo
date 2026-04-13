package com.fincons.pgd.repositories;

import com.fincons.pgd.models.Delega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDelegaRepository extends JpaRepository<Delega, Long>{

}
