package br.gov.mt.intermat.projeto03.domain.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import br.gov.mt.intermat.projeto03.domain.dto.ClienteDto;
import br.gov.mt.intermat.projeto03.domain.model.Cliente;
import br.gov.mt.intermat.projeto03.domain.repository.ClienteRepository;
import br.gov.mt.intermat.projeto03.domain.service.exceptions.DataIntegrityException;
import br.gov.mt.intermat.projeto03.domain.service.exceptions.ObjetcNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClienteService{

    @Autowired
    private ClienteRepository clienteRepository;
    
    public Cliente buscar (long clienteId){
        Optional <Cliente> obj = clienteRepository.findById(clienteId);
        return obj.orElseThrow(() -> new ObjetcNotFoundException("Objeto não encontrado! Id: " + clienteId + ", Tipo: " + Cliente.class.getName()));
    }    
    
    public Cliente salvar(Cliente obj){
        return clienteRepository.save(obj);
    }
    //
    // recebo como entrada os dados que devo atualizar, acesso o banco de dados (newObj)
    // todos os campos e atualizo apenas aqueles que foram enviados como entrada 
    public Cliente atualizar(Cliente obj){
        Cliente newObj = buscar(obj.getId());
        atualizaDados (newObj,obj);
        return clienteRepository.save(newObj);
    }

    public void excluir (Long clienteId){
        buscar(clienteId);
            try{
                clienteRepository.deleteById(clienteId);
            }
            catch (DataIntegrityViolationException e) {
                throw new DataIntegrityException("Não é possível excluir uma cliente pois existem relacionamentos");
            }
        }

    public List<Cliente> listarTudo (){
        
            return  clienteRepository.findAll();
           // return clienteRepository.findByNome("maria soares");
            // return clienteRepository.findByNomeContaining("taques");
        } 

    public Page <Cliente> montaPagina(Integer pagina, Integer linhasPorPagina, String ordenadoPor, String direcao){
            PageRequest pageRequest = PageRequest.of(pagina, linhasPorPagina,Direction.valueOf(direcao), ordenadoPor);
            return  clienteRepository.findAll(pageRequest);
        }

    public Cliente fromDto(ClienteDto objDto){
        // vou me basear no construtor da classe e não dto
           return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(),null,null);
        }

    private void atualizaDados (Cliente newObj, Cliente obj){
        // vou inserir aqui apenas os atributos que poderão ser alterados
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }
}
