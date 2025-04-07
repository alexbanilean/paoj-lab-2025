package practice;

/**
 * Cerință:
 * 1. Definiți o clasă sealed `Eveniment` care permite moștenirea doar pentru clasele `Conferinta` și `Workshop`.
 * 2. Creați clasele `Conferinta` și `Workshop`, fiecare cu un câmp: `nume` și o metodă `descriere()`.
 * 3. Creați o clasă `CursOnline` care încearcă să extindă `Eveniment` – observați eroarea de compilare.
 * 4. Creați o metodă statică care primește un `Eveniment` și apelează `descriere()` folosind pattern matching cu `instanceof`.
 * 5. Adăugați și o clasă `Webinar` care extinde `Workshop` și observă că este permis, pentru că `Workshop` nu e `sealed`.
 */
public class Ex2_Sealed {
}
