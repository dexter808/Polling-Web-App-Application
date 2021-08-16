package com.spring.polls.models.repositories;

import com.spring.polls.models.entities.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option,Long> {
}
