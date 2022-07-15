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

import br.gov.mt.intermat.projeto03.domain.model.Cliente;
import br.gov.mt.intermat.projeto03.domain.service.ClienteService;
import lombok.AllArgsConstructor;

// cria os construtures automaticamente - allargsconstructor
@AllArgsConstructor
@RestController
@RequestMapping("/clientes") //desta forma não necessito repetir no codigo
public class ClienteController {

   @Autowired
    private ClienteService clienteService; 
/*
    public ClienteController(ClienteRepository clienteRepository) {
        super();
        this.clienteRepository = clienteRepository;
    }
    O construtor acima será criado com a anotação @allargsconstructor acima
**/


    @GetMapping 
    public List<Cliente> listar (){

          return  clienteService.listarTudo();
         // return clienteRepository.findByNome("maria soares");
          // return clienteRepository.findByNomeContaining("taques");
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> buscar ( @PathVariable Long clienteId){ //binding = vincular com o path
        Cliente obj = clienteService.buscar(clienteId);
        return ResponseEntity.ok().body(obj);


     //   return clienteRepository.findById(clienteId)
     //          .map(cliente->ResponseEntity.ok(cliente))
     //          .orElse(ResponseEntity.notFound().build());
  }
  //  vincular o parãmetro do método ao corpo da requisição (vide postman)


    @PostMapping
    public ResponseEntity<Void> adicionar (@Valid @RequestBody Cliente obj){
        obj = clienteService.salvar(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Void> atualizar ( @PathVariable Long clienteId, 
                 @Valid @RequestBody Cliente obj){
                    obj.setId(clienteId);
                    obj = clienteService.atualizar(obj);
            return ResponseEntity.noContent().build();// return ResponseEntity.ok(obj);
    }

    // @PutMapping("/{clienteId}")
    // public ResponseEntity<Cliente> atualizar ( @PathVariable Long clienteId, 
    //              @Valid @RequestBody Cliente cliente){
    //         if(!clienteRepository.existsById(clienteId)){
    //             return ResponseEntity.notFound().build();
    //         }else {
    //             cliente.setId(clienteId);
    //             cliente = clienteService.salvar(cliente);
    //             return ResponseEntity.ok(cliente);
    //         }

    // }
    // @DeleteMapping("/{clienteId}")
    // public ResponseEntity<Void> remover (@PathVariable Long clienteId){
    //     if(!clienteRepository.existsById(clienteId)){
    //         return ResponseEntity.notFound().build();
    //     }else {
    //         clienteService.excluir(clienteId);
    //         return ResponseEntity.noContent().build();
    //     }
    // }

}