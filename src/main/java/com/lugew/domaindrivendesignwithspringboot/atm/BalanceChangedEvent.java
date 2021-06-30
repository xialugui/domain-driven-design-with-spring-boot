package com.lugew.domaindrivendesignwithspringboot.atm;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class BalanceChangedEvent extends ApplicationEvent {
    public float delta;

    public BalanceChangedEvent(Object source, float delta) {
        super(source);
        this.delta = delta;
    }
}