/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio;

/**
 *
 * @author Familia Pabon
 */
public class Persona_Fabrica {
    
    protected int nitFabrica;
    protected int cedula;

    public Persona_Fabrica(int nitFabrica, int cedula) {
        this.nitFabrica = nitFabrica;
        this.cedula = cedula;
    }

    public int getNitFabrica() {
        return nitFabrica;
    }

    public void setNitFabrica(int nitFabrica) {
        this.nitFabrica = nitFabrica;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }    
}
