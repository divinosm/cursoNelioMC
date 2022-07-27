package br.gov.mt.intermat.projeto03.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.gov.mt.intermat.projeto03.domain.dto.CategoriaDto;
import br.gov.mt.intermat.projeto03.domain.model.Categoria;
import br.gov.mt.intermat.projeto03.domain.repository.CategoriaRepository;
import br.gov.mt.intermat.projeto03.domain.service.exceptions.DataIntegrityException;
import br.gov.mt.intermat.projeto03.domain.service.exceptions.ObjetcNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoriaService{

    @Autowired
    private CategoriaRepository categoriaRepository;
    
    public Categoria buscar (long categoriaId){
        Optional <Categoria> obj = categoriaRepository.findById(categoriaId);
        return obj.orElseThrow(() -> new ObjetcNotFoundException("Objeto não encontrado! Id: " + categoriaId + ", Tipo: " + Categoria.class.getName()));
    }    
    
    public Categoria salvar(Categoria obj){
        return categoriaRepository.save(obj);
    }
    
    public Categoria atualizar(Categoria obj){
        Categoria newObj = buscar(obj.getId());
        atualizaDados (newObj,obj);
        return categoriaRepository.save(newObj);
    }

    public void excluir (Long categoriaId){
        buscar(categoriaId);
            try{
                categoriaRepository.deleteById(categoriaId);
            }
            catch (DataIntegrityViolationException e) {
                throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
            }
        }

    public List<Categoria> listarTudo (){
        
            return  categoriaRepository.findAll();
           // return categoriaRepository.findByNome("maria soares");
            // return categoriaRepository.findByNomeContaining("taques");
        } 

    public Page <Categoria> montaPagina(Integer pagina, Integer linhasPorPagina, String ordenadoPor, String direcao){
            PageRequest pageRequest = PageRequest.of(pagina, linhasPorPagina,Direction.valueOf(direcao), ordenadoPor);
            return  categoriaRepository.findAll(pageRequest);
        }

    public Categoria fromDto(CategoriaDto objDto){
            return new Categoria(objDto.getId(), objDto.getNome());
        }

    private void atualizaDados (Categoria newObj, Categoria obj){
        // vou inserir aqui apenas os atributos que poderão ser alterados
        newObj.setNome(obj.getNome());
    }
}