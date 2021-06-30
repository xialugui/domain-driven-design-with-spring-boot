package com.lugew.domaindrivendesignwithspringboot.snackmachine;

import org.springframework.data.repository.CrudRepository;

/**
 * @author 夏露桂
 * @since 2021/6/15 10:50
 */
public interface SnackMachineRepository extends CrudRepository<SnackMachineDto, Long> {
}
