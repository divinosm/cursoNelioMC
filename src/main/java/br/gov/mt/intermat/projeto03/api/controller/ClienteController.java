package br.gov.mt.intermat.projeto03.api.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.gov.mt.intermat.projeto03.domain.dto.ClienteDto;
import br.gov.mt.intermat.projeto03.domain.model.Cliente;
import br.gov.mt.intermat.projeto03.domain.service.ClienteService;


@RestController
@RequestMapping("/clientes") //desta forma não necessito repetir no codigo
public class ClienteController {


    @Autowired
    private ClienteService clienteService; 

    //DTO - DATA TRANSFER OBJECT
    @GetMapping 
    public ResponseEntity<List<ClienteDto>> listar (){
        List<Cliente> lista = clienteService.listarTudo();
        List<ClienteDto> listaDTO = lista.stream()
             .map(obj -> new ClienteDto(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listaDTO);
         // return clienteRepository.findByNome("maria soares");
         // return clienteRepository.findByNomeContaining("taques");
    }

    @GetMapping ("/page")
    public ResponseEntity<Page<ClienteDto>> listarPagina (
             @RequestParam(value = "pagina", defaultValue = "0")   Integer pagina, 
             @RequestParam(value = "linhasPorPagina", defaultValue = "24")   Integer linhasPorPagina, 
             @RequestParam(value = "ordenadoPor", defaultValue = "nome") String ordenadoPor, 
             @RequestParam(value = "direcao", defaultValue = "ASC") String direcao ){ //ASC OU DESC
        Page<Cliente> lista = clienteService.montaPagina(pagina, linhasPorPagina, ordenadoPor, direcao);
        Page<ClienteDto> listaDTO = lista.map(obj -> new ClienteDto(obj));
        return ResponseEntity.ok().body(listaDTO);
         // return clienteRepository.findByNome("maria soares");
         // return clienteRepository.findByNomeContaining("taques");
    }


    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> buscar ( @PathVariable Long clienteId){ //binding = vincular com o path
        Cliente obj = clienteService.buscar(clienteId);
        return ResponseEntity.ok().body(obj);

    }
  //  vincular o parãmetro do método ao corpo da requisição (vide postman)
    @PostMapping
    public ResponseEntity<Void> adicionar (@Valid @RequestBody ClienteDto objDto){
        Cliente obj = clienteService.fromDto(objDto);
        obj = clienteService.salvar(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


    @PutMapping("/{clienteId}")
    public ResponseEntity<Void> atualizar ( @PathVariable Long clienteId, 
                 @Valid @RequestBody ClienteDto objDto){
                    Cliente obj = clienteService.fromDto(objDto);
                    obj.setId(clienteId);
                    obj = clienteService.atualizar(obj);
            return ResponseEntity.noContent().build();// return ResponseEntity.ok(obj);
    }


    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> remover (@PathVariable Long clienteId){
        clienteService.excluir(clienteId);
        return ResponseEntity.noContent().build();
        
    }

}