package com.lugew.domaindrivendesignwithspringboot.management;

import com.lugew.domaindrivendesignwithspringboot.atm.Atm;
import com.lugew.domaindrivendesignwithspringboot.atm.AtmDto;
import com.lugew.domaindrivendesignwithspringboot.atm.AtmRepository;
import com.lugew.domaindrivendesignwithspringboot.snackmachine.SnackMachine;
import com.lugew.domaindrivendesignwithspringboot.snackmachine.SnackMachineDto;
import com.lugew.domaindrivendesignwithspringboot.snackmachine.SnackMachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/headOffice")
public class HeadOfficeController {
    @Autowired
    private SnackMachineRepository snackMachineRepository;
    @Autowired
    private AtmRepository atmRepository;
    @Autowired
    private HeadOfficeRepository headOfficeRepository;
    @Autowired
    private HeadOfficeInstance headOfficeInstance;

    @GetMapping
    @ResponseBody
    public HeadOfficeDto getHeadOffice() {
        return headOfficeInstance.getInstance();
    }

    @PutMapping("/{atmId}/loadCash")
    @ResponseStatus(HttpStatus.OK)
    public void loadCashToAtm(@PathVariable("atmId") long atmId) {
        AtmDto atmDto = atmRepository.findById(atmId).orElse(null);
        HeadOfficeDto headOfficeDto = headOfficeInstance.getInstance();
        Atm atm = atmDto.convertToAtm();
        HeadOffice headOffice = headOfficeDto.convertToHeadOffice();
        headOffice.loadCashToAtm(atm);
        atmRepository.save(atm.convertToAtmDto());
        headOfficeRepository.save(headOffice.convertToHeadOfficeDto());
    }

    @PutMapping("/{snackMachineId}/unloadCash")
    @ResponseStatus(HttpStatus.OK)
    public void unloadCash(@PathVariable("snackMachineId") long snackMachineId) {
        SnackMachineDto snackMachineDto =
                snackMachineRepository.findById(snackMachineId).orElse(null);
        if (snackMachineDto == null) return;
        HeadOfficeDto headOfficeDto = headOfficeInstance.getInstance();
        HeadOffice headOffice = headOfficeDto.convertToHeadOffice();
        SnackMachine snackMachine = snackMachineDto.convertToSnackMachine();
        headOffice.unloadCashFromSnackMachine(snackMachine);
        snackMachineRepository.save(snackMachine.convertToSnackMachineDto());
        headOfficeRepository.save(headOffice.convertToHeadOfficeDto());
    }
}