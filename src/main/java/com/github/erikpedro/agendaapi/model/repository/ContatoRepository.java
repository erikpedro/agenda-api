package com.github.erikpedro.agendaapi.model.repository;

import com.github.erikpedro.agendaapi.model.entity.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository <Contato, Integer> {
}
