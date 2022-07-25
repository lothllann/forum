package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.DetalhesDoTopicoDto;
import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.controller.form.AtualizacaoTopicoForm;
import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
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

    @GetMapping("/{id}")
    public DetalhesDoTopicoDto detalhar(@PathVariable Long id){
       Topico topico = tRepository.getOne(id);
       return new DetalhesDoTopicoDto(topico);
    }

    @PostMapping
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm tForm, UriComponentsBuilder uriBuilder){
       Topico topico = tForm.converter(cRepository);
       tRepository.save(topico);

        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
       return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm attForm){
        Topico topico = attForm.atualizar(id,tRepository);

        return ResponseEntity.ok(new TopicoDto(topico));
    }


}
