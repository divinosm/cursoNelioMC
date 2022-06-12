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

import br.gov.mt.intermat.projeto03.domain.model.Estado;
import br.gov.mt.intermat.projeto03.domain.repository.EstadoRepository;
import br.gov.mt.intermat.projeto03.domain.service.EstadoService;
import lombok.AllArgsConstructor;

// cria os construtures automaticamente - allargsconstructor
@AllArgsConstructor
@RestController
@RequestMapping("/estados") //desta forma não necessito repetir no codigo
public class EstadoController {

   // @Autowired     
   //caso não queira injetar, retira o autowired
   //mesmo resultado com a geração de constructor, conforme a seguire
   //
    private EstadoRepository estadoRepository;
    private EstadoService estadoService; 
/*
    public EstadoController(EstadoRepository estadoRepository) {
        super();
        this.estadoRepository = estadoRepository;
    }
    O construtor acima será criado com a anotação @allargsconstructor acima
**/


    @GetMapping 
    public List<Estado> listar (){

          return  estadoService.listarTudo();
         // return estadoRepository.findByNome("maria soares");
          // return estadoRepository.findByNomeContaining("taques");
    }

    @GetMapping("/{estadoId}")
    public ResponseEntity<Estado> buscar ( @PathVariable Long estadoId){ //binding = vincular com o path
        Estado obj = estadoService.buscar(estadoId);
        return ResponseEntity.ok().body(obj);


     //   return estadoRepository.findById(estadoId)
     //          .map(estado->ResponseEntity.ok(estado))
     //          .orElse(ResponseEntity.notFound().build());
  }
  //  vincular o parãmetro do método ao corpo da requisição (vide postman)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado adicionar (@Valid @RequestBody Estado estado){
        return estadoService.salvar(estado);
    }
    @PutMapping("/{estadoId}")
    public ResponseEntity<Estado> atualizar ( @PathVariable Long estadoId, 
                 @Valid @RequestBody Estado estado){
            if(!estadoRepository.existsById(estadoId)){
                return ResponseEntity.notFound().build();
            }else {
                estado.setId(estadoId);
                estado = estadoService.salvar(estado);
                return ResponseEntity.ok(estado);
            }

    }
    @DeleteMapping("/{estadoId}")
    public ResponseEntity<Void> remover (@PathVariable Long estadoId){
        if(!estadoRepository.existsById(estadoId)){
            return ResponseEntity.notFound().build();
        }else {
            estadoService.excluir(estadoId);
            return ResponseEntity.noContent().build();
        }
    }

}