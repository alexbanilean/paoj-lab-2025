package theory;

/**
 * REFERINȚE
 * - O referință este o variabilă care conține adresa unui obiect stocat în heap.
 * - O atribuție între două referințe nu creează o copie a obiectului, ci doar o nouă referință către același obiect.
 */
class Ex1_References {
    static class Persoana {
        String nume;
        Persoana(String nume) {
            this.nume = nume;
        }
    }
    public static void main(String[] args) {
        Persoana p1 = new Persoana("Ion");
        Persoana p2 = p1; // p2 referă același obiect ca p1
        p2.nume = "Maria";
        System.out.println(p1.nume); // Maria
    }
}