package theory;

/**
 * CLASE ȘI OBIECTE
 * - O clasă definește un tip de date personalizat, incluzând atribute și metode.
 */
abstract class Animal {
    protected String nume;
    public Animal(String nume) {
        this.nume = nume;
    }
    public abstract void scoateSunet();
}
class Caine extends Animal {
    public Caine(String nume) { super(nume); }
    @Override
    public void scoateSunet() { System.out.println(nume + " latră: Ham Ham!"); }
}

/**
 * MODIFICATORI DE ACCES
 * - public: accesibil oriunde
 * - private: accesibil doar în clasă
 * - protected: accesibil în pachet și subclase
 */
class ExampleClass {
    private int privateField = 10;
    protected int protectedField = 20;
    public int publicField = 30;
    int defaultField = 40;
}

/**
 * MOȘTENIRE ȘI POLIMORFISM
 * - Moștenirea permite unei clase să preia atribute și metode dintr-o superclasă.
 * - Polimorfismul permite ca o metodă să se comporte diferit în funcție de instanța apelată.
 */
class OOP {
    public static void main(String[] args) {
        Animal caine = new Caine("Rex");
        caine.scoateSunet(); // Rex latră: Ham Ham!
    }
}
