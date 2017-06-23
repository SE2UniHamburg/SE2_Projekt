package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.barzahlung;


import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

/**
 * Die UI des {@link Barzahlung}.
 * 
 * @author SE2-Team
 * @version SoSe 2017
 */
public class BarzahlungsWerkzeugUI
{
    // Die Widgets, aus denen das UI sich zusammensetzt
	private JFrame _frame;
	//private JPanel _hauptPanel;
    private JPanel _infoPanel;
    private JPanel _bezahlPanel;
    private JPanel _auswahlPanel;
    private JLabel _vorstellungLabel;
    private JLabel _infovorstellungLabel;
    private JLabel _anzahlPlaetzeLabel;
    private JLabel _infoAnzahlPlaetzeLabel;
    private JLabel _preisProPlaetzeLabel;
    private JLabel _infoPreisProPlaetzeLabel;
    private JLabel _infoGesamtBetragLabel;
    private JLabel _gesamtBetragLabel;
    private JLabel _barLabel;
    private JTextField _eintragBarField;
    private JLabel _euroLabel;
    private JLabel _rueckgeldLabel;
    private JLabel _infoRueckgeldLabel;
    private JButton _okButton;
    private JButton _abbrechenButton;
    //private JPlatzplan _platzplan;
   
    /**
     * Initialisiert die UI.
     */
    public BarzahlungsWerkzeugUI()
    {
        //_hauptPanel = erstellePanel();
    	_frame = new JFrame("Barzahlung");
        
	    _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    _frame.getContentPane().setLayout(new BorderLayout());
	
	    JComponent infoPanel = erstelle_infoPanel();
	    JComponent bezahlPanel = erstelle_bezahlPanel();
	    JComponent auswahlPanel = erstelle_auswahlPanel();
	    
	
	    //JSplitPane splitter = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
	            //_infoPanel, _bezahlPanel);
	    //_frame.getContentPane().add(splitter, BorderLayout.CENTER);
	    //_frame.getContentPane().add(Panel, BorderLayout.NORTH);
	    //_frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
	}

    private JPanel erstelle_infoPanel()
    {
        _infoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        _vorstellungLabel = new JLabel("Vorstellung");
        _infoPanel.add(_vorstellungLabel, BorderLayout.EAST);
        
        _infovorstellungLabel = new JLabel ("");
        _infoPanel.add(_infovorstellungLabel);
        
        _anzahlPlaetzeLabel = new JLabel("Anzahl Plaetze");
        _infoPanel.add(_anzahlPlaetzeLabel, BorderLayout.EAST);
        
        _infoAnzahlPlaetzeLabel = new JLabel("");
        _infoPanel.add(_infoAnzahlPlaetzeLabel, BorderLayout.CENTER);
                
        _preisProPlaetzeLabel = new JLabel("Preis pro Plaetze");
        _infoPanel.add(_preisProPlaetzeLabel, BorderLayout.EAST);  
        
        _infoPreisProPlaetzeLabel = new JLabel("");
        _infoPanel.add(_infoPreisProPlaetzeLabel, BorderLayout.EAST);
        
        _gesamtBetragLabel = new JLabel("gesamter Betrag");
        _infoPanel.add(_gesamtBetragLabel, BorderLayout.EAST);
        
        _euroLabel = new JLabel("Euro");
        _infoPanel.add(_euroLabel, BorderLayout.WEST);
        
        _infoGesamtBetragLabel = new JLabel("");
        _infoPanel.add(_infoGesamtBetragLabel, BorderLayout.EAST);
        _infoPanel.setBorder(BorderFactory.createEmptyBorder(5,10,5,0));
        _frame.add(_infoPanel, BorderLayout.NORTH);
        return _infoPanel;
    }
    
    private JPanel erstelle_bezahlPanel()
    {
        _bezahlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        _barLabel = new JLabel("Bezahlt");
        _bezahlPanel.add(_barLabel, BorderLayout.EAST);
        
        _eintragBarField = new JTextField("");
        _bezahlPanel.add(_eintragBarField, BorderLayout.CENTER);
        
        _euroLabel = new JLabel("Euro");
        _bezahlPanel.add(_euroLabel, BorderLayout.WEST);
        
        _rueckgeldLabel = new JLabel("Rueckgeld");
        _bezahlPanel.add(_rueckgeldLabel, BorderLayout.EAST);
        
        _infoRueckgeldLabel = new JLabel("");
        _bezahlPanel.add(_rueckgeldLabel, BorderLayout.CENTER);
        
        _euroLabel = new JLabel("Euro");
        _bezahlPanel.add(_euroLabel, BorderLayout.WEST);
        
        _frame.add(_bezahlPanel, BorderLayout.CENTER);
        return _bezahlPanel;
    }
    
    private JPanel erstelle_auswahlPanel()
    {       
        _auswahlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        _abbrechenButton = new JButton("Abbrechen");
        _auswahlPanel.add(_abbrechenButton);    
        _okButton = new JButton("OK");
        _auswahlPanel.add(_okButton);
        _frame.add(_auswahlPanel, BorderLayout.SOUTH);
        return _auswahlPanel;
    }
        

    
    /**
     * 
     
    private JPanel erstellePanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        //_platzplan = new JPlatzplan();
        //panel.add(new JScrollPane(_platzplan), BorderLayout.CENTER);
        
        _infoPanel = new JPanel(new BorderLayout());
        _vorstellungLabel = new JLabel();
        _infoPanel.add(_vorstellungLabel, BorderLayout.EAST);
        //_infoPanel.setBorder(BorderFactory.createEmptyBorder(5,10,5,0));
        
        _bezahlPanel = new JPanel(new BorderLayout());
        _barLabel = new JLabel();
        _bezahlPanel.add(_barLabel, BorderLayout.EAST);
        _euroLabel = new JLabel();
        _bezahlPanel.add(_euroLabel, BorderLayout.WEST);
        _rueckgeldLabel = new JLabel();
        _bezahlPanel.add(_rueckgeldLabel, BorderLayout.EAST);
        _euroLabel = new JLabel();
        _bezahlPanel.add(_euroLabel, BorderLayout.EAST);
        
        
        _auswahlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        _abbrechenButton = new JButton("Abbrechen");
        _auswahlPanel.add(_abbrechenButton);    
        _okButton = new JButton("OK");
        _auswahlPanel.add(_okButton);

        
        panel.add(_infoPanel, BorderLayout.NORTH);
        panel.add(_bezahlPanel, BorderLayout.CENTER);
        panel.add(_auswahlPanel, BorderLayout.SOUTH);
        
        return panel;
    } 
    

    /**
     * Zeigt das Fenster an.
     */
    public void zeigeFenster()
    {
        _frame.setSize(500, 500);
        _frame.setVisible(true);
    }

    
    /**
     * Zeigt das Fenster an.
     
    public void zeigeFenster()
    {
        //_frame.setSize(1200, 900);
        panel.setVisible(true);
    }

//TODO getter von restlichen Labels
    
    /**
     * Gibt das Label für die Preisanzeige zurück.
     */
    public JLabel get_barLabel()
    {
        return _barLabel;
    }
    
    /**
     * Gibt den ok-Button zurück.
     */
    public JButton getokButton()
    {
        return _okButton;
    }
    
    /**
     * Gibt den Abbrechen-Button zurück.
     */
    public JButton getabbrechenButton()
    {
        return _abbrechenButton;
    }
    
    /**
     * Gibt das Panel zurück, in dem die Widgets angeordnet sind.
     */
    public JFrame getUIframe()
    {
        return _frame;
    }


}

