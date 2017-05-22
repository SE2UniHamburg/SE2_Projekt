package de.uni_hamburg.informatik.swt.se2.mediathek.services.verleih;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Datum;
import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Kundennummer;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Kunde;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Verleihkarte;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.CD;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.ServiceObserver;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.kundenstamm.KundenstammService;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.kundenstamm.KundenstammServiceImpl;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.medienbestand.MedienbestandService;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.medienbestand.MedienbestandServiceImpl;

/**
 * @author SE2-Team
 */
public class VerleihServiceImplTest
{
    private Datum _datum;
    private Kunde _kunde1;
    private Kunde _kunde2;
    private Kunde _kunde3;
    private Kunde _kunde4;
    private VerleihService _service;
    private List<Medium> _medienListe;
    private Kunde _vormerkkunde;

    public VerleihServiceImplTest()
    {
        _datum = new Datum(3, 4, 2009);
        KundenstammService kundenstamm = new KundenstammServiceImpl(
                new ArrayList<Kunde>());
        _kunde1 = new Kunde(new Kundennummer(123456), "ich", "du1");
        _kunde2 = new Kunde(new Kundennummer(123456), "ich", "du2");

        _vormerkkunde = new Kunde(new Kundennummer(666999), "paul", "panter");

        kundenstamm.fuegeKundenEin(_kunde1);
        kundenstamm.fuegeKundenEin(_vormerkkunde);
        MedienbestandService medienbestand = new MedienbestandServiceImpl(
                new ArrayList<Medium>());
        Medium medium = new CD("CD1", "baz", "foo", 123);
        medienbestand.fuegeMediumEin(medium);
        medium = new CD("CD2", "baz", "foo", 123);
        medienbestand.fuegeMediumEin(medium);
        medium = new CD("CD3", "baz", "foo", 123);
        medienbestand.fuegeMediumEin(medium);
        medium = new CD("CD4", "baz", "foo", 123);
        medienbestand.fuegeMediumEin(medium);
        _medienListe = medienbestand.getMedien();
        _service = new VerleihServiceImpl(kundenstamm, medienbestand,
                new ArrayList<Verleihkarte>());
    }

    @Test
    public void testeNachInitialisierungIstNichtsVerliehen() throws Exception
    {
        assertTrue(_service.getVerleihkarten()
            .isEmpty());
        assertFalse(_service.istVerliehen(_medienListe.get(0)));
        assertFalse(_service.sindAlleVerliehen(_medienListe));
        assertTrue(_service.sindAlleNichtVerliehen(_medienListe));
    }

    @Test
    public void testeVerleihUndRueckgabeVonMedien() throws Exception
    {
        // Lege eine Liste mit nur verliehenen und eine Liste mit ausschließlich
        // nicht verliehenen Medien an
        List<Medium> verlieheneMedien = _medienListe.subList(0, 2);
        List<Medium> nichtVerlieheneMedien = _medienListe.subList(2, 4);
        _service.verleiheAn(_kunde1, verlieheneMedien, _datum);

        // Prüfe, ob alle sondierenden Operationen für das Vertragsmodell
        // funktionieren
        assertTrue(_service.istVerliehen(verlieheneMedien.get(0)));
        assertTrue(_service.istVerliehen(verlieheneMedien.get(1)));
        assertFalse(_service.istVerliehen(nichtVerlieheneMedien.get(0)));
        assertFalse(_service.istVerliehen(nichtVerlieheneMedien.get(1)));
        assertTrue(_service.sindAlleVerliehen(verlieheneMedien));
        assertTrue(_service.sindAlleNichtVerliehen(nichtVerlieheneMedien));
        assertFalse(_service.sindAlleNichtVerliehen(verlieheneMedien));
        assertFalse(_service.sindAlleVerliehen(nichtVerlieheneMedien));
        assertFalse(_service.sindAlleVerliehen(_medienListe));
        assertFalse(_service.sindAlleNichtVerliehen(_medienListe));
        assertTrue(_service.istVerliehenAn(_kunde1, verlieheneMedien.get(0)));
        assertTrue(_service.istVerliehenAn(_kunde1, verlieheneMedien.get(1)));
        assertFalse(
                _service.istVerliehenAn(_kunde1, nichtVerlieheneMedien.get(0)));
        assertFalse(
                _service.istVerliehenAn(_kunde1, nichtVerlieheneMedien.get(1)));
        assertTrue(_service.sindAlleVerliehenAn(_kunde1, verlieheneMedien));
        assertFalse(
                _service.sindAlleVerliehenAn(_kunde1, nichtVerlieheneMedien));

        // Prüfe alle sonstigen sondierenden Methoden
        assertEquals(2, _service.getVerleihkarten()
            .size());

        _service.nimmZurueck(verlieheneMedien, _datum);
        // Prüfe, ob alle sondierenden Operationen für das Vertragsmodell
        // funktionieren
        assertFalse(_service.istVerliehen(verlieheneMedien.get(0)));
        assertFalse(_service.istVerliehen(verlieheneMedien.get(1)));
        assertFalse(_service.istVerliehen(nichtVerlieheneMedien.get(0)));
        assertFalse(_service.istVerliehen(nichtVerlieheneMedien.get(1)));
        assertFalse(_service.sindAlleVerliehen(verlieheneMedien));
        assertTrue(_service.sindAlleNichtVerliehen(nichtVerlieheneMedien));
        assertTrue(_service.sindAlleNichtVerliehen(verlieheneMedien));
        assertFalse(_service.sindAlleVerliehen(nichtVerlieheneMedien));
        assertFalse(_service.sindAlleVerliehen(_medienListe));
        assertTrue(_service.sindAlleNichtVerliehen(_medienListe));
        assertTrue(_service.getVerleihkarten()
            .isEmpty());
    }

    /*@Test
    public void testeVormerkenVonMedien() throws Exception
    {
        // Lege eine Liste mit nur verliehenen und eine Liste mit ausschließlich
        // nicht verliehenen Medien an
        List<Medium> verlieheneMedien1 = _medienListe.subList(0, 0);
        List<Medium> verlieheneMedien2 = _medienListe.subList(1, 1);
        List<Medium> verlieheneMedien4 = _medienListe.subList(2, 2);
        List<Medium> nichtVerlieheneMedien = _medienListe.subList(3, 3);
        _service.verleiheAn(_kunde1, verlieheneMedien1, _datum);
        _service.verleiheAn(_kunde1, verlieheneMedien2, _datum);
        _service.verleiheAn(_kunde1, verlieheneMedien4, _datum);
        
        //Vormerken der Medien der Listen, bis jede 0-3 Vormerker hat
        _service.vormerkenFuer(_kunde2, verlieheneMedien2);
        _service.vormerkenFuer(_kunde2, verlieheneMedien4);
        _service.vormerkenFuer(_kunde3, verlieheneMedien4);
        _service.vormerkenFuer(_kunde4, verlieheneMedien4);

        // Prüfe, ob alle sondierenden Operationen für das Vertragsmodell
        // funktionieren
        assertFalse(_service.sindAlleVormerkbar(_kunde1, verlieheneMedien1));
        assertFalse(_service.sindAlleVormerkbar(_kunde2, verlieheneMedien2));
        assertTrue(_service.sindAlleVormerkbar(_kunde2, verlieheneMedien1));
        assertFalse(_service.sindAlleVormerkbar(_kunde1, nichtVerlieheneMedien));
        assertFalse(_service.istVorgemerkt(verlieheneMedien1.get(0)));
        assertFalse(_service.istVorgemerkt(verlieheneMedien1.get(1)));
        assertTrue(_service.istVorgemerkt(verlieheneMedien2.get(0)));
        assertTrue(_service.istVorgemerkt(verlieheneMedien2.get(1)));
        assertFalse(_service.istVorgemerktFuer(_kunde1, verlieheneMedien1.get(0)));
        assertFalse(_service.istVorgemerktFuer(_kunde1, verlieheneMedien1.get(1)));
        assertTrue(_service.istVorgemerktFuer(_kunde2, verlieheneMedien2.get(0)));
        assertTrue(_service.istVorgemerktFuer(_kunde2, verlieheneMedien2.get(1)));
        assertFalse(_service.sindAlleVorgemerkt(verlieheneMedien1));
        assertTrue(_service.sindAlleVorgemerkt(verlieheneMedien2));
        assertFalse(_service.sindAlleVorgemerktFuer(_kunde1, verlieheneMedien1));
        assertTrue(_service.sindAlleVorgemerktFuer(_kunde1, verlieheneMedien2));
        assertFalse(_service.sindAlleKomplettVorgemerkt(verlieheneMedien2));
        assertTrue(_service.sindAlleKomplettVorgemerkt(verlieheneMedien4));
        assertFalse(_service.sindAlleNichtVorgemerkt(verlieheneMedien2));
        assertTrue(_service.sindAlleNichtVorgemerkt(verlieheneMedien1));
        
        // Prüfe alle sonstigen sondierenden Methoden
        assertEquals(6, _service.getVormerkkarten()
            .size());
    }*/

    @Test
    public void testVerleihEreignisBeobachter() throws ProtokollierException
    {
        final boolean ereignisse[] = new boolean[1];
        ereignisse[0] = false;
        ServiceObserver beobachter = new ServiceObserver()
        {
            @Override
            public void reagiereAufAenderung()
            {
                ereignisse[0] = true;
            }
        };
        _service.verleiheAn(_kunde1,
                Collections.singletonList(_medienListe.get(0)), _datum);
        assertFalse(ereignisse[0]);

        _service.registriereBeobachter(beobachter);
        _service.verleiheAn(_kunde1,
                Collections.singletonList(_medienListe.get(1)), _datum);
        assertTrue(ereignisse[0]);

        _service.entferneBeobachter(beobachter);
        ereignisse[0] = false;
        _service.verleiheAn(_kunde1,
                Collections.singletonList(_medienListe.get(2)), _datum);
        assertFalse(ereignisse[0]);
    }

}
