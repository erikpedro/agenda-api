package com.github.erikpedro.agendaapi.model.api.rest;


import com.github.erikpedro.agendaapi.model.entity.Contato;
import com.github.erikpedro.agendaapi.model.repository.ContatoRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Part;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contatos")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ContatoController {

    private final ContatoRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Contato save(@RequestBody Contato contato) {
        return repository.save(contato);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        repository.deleteById(id);
    }

    @GetMapping
    public List<Contato> list() {
        return repository.findAll();
    }

    @PatchMapping("{id}/favorito")
    public void favorite(@PathVariable Integer id) {
        Optional<Contato> contato = repository.findById(id);
        contato.ifPresent(c -> {
            boolean favorito = c.getFavorito() == Boolean.TRUE;
            c.setFavorito(!favorito);
            repository.save(c);
        });
    }


    public byte[] addPhoto(@PathVariable Integer id, @RequestParam("foto") Part arquivo) {
        Optional<Contato> contato = repository.findById(id);
        return contato.map(c -> {

            try {
                InputStream in = arquivo.getInputStream();
                byte[] bytes = new byte[(int) arquivo.getSize()];
                IOUtils.readFully(in, bytes);
                c.setFoto(bytes);
                repository.save(c);
                in.close();
                return bytes;
            } catch (IOException e) {
                return null;
            }

        }).orElse(null);
    }


}
