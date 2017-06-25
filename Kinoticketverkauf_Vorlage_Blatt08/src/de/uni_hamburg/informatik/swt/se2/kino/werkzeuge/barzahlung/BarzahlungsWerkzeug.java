package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.barzahlung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;

import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Platz;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Kinosaal;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Vorstellung;

/**
 * Mit diesem Werkzeug können Plätze verkauft und storniert werden. Es arbeitet
 * auf einer Vorstellung als Material. Mit ihm kann angezeigt werden, welche
 * Plätze schon verkauft und welche noch frei sind.
 * 
 * Dieses Werkzeug ist ein eingebettetes Subwerkzeug.
 * 
 * @author SE2-Team
 * @version SoSe 2017
 */
public class BarzahlungsWerkzeug
{
    // Die aktuelle Vorstellung, deren Plätze angezeigt werden. Kann null sein.
    private Vorstellung _vorstellung;
    
    private BarzahlungsWerkzeugUI _barui;


    /**
     * Initialisiert das PlatzVerkaufsWerkzeug.
     */
    public BarzahlungsWerkzeug()
    {
        _barui = new BarzahlungsWerkzeugUI();
        //registriereUIAktionen();
        // Am Anfang wird keine Vorstellung angezeigt:
        //setVorstellung(null);
    }

    /**
     * Gibt das Panel dieses Subwerkzeugs zurück. Das Panel sollte von einem
     * Kontextwerkzeug eingebettet werden.
     * 
     * @ensure result != null
     */
    public JFrame getUIframe()
    {
        return _barui.getUIframe();
    }
    
    
    public void getUIPanel()
    {
    	_barui.zeigeFenster();
    }

    
    /**
     * Fügt der UI die Funktionalität hinzu mit entsprechenden Listenern.
     
    private void registriereUIAktionen()
    {
        _barui.get_abbrechenButton().addActionListener(new ActionListener()
        {
            //@Override
            //TODO
            return null;
        });

        _ui.get_okButton().addActionListener(new ActionListener()
        {
             //TODO
            return null;
            }
        });

        
    /**
     * Startet die Barzahlung.
     
    private void fuehreBarzahlungDurch()
    {
        //verkaufePlaetze(_vorstellung);
        _barui.zeigeFenster();
    }
*/


  
}
