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

import br.gov.mt.intermat.projeto03.domain.model.Endereco;
import br.gov.mt.intermat.projeto03.domain.repository.EnderecoRepository;
import br.gov.mt.intermat.projeto03.domain.service.EnderecoService;
import lombok.AllArgsConstructor;

// cria os construtures automaticamente - allargsconstructor
@AllArgsConstructor
@RestController
@RequestMapping("/enderecos") //desta forma não necessito repetir no codigo
public class EnderecoController {

   // @Autowired     
   //caso não queira injetar, retira o autowired
   //mesmo resultado com a geração de constructor, conforme a seguire
   //
    private EnderecoRepository enderecoRepository;
    private EnderecoService enderecoService; 
/*
    public EnderecoController(EnderecoRepository enderecoRepository) {
        super();
        this.enderecoRepository = enderecoRepository;
    }
    O construtor acima será criado com a anotação @allargsconstructor acima
**/


    @GetMapping 
    public List<Endereco> listar (){

          return  enderecoService.listarTudo();
         // return enderecoRepository.findByNome("maria soares");
          // return enderecoRepository.findByNomeContaining("taques");
    }

    @GetMapping("/{enderecoId}")
    public ResponseEntity<Endereco> buscar ( @PathVariable Long enderecoId){ //binding = vincular com o path
        Endereco obj = enderecoService.buscar(enderecoId);
        return ResponseEntity.ok().body(obj);


     //   return enderecoRepository.findById(enderecoId)
     //          .map(endereco->ResponseEntity.ok(endereco))
     //          .orElse(ResponseEntity.notFound().build());
  }
  //  vincular o parãmetro do método ao corpo da requisição (vide postman)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Endereco adicionar (@Valid @RequestBody Endereco endereco){
        return enderecoService.salvar(endereco);
    }
    @PutMapping("/{enderecoId}")
    public ResponseEntity<Endereco> atualizar ( @PathVariable Long enderecoId, 
                 @Valid @RequestBody Endereco endereco){
            if(!enderecoRepository.existsById(enderecoId)){
                return ResponseEntity.notFound().build();
            }else {
                endereco.setId(enderecoId);
                endereco = enderecoService.salvar(endereco);
                return ResponseEntity.ok(endereco);
            }

    }
    @DeleteMapping("/{enderecoId}")
    public ResponseEntity<Void> remover (@PathVariable Long enderecoId){
        if(!enderecoRepository.existsById(enderecoId)){
            return ResponseEntity.notFound().build();
        }else {
            enderecoService.excluir(enderecoId);
            return ResponseEntity.noContent().build();
        }
    }

}