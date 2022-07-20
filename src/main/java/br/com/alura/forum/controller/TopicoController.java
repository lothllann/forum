package br.com.alura.forum.controller;

import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class TopicoController {

    @RequestMapping("/topicos")

    public List<TopicoDto> Lista(){
        Topico topico = new Topico("Duvida!", "Duvida de Springboot", new Curso("Springboot","Programação"));
        Topico topico2 = new Topico("Devtools", "Duvida de devtools", new Curso("Springboot","Programação"));
        Topico topico3 = new Topico("Devtools dnovo", "Duvida de devtools denovo", new Curso("Springboot","Programação"));


        return TopicoDto.converter(Arrays.asList(topico, topico2, topico3));
    }

}
