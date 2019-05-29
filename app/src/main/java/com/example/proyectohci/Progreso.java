package com.example.proyectohci;

import java.util.Date;

public class Progreso {

    private Date fecha;
    private int peso;
    private int cintura;
    private int brazoIzquierdo;
    private int brazoDerecho;
    private int piernaIzquierda;
    private int piernaDerecha;

    public Progreso(Date fecha, int peso, int cintura, int brazoIzquierdo, int brazoDerecho, int piernaIzquierda, int piernaDerecha) {
        this.fecha = fecha;
        this.peso = peso;
        this.cintura = cintura;
        this.brazoIzquierdo = brazoIzquierdo;
        this.brazoDerecho = brazoDerecho;
        this.piernaIzquierda = piernaIzquierda;
        this.piernaDerecha = piernaDerecha;
    }

    public int getCintura() {
        return cintura;
    }

    public void setCintura(int cintura) {
        this.cintura = cintura;
    }

    public int getBrazoIzquierdo() {
        return brazoIzquierdo;
    }

    public void setBrazoIzquierdo(int brazoIzquierdo) {
        this.brazoIzquierdo = brazoIzquierdo;
    }

    public int getBrazoDerecho() {
        return brazoDerecho;
    }

    public void setBrazoDerecho(int brazoDerecho) {
        this.brazoDerecho = brazoDerecho;
    }

    public int getPiernaIzquierda() {
        return piernaIzquierda;
    }

    public void setPiernaIzquierda(int piernaIzquierda) {
        this.piernaIzquierda = piernaIzquierda;
    }

    public int getPiernaDerecha() {
        return piernaDerecha;
    }

    public void setPiernaDerecha(int piernaDerecha) {
        this.piernaDerecha = piernaDerecha;
    }
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
}
