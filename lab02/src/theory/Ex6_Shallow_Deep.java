package theory;

/**
 * SHALLOW COPY VS DEEP COPY
 */
class Adresa {
    String strada;
    public Adresa(String strada) { this.strada = strada; }
}

class Persoana implements Cloneable {
    String nume;
    Adresa adresa;
    public Persoana(String nume, Adresa adresa) { this.nume = nume; this.adresa = adresa; }
    @Override
    protected Object clone() throws CloneNotSupportedException { return super.clone(); }
    public Persoana deepCopy() { return new Persoana(nume, new Adresa(adresa.strada)); }
}
