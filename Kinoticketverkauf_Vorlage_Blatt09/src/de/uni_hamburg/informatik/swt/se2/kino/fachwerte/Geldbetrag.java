package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import de.uni_hamburg.informatik.swt.se2.kino.services.parseService.*;

public final class Geldbetrag
{
    private final int _euro;
    private final int _cent;
    private final boolean _schulden;
    
    private Geldbetrag(int eurocent)
    {
        if(eurocent < 0)
        {
            _schulden = true;
            eurocent *= -1;
        }
        else
            _schulden = false;
        _cent = eurocent % 100;
        _euro = (eurocent-_cent)/100;
    }
    
    private Geldbetrag(int euro, int cent, boolean schulden)
    {
        _schulden = schulden;
        _cent = cent;
        _euro = euro;
    }
    
    public static Geldbetrag get(int eurocent)
    {
        return new Geldbetrag(eurocent);
    }
    
    public static Geldbetrag get(int euro, int cent, boolean schulden)
    {
        return new Geldbetrag(euro, cent, schulden);
    }
    
    public static Geldbetrag get(double euro)
    {
        int eurocent = (int)Math.round(euro*100.0);
        return new Geldbetrag(eurocent);
    }
    
    public static Geldbetrag get(String euroString)
    {
        double euro = ParseService.parseToDouble(euroString);
        if(euro != Double.MIN_VALUE)
        {
            return get(euro);
        }
        else
            return null;
    }
    
    public boolean getSchulden()
    {
        return _schulden;
    }
    
    public int getEuro()
    {
        return _euro;
    }
    
    public int getCent()
    {
        return _cent;
    }
    
    public String toStringSigned()
    {
        return ((_schulden)?"-":"")+toString();
    }
    
    @Override
    public String toString()
    {
        return ""+formatTwoDigit(_euro)+","+formatTwoDigit(_cent);
    }
    
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + _cent;
        result = prime * result + _euro;
        result = prime * result + (_schulden ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Geldbetrag other = (Geldbetrag) obj;
        if (_cent != other._cent) return false;
        if (_euro != other._euro) return false;
        if (_schulden != other._schulden) return false;
        return true;
    }

    public static Geldbetrag addieren(Geldbetrag betrag1, Geldbetrag betrag2)
    {
        int eurocent1 = betrag1.convertToEurocent();
        int eurocent2 = betrag2.convertToEurocent();
        return new Geldbetrag(eurocent1+eurocent2);
    }

    public static Geldbetrag subtrahieren(Geldbetrag betrag1, Geldbetrag betrag2)
    {
        int eurocent1 = betrag1.convertToEurocent();
        int eurocent2 = betrag2.convertToEurocent();
        return new Geldbetrag(eurocent1-eurocent2);
    }
    
    public static Geldbetrag multiplizieren(Geldbetrag betrag, int faktor)
    {
        int eurocent = betrag.convertToEurocent();
        return new Geldbetrag(eurocent*faktor);
    }
    
    private int convertToEurocent()
    {
        return ((_schulden)?-1:1)*((_euro*100)+_cent);
    }
    
    private String formatTwoDigit(int val)
    {
        return ((val < 10 && val > -10)?"0":"")+val;
    }
}
