package com.xcontrol.service;

import com.xcontrol.model.Account;
import com.xcontrol.model.Category;
import com.xcontrol.model.Transaction;
import com.xcontrol.repository.AccountRepository;
import com.xcontrol.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Component
public class TransactionService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction registerTransaction(String name, LocalDate when, BigDecimal amount, Category category, Account account) {
        Assert.isTrue(!StringUtils.isEmpty(name), "The name must not be empty");
        Assert.isTrue(!StringUtils.isEmpty(name), "The amount must not be empty");
        Assert.notNull(account, "The account must not be empty");

        Transaction tx = new Transaction(name, Optional.ofNullable(when).orElse(LocalDate.now()), amount, category);
        account.addTransaction(tx);

        transactionRepository.save(tx);
        accountRepository.save(account);

        return tx;
    }
}
