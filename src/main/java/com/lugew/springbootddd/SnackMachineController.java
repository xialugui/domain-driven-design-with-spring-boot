package com.lugew.springbootddd;

import com.lugew.springbootddd.snackmachine.SnackMachine;
import org.springframework.web.bind.annotation.*;

import static com.lugew.springbootddd.snackmachine.Money.*;

/**
 * @author 夏露桂
 * @since 2021/6/10 11:55
 */
@RestController
@RequestMapping("snackmachines")
public class SnackMachineController {
    static SnackMachine snackMachine = new SnackMachine();

    @GetMapping("{id}")
    public SnackMachineDto getSnackMachine(@PathVariable("id") long id) {
        return snackMachine.convertToSnackMachineDto();
    }

    @PutMapping("{id}/moneyInTransaction/{coinOrNote}")
    public void insertCoinOrNote(@PathVariable("id") long id, @PathVariable("coinOrNote") String coinOrNote) {
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
    }

    @PutMapping("/{id}/moneyInTransaction")
    public void returnMoney(@PathVariable("id") long id) {
        snackMachine.returnMoney();
    }

    @PutMapping("/{id}/{slotNumber}")
    public void buySnack(@PathVariable("id") long id, @PathVariable("slotNumber")
            int slotNumber) {
        snackMachine.buySnack();
    }
}
