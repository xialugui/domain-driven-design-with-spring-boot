package com.lugew.springbootddd.atm;

import org.springframework.data.repository.CrudRepository;

public interface AtmRepository extends CrudRepository<AtmDto, Long> {
}