package model;

import java.math.BigDecimal;

public class Proiect {
    private int id;
    private String denumire;
    private BigDecimal buget;

    public Proiect(int id, String denumire, BigDecimal buget) {
        this.id = id; this.denumire = denumire; this.buget = buget;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public BigDecimal getBuget() {
        return buget;
    }

    public void setBuget(BigDecimal buget) {
        this.buget = buget;
    }
}
