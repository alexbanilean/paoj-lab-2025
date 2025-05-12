package model;

public class Participare {
    private String cnpAngajat;
    private int idProiect;
    private String rol;
    private int nrOre;

    public Participare(String cnpAngajat, int idProiect, String rol, int nrOre) {
        this.cnpAngajat = cnpAngajat;
        this.idProiect = idProiect;
        this.rol = rol;
        this.nrOre = nrOre;
    }

    // supraincarcare pentru JOIN (nume, denumire, rol, nr_ore)
    public Participare(String nume, String denumire, String rol, int nrOre) {
        this.cnpAngajat = nume;
        this.idProiect = -1;
        this.rol = rol;
        this.nrOre = nrOre;
    }

    public String getCnpAngajat() {
        return cnpAngajat;
    }

    public void setCnpAngajat(String cnpAngajat) {
        this.cnpAngajat = cnpAngajat;
    }

    public int getIdProiect() {
        return idProiect;
    }

    public void setIdProiect(int idProiect) {
        this.idProiect = idProiect;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getNrOre() {
        return nrOre;
    }

    public void setNrOre(int nrOre) {
        this.nrOre = nrOre;
    }
}
