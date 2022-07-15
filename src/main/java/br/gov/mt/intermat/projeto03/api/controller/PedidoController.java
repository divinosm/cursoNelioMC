package br.gov.mt.intermat.projeto03.api.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.gov.mt.intermat.projeto03.domain.model.Pedido;
import br.gov.mt.intermat.projeto03.domain.service.PedidoService;
import lombok.AllArgsConstructor;

// cria os construtures automaticamente - allargsconstructor
@AllArgsConstructor
@RestController
@RequestMapping("/pedidos") //desta forma não necessito repetir no codigo
public class PedidoController {

    @Autowired
    private PedidoService pedidoService; 
/*
    public PedidoController(PedidoRepository pedidoRepository) {
        super();
        this.pedidoRepository = pedidoRepository;
    }
    O construtor acima será criado com a anotação @allargsconstructor acima
**/


    @GetMapping 
    public List<Pedido> listar (){

          return  pedidoService.listarTudo();
         // return pedidoRepository.findByNome("maria soares");
          // return pedidoRepository.findByNomeContaining("taques");
    }

    @GetMapping("/{pedidoId}")
    public ResponseEntity<Pedido> buscar ( @PathVariable Long pedidoId){ //binding = vincular com o path
        Pedido obj = pedidoService.buscar(pedidoId);
        return ResponseEntity.ok().body(obj);


     //   return pedidoRepository.findById(pedidoId)
     //          .map(pedido->ResponseEntity.ok(pedido))
     //          .orElse(ResponseEntity.notFound().build());
  }
  //  vincular o parãmetro do método ao corpo da requisição (vide postman)

    @PostMapping
    public ResponseEntity<Void> adicionar (@Valid @RequestBody Pedido obj){
        obj = pedidoService.salvar(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{pedidoId}")
    public ResponseEntity<Void> atualizar ( @PathVariable Long pedidoId, 
                 @Valid @RequestBody Pedido obj){
                    obj.setId(pedidoId);
                    obj = pedidoService.atualizar(obj);
            return ResponseEntity.noContent().build();// return ResponseEntity.ok(obj);
    }


    // @PutMapping("/{pedidoId}")
    // public ResponseEntity<Pedido> atualizar ( @PathVariable Long pedidoId, 
    //              @Valid @RequestBody Pedido pedido){
    //         if(!pedidoRepository.existsById(pedidoId)){
    //             return ResponseEntity.notFound().build();
    //         }else {
    //             pedido.setId(pedidoId);
    //             pedido = pedidoService.salvar(pedido);
    //             return ResponseEntity.ok(pedido);
    //         }

    // }
    // @DeleteMapping("/{pedidoId}")
    // public ResponseEntity<Void> remover (@PathVariable Long pedidoId){
    //     if(!pedidoRepository.existsById(pedidoId)){
    //         return ResponseEntity.notFound().build();
    //     }else {
    //         pedidoService.excluir(pedidoId);
    //         return ResponseEntity.noContent().build();
    //     }
    // }

}