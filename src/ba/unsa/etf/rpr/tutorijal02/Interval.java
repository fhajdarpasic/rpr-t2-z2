package ba.unsa.etf.rpr.tutorijal02;

import java.util.Objects;

public class Interval {
    private double pocetnaTacka;
    private double krajnjaTacka;
    private boolean pocetnaTackaPripada;
    private boolean krajnjaTackaPripada;

    public Interval() {

    }

    public Interval(double pocetnaTacka, double krajnjaTacka, boolean pocetnaTackaPripada, boolean krajnjaTackaPripada) {
        if(pocetnaTacka > krajnjaTacka) throw new IllegalArgumentException("Pocetna tacka veca od krajnje");
        this.pocetnaTacka = pocetnaTacka;
        this.krajnjaTacka = krajnjaTacka;
        this.pocetnaTackaPripada = pocetnaTackaPripada;
        this.krajnjaTackaPripada = krajnjaTackaPripada;
    }

    @Override
    public String toString() {
        String s = new String();
        if(isNull()) return "()";
        if(pocetnaTackaPripada) s="[";
        else s="(";
        s = s + pocetnaTacka + "," + krajnjaTacka;
        if(krajnjaTackaPripada) s = s + "]";
        else s = s+ ")";
        return s;
    }

    public boolean isIn(double testnaTacka) {
        if(testnaTacka > pocetnaTacka && testnaTacka < krajnjaTacka) return true;
        if(testnaTacka==pocetnaTacka && pocetnaTackaPripada) return true;
        if(testnaTacka==krajnjaTacka && krajnjaTackaPripada) return true;
        return false;
    }

    public boolean isNull() {
        if(pocetnaTacka-krajnjaTacka == 0) return true;
        return false;
    }

    /* Metoda instance */
    public Interval intersect(Interval i1) {
        return Interval.intersect(i1,this);
    }

    /* Staticka metoda */
    public static Interval intersect(Interval i1,Interval i2) {
        double pt=0;
        double kt=0;
        boolean ptU=false;
        boolean ktU=false;
        boolean promjena=false;
        if(i1.isIn(i2.pocetnaTacka)) {
            pt = i2.pocetnaTacka;
            ptU = i2.pocetnaTackaPripada;
            promjena=true;
        }
        else if(i2.isIn(i1.pocetnaTacka)) {
            pt = i1.pocetnaTacka;
            ptU = i1.pocetnaTackaPripada;
            promjena=true;
        }
        if(i1.isIn(i2.krajnjaTacka)) {
            kt = i2.krajnjaTacka;
            ktU = i2.krajnjaTackaPripada;
            promjena=true;
        }
        else if(i2.isIn(i1.krajnjaTacka)) {
            kt = i1.krajnjaTacka;
            ktU = i1.krajnjaTackaPripada;
            promjena=true;
        }
        if(!promjena) return new Interval();
        else return new Interval(pt,kt,ptU,ktU);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interval interval = (Interval) o;
        return Double.compare(interval.pocetnaTacka, pocetnaTacka) == 0 &&
                Double.compare(interval.krajnjaTacka, krajnjaTacka) == 0 &&
                pocetnaTackaPripada == interval.pocetnaTackaPripada &&
                krajnjaTackaPripada == interval.krajnjaTackaPripada;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pocetnaTacka, krajnjaTacka, pocetnaTackaPripada, krajnjaTackaPripada);
    }
}
