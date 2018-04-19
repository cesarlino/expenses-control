package com.xcontrol.repository;

import com.xcontrol.model.Account;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Default {@link Account} repository.
 */
public interface AccountRepository extends PagingAndSortingRepository<Account, Long> {}