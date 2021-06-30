package com.lugew.domaindrivendesignwithspringboot.management;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HeadOfficeInstance {
    private final HeadOfficeRepository headOfficeRepository;
    private static final long HeadOfficeId = 1;
    private HeadOfficeDto headOfficeDto;

    public HeadOfficeDto getInstance() {
        return headOfficeRepository.findById(HeadOfficeId).orElse(null);
    }
}