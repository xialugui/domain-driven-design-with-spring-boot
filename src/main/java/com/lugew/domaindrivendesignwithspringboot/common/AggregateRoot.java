package com.lugew.domaindrivendesignwithspringboot.common;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 夏露桂
 * @since 2021/6/22 10:07
 */
@Getter
public abstract class AggregateRoot extends Entity {
    private List<ApplicationEvent> domainEvents = new ArrayList<>();

    protected void addDomainEvent(ApplicationEvent newEvent) {
        domainEvents.add(newEvent);
    }

    public void clearEvents() {
        domainEvents.clear();
    }
}
