package com.xcontrol.model;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public final class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    String name;

    @OneToMany(mappedBy = "account")
    Collection<Transaction> transactions;

    private Account() {
    }

    private Account(String name) {
        this.name = name;
        this.transactions = new ArrayList<>();
    }

    public static Account from(String name) {
        Assert.isTrue(!StringUtils.isEmpty(name), "The name must not be empty");
        return new Account(name);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
