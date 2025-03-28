package com.marcelo.buscaCEP.ports.out;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.marcelo.buscaCEP.domain.entity.CepEntity;

@Repository
public interface CepRepository extends CrudRepository<CepEntity, Long> {

}
