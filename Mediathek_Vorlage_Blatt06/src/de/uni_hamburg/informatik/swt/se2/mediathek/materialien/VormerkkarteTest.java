package de.uni_hamburg.informatik.swt.se2.mediathek.materialien;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;

import org.junit.Test;

import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Kundennummer;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.CD;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;

public class VormerkkarteTest
{
    private Vormerkkarte _karte;
    private Medium _medium1;
    private Medium _medium2;
    private Kunde _kunde1;
    private Kunde _kunde2;
    private Kunde _kunde3;

    public VormerkkarteTest()
    {
        _kunde1 = new Kunde(new Kundennummer(123456), "ich1", "du1");
        _kunde2 = new Kunde(new Kundennummer(123456), "ich2", "du2");
        _kunde3 = new Kunde(new Kundennummer(123456), "ich3", "du3");
        _medium1 = new CD("bar1", "baz", "foo", 123);
        _medium2 = new CD("bar2", "baz", "foo", 123);
        _karte = new Vormerkkarte(_kunde1, _medium1);
        _karte.neuerVormerker(_kunde2);
        _karte.neuerVormerker(_kunde3);
    }
    
    @Test
    public void testEquals()
    {
        Vormerkkarte karte1 = new Vormerkkarte(_kunde1, _medium1);
        karte1.neuerVormerker(_kunde2);
        karte1.neuerVormerker(_kunde3);

        assertTrue(_karte.equals(karte1));
        assertEquals(_karte.hashCode(), karte1.hashCode());
        

        Vormerkkarte karte2 = new Vormerkkarte(_kunde2, _medium2);

        assertFalse(_karte.equals(karte2));
        assertNotSame(_karte.hashCode(), karte2.hashCode());
    }


    @Test
    public void testeKonstruktor() throws Exception
    {
        assertEquals(_medium1, _karte.getMedium());
        assertEquals(_kunde1, _karte.getVormerker1());
        assertEquals(_kunde2, _karte.getVormerker2());
        assertEquals(_kunde3, _karte.getVormerker3());
       
    }
    
    @Test
    public void testNeuerVormerker()
    {
        Vormerkkarte karte = new Vormerkkarte(_kunde1, _medium1);
        assertEquals(null, karte.getVormerker2());
        karte.neuerVormerker(_kunde2);
        assertEquals(_kunde2, karte.getVormerker2());
    }
    
    @Test
    public void testEntferneVormerker()
    {
        Vormerkkarte karte = new Vormerkkarte(_kunde1, _medium1);
        karte.neuerVormerker(_kunde2);
        karte.entferneVormerker1();
        assertEquals(_kunde2, karte.getVormerker1());
        assertEquals(null, karte.getVormerker2());
    }
    
    
}
