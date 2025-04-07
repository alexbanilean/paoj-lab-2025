package practice;

/**
 * Cerință:
 * 1. Definiți o interfață `Actiune` cu metodele: `executa()` și `revoca()`.
 * 2. Creați o clasă abstractă `AdaptorActiune` care oferă implementări goale (do-nothing) pentru metodele din interfață.
 * 3. Creați o clasă `StergereEveniment` care extinde `AdaptorActiune`, și implementează doar metoda `executa()`.
 * 4. Creați o interfață marker `Critica`, fără metode.
 * 5. Marcați `StergereFisier` cu această interfață.
 * 6. Scrieți o metodă care primește o listă de obiecte `Actiune` și execută doar cele care sunt și `instanceof Critica`.
 */
public class Ex1_Adapter_Marker {
}
