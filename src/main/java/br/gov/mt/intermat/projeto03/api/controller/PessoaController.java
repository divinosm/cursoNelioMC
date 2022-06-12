package br.gov.mt.intermat.projeto03.api.controller;

import java.util.List;
import javax.validation.Valid;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gov.mt.intermat.projeto03.domain.model.Pessoa;
import br.gov.mt.intermat.projeto03.domain.repository.PessoaRepository;
import br.gov.mt.intermat.projeto03.domain.service.PessoaService;
import lombok.AllArgsConstructor;

// cria os construtures automaticamente - allargsconstructor
@AllArgsConstructor
@RestController
@RequestMapping("/pessoas") //desta forma não necessito repetir no codigo
public class PessoaController {

   // @Autowired     
   //caso não queira injetar, retira o autowired
   //mesmo resultado com a geração de constructor, conforme a seguire
   //
    private PessoaRepository pessoaRepository;
    private PessoaService pessoaService; 
/*
    public PessoaController(PessoaRepository pessoaRepository) {
        super();
        this.pessoaRepository = pessoaRepository;
    }
    O construtor acima será criado com a anotação @allargsconstructor acima
**/


    @GetMapping 
    public List<Pessoa> listar (){
        return  pessoaRepository.findAll();
         // return pessoaRepository.findByNome("maria soares");
          // return pessoaRepository.findByNomeContaining("taques");
    }
    @GetMapping("/{pessoaId}")
    public ResponseEntity<Pessoa> buscar ( @PathVariable Long pessoaId){ //binding = vincular com o path
        return pessoaRepository.findById(pessoaId)
            .map(pessoa->ResponseEntity.ok(pessoa))
            .orElse(ResponseEntity.notFound().build());
    }
  //  vincular o parãmetro do método ao corpo da requisição (vide postman)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pessoa adicionar (@Valid @RequestBody Pessoa pessoa){
        return pessoaService.salvar(pessoa);
    }
    @PutMapping("/{pessoaId}")
    public ResponseEntity<Pessoa> atualizar ( @PathVariable Long pessoaId, 
            @Valid @RequestBody Pessoa pessoa){
            if(!pessoaRepository.existsById(pessoaId)){
                return ResponseEntity.notFound().build();
            }else {
                pessoa.setId(pessoaId);
                pessoa = pessoaService.salvar(pessoa);
                return ResponseEntity.ok(pessoa);
            }

    }
    @DeleteMapping("/{pessoaId}")
    public ResponseEntity<Void> remover (@PathVariable Long pessoaId){
        if(!pessoaRepository.existsById(pessoaId)){
            return ResponseEntity.notFound().build();
        }else {
            pessoaService.excluir(pessoaId);
            return ResponseEntity.noContent().build();
        }
    }

}
