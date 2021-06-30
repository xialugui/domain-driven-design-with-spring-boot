package com.lugew.domaindrivendesignwithspringboot.management;

import com.lugew.domaindrivendesignwithspringboot.common.AggregateRoot;
import com.lugew.domaindrivendesignwithspringboot.sharedkernel.Money;
import lombok.Getter;
import lombok.Setter;

import static com.lugew.domaindrivendesignwithspringboot.sharedkernel.Money.None;

@Getter
@Setter
public class HeadOffice extends AggregateRoot {
    private float balance;
    private Money cash = None;

    public void changeBalance(float delta) {
        balance += delta;
    }

    public HeadOfficeDto convertToHeadOfficeDto() {
        HeadOfficeDto headOfficeDto = new HeadOfficeDto();
        headOfficeDto.setId(id);
        headOfficeDto.setBalance(balance);
        headOfficeDto.setOneCentCount(cash.getOneCentCount());
        headOfficeDto.setTenCentCount(cash.getTenCentCount());
        headOfficeDto.setQuarterCount(cash.getQuarterCount());
        headOfficeDto.setOneDollarCount(cash.getOneDollarCount());
        headOfficeDto.setFiveDollarCount(cash.getFiveDollarCount());
        headOfficeDto.setTwentyDollarCount(cash.getTwentyDollarCount());
        return headOfficeDto;
    }
}