package com.github.erikpedro.agendaapi.model.api.rest;


import com.github.erikpedro.agendaapi.model.repository.ContatoRepository;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contatos")
@NoArgsConstructor
public class ContatoController {


    private final ContatoRepository repository;





}
