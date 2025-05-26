package practice;

/*
 * Aplicație Swing pentru Gestionarea unei Liste de Teme și Priorități
 *
 * Descriere generală:
 * Creați o aplicație desktop GUI care permite utilizatorului să adauge,
 * să vizualizeze, să modifice și să elimine teme (task-uri) cu diverse niveluri
 * de prioritate și stări de finalizare.
 *
 * Cerințe funcționale:
 *
 * Fereastra principală (JFrame):
 * - Titlu: „Task Manager Simplu”
 * - Dimensiune fixă recomandată (ex: 700x500 px)
 * - Inițializare în EDT cu SwingUtilities.invokeLater.
 *
 * Componente principale:
 * - JTable care afișează lista temelor, cu coloanele:
 *     • Descriere (String)
 *     • Prioritate (combo box în celula tabelului, valori: Scăzută, Medie, Ridicată)
 *     • Status (checkbox pentru marcat finalizat / nefinalizat)
 *     • Dată limită (JTextField cu validare format dată simplu, ex: YYYY-MM-DD)
 *
 * - Buton Adaugă Task care deschide un dialog modal cu un formular pentru introducerea unui task nou
 *   (descriere, prioritate, dată limită).
 * - Buton Șterge Task care șterge task-ul selectat în tabel.
 * - Buton Modifică Task care deschide un dialog modal pentru editarea task-ului selectat.
 *
 * Dialog de adăugare/modificare task:
 * - Câmpuri pentru descriere, prioritate (combo box), dată limită (text), stare (checkbox finalizat).
 * - Buton Salvare, Buton Anulare.
 * - Validare input (ex: descriere obligatorie, dată validă).
 *
 * Funcționalități avansate:
 * - Sortare după prioritate (implicită, temele cu prioritate ridicată să apară primele).
 * - Colorare rânduri în tabel:
 *     • Verde pentru task-uri finalizate.
 *     • Roșu pentru task-uri cu data limită depășită și nefinalizate.
 * - Filtrare simplă (ex: checkbox în interfață „Afișează doar task-urile nefinalizate”).
 *
 * Gestionarea evenimentelor:
 * - Evenimente pentru butoane, validare, modificare în tabel (editarea directă a celulelor).
 * - Folosirea corectă a EDT pentru toate modificările grafice.
 * - Actualizarea UI după fiecare modificare (refresh tabel, sortare, colorare).
 *
 * Extra:
 * - Salvarea și încărcarea listei task-urilor într-un fișier text (serializare simplă).
 * - Confirmări înainte de ștergerea unui task.
 */

public class Ex1_TaskManagerGUI {
}
