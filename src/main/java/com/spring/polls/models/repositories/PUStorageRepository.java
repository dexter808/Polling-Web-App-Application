package com.spring.polls.models.repositories;

import com.spring.polls.models.entities.PUStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PUStorageRepository extends JpaRepository<PUStorage,Long> {

    boolean existsPUStorageByPollIdAndUsername(Long id, String name);
}
