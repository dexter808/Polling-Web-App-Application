package com.spring.polls.models.repositories;

import com.spring.polls.models.entities.PEStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PEStorageRepository extends JpaRepository<PEStorage,Long> {
    boolean existsPEStorageByPollIdAndUID(Long pollId,String UID);
}
