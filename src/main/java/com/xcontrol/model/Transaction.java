package com.xcontrol.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public final class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    String name;

    LocalDate when;

    BigDecimal amount;

    @ManyToOne
    Category category;

    @ManyToOne
    Account account;

    public Transaction() {
    }

    public Transaction(String name, LocalDate when, BigDecimal amount, Category category) {
        this.name = name;
        this.when = when;
        this.amount = amount;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public Account getAccount() {
        return account;
    }

    @JsonFormat(pattern = "dd-MM-yyyy")
    public LocalDate getWhen() {
        return when;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
