package com.xcontrol.model;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.Collection;

@Entity
public final class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    String name;

    @OneToOne
    Category parent;

    @OneToMany(mappedBy = "category")
    Collection<Transaction> transactions;

    private Category() {
    }

    private Category(String name, Category parent, Collection<Transaction> transactions) {
        this.name = name;
        this.parent = parent;
        this.transactions = transactions;
    }

    public static Category from(String name, Category parent, Collection<Transaction> transactions) {
        Assert.isTrue(!StringUtils.isEmpty(name), "The name must not be empty");
        Assert.isTrue(transactions != null, "The transactions must not be null");
        return new Category(name, parent, transactions);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getParent() {
        return parent;
    }

    public Collection<Transaction> getTransactions() {
        return transactions;
    }
}
