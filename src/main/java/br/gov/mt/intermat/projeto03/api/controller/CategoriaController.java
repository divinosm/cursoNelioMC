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

import br.gov.mt.intermat.projeto03.domain.model.Categoria;
import br.gov.mt.intermat.projeto03.domain.repository.CategoriaRepository;
import br.gov.mt.intermat.projeto03.domain.service.CategoriaService;
import lombok.AllArgsConstructor;

// cria os construtures automaticamente - allargsconstructor
@AllArgsConstructor
@RestController
@RequestMapping("/categorias") //desta forma não necessito repetir no codigo
public class CategoriaController {

   // @Autowired     
   //caso não queira injetar, retira o autowired
   //mesmo resultado com a geração de constructor, conforme a seguire
   //
    private CategoriaRepository categoriaRepository;
    private CategoriaService categoriaService; 
/*
    public CategoriaController(CategoriaRepository categoriaRepository) {
        super();
        this.categoriaRepository = categoriaRepository;
    }
    O construtor acima será criado com a anotação @allargsconstructor acima
**/


    @GetMapping 
    public List<Categoria> listar (){
          return  categoriaRepository.findAll();
         // return categoriaRepository.findByNome("maria soares");
          // return categoriaRepository.findByNomeContaining("taques");
    }
    @GetMapping("/{categoriaId}")
    public ResponseEntity<Categoria> buscar ( @PathVariable Long categoriaId){ //binding = vincular com o path
        Categoria obj = categoriaService.buscar(categoriaId);
        return ResponseEntity.ok().body(obj);


     //   return categoriaRepository.findById(categoriaId)
     //          .map(categoria->ResponseEntity.ok(categoria))
     //          .orElse(ResponseEntity.notFound().build());
  }
  //  vincular o parãmetro do método ao corpo da requisição (vide postman)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Categoria adicionar (@Valid @RequestBody Categoria categoria){
        return categoriaService.salvar(categoria);
    }
    @PutMapping("/{categoriaId}")
    public ResponseEntity<Categoria> atualizar ( @PathVariable Long categoriaId, 
                 @Valid @RequestBody Categoria categoria){
            if(!categoriaRepository.existsById(categoriaId)){
                return ResponseEntity.notFound().build();
            }else {
                categoria.setId(categoriaId);
                categoria = categoriaService.salvar(categoria);
                return ResponseEntity.ok(categoria);
            }

    }
    @DeleteMapping("/{categoriaId}")
    public ResponseEntity<Void> remover (@PathVariable Long categoriaId){
        if(!categoriaRepository.existsById(categoriaId)){
            return ResponseEntity.notFound().build();
        }else {
            categoriaService.excluir(categoriaId);
            return ResponseEntity.noContent().build();
        }
    }

}