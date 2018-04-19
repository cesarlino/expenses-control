package com.xcontrol.repository;

import com.xcontrol.model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Default {@link Category} repository.
 */
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {}