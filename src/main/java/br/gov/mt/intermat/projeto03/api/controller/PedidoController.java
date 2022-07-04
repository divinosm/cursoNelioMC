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

import br.gov.mt.intermat.projeto03.domain.model.Pedido;
import br.gov.mt.intermat.projeto03.domain.repository.PedidoRepository;
import br.gov.mt.intermat.projeto03.domain.service.PedidoService;
import lombok.AllArgsConstructor;

// cria os construtures automaticamente - allargsconstructor
@AllArgsConstructor
@RestController
@RequestMapping("/pedidos") //desta forma não necessito repetir no codigo
public class PedidoController {

   // @Autowired     
   //caso não queira injetar, retira o autowired
   //mesmo resultado com a geração de constructor, conforme a seguire
   //
    private PedidoRepository pedidoRepository;
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
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido adicionar (@Valid @RequestBody Pedido pedido){
        return pedidoService.salvar(pedido);
    }
    @PutMapping("/{pedidoId}")
    public ResponseEntity<Pedido> atualizar ( @PathVariable Long pedidoId, 
                 @Valid @RequestBody Pedido pedido){
            if(!pedidoRepository.existsById(pedidoId)){
                return ResponseEntity.notFound().build();
            }else {
                pedido.setId(pedidoId);
                pedido = pedidoService.salvar(pedido);
                return ResponseEntity.ok(pedido);
            }

    }
    @DeleteMapping("/{pedidoId}")
    public ResponseEntity<Void> remover (@PathVariable Long pedidoId){
        if(!pedidoRepository.existsById(pedidoId)){
            return ResponseEntity.notFound().build();
        }else {
            pedidoService.excluir(pedidoId);
            return ResponseEntity.noContent().build();
        }
    }

}