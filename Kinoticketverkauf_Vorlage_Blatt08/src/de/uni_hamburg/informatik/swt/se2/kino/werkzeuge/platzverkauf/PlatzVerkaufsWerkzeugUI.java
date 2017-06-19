package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.platzverkauf;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Platz;

/**
 * Die UI des {@link PlatzVerkaufsWerkzeug}.
 * 
 * @author SE2-Team
 * @version SoSe 2017
 */
class PlatzVerkaufsWerkzeugUI
{
    // Die Widgets, aus denen das UI sich zusammensetzt
    private JPanel _hauptPanel;
    private JLabel _preisLabel;
    private JButton _verkaufenButton;
    private JButton _stornierenButton;
    private JPlatzplan _platzplan;
   
    /**
     * Initialisiert die UI.
     */
    public PlatzVerkaufsWerkzeugUI()
    {
        _hauptPanel = erstellePanel();
    }

    /**
     * Erzeugt das Panel, in dem der Kinosaal mit den Sitzplätzen dargestellt
     * wird.
     */
    private JPanel erstellePanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        _platzplan = new JPlatzplan();
        panel.add(new JScrollPane(_platzplan), BorderLayout.CENTER);
        
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setBorder(BorderFactory.createEmptyBorder(5,10,5,0));
        
        JPanel preisPanel = new JPanel(new BorderLayout());
        _preisLabel = new JLabel();
        preisPanel.add(_preisLabel, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        _verkaufenButton = new JButton("Verkaufen");
        buttonPanel.add(_verkaufenButton);    
        _stornierenButton = new JButton("Stornieren");
        buttonPanel.add(_stornierenButton);
        
        southPanel.add(_preisLabel, BorderLayout.CENTER);
        southPanel.add(buttonPanel, BorderLayout.EAST);
        
        panel.add(southPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    /**
     * 
     */
    public int getAnzahlSelectedPlaetze()
    {
    	return _platzplan.getAusgewaehltePlaetze().size();
    }
    
    /**
     * 
     */
    public Set<Platz> getSelectedPlaetze()
    {
    	return _platzplan.getAusgewaehltePlaetze();
    }
    
    /**
     * Gibt den Platzplan zurück.
     */
    public JPlatzplan getPlatzplan()
    {
        return _platzplan;
    }
    
    /**
     * Gibt das Label für die Preisanzeige zurück.
     */
    public JLabel getPreisLabel()
    {
        return _preisLabel;
    }
    
    /**
     * Gibt den Stornieren-Button zurück.
     */
    public JButton getStornierenButton()
    {
        return _stornierenButton;
    }
    
    /**
     * Gibt den Verkaufen-Button zurück.
     */
    public JButton getVerkaufenButton()
    {
        return _verkaufenButton;
    }
    
    /**
     * Gibt das Panel zurück, in dem die Widgets angeordnet sind.
     */
    public JPanel getUIPanel()
    {
        return _hauptPanel;
    }

}
