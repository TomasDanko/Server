package com.assignment.server.repositories;

import com.assignment.server.models.Server;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ServerRepository extends CrudRepository<Server, UUID> {
}
