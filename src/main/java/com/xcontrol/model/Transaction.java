package com.xcontrol.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

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

    private Transaction() {
    }

    private Transaction(String name, LocalDate when, BigDecimal amount, Category category, Account account) {
        this.name = name;
        this.when = when;
        this.amount = amount;
        this.category = category;
        this.account = account;
    }

    public static Transaction from(String name, LocalDate when, BigDecimal amount, Category category, Account account) {
        Assert.isTrue(!StringUtils.isEmpty(name), "The name must not be empty");
        Assert.isTrue(!StringUtils.isEmpty(name), "The amount must not be empty");
        Assert.notNull(account, "The account must not be empty");
        return new Transaction(name, Optional.ofNullable(when).orElse(LocalDate.now()), amount, category, account);
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
}
