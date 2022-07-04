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

import br.gov.mt.intermat.projeto03.domain.model.Pagamento;
import br.gov.mt.intermat.projeto03.domain.repository.PagamentoRepository;
import br.gov.mt.intermat.projeto03.domain.service.PagamentoService;
import lombok.AllArgsConstructor;

// cria os construtures automaticamente - allargsconstructor
@AllArgsConstructor
@RestController
@RequestMapping("/pagamentos") //desta forma não necessito repetir no codigo
public class PagamentoController {

   // @Autowired     
   //caso não queira injetar, retira o autowired
   //mesmo resultado com a geração de constructor, conforme a seguire
   //
    private PagamentoRepository pagamentoRepository;
    private PagamentoService pagamentoService; 
/*
    public PagamentoController(PagamentoRepository pagamentoRepository) {
        super();
        this.pagamentoRepository = pagamentoRepository;
    }
    O construtor acima será criado com a anotação @allargsconstructor acima
**/


    @GetMapping 
    public List<Pagamento> listar (){

          return  pagamentoService.listarTudo();
         // return pagamentoRepository.findByNome("maria soares");
          // return pagamentoRepository.findByNomeContaining("taques");
    }

    @GetMapping("/{pagamentoId}")
    public ResponseEntity<Pagamento> buscar ( @PathVariable Long pagamentoId){ //binding = vincular com o path
        Pagamento obj = pagamentoService.buscar(pagamentoId);
        return ResponseEntity.ok().body(obj);


     //   return pagamentoRepository.findById(pagamentoId)
     //          .map(pagamento->ResponseEntity.ok(pagamento))
     //          .orElse(ResponseEntity.notFound().build());
  }
  //  vincular o parãmetro do método ao corpo da requisição (vide postman)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pagamento adicionar (@Valid @RequestBody Pagamento pagamento){
        return pagamentoService.salvar(pagamento);
    }
    @PutMapping("/{pagamentoId}")
    public ResponseEntity<Pagamento> atualizar ( @PathVariable Long pagamentoId, 
                 @Valid @RequestBody Pagamento pagamento){
            if(!pagamentoRepository.existsById(pagamentoId)){
                return ResponseEntity.notFound().build();
            }else {
                pagamento.setId(pagamentoId);
                pagamento = pagamentoService.salvar(pagamento);
                return ResponseEntity.ok(pagamento);
            }

    }
    @DeleteMapping("/{pagamentoId}")
    public ResponseEntity<Void> remover (@PathVariable Long pagamentoId){
        if(!pagamentoRepository.existsById(pagamentoId)){
            return ResponseEntity.notFound().build();
        }else {
            pagamentoService.excluir(pagamentoId);
            return ResponseEntity.noContent().build();
        }
    }

}
