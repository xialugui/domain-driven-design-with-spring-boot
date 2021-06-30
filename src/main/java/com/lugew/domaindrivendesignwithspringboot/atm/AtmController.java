package com.lugew.domaindrivendesignwithspringboot.atm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/atms")
public class AtmController {
    @Autowired
    private AtmRepository atmRepository;
    @Autowired
    private PaymentGateway paymentGateway;

    @GetMapping()
    @ResponseBody
    public List<AtmDto> getAtms() {
        List<AtmDto> list = new ArrayList<>();
        atmRepository.findAll().forEach(list::add);
        return list;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public AtmDto getAtm(@PathVariable("id") long id) {
        return atmRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}/{amount}")
    @ResponseBody
    public String takeMoney(@PathVariable("id") long id, @PathVariable("amount")
            float amount) {
        AtmDto atmDto = atmRepository.findById(id).orElse(null);
        Atm atm = atmDto.convertToAtm();
        if (!atm.canTakeMoney(amount).isEmpty()) return
                atm.canTakeMoney(amount);
        float amountWithCommission =
                atm.calculateAmountWithCommission(amount);
        paymentGateway.chargePayment(amountWithCommission);
        atm.takeMoney(amount);
        atmRepository.save(atm.convertToAtmDto());
        return "You have withrawn amount : $" + amount;
    }
}

