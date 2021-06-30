package com.lugew.domaindrivendesignwithspringboot.atm;

import org.springframework.data.repository.CrudRepository;

public interface AtmRepository extends CrudRepository<AtmDto, Long> {
}