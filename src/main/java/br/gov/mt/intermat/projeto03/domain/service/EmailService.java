package br.gov.mt.intermat.projeto03.domain.service;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import br.gov.mt.intermat.projeto03.domain.model.Pedido;

public interface EmailService {
// para email normal (texto plano) 
    void sendOrderConfirmationEmail(Pedido obj);
	void sendEmail(SimpleMailMessage msg);
// para email html
    void sendOrderConfirmationHtmlEmail(Pedido obj);
    void sendHtmlEmail(MimeMessage msg);
}
