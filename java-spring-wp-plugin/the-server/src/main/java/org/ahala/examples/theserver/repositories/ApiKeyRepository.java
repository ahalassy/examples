package org.ahala.examples.theserver.repositories;

import org.ahala.examples.theserver.entities.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {

    ApiKey findById(long id);

}
