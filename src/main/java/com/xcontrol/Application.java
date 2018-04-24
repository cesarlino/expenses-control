package com.xcontrol;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.xcontrol.model.Account;
import com.xcontrol.model.AccountType;
import com.xcontrol.model.Category;
import com.xcontrol.repository.AccountRepository;
import com.xcontrol.repository.CategoryRepository;
import com.xcontrol.service.TransactionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

/**
 * Main application class which is auto configured by Spring. It adds default values in the main method
 * and sets the base URI of the REST endpoint to "/api".
 */
@Configuration
@EnableJpaRepositories
@EnableAutoConfiguration
@ComponentScan
public class Application extends RepositoryRestConfigurerAdapter {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class);
        CategoryRepository categoryRepository = context.getBean(CategoryRepository.class);
        AccountRepository accountRepository = context.getBean(AccountRepository.class);
        TransactionService transactionService = context.getBean(TransactionService.class);

        // save a couple of categories
        Category household = categoryRepository.save(Category.from("Household", null, new ArrayList<>()));
        categoryRepository.save(Category.from("Market", household, new ArrayList<>()));
        categoryRepository.save(Category.from("Internet", household, new ArrayList<>()));
        categoryRepository.save(Category.from("Health", null, new ArrayList<>()));
        Category entertainment = categoryRepository.save(Category.from("Entertainment", null, new ArrayList<>()));
        categoryRepository.save(Category.from("Dinning Out", entertainment, new ArrayList<>()));
        Category pub = categoryRepository.save(Category.from("Pub", entertainment, new ArrayList<>()));

        Account current = accountRepository.save(Account.from(AccountType.CURRENT, "Current Account", BigDecimal.ZERO));
        accountRepository.save(Account.from(AccountType.SAVINGS, "Savings Account", new BigDecimal(200.00)));

        // save a couple of items
        transactionService.registerTransaction("Lidl", LocalDate.of(2018, Month.APRIL, 10), new BigDecimal(50.00), household, current);
        transactionService.registerTransaction("Tesco", LocalDate.of(2018, Month.APRIL, 14), new BigDecimal(27.00),household, current);
        transactionService.registerTransaction("Cineworld", LocalDate.of(2018, Month.APRIL, 15), new BigDecimal(6.50), entertainment, current);
        transactionService.registerTransaction("Pit Bros", LocalDate.of(2018, Month.APRIL, 15), new BigDecimal(45.00), entertainment, current);
        transactionService.registerTransaction("Schoolhouse", LocalDate.of(2018, Month.APRIL, 13), new BigDecimal(11.00), pub, current);
        transactionService.registerTransaction("Ginger man", LocalDate.of(2018, Month.APRIL, 13), new BigDecimal(10.00), pub, current);
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.setBasePath("/api");
    }

    @Override
    public void configureJacksonObjectMapper(ObjectMapper objectMapper) {
        super.configureJacksonObjectMapper(objectMapper);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }
}