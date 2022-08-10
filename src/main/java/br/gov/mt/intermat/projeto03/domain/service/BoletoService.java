package br.gov.mt.intermat.projeto03.domain.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;


import br.gov.mt.intermat.projeto03.domain.model.PagamentoBoleto;

@Service
public class BoletoService {

	public void preencherPagamentoBoleto(PagamentoBoleto pagto, Date instanteDoPedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDoPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataVencimento(cal.getTime());
	}
}