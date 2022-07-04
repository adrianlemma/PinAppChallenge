package com.challenge.pinapp.repositories;

import com.challenge.pinapp.models.ClienteModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<ClienteModel, Long> {

}
