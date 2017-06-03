package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.beobachtungsmuster;

import java.util.*;

/* TODO comments */
public abstract class Beobachtbar
{
    private HashSet<Beobachter> _beobachter = new HashSet<Beobachter>();
    
    protected void meldeAenderung()
    {
        _beobachter.forEach(beobachter -> beobachter.beachteAenderung());
    }
    
    public void setzeBeobachter(Beobachter b)
    {
        _beobachter.add(b);
    }
}
