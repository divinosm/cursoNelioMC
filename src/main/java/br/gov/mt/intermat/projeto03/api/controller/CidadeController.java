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

import br.gov.mt.intermat.projeto03.domain.model.Cidade;
import br.gov.mt.intermat.projeto03.domain.repository.CidadeRepository;
import br.gov.mt.intermat.projeto03.domain.service.CidadeService;
import lombok.AllArgsConstructor;

// cria os construtures automaticamente - allargsconstructor
@AllArgsConstructor
@RestController
@RequestMapping("/cidades") //desta forma não necessito repetir no codigo
public class CidadeController {

   // @Autowired     
   //caso não queira injetar, retira o autowired
   //mesmo resultado com a geração de constructor, conforme a seguire
   //
    private CidadeRepository cidadeRepository;
    private CidadeService cidadeService; 
/*
    public CidadeController(CidadeRepository cidadeRepository) {
        super();
        this.cidadeRepository = cidadeRepository;
    }
    O construtor acima será criado com a anotação @allargsconstructor acima
**/


    @GetMapping 
    public List<Cidade> listar (){

          return  cidadeService.listarTudo();
         // return cidadeRepository.findByNome("maria soares");
          // return cidadeRepository.findByNomeContaining("taques");
    }

    @GetMapping("/{cidadeId}")
    public ResponseEntity<Cidade> buscar ( @PathVariable Long cidadeId){ //binding = vincular com o path
        Cidade obj = cidadeService.buscar(cidadeId);
        return ResponseEntity.ok().body(obj);


     //   return cidadeRepository.findById(cidadeId)
     //          .map(cidade->ResponseEntity.ok(cidade))
     //          .orElse(ResponseEntity.notFound().build());
  }
  //  vincular o parãmetro do método ao corpo da requisição (vide postman)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade adicionar (@Valid @RequestBody Cidade cidade){
        return cidadeService.salvar(cidade);
    }
    @PutMapping("/{cidadeId}")
    public ResponseEntity<Cidade> atualizar ( @PathVariable Long cidadeId, 
                 @Valid @RequestBody Cidade cidade){
            if(!cidadeRepository.existsById(cidadeId)){
                return ResponseEntity.notFound().build();
            }else {
                cidade.setId(cidadeId);
                cidade = cidadeService.salvar(cidade);
                return ResponseEntity.ok(cidade);
            }

    }
    @DeleteMapping("/{cidadeId}")
    public ResponseEntity<Void> remover (@PathVariable Long cidadeId){
        if(!cidadeRepository.existsById(cidadeId)){
            return ResponseEntity.notFound().build();
        }else {
            cidadeService.excluir(cidadeId);
            return ResponseEntity.noContent().build();
        }
    }

}
