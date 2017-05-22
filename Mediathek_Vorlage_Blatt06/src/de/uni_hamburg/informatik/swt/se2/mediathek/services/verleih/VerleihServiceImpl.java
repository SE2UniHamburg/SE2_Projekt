package de.uni_hamburg.informatik.swt.se2.mediathek.services.verleih;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Datum;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Kunde;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Verleihkarte;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Vormerkkarte;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.AbstractObservableService;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.kundenstamm.KundenstammService;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.medienbestand.MedienbestandService;

/**
 * Diese Klasse implementiert das Interface VerleihService. Siehe dortiger
 * Kommentar.
 * 
 * @author SE2-Team
 * @version SoSe 2017
 */
public class VerleihServiceImpl extends AbstractObservableService
        implements VerleihService
{
    /**
     * Diese Map speichert für jedes eingefügte Medium die dazugehörige
     * Verleihkarte. Ein Zugriff auf die Verleihkarte ist dadurch leicht über
     * die Angabe des Mediums möglich. Beispiel: _verleihkarten.get(medium)
     */
    private Map<Medium, Verleihkarte> _verleihkarten;

    /**
     * Diese Map speichert für jedes eingefügte Medium die dazugehörige
     * Vormerkkarte. Ein Zugriff auf die Vormerkkarte ist dadurch leicht über
     * die Angabe des Mediums möglich. Beispiel: _vormerkkarten.get(medium)
     */
    private Map<Medium, Vormerkkarte> _vormerkkarten;

    /**
     * Der Medienbestand.
     */
    private MedienbestandService _medienbestand;

    /**
     * Der Kundenstamm.
     */
    private KundenstammService _kundenstamm;

    /**
     * Der Protokollierer für die Verleihvorgänge.
     */
    private VerleihProtokollierer _protokollierer;

    /**
     * Konstruktor. Erzeugt einen neuen VerleihServiceImpl.
     * 
     * @param kundenstamm Der KundenstammService.
     * @param medienbestand Der MedienbestandService.
     * @param initialBestand Der initiale Bestand.
     * 
     * @require kundenstamm != null
     * @require medienbestand != null
     * @require initialBestand != null
     */
    public VerleihServiceImpl(KundenstammService kundenstamm,
            MedienbestandService medienbestand,
            List<Verleihkarte> initialBestand)
    {
        assert kundenstamm != null : "Vorbedingung verletzt: kundenstamm  != null";
        assert medienbestand != null : "Vorbedingung verletzt: medienbestand  != null";
        assert initialBestand != null : "Vorbedingung verletzt: initialBestand  != null";
        _verleihkarten = erzeugeVerleihkartenBestand(initialBestand);
        _vormerkkarten = new HashMap<Medium, Vormerkkarte>();
        _kundenstamm = kundenstamm;
        _medienbestand = medienbestand;
        _protokollierer = new VerleihProtokollierer();
    }

    @Override
    public boolean kundeImBestand(Kunde kunde)
    {
        return _kundenstamm.enthaeltKunden(kunde);
    }

    @Override
    public boolean mediumImBestand(Medium medium)
    {
        return _medienbestand.enthaeltMedium(medium);
    }

    @Override
    public boolean medienImBestand(List<Medium> medien)
    {
        assert medien != null : "Vorbedingung verletzt: medien != null";
        assert !medien.isEmpty() : "Vorbedingung verletzt: !medien.isEmpty()";

        boolean result = true;
        for (Medium medium : medien)
        {
            if (!mediumImBestand(medium))
            {
                result = false;
                break;
            }
        }
        return result;
    }

    //Ab hier die Methoden für das Verleihen und Zurücknehmen von Medien

    /**
     * Erzeugt eine neue HashMap aus dem Initialbestand.
     */
    private HashMap<Medium, Verleihkarte> erzeugeVerleihkartenBestand(
            List<Verleihkarte> initialBestand)
    {
        HashMap<Medium, Verleihkarte> result = new HashMap<Medium, Verleihkarte>();
        for (Verleihkarte verleihkarte : initialBestand)
        {
            result.put(verleihkarte.getMedium(), verleihkarte);
        }
        return result;
    }

    @Override
    public List<Verleihkarte> getVerleihkarten()
    {
        return new ArrayList<Verleihkarte>(_verleihkarten.values());
    }

    @Override
    public Verleihkarte getVerleihkarteFuer(Medium medium)
    {
        assert istVerliehen(
                medium) : "Vorbedingung verletzt: istVerliehen(medium)";
        return _verleihkarten.get(medium);
    }

    @Override
    public Kunde getEntleiherFuer(Medium medium)
    {
        assert istVerliehen(
                medium) : "Vorbedingung verletzt: istVerliehen(medium)";
        Verleihkarte verleihkarte = _verleihkarten.get(medium);
        return verleihkarte.getEntleiher();
    }

    @Override
    public List<Verleihkarte> getVerleihkartenFuer(Kunde kunde)
    {
        assert kundeImBestand(
                kunde) : "Vorbedingung verletzt: kundeImBestand(kunde)";
        List<Verleihkarte> result = new ArrayList<Verleihkarte>();
        for (Verleihkarte verleihkarte : _verleihkarten.values())
        {
            if (verleihkarte.getEntleiher()
                .equals(kunde))
            {
                result.add(verleihkarte);
            }
        }
        return result;
    }

    @Override
    public List<Medium> getAusgelieheneMedienFuer(Kunde kunde)
    {
        assert kundeImBestand(
                kunde) : "Vorbedingung verletzt: kundeImBestand(kunde)";
        List<Medium> result = new ArrayList<Medium>();
        for (Verleihkarte verleihkarte : _verleihkarten.values())
        {
            if (verleihkarte.getEntleiher()
                .equals(kunde))
            {
                result.add(verleihkarte.getMedium());
            }
        }
        return result;
    }

    @Override
    public void verleiheAn(Kunde kunde, List<Medium> medien, Datum ausleihDatum)
            throws ProtokollierException
    {
        assert kundeImBestand(
                kunde) : "Vorbedingung verletzt: kundeImBestand(kunde)";
        assert sindAlleNichtVerliehen(
                medien) : "Vorbedingung verletzt: sindAlleNichtVerliehen(medien) ";
        assert ausleihDatum != null : "Vorbedingung verletzt: ausleihDatum != null";
        assert istVerleihenMoeglich(kunde,
                medien) : "Vorbedingung verletzt:  istVerleihenMoeglich(kunde, medien)";

        for (Medium medium : medien)
        {
            Verleihkarte verleihkarte = new Verleihkarte(kunde, medium,
                    ausleihDatum);

            _verleihkarten.put(medium, verleihkarte);
            if(_vormerkkarten.containsKey(medium))
            {
                _vormerkkarten.get(medium).entferneVormerker1();
                if(_vormerkkarten.get(medium).getVormerker1() == null)
                {
                    _vormerkkarten.remove(medium);
                }
            }
            _protokollierer.protokolliere(
                    VerleihProtokollierer.EREIGNIS_AUSLEIHE, verleihkarte);
        }
        // Was passiert wenn das Protokollieren mitten in der Schleife
        // schief geht? informiereUeberAenderung in einen finally Block?
        informiereUeberAenderung();
    }

    @Override
    public boolean istVerleihenMoeglich(Kunde kunde, List<Medium> medien)
    {
        assert kundeImBestand(
                kunde) : "Vorbedingung verletzt: kundeImBestand(kunde)";
        assert medienImBestand(
                medien) : "Vorbedingung verletzt: medienImBestand(medien)";

        boolean ivm = sindAlleNichtVerliehen(medien);
        if(ivm)
        {
            if(!sindAlleNichtVorgemerkt(medien))
            {
                for(Medium m : medien)
                {
                    if(istVorgemerkt(m) && !istErsterVormerker(kunde, m))
                    {
                        ivm = false;
                    }
                }
            }
        }
        return ivm;
    }

    @Override
    public boolean sindAlleVerliehen(List<Medium> medien)
    {
        assert medienImBestand(
                medien) : "Vorbedingung verletzt: medienImBestand(medien)";

        boolean result = true;
        for (Medium medium : medien)
        {
            if (!istVerliehen(medium))
            {
                result = false;
            }
        }
        return result;
    }

    @Override
    public boolean sindAlleNichtVerliehen(List<Medium> medien)
    {
        assert medienImBestand(
                medien) : "Vorbedingung verletzt: medienImBestand(medien)";
        boolean result = true;
        for (Medium medium : medien)
        {
            if (istVerliehen(medium))
            {
                result = false;
            }
        }
        return result;
    }

    @Override
    public boolean istVerliehen(Medium medium)
    {
        assert mediumImBestand(
                medium) : "Vorbedingung verletzt: mediumExistiert(medium)";
        return _verleihkarten.get(medium) != null;
    }

    @Override
    public boolean sindAlleVerliehenAn(Kunde kunde, List<Medium> medien)
    {
        assert kundeImBestand(
                kunde) : "Vorbedingung verletzt: kundeImBestand(kunde)";
        assert medienImBestand(
                medien) : "Vorbedingung verletzt: medienImBestand(medien)";

        boolean result = true;
        for (Medium medium : medien)
        {
            if (!istVerliehenAn(kunde, medium))
            {
                result = false;
            }
        }
        return result;
    }
    
    @Override
    public boolean sindAlleNichtVerliehenAn(Kunde kunde, List<Medium> medien)
    {
        assert kundeImBestand(
                kunde) : "Vorbedingung verletzt: kundeImBestand(kunde)";
        assert medienImBestand(
                medien) : "Vorbedingung verletzt: medienImBestand(medien)";

        boolean result = true;
        for (Medium medium : medien)
        {
            if (istVerliehenAn(kunde, medium))
            {
                result = false;
            }
        }
        return result;
    }

    @Override
    public boolean istVerliehenAn(Kunde kunde, Medium medium)
    {
        assert kundeImBestand(
                kunde) : "Vorbedingung verletzt: kundeImBestand(kunde)";
        assert mediumImBestand(
                medium) : "Vorbedingung verletzt: mediumImBestand(medium)";

        return istVerliehen(medium) && getEntleiherFuer(medium).equals(kunde);
    }
    
    @Override
    public void nimmZurueck(List<Medium> medien, Datum rueckgabeDatum)
            throws ProtokollierException
    {
        assert sindAlleVerliehen(
                medien) : "Vorbedingung verletzt: sindAlleVerliehen(medien)";
        assert rueckgabeDatum != null : "Vorbedingung verletzt: rueckgabeDatum != null";

        for (Medium medium : medien)
        {
            Verleihkarte verleihkarte = _verleihkarten.get(medium);
            _verleihkarten.remove(medium);
            _protokollierer.protokolliere(
                    VerleihProtokollierer.EREIGNIS_RUECKGABE, verleihkarte);
        }

        informiereUeberAenderung();
    }

    //Ab hier die Methoden für das Vormerken von Medien

    @Override
    public List<Vormerkkarte> getVormerkkarten()
    {
        return new ArrayList<Vormerkkarte>(_vormerkkarten.values());
    }

    @Override
    public Vormerkkarte getVormerkkarteFuer(Medium medium)
    {
        return _vormerkkarten.get(medium);
    }

    @Override
    public List<Kunde> getVormerkerFuer(Medium medium)
    {
        Vormerkkarte vmk;
        List<Kunde> vormerker = new ArrayList<Kunde>();
        if(_vormerkkarten.containsKey(medium))
        {
        vmk = getVormerkkarteFuer(medium);
        vormerker.add(vmk.getVormerker1());
        vormerker.add(vmk.getVormerker2());
        vormerker.add(vmk.getVormerker3());
        }
        else
        {
            vormerker.add(null);
            vormerker.add(null);
            vormerker.add(null);
        }
        return vormerker;
    }

    @Override
    public List<Vormerkkarte> getVormerkkartenFuer(Kunde kunde)
    {
        List<Vormerkkarte> karten = new ArrayList<Vormerkkarte>();
        for(Vormerkkarte v : _vormerkkarten.values())
        {
            if(istVorgemerktFuer(kunde, v.getMedium()))
            {
                karten.add(v);
            }
        }
        return karten;
    }

    @Override
    public List<Medium> getVorgemerkteMedienFuer(Kunde kunde)
    {
        List<Medium> lm = new ArrayList<Medium>();
        List<Vormerkkarte> vmk = getVormerkkartenFuer(kunde);
        for(Vormerkkarte v : vmk)
        {
            lm.add(v.getMedium());
        }
        /*//ALTERNAIVE
         * List<Medium> lm = new ArrayList<Medium>();
            for(Vormerkkarte v : _vormerkkarten.values())
            {
                if(istVorgemerktFuer(kunde, v.getMedium()))
                {
                    karten.add(v.getMedium());
                }
            }
         */
        return lm;
    }
    
    private void mediumVormerkenFuer(Kunde kunde, Medium medium)
    {
        if(!istVorgemerktFuer(kunde, medium))
        {
            if(_vormerkkarten.containsKey(medium))
            {
                Vormerkkarte v = _vormerkkarten.get(medium);
                v.neuerVormerker(kunde);
                _vormerkkarten.replace(medium, v);
            }
            else
            {
                Vormerkkarte v = new Vormerkkarte(kunde, medium);
                _vormerkkarten.put(medium, v);
            }
        }
    }

    @Override
    public void vormerkenFuer(Kunde kunde, List<Medium> medien)
    {
        if(sindAlleVormerkbar(kunde, medien))
        {
            for(Medium m : medien)
            {
                mediumVormerkenFuer(kunde, m);
            }

            informiereUeberAenderung();
        }
    }

    @Override
    public boolean sindAlleVormerkbar(Kunde kunde, List<Medium> medien)
    {
        boolean sav = true;
        for(Medium m : medien)
        {
            if(istVerliehenAn(kunde, m) 
                    || istVorgemerktFuer(kunde,m))
            {
                sav = false;
            }
            else
            {
                if(_vormerkkarten.containsKey(m))
                {
                    Vormerkkarte v = _vormerkkarten.get(m);
                    sav = v.getVormerker3() == null;
                }
            }
        }
        return sav;
    }

    @Override
    public boolean istVorgemerktFuer(Kunde kunde, Medium medium)
    {
        assert(medium != null):"Vorbedingung verletzt: Medium ist null";
        assert(kunde != null):"Vorbedingung verletzt: Kunde ist null";
        boolean ivf = false;
        if(_vormerkkarten.containsKey(medium))
        {
            Vormerkkarte v = _vormerkkarten.get(medium);
            if(v.getVormerker1() != null)
            {
                if(v.getVormerker1().equals(kunde))
                    ivf = true;
            }
            if(!ivf && v.getVormerker2() != null)
            {
                if(v.getVormerker2().equals(kunde))
                    ivf = true;
            }
            if(!ivf && v.getVormerker3() != null)
            {
                if(v.getVormerker3().equals(kunde))
                    ivf = true;
            }
        }
        return ivf;
    }

    @Override
    public boolean istVorgemerkt(Medium medium)
    {
        return (_vormerkkarten.containsKey(medium));
    }

    @Override
    public boolean sindAlleVorgemerktFuer(Kunde kunde, List<Medium> medien)
    {
        boolean savf = true;
        for(Medium m : medien)
        {
                if(!istVorgemerktFuer(kunde,m))
                    savf = false;
        }
        return savf;
    }

    @Override
    public boolean sindAlleVorgemerkt(List<Medium> medien)
    {
        boolean sav = true;
        for(Medium m : medien)
        {
                if(!istVorgemerkt(m))
                    sav = false;
        }
        return sav;
    }

    @Override
    public boolean sindAlleKomplettVorgemerkt(List<Medium> medien)
    {
        boolean sakv = true;
        for(Medium m : medien)
        {
                if(_vormerkkarten.containsKey(m))
                {
                    if(_vormerkkarten.get(m).getVormerker3() == null)
                        sakv = false;
                }
                else
                    sakv = false;
        }
        return sakv;
    }

    @Override
    public boolean sindAlleNichtVorgemerkt(List<Medium> medien)
    {
        boolean sanv = true;
        for(Medium m : medien)
        {
                if(istVorgemerkt(m))
                    sanv = false;
        }
        return sanv;
    }
    
    @Override
    public boolean istErsterVormerker(Kunde kunde, Medium medium)
    {
        assert(medium != null):"Vorbedingung verletzt: Medium ist null";
        assert(kunde != null):"Vorbedingung verletzt: Kunde ist null";
        if(_vormerkkarten.containsKey(medium))
        {
            return kunde.equals(_vormerkkarten.get(medium).getVormerker1());
        }
        return false;
    }
    
}