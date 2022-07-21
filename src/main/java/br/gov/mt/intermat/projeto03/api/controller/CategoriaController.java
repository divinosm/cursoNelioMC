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

import br.gov.mt.intermat.projeto03.domain.dto.CategoriaDTO;
import br.gov.mt.intermat.projeto03.domain.model.Categoria;
import br.gov.mt.intermat.projeto03.domain.service.CategoriaService;
import lombok.AllArgsConstructor;

// cria os construtures automaticamente - allargsconstructor
@AllArgsConstructor
@RestController
@RequestMapping("/categorias") //desta forma não necessito repetir no codigo
public class CategoriaController {

    /*
    public CategoriaController(CategoriaRepository categoriaRepository) {
        super();
        this.categoriaRepository = categoriaRepository;
    }
    O construtor abaixo será criado com a anotação @allargsconstructor acima
**/
    @Autowired
    private CategoriaService categoriaService; 

    //DTO - DATA TRANSFER OBJECT
    @GetMapping 
    public ResponseEntity<List<CategoriaDTO>> listar (){
        List<Categoria> lista = categoriaService.listarTudo();
        List<CategoriaDTO> listaDTO = lista.stream()
             .map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listaDTO);
         // return categoriaRepository.findByNome("maria soares");
         // return categoriaRepository.findByNomeContaining("taques");
    }

    @GetMapping ("/page")
    public ResponseEntity<Page<CategoriaDTO>> listarPagina (
             @RequestParam(value = "pagina", defaultValue = "0")   Integer pagina, 
             @RequestParam(value = "linhasPorPagina", defaultValue = "24")   Integer linhasPorPagina, 
             @RequestParam(value = "ordenadoPor", defaultValue = "nome") String ordenadoPor, 
             @RequestParam(value = "direcao", defaultValue = "ASC") String direcao ){ //ASC OU DESC
        Page<Categoria> lista = categoriaService.montaPagina(pagina, linhasPorPagina, ordenadoPor, direcao);
        Page<CategoriaDTO> listaDTO = lista.map(obj -> new CategoriaDTO(obj));
        return ResponseEntity.ok().body(listaDTO);
         // return categoriaRepository.findByNome("maria soares");
         // return categoriaRepository.findByNomeContaining("taques");
    }


    @GetMapping("/{categoriaId}")
    public ResponseEntity<Categoria> buscar ( @PathVariable Long categoriaId){ //binding = vincular com o path
        Categoria obj = categoriaService.buscar(categoriaId);
        return ResponseEntity.ok().body(obj);

    }
  //  vincular o parãmetro do método ao corpo da requisição (vide postman)
    @PostMapping
    public ResponseEntity<Void> adicionar (@Valid @RequestBody Categoria obj){
        obj = categoriaService.salvar(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


    @PutMapping("/{categoriaId}")
    public ResponseEntity<Void> atualizar ( @PathVariable Long categoriaId, 
                 @Valid @RequestBody Categoria obj){
                    obj.setId(categoriaId);
                    obj = categoriaService.atualizar(obj);
            return ResponseEntity.noContent().build();// return ResponseEntity.ok(obj);
    }


    @DeleteMapping("/{categoriaId}")
    public ResponseEntity<Void> remover (@PathVariable Long categoriaId){
        categoriaService.excluir(categoriaId);
        return ResponseEntity.noContent().build();
        
    }

}