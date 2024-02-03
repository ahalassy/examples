package org.ahala.examples.theserver.repositories;

import org.ahala.examples.theserver.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

    AppUser findUserByEmail(String email);

}
