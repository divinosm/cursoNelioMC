package br.gov.mt.intermat.projeto03.api.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;


@AllArgsConstructor
@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {
    @GetMapping
    public String listar (){
          return  "rest está funcionando";
         // return pessoaRepository.findByNome("maria soares");
          // return pessoaRepository.findByNomeContaining("taques");
    }
}
