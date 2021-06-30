package com.lugew.domaindrivendesignwithspringboot.snackmachine;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class SnackDto {
    @Id
    private long id;
    private String name;

    public Snack convertToSnack() {
        if (id == 0) return Snack.None;
        else if (id == 1) return Snack.Chocolate;
        else if (id == 2) return Snack.Soda;
        else return Snack.Gum;
    }
}