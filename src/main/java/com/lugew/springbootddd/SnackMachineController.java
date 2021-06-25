package com.lugew.springbootddd;

import com.lugew.springbootddd.snackmachine.SnackMachine;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.lugew.springbootddd.snackmachine.Money.*;

/**
 * @author 夏露桂
 * @since 2021/6/10 11:55
 */
@RestController
@RequestMapping("snackmachines")
@RequiredArgsConstructor
public class SnackMachineController {
    private final SnackMachineRepository snackMachineRepository;

    @GetMapping("/{id}")
    @ResponseBody
    public SnackMachineDto getSnackMachine(@PathVariable("id") long id) {
        return snackMachineRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}/moneyInTransaction/{coinOrNote}")
    public void insertCoinOrNote(@PathVariable("id") long id, @PathVariable("coinOrNote") String coinOrNote) {
        SnackMachineDto snackMachineDto =
                snackMachineRepository.findById(id).orElse(null);
        SnackMachine snackMachine = snackMachineDto.convertToSnackMachine();
        if (coinOrNote.equalsIgnoreCase("Cent")) snackMachine.insertMoney(Cent);
        else if (coinOrNote.equalsIgnoreCase("TenCent"))
            snackMachine.insertMoney(TenCent);
        else if (coinOrNote.equalsIgnoreCase("Quarter"))
            snackMachine.insertMoney(Quarter);
        else if (coinOrNote.equalsIgnoreCase("Dollar"))
            snackMachine.insertMoney(Dollar);
        else if (coinOrNote.equalsIgnoreCase("FiveDollar"))
            snackMachine.insertMoney(FiveDollar);
        else if (coinOrNote.equalsIgnoreCase("TwentyDollar"))
            snackMachine.insertMoney(TwentyDollar);
        snackMachineRepository.save(snackMachine.convertToSnackMachineDto());
    }

    @PutMapping("/{id}/moneyInTransaction")
    public void returnMoney(@PathVariable("id") long id) {
        SnackMachineDto snackMachineDto =
                snackMachineRepository.findById(id).orElse(null);
        SnackMachine snackMachine = snackMachineDto.convertToSnackMachine();
        snackMachine.returnMoney();
        snackMachineRepository.save(snackMachine.convertToSnackMachineDto());
    }


    @PutMapping("/{id}/{slotNumber}")
    public void buySnack(@PathVariable("id") long id, @PathVariable("slotNumber")
            int slotNumber) {
        SnackMachineDto snackMachineDto =
                snackMachineRepository.findById(id).orElse(null);
        SnackMachine snackMachine = snackMachineDto.convertToSnackMachine();
        snackMachine.buySnack(slotNumber);
        snackMachineRepository.save(snackMachine.convertToSnackMachineDto());
    }

}
