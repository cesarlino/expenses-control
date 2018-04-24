package com.xcontrol.model;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public final class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    String name;

    @Enumerated(EnumType.STRING)
    AccountType type;

    LocalDateTime createdAt;

    LocalDateTime closedAt;

    BigDecimal balance;

    @OneToMany(mappedBy = "account")
    Collection<Transaction> transactions;

    private Account() {
    }

    private Account(AccountType type, String name, BigDecimal initialBalance) {
        this.type = type;
        this.name = name;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
    }

    public static Account from(AccountType type, String name, BigDecimal initialBalance) {
        Assert.isTrue(type != null, "The type must not be empty");
        Assert.isTrue(!StringUtils.isEmpty(name), "The name must not be empty");
        return new Account(type, name, initialBalance);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public AccountType getType() {
        return type;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        transaction.setAccount(this);
        balance = balance.add(transaction.getAmount());
    }
}
