package com.lugew.domaindrivendesignwithspringboot.common;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 夏露桂
 * @since 2021/6/22 10:07
 */
@Getter
@Setter
public abstract class AggregateRoot extends Entity {
/*    private int version;
    private List<DomainEvent> events = new ArrayList<>();*/
}
