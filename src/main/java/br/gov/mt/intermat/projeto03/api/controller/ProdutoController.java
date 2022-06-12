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

import br.gov.mt.intermat.projeto03.domain.model.Produto;
import br.gov.mt.intermat.projeto03.domain.repository.ProdutoRepository;
import br.gov.mt.intermat.projeto03.domain.service.ProdutoService;
import lombok.AllArgsConstructor;

// cria os construtures automaticamente - allargsconstructor
@AllArgsConstructor
@RestController
@RequestMapping("/produtos") //desta forma não necessito repetir no codigo
public class ProdutoController {

   // @Autowired     
   //caso não queira injetar, retira o autowired
   //mesmo resultado com a geração de constructor, conforme a seguire
   //
    private ProdutoRepository produtoRepository;
    private ProdutoService produtoService; 
/*
    public ProdutoController(ProdutoRepository produtoRepository) {
        super();
        this.produtoRepository = produtoRepository;
    }
    O construtor acima será criado com a anotação @allargsconstructor acima
**/


    @GetMapping 
    public List<Produto> listar (){

        return  produtoService.listarTudo();
         // return produtoRepository.findByNome("maria soares");
          // return produtoRepository.findByNomeContaining("taques");
    }

    @GetMapping("/{produtoId}")
    public ResponseEntity<Produto> buscar ( @PathVariable Long produtoId){ //binding = vincular com o path
        Produto obj = produtoService.buscar(produtoId);
        return ResponseEntity.ok().body(obj);


     //   return produtoRepository.findById(produtoId)
     //          .map(produto->ResponseEntity.ok(produto))
     //          .orElse(ResponseEntity.notFound().build());
    }
  //  vincular o parãmetro do método ao corpo da requisição (vide postman)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto adicionar (@Valid @RequestBody Produto produto){
        return produtoService.salvar(produto);
    }
    @PutMapping("/{produtoId}")
    public ResponseEntity<Produto> atualizar ( @PathVariable Long produtoId, 
                @Valid @RequestBody Produto produto){
            if(!produtoRepository.existsById(produtoId)){
                return ResponseEntity.notFound().build();
            }else {
                produto.setId(produtoId);
                produto = produtoService.salvar(produto);
                return ResponseEntity.ok(produto);
            }

    }
    @DeleteMapping("/{produtoId}")
    public ResponseEntity<Void> remover (@PathVariable Long produtoId){
        if(!produtoRepository.existsById(produtoId)){
            return ResponseEntity.notFound().build();
        }else {
            produtoService.excluir(produtoId);
            return ResponseEntity.noContent().build();
        }
    }

}