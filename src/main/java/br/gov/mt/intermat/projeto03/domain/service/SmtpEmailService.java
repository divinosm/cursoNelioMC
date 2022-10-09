package br.gov.mt.intermat.projeto03.domain.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SmtpEmailService extends AbstractEmailService{

    // PARA INSTANCIAR COM TODOS DADOS DO EMAIL JÁ DECLARADOS PROFILE
    @Autowired
    private MailSender mailSender;

    private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);


    @Override
    public void sendEmail(SimpleMailMessage msg) {
		LOG.info("iniciando envio de email...");
		mailSender.send(msg);
		LOG.info("Email enviado");
    }
    
}
