package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GeldbetragTest
{
    /**
     * Testet, ob gleiche Zahlen in unterschiedlichen Schreibweisen als
     * die gleichen angesehen werden.
     * Beispiel: 00 == 0
     */
    @Test
    public void testNullen()
    {
        Geldbetrag gN1 = Geldbetrag.get(1, 0, false);
        Geldbetrag gN2 = Geldbetrag.get(01, 00, false);
        
        assertTrue(gN1.getEuro() == gN2.getEuro());
        assertTrue(gN1.getCent() == gN2.getCent());
    }
    
    /**
     * Testet die Funktionalität des Getters.
     */
    @Test
    public void testGetSchulden()
    {
        Geldbetrag gGS1 = Geldbetrag.get(1, 0, false);
        Geldbetrag gGS2 = Geldbetrag.get(1, 0, false);
        Geldbetrag gGS3 = Geldbetrag.get(1, 0, true);
        
        assertTrue(gGS1.getSchulden() == gGS2.getSchulden());
        assertFalse(gGS1.getSchulden() == gGS3.getSchulden());
    }
    
    /**
     * Testet die Funktionalität des Getters.
     */
    @Test
    public void testGetEuro()
    {
        Geldbetrag gGE1 = Geldbetrag.get(1, 0, false);
        Geldbetrag gGE2 = Geldbetrag.get(1, 0, false);
        Geldbetrag gGE3 = Geldbetrag.get(2, 0, false);
        
        assertTrue(gGE1.getEuro() == gGE2.getEuro());
        assertFalse(gGE1.getEuro() == gGE3.getEuro());
    }
    
    /**
     * Testet die Funktionalität des Getters.
     */
    @Test
    public void testGetCent()
    {
        Geldbetrag gGC1 = Geldbetrag.get(1, 0, false);
        Geldbetrag gGC2 = Geldbetrag.get(1, 0, false);
        Geldbetrag gGC3 = Geldbetrag.get(2, 10, false);
        
        assertTrue(gGC1.getCent() == gGC2.getCent());
        assertFalse(gGC1.getCent() == gGC3.getCent());
    }
    
    /**
     * Testet, ob das Addieren zweier Geldbeträge funktioniert.
     */
    @Test
    public void testAddieren()
    {
        Geldbetrag gA1 = Geldbetrag.get(1, 45, false);
        Geldbetrag gA2 = Geldbetrag.get(0, 75, false);
        Geldbetrag gA3 = Geldbetrag.get(2, 35, true);
        
        Geldbetrag add = Geldbetrag.addieren(gA1, gA3);
        
        assertEquals(Geldbetrag.get(2, 20, false), Geldbetrag.addieren(gA1, gA2));
        assertEquals(Geldbetrag.get(0, 90, true), Geldbetrag.addieren(gA1, gA3));
    }
    
    /**
     * Testet, ob das Subtrahieren zweier Geldbeträge funktioniert.
     */
    @Test
    public void testSubtrahieren()
    {
        Geldbetrag gS1 = Geldbetrag.get(1, 45, false);
        Geldbetrag gS2 = Geldbetrag.get(0, 75, false);
        Geldbetrag gS3 = Geldbetrag.get(2, 35, true);
        
        assertEquals(Geldbetrag.get(0, 70, false), Geldbetrag.subtrahieren(gS1, gS2));
        assertEquals(Geldbetrag.get(3, 80, false), Geldbetrag.subtrahieren(gS1, gS3));
        assertEquals(Geldbetrag.get(3, 80, true), Geldbetrag.subtrahieren(gS3, gS1));
    }
    
    /**
     * Testet, ob das Subtrahieren zweier Geldbeträge funktioniert.
     */
    @Test
    public void testMultiplizieren()
    {
        Geldbetrag gM1 = Geldbetrag.get(1, 45, false);
        Geldbetrag gM2 = Geldbetrag.get(2, 35, true);

        assertEquals(Geldbetrag.get(2, 90, false), Geldbetrag.multiplizieren(gM1, 2));
        assertEquals(Geldbetrag.get(1, 45, true), Geldbetrag.multiplizieren(gM1, -1));
        assertEquals(Geldbetrag.get(7, 05, true), Geldbetrag.multiplizieren(gM2, 3));
    }
    
    /**
     * Testet, ob die Methoden equals und hashCode richtig oder überhaupt
     * überschrieben wurden.
     */
    @Test
    public void testEqualsUndHashCode()
    {
        Geldbetrag gEUH1 = Geldbetrag.get(1, 0, false);
        Geldbetrag gEUH2 = Geldbetrag.get(1, 0, false);
        Geldbetrag gEUH3 = Geldbetrag.get(1, 0, true);
        Geldbetrag gEUH4 = Geldbetrag.get(2, 45, false);
        Geldbetrag gEUH5 = Geldbetrag.get(2, 45, true);
        Geldbetrag gEUH6 = Geldbetrag.get(2, 45, true);
        
        assertTrue(gEUH1.equals(gEUH2));
        assertTrue(gEUH1.hashCode() == gEUH2.hashCode());
        assertTrue(gEUH5.equals(gEUH6));
        assertTrue(gEUH5.hashCode() == gEUH6.hashCode());
        
        assertFalse(gEUH1.equals(gEUH3));
        assertFalse(gEUH1.hashCode() == gEUH3.hashCode());
        assertFalse(gEUH1.equals(gEUH4));
        assertFalse(gEUH1.hashCode() == gEUH4.hashCode());
        assertFalse(gEUH1.equals(gEUH5));
        assertFalse(gEUH1.hashCode() == gEUH5.hashCode());
    }
    
    @Test
    public void testToString()
    {
        Geldbetrag ts1 = Geldbetrag.get(1.02);
        Geldbetrag ts2 = Geldbetrag.get(123.02);
        Geldbetrag ts3 = Geldbetrag.get("-123.456,78");
        Geldbetrag ts4 = Geldbetrag.get(3.444);
        Geldbetrag ts5 = Geldbetrag.get(01, 60, false);
        
        String x3 = ts3.toString();
        
        assertEquals(ts1.toString(), "01,02");
        assertEquals(ts1.toString(), ts1.toStringSigned());
        assertEquals(ts2.toString(), "123,02");
        assertEquals(ts3.toString(), "123456,78");
        assertEquals(ts3.toStringSigned(), "-123456,78");
        assertEquals(ts4.toString(), "03,44");
        assertEquals(ts5.toString(), "01,60");
        
    }
}