package model;

import java.math.BigDecimal;

public class Angajat {
    private String cnp;
    private String nume;
    private BigDecimal salariu;

    public Angajat(String cnp, String nume, BigDecimal salariu) {
        this.cnp = cnp; this.nume = nume; this.salariu = salariu;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public BigDecimal getSalariu() {
        return salariu;
    }

    public void setSalariu(BigDecimal salariu) {
        this.salariu = salariu;
    }
}
