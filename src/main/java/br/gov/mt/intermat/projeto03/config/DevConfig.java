package br.gov.mt.intermat.projeto03.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.gov.mt.intermat.projeto03.domain.service.DbService;
import br.gov.mt.intermat.projeto03.domain.service.EmailService;
import br.gov.mt.intermat.projeto03.domain.service.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {
    @Autowired
    private DbService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;
    @Bean
    public boolean InstantiateDatabase() throws ParseException{
        if (!"create".equals(strategy)){
             return false;
        }
        dbService.instatiateTestDatabase();
        return true;
    }
    @Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
}
