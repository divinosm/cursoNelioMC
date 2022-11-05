package br.gov.mt.intermat.projeto03.domain.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.mt.intermat.projeto03.domain.enums.EstadoPagamento;
import br.gov.mt.intermat.projeto03.domain.model.ItemPedido;
import br.gov.mt.intermat.projeto03.domain.model.PagamentoBoleto;
import br.gov.mt.intermat.projeto03.domain.model.Pedido;
import br.gov.mt.intermat.projeto03.domain.repository.ItemPedidoRepository;
import br.gov.mt.intermat.projeto03.domain.repository.PagamentoRepository;
import br.gov.mt.intermat.projeto03.domain.repository.PedidoRepository;
import br.gov.mt.intermat.projeto03.domain.service.exceptions.ObjetcNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PedidoService{
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
	private BoletoService boletoService;

    @Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Autowired
	private ProdutoService produtoService;

    @Autowired
	private PagamentoRepository pagamentoRepository;

    @Autowired    
    private ClienteService clienteService;

    @Autowired
    private EmailService emailService;
    
    public Pedido buscar (long pedidoId){
        Optional <Pedido> pedido = pedidoRepository.findById(pedidoId);
        return pedido.orElseThrow(() -> new ObjetcNotFoundException("Objeto não encontrado! Id: " + pedidoId + ", Tipo: " + Pedido.class.getName()));
    }    
    
    public List<Pedido> listarTudo (){
        
        return  pedidoRepository.findAll();
       // return pedidoRepository.findByNome("maria soares");
        // return pedidoRepository.findByNomeContaining("taques");
  }

    @Transactional
    public Pedido salvar(Pedido obj){
        obj.setId(null);
        obj.setCliente(clienteService.buscar(obj.getCliente().getId()));
		obj.setInstante(new Date());
		obj.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoBoleto) {
			PagamentoBoleto pagto = (PagamentoBoleto) obj.getPagamento();
			boletoService.preencherPagamentoBoleto(pagto, obj.getInstante());
		}
		obj = pedidoRepository.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
            ip.setProduto(produtoService.buscar(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
        //emailService.sendOrderConfirmationEmail(obj);
        emailService.sendOrderConfirmationHtmlEmail(obj);
		return obj;           
    }

    @Transactional
    public Pedido atualizar(Pedido obj){
        buscar(obj.getId());
        return pedidoRepository.save(obj);
    }

    @Transactional
    public void excluir (Long pedidoId){
    pedidoRepository.deleteById(pedidoId);
    }
}