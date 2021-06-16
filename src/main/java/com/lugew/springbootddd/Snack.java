package com.lugew.springbootddd;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 夏露桂
 * @since 2021/6/16 10:45
 */
@Getter
@Setter
public class Snack extends Entity {
    private String name;

    public Snack() {
    }

    public Snack(String name) {
        this.name = name;
    }

}
