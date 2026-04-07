package com.fincons.pgd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fincons.pgd.models.MessageID;
import com.fincons.pgd.models.Messaggi;

public interface IMessaggiRepository extends JpaRepository<Messaggi, MessageID>{

}
