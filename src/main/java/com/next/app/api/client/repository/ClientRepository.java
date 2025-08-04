package com.next.app.api.client.repository;

import com.next.app.api.client.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByContactEmail(String contactEmail);

    boolean existsByContactEmail(String contactEmail);

}
