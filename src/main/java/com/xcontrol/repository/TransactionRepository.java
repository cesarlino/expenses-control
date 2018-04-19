package com.xcontrol.repository;

import com.xcontrol.model.Transaction;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Default {@link Transaction} repository.
 */
public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Long> {}