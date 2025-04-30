/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peonVspeon;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @argenis
 */
public class Peon extends Ficha {

    @Override
    public Ficha clone() {
        return new Peon(tipo, getX(), getY(), icon, (Rey) rey.clone(), ajedrez);
    }

    public Peon(int tipo, int x, int y, String icon, Rey rey, AjedrezApp ajedrez) {
        super(tipo, x, y, icon, rey, ajedrez);
    }

    public boolean puede(int posx, int posy) {

        if (tipo == 1 && posx == getX()
                && ((0 < posy / 60 - getY() / 60 && posy / 60 - getY() / 60 < 2)
                || (0 < posy / 60 - getY() / 60 && posy / 60 - getY() / 60 < 3 && posy / 60 == 3))) {
            return true;
        }

        if (tipo == 2 && posx == getX()
                && ((0 < getY() / 60 - posy / 60 && getY() / 60 - posy / 60 < 2)
                || (0 < getY() / 60 - posy / 60 && getY() / 60 - posy / 60 < 3 && posy / 60 == 4))) {
            return true;
        }

        return false;
    }

    public boolean come(Ficha f) {
        if (tipo == 2) {
            return (tipo != f.tipo && f.icon != "" && Math.abs(getX() / 60 - f.getX() / 60) == 1 && getY() / 60 - f.getY() / 60 == 1);
        } else {
            return (tipo != f.tipo && f.icon != "" && Math.abs(getX() / 60 - f.getX() / 60) == 1 && f.getY() / 60 - getY() / 60 == 1);
        }
    }

}
