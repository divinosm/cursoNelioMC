package br.gov.mt.intermat.projeto03.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.gov.mt.intermat.projeto03.domain.service.DbService;
import br.gov.mt.intermat.projeto03.domain.service.EmailService;
import br.gov.mt.intermat.projeto03.domain.service.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {
    @Autowired
    private DbService dbService;


    @Bean
    public boolean InstantiateDatabase() throws ParseException{
        dbService.instatiateTestDatabase();
        return true;
    }
    @Bean
	public EmailService emailService() {
		return new MockEmailService();
	}
}
