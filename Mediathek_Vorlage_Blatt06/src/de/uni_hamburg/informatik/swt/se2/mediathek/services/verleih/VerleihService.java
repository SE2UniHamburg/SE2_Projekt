package de.uni_hamburg.informatik.swt.se2.mediathek.services.verleih;

import java.util.List;

import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Datum;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Kunde;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Verleihkarte;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Vormerkkarte;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.ObservableService;

/**
 * Der VerleihService erlaubt es, Medien auszuleihen und zurückzugeben.
 * 
 * Für jedes ausgeliehene Medium wird eine neue Verleihkarte angelegt. Wird das
 * Medium wieder zurückgegeben, so wird diese Verleihkarte wieder gelöscht.
 * 
 * VerleihService ist ein ObservableService, als solcher bietet er die
 * Möglichkeit über Verleihvorgänge zu informieren. Beobachter müssen das
 * Interface ServiceObserver implementieren.
 * 
 * @author SE2-Team
 * @version SoSe 2017
 */
public interface VerleihService extends ObservableService
{
    /**
     * Prüft ob der angebene Kunde existiert. Ein Kunde existiert, wenn er im
     * Kundenstamm enthalten ist.
     * 
     * @param kunde Ein Kunde.
     * @return true wenn der Kunde existiert, sonst false.
     * 
     * @require kunde != null
     */
    boolean kundeImBestand(Kunde kunde);

    /**
     * Prüft ob das angebene Medium existiert. Ein Medium existiert, wenn es im
     * Medienbestand enthalten ist.
     * 
     * @param medium Ein Medium.
     * @return true wenn das Medium existiert, sonst false.
     * 
     * @require medium != null
     */
    boolean mediumImBestand(Medium medium);

    /**
     * Prüft ob die angebenen Medien existierien. Ein Medium existiert, wenn es
     * im Medienbestand enthalten ist.
     * 
     * @param medien Eine Liste von Medien.
     * @return true wenn die Medien existieren, sonst false.
     * 
     * @require medien != null
     * @require !medien.isEmpty()
     */
    boolean medienImBestand(List<Medium> medien);

    //Ab hier die Methoden für das Verleihen und Zurücknehmen von Medien

    /**
     * Gibt eine Liste mit allen existierenden Verleihkarten zurück.
     * 
     * @return Eine Listenkopie aller Verleihkarten. Für jedes ausgeliehene
     *         Medium existiert eine Verleihkarte. Ist kein Medium verliehen,
     *         wird eine leere Liste zurückgegeben.
     * 
     * @ensure result != null
     */
    List<Verleihkarte> getVerleihkarten();

    /**
     * Gibt die Verleihkarte für das angegebene Medium zurück, oder null wenn
     * das Medium nicht verliehen ist.
     * 
     * @param medium Ein Medium.
     * @return Die Verleihkarte für das angegebene Medium.
     * 
     * @require istVerliehen(medium)
     * 
     * @ensure (result != null)
     */
    Verleihkarte getVerleihkarteFuer(Medium medium);

    /**
     * Liefert den Entleiher des angegebenen Mediums.
     * 
     * @param medium Das Medium.
     * @return Den Entleiher des Mediums.
     * 
     * @require istVerliehen(medium)
     * 
     * @ensure result != null
     */
    Kunde getEntleiherFuer(Medium medium);

    /**
     * Gibt alle Verleihkarten für den angegebenen Kunden zurück.
     * 
     * @param kunde Ein Kunde.
     * @return Alle Verleihkarten des angebenen Kunden. Eine leere Liste, wenn
     *         der Kunde nichts entliehen hat.
     * 
     * @require kundeImBestand(kunde)
     * 
     * @ensure result != null
     */
    List<Verleihkarte> getVerleihkartenFuer(Kunde kunde);

    /**
     * Liefert alle Medien, die von dem gegebenen Kunden ausgeliehen sind.
     * 
     * @param kunde Der Kunde.
     * @return Alle Medien, die von dem gegebenen Kunden ausgeliehen sind.
     *         Liefert eine leere Liste, wenn der Kunde aktuell nichts
     *         ausgeliehen hat.
     * 
     * @require kundeImBestand(kunde)
     * 
     * @ensure result != null
     */
    List<Medium> getAusgelieheneMedienFuer(Kunde kunde);

    /**
     * Verleiht Medien an einen Kunden. Dabei wird für jedes Medium eine neue
     * Verleihkarte angelegt.
     * 
     * @param kunde Ein Kunde, an den ein Medium verliehen werden soll
     * @param medien Die Medien, die verliehen werden sollen
     * @param ausleihDatum Der erste Ausleihtag
     * 
     * @throws ProtokollierException Wenn beim Protokollieren des
     *             Verleihvorgangs ein Fehler auftritt.
     * 
     * @require kundeImBestand(kunde)
     * @require sindAlleNichtVerliehen(medien)
     * @require ausleihDatum != null
     * 
     * @ensure sindAlleVerliehenAn(kunde, medien)
     */
    void verleiheAn(Kunde kunde, List<Medium> medien, Datum ausleihDatum)
            throws ProtokollierException;

    /**
     * Prüft ob die ausgewählten Medium für den Kunde ausleihbar sind
     * 
     * @param kunde Der Kunde für den geprüft werden soll
     * @param medien Die medien
     * @return true, wenn das Entleihen für diesen Kunden möglich ist, sonst
     *         false
     * 
     * @require kundeImBestand(kunde)
     * @require medienImBestand(medien)
     */
    boolean istVerleihenMoeglich(Kunde kunde, List<Medium> medien);

    /**
     * Prüft ob alle angegebenen Medien verliehen sind.
     * 
     * @param medien Eine Liste von Medien.
     * @return true, wenn alle gegebenen Medien verliehen sind, sonst false.
     * 
     * @require medienImBestand(medien)
     */
    boolean sindAlleVerliehen(List<Medium> medien);

    /**
     * Prüft ob alle angegebenen Medien nicht verliehen sind.
     * 
     * @param medien Eine Liste von Medien.
     * @return true, wenn alle gegebenen Medien nicht verliehen sind, sonst
     *         false.
     * 
     * @require medienImBestand(medien)
     */
    boolean sindAlleNichtVerliehen(List<Medium> medien);

    /**
     * Prüft ob das angegebene Medium verliehen ist.
     * 
     * @param medium Ein Medium, für das geprüft werden soll ob es verliehen ist.
     * @return true, wenn das gegebene medium verliehen ist, sonst false.
     * 
     * @require mediumImBestand(medium)
     */
    boolean istVerliehen(Medium medium);

    /**
     * Prüft, ob alle angegebenen Medien an einen bestimmten Kunden verliehen
     * sind.
     * 
     * @param kunde Ein Kunde
     * @param medien Eine Liste von Medien
     * @return true, wenn alle Medien an den Kunden verliehen sind, sonst false.
     * 
     * @require kundeImBestand(kunde)
     * @require medienImBestand(medien)
     */
    boolean sindAlleVerliehenAn(Kunde kunde, List<Medium> medien);

    /**
     * Prüft, ob alle angegebenen Medien nicht an einen bestimmten Kunden
     * verliehen sind.
     * 
     * @param kunde Ein Kunde
     * @param medien Eine Liste von Medien
     * @return true, wenn alle Medien nicht an den Kunden verliehen sind, sonst
     *         false.
     * 
     * @require kundeImBestand(kunde)
     * @require medienImBestand(medien)
     */
    boolean sindAlleNichtVerliehenAn(Kunde kunde, List<Medium> medien);

    /**
     * Prüft, ob ein Medium an einen bestimmten Kunden verliehen ist.
     * 
     * @param kunde Ein Kunde
     * @param medium Ein Medium
     * @return true, wenn das Medium an den Kunden verliehen ist, sonst false.
     * 
     * @require kundeImBestand(kunde)
     * @require mediumImBestand(medium)
     */
    boolean istVerliehenAn(Kunde kunde, Medium medium);

    /**
     * Nimmt zuvor ausgeliehene Medien zurück. Die entsprechenden Verleihkarten
     * werden gelöscht.
     * 
     * 
     * @param medien Die Medien.
     * @param rueckgabeDatum Das Rückgabedatum.
     * 
     * @require sindAlleVerliehen(medien)
     * @require rueckgabeDatum != null
     * 
     * @ensure sindAlleNichtVerliehen(medien)
     */
    void nimmZurueck(List<Medium> medien, Datum rueckgabeDatum)
            throws ProtokollierException;

    //Ab hier die Methoden für das Vormerken von Medien

    /**
     * Gibt eine Liste mit allen existierenden Vormerkkarten zurück.
     * 
     * @return Eine Listenkopie aller Vormerkkarten. Für jedes vorgemerkte
     *         Medium existiert eine Vormerkkarte. Ist kein Medium vorgemerkt,
     *         wird eine leere Liste zurückgegeben.
     * 
     * @ensure result != null
     */
    public List<Vormerkkarte> getVormerkkarten();

    /**
     * Gibt die Vormerkkarte für das angegebene Medium zurück, oder null wenn
     * das Medium nicht vorgemerkt ist.
     * 
     * @param medium Ein Medium.
     * @return Die Vormerkkarte für das angegebene Medium.
     * 
     * @require istVorgemerkt(medium)
     * 
     * @ensure (result != null)
     */
    public Vormerkkarte getVormerkkarteFuer(Medium medium);

    /**
     * Liefert die Vormerker des angegebenen Mediums.
     * 
     * @param medium Das Medium.
     * @return Alle Vormerker, für die das gegebene Medium vorgemerkt ist,
     *         in der Reihenfolge des Vormerkens.
     * 
     * @require istVorgemerkt(medium)
     * 
     * @ensure result != null
     */
    public List<Kunde> getVormerkerFuer(Medium medium);

    /**
     * Gibt alle Vormerkkarten für den angegebenen Kunden zurück.
     * 
     * @param kunde Ein Kunde.
     * @return Alle Vormerkkarten des angebenen Kunden. Eine leere Liste, wenn
     *         der Kunde nichts vorgemerkt hat.
     * 
     * @require kundeImBestand(kunde)
     * 
     * @ensure result != null
     */
    public List<Vormerkkarte> getVormerkkartenFuer(Kunde kunde);

    /**
     * Liefert alle Medien, die für den gegebenen Kunden vorgemerkt sind.
     * 
     * @param kunde Der Kunde.
     * @return Alle Medien, die für den gegebenen Kunden vorgemerkt sind.
     *         Liefert eine leere Liste, wenn für den Kunde aktuell nichts
     *         vorgemerkt ist.
     * 
     * @require kundeImBestand(kunde)
     * 
     * @ensure result != null
     */
    public List<Medium> getVorgemerkteMedienFuer(Kunde kunde);

    /**
     * Merkt Medien für einen Kunden vor. Dabei wird für jedes Medium eine neue
     * Vormerkkarte angelegt.
     * 
     * @param kunde Ein Kunde, für den ein Medium vorgemerkt werden soll.
     * @param medien Die Medien, die vorgemerkt werden sollen.
     * 
     * @require kundeImBestand(kunde)
     * @require sindAlleNichtKomplettVorgemerkt(medien)
     * @require sindAlleNichtVerliehenAn(kunde, medien)
     * 
     * @ensure sindAlleVorgemerktFuer(kunde, medien)
     */
    public void vormerkenFuer(Kunde kunde, List<Medium> medien);

    /**
     * Prüft, ob die ausgewählten Medien für den Kunden vormerkbar sind.
     * 
     * @param kunde Der Kunde, für den geprüft werden soll.
     * @param medien Eine Liste von Medien.
     * @return true, wenn das Vormerken für diesen Kunden möglich ist, sonst
     *         false
     * 
     * @require kundeImBestand(kunde)
     * @require medienImBestand(medien)
     */
    public boolean sindAlleVormerkbar(Kunde kunde, List<Medium> medien);

    /**
     * Prüft, ob ein Medium für einen bestimmten Kunden vorgemerkt ist.
     * 
     * @param kunde Ein Kunde.
     * @param medium Ein Medium.
     * @return true, wenn das Medium für den Kunden vorgemerkt ist, sonst false.
     * 
     * @require kundeImBestand(kunde)
     * @require mediumImBestand(medium)
     */
    public boolean istVorgemerktFuer(Kunde kunde, Medium medium);

    /**
     * Prüft, ob das angegebene Medium vorgemerkt ist.
     * 
     * @param medium Ein Medium, für das geprüft werden soll ob es vorgemerkt ist.
     * @return true, wenn das gegebene Medium vorgemerkt ist, sonst false.
     * 
     * @require mediumImBestand(medium)
     */
    public boolean istVorgemerkt(Medium medium);

    /**
     * Prüft, ob alle angegebenen Medien für einen bestimmten Kunden vorgemerkt
     * sind.
     * 
     * @param kunde Ein Kunde.
     * @param medien Eine Liste von Medien.
     * @return true, wenn alle Medien für den Kunden vorgemerkt sind, sonst false.
     * 
     * @require kundeImBestand(kunde)
     * @require medienImBestand(medien)
     */
    public boolean sindAlleVorgemerktFuer(Kunde kunde, List<Medium> medien);

    /**
     * Prüft, ob alle angegebenen Medien vorgemerkt sind.
     * 
     * @param medien Eine Liste von Medien.
     * @return true, wenn alle gegebenen Medien vorgemerkt sind, sonst false.
     * 
     * @require medienImBestand(medien)
     */
    public boolean sindAlleVorgemerkt(List<Medium> medien);

    /**
     * Prüft, ob alle angegebenen Medien drei Mal vorgemerkt sind.
     * 
     * @param medien Eine Liste von Medien.
     * @return true, wenn alle gegebenen Medien drei Mal vorgemerkt sind, sonst false.
     * 
     * @require medienImBestand(medien)
     */
    public boolean sindAlleKomplettVorgemerkt(List<Medium> medien);

    /**
     * Prüft, ob alle angegebenen Medien nicht vorgemerkt sind.
     * 
     * @param medien Eine Liste von Medien.
     * @return true, wenn alle gegebenen Medien nicht vorgemerkt sind, sonst
     *         false.
     *         
     * @require medienImBestand(medien)
     */
    public boolean sindAlleNichtVorgemerkt(List<Medium> medien);

    /**
     * Prüft ob der Kunde der Erste Vormerker des Mediums ist
     * 
     * @param kunde der Potentielle erste Vormerker
     * @param medium Das Medium, dessen Vormerker kunde sein Könnte
     * 
     * @return true, wenn Kunde erster Vormerker, sonst false
     */
    public boolean istErsterVormerker(Kunde kunde, Medium medium);

}
