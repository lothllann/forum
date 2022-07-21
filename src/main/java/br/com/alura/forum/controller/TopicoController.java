package br.com.alura.forum.controller;

import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository tRepository;

    @Autowired
    private CursoRepository cRepository;

   @GetMapping
    public List<TopicoDto> lista(String nomeCurso){
        if (nomeCurso == null){
        List<Topico> topicos = tRepository.findAll();
            return TopicoDto.converter(topicos);
        } else {
            List<Topico> topicos = tRepository.findByCurso_Nome(nomeCurso);
            return TopicoDto.converter(topicos);
        }
    }

    @PostMapping
    public void cadastrar(@RequestBody TopicoForm tForm){
       Topico topico = tForm.converter(cRepository);
       tRepository.save(topico);

    }

}
