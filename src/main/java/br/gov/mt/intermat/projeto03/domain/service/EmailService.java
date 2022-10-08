package br.gov.mt.intermat.projeto03.domain.service;

import org.springframework.mail.SimpleMailMessage;

import br.gov.mt.intermat.projeto03.domain.model.Pedido;

public interface EmailService {
    void sendOrderConfirmationEmail(Pedido obj);

	void sendEmail(SimpleMailMessage msg);
}
