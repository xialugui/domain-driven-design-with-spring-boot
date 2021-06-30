package com.lugew.domaindrivendesignwithspringboot.management;

import com.lugew.domaindrivendesignwithspringboot.atm.BalanceChangedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BalanceChangedEventHandler implements
        ApplicationListener<BalanceChangedEvent> {
    private final HeadOfficeInstance headOfficeInstance;
    private final HeadOfficeRepository headOfficeRepository;

    @Override
    public void onApplicationEvent(BalanceChangedEvent domainEvent) {
        HeadOfficeDto headOfficeDto = headOfficeInstance.getInstance();
        HeadOffice headOffice = headOfficeDto.convertToHeadOffice();
        headOffice.changeBalance(domainEvent.getDelta());
        headOfficeRepository.save(headOffice.convertToHeadOfficeDto());
    }
}
