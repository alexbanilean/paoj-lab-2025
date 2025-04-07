package practice;

/**
 * Cerință:
 * 1. Creați o clasă sealed `Rezervare` cu două subclase permise: `RezervareHotel` și `RezervareAvion`.
 * 2. Creați o interfață `Validabil` cu o metodă `valideaza()`, implementată diferit în fiecare subclasă:
 *    - dacă numărul de persoane e negativ => aruncă `IllegalArgumentException`
 *    - dacă datele nu sunt completate => aruncă `CheckedCustomException`
 * 3. Creați o interfață `Persistenta` cu o metodă `salveaza()`, dar oferă o clasă adaptor `PersistentaAdaptor`
 *    care implementează metodele goale.
 * 4. Creați o clasă `ManagerRezervari` care stochează o listă de `Rezervare`, apelează `valideaza()` și tratează erorile.
 * 5. Adăugați o clasă `RezervareInterna` care încearcă să extindă `Rezervare` și observați eroarea.
 */
public class Ex5_Booking {
}
