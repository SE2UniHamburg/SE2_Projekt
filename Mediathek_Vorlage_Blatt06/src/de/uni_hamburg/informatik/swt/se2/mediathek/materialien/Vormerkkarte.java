package de.uni_hamburg.informatik.swt.se2.mediathek.materialien;

import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;

/**
 * Mit Hilfe von Vormerkkarten werden beim Vormerken eines Mediums alle relevanten
 * Daten notiert.
 * 
 * Sie beantwortet die folgenden Fragen: Welches Medium wurde vorgemerkt? Wer
 * hat das Medium vorgemerkt?
 * 
 * Wenn Medien an der ersten Vormerker verliehen werden und keine weiteren Vormerker
 * mehr existieren, kann die zugehörige Vormerkkarte entsorgt werden. Um die
 * Verwaltung der Karten kümmert sich der VerleihService.
 * 
 * @author Gruppe
 * @version SoSe 2017
 */
public class Vormerkkarte
{
    
    // Eigenschaften einer Vormerkkarte
    private final Medium _medium;
    private Kunde _vormerker1;
    private Kunde _vormerker2;
    private Kunde _vormerker3;

    /**
     * Initialisert eine neue Vormerkkarte mit den gegebenen Daten.
     * 
     * @param vormerker Ein Kunde, der das Medium vormerkt.
     * @param medium Ein Medium.
     * 
     * @require vormerker != null
     * @require medium != null
     * 
     * @ensure #istVormerker() == vormerker
     * @ensure #getMedium() == medium
     */
    public Vormerkkarte(Kunde vormerker, Medium medium)
    {
        assert(medium != null):"Vorbedingung verletzt: Medium ist null";
        assert(vormerker != null):"Vorbedingung verletzt: Vormerker ist null";
        _medium = medium;
        _vormerker1 = vormerker;
    }
    
    /**
     * Gibt das Medium, dessen Vormerke auf der Karte vermerkt sind, zurück.
     * 
     * @return Das Medium, dessen Vormerke auf dieser Karte vermerkt sind.
     * 
     * @ensure result != null
     */
    public Medium getMedium()
    {
        return _medium;
    }
    
    /**
     * Gibt den Vormerker1 zurück.
     * 
     * @return Kunde, der an erster Stelle der Vormerker steht.
     */
    public Kunde getVormerker1()
    {
        return _vormerker1;
    }
    
    /**
     * Gibt den Vormerker2 zurück.
     * 
     * @return Kunde, der an zweiter Stelle der Vormerker steht.
     */
    public Kunde getVormerker2()
    {
        return _vormerker2;
    }
    
    /**
     * Gibt den Vormerker3 zurück.
     * 
     * @return Kunde, der an dritter Stelle der Vormerker steht.
     */
    public Kunde getVormerker3()
    {
        return _vormerker3;
    }
    
    /**
     * Fügt einen neuen Vormerker hinzu.
     * 
     * @require kunde != null
     * 
     */
    public void neuerVormerker(Kunde kunde)
    {
        assert(kunde != null):"Vorbedingung verletzt: Vormerker ist null";
        if(_vormerker1 == null)
        {
            _vormerker1 = kunde;
        }
        else if(_vormerker2 == null)
        {
            _vormerker2 = kunde;
        }
        else if(_vormerker3 == null)
        {
            _vormerker3 = kunde;
        }
    }
    
    
    /**
     * Entfernt den ersten Vormerker.
     */
    public void entferneVormerker1()
    {
        _vormerker1 = null;
        rueckeNach();
    }
    
    /**
     * Entfernt den zweiten Vormerker.
     */
    public void entferneVormerker2()
    {
        _vormerker2 = null;
        rueckeNach();
    }
    
    /**
     * Entfernt den dritten Vormerker.
     */
    public void entferneVormerker3()
    {
        _vormerker3 = null;
    }
    
    /**
     * Rückt die Vormerker jeweils um einen Platz nach vorne.
     */
    private void rueckeNach()
    {
        if(_vormerker2 == null)
        {
            _vormerker2 = _vormerker3;
            _vormerker3 = null;
        }
        if(_vormerker1 == null && _vormerker2 != null)
        {
            _vormerker1 = _vormerker2;
            entferneVormerker2();
        }
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((_medium == null) ? 0 : _medium.hashCode());
        result = prime * result
                + ((_vormerker1 == null) ? 0 : _vormerker1.hashCode());
        result = prime * result
                + ((_vormerker2 == null) ? 0 : _vormerker2.hashCode());
        result = prime * result
                + ((_vormerker3 == null) ? 0 : _vormerker3.hashCode());
        result = prime * result + ((_medium == null) ? 0 : _medium.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        boolean result = false;
        if (obj instanceof Vormerkkarte)
        {
            Vormerkkarte other = (Vormerkkarte) obj;

            if (other.getMedium()
                .equals(_medium)
             && other.getVormerker1()
                .equals(_vormerker1)
             && other.getVormerker2()
                .equals(_vormerker2)
             && other.getVormerker3()
                .equals(_vormerker3))

                result = true;
        }
        return result;
    }

}
