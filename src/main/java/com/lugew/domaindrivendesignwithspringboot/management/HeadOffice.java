package com.lugew.domaindrivendesignwithspringboot.management;

import com.lugew.domaindrivendesignwithspringboot.common.AggregateRoot;
import com.lugew.domaindrivendesignwithspringboot.sharedkernel.Money;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HeadOffice extends AggregateRoot {
    private float balance;
    private Money cash;
}