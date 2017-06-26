package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.barzahlung;


import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import de.uni_hamburg.informatik.swt.se2.kino.materialien.Vorstellung;

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
	private JPanel _infoPanel;
    private JPanel _infoPanel1;
    private JPanel _infoPanel2;
    private JPanel _infoPanel3;
    private JPanel _bezahlPanel;
    private JPanel _bezahlPanel1;
    private JPanel _auswahlPanel;
    private JLabel _vorstellungLabel;
    private JLabel _infoVorstellungLabel;
    private JLabel _anzahlPlaetzeLabel;
    private JLabel _infoAnzahlPlaetzeLabel;
    private JLabel _preisProPlaetzeLabel;
    private JLabel _infoPreisProPlaetzeLabel;
    private JLabel _gesamtBetragLabel;
    private JLabel _infoGesamtBetragLabel;
    private JLabel _barLabel;
    private JTextField _eintragBarField;
    private JLabel _euroLabel;
    private JLabel _rueckgeldLabel;
    private JLabel _infoRueckgeldLabel;
    private JButton _okButton;
    private JButton _abbrechenButton;
    private int _gesamtbetrag;
   
    /**
     * Initialisiert die UI.
     */
    public BarzahlungsWerkzeugUI()
    {
    	_frame = new JFrame("Barzahlung");
    	
	    //_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    _frame.getContentPane().setLayout(new BorderLayout());
	    
	    JComponent infoPanel = erstelleInfoPanel();
	    JComponent infoPanel1 = erstelleInfoPanel1();
	    JComponent infoPanel2 = erstelleInfoPanel2();
	    JComponent infoPanel3 = erstelleInfoPanel3();
        JComponent bezahlPanel = erstelleBezahlPanel();
        JComponent bezahlPanel1 = erstelleBezahlPanel1();
	    JComponent auswahlPanel = erstelleAuswahlPanel();
	    
	    JSplitPane splitterTopInfo = new JSplitPane(JSplitPane.VERTICAL_SPLIT, infoPanel, infoPanel1);
	    JSplitPane splitterBottomInfo = new JSplitPane(JSplitPane.VERTICAL_SPLIT, infoPanel2, infoPanel3);
	    JSplitPane splitterInfo = new JSplitPane(JSplitPane.VERTICAL_SPLIT, splitterTopInfo, splitterBottomInfo);
	    
	    JSplitPane splitterBezahlen = new JSplitPane(JSplitPane.VERTICAL_SPLIT, bezahlPanel, bezahlPanel1);
	    

        _frame.getContentPane().add(splitterInfo, BorderLayout.NORTH);
	    _frame.getContentPane().add(splitterBezahlen, BorderLayout.CENTER);
	    _frame.getContentPane().add(auswahlPanel, BorderLayout.SOUTH);
	}
    
    private JPanel erstelleInfoPanel()
    {
        _infoPanel = new JPanel(new BorderLayout());
        
        _vorstellungLabel = new JLabel("Vorstellung:                  ");
        _infoPanel.add(_vorstellungLabel, BorderLayout.WEST);
        
        _infoVorstellungLabel = new JLabel ("");
        _infoPanel.add(_infoVorstellungLabel, BorderLayout.CENTER);
        
        return _infoPanel;
    }
    private JPanel erstelleInfoPanel1()
    {
    	_infoPanel1 = new JPanel(new BorderLayout());
    	
        _anzahlPlaetzeLabel = new JLabel("Anzahl Plaetze:            ");
        _infoPanel1.add(_anzahlPlaetzeLabel, BorderLayout.WEST);
        
        _infoAnzahlPlaetzeLabel = new JLabel("");
        _infoPanel1.add(_infoAnzahlPlaetzeLabel, BorderLayout.CENTER);
        
        return _infoPanel1;
    }
     
    private JPanel erstelleInfoPanel2()
    {
    	_infoPanel2 = new JPanel(new BorderLayout());
    	
        _preisProPlaetzeLabel = new JLabel("Preis pro Platz:            ");
        _infoPanel2.add(_preisProPlaetzeLabel, BorderLayout.WEST);  
        
        _infoPreisProPlaetzeLabel = new JLabel("");
        _infoPanel2.add(_infoPreisProPlaetzeLabel, BorderLayout.CENTER);
        
        _euroLabel = new JLabel("Eurocent");
        _infoPanel2.add(_euroLabel, BorderLayout.EAST);
        
        return _infoPanel2;
    }
    
    private JPanel erstelleInfoPanel3()
       {
    	_infoPanel3 = new JPanel(new BorderLayout());
        _gesamtBetragLabel = new JLabel("Gesamtbetrag:             ");
        _infoPanel3.add(_gesamtBetragLabel, BorderLayout.WEST);
        
        _infoGesamtBetragLabel = new JLabel("");
        _infoPanel3.add(_infoGesamtBetragLabel, BorderLayout.CENTER);
        
        _euroLabel = new JLabel("Eurocent");
        _infoPanel3.add(_euroLabel, BorderLayout.EAST);
        
        return _infoPanel3;
    }
    
    private JPanel erstelleBezahlPanel()
    {
        _bezahlPanel = new JPanel(new BorderLayout());
        
        _barLabel = new JLabel("Bar:               ");
        _bezahlPanel.add(_barLabel, BorderLayout.WEST);
        
        _eintragBarField = new JTextField("");
        _eintragBarField.setText("0");
        _eintragBarField.setSize(100, 25);
        _bezahlPanel.add(_eintragBarField, BorderLayout.CENTER);
        
        _euroLabel = new JLabel("Eurocent");
        _bezahlPanel.add(_euroLabel, BorderLayout.EAST);
        
        return _bezahlPanel;
    }
    
    private JPanel erstelleBezahlPanel1()
    {
        _bezahlPanel1 = new JPanel(new BorderLayout());
        
        _rueckgeldLabel = new JLabel("Rückgeld:                ");
        _bezahlPanel.add(_rueckgeldLabel, BorderLayout.WEST);
        
        _infoRueckgeldLabel = new JLabel("");
        _bezahlPanel.add(_infoRueckgeldLabel, BorderLayout.CENTER);
        
        _euroLabel = new JLabel("Eurocent");
        _bezahlPanel.add(_euroLabel, BorderLayout.EAST);
        
        return _bezahlPanel1;
    }
    
    private JPanel erstelleAuswahlPanel()
    {       
        _auswahlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        _abbrechenButton = new JButton("Abbrechen");
        _auswahlPanel.add(_abbrechenButton); 
        
        _okButton = new JButton("        OK        ");
        _auswahlPanel.add(_okButton);
        
        return _auswahlPanel;
    }

    /**
     * Zeigt das Fenster an, nachdem sein Inhalt aktualisiert wurde.
     */
    public void zeigeFenster(Vorstellung vorstellung, int anzahlPlaetze, int preisProPlatz)
    {
        _frame.setSize(400, 300);
        _gesamtbetrag= anzahlPlaetze*preisProPlatz;
        
        _infoVorstellungLabel.setText(vorstellung.getFilm().getTitel());
        _infoAnzahlPlaetzeLabel.setText("" + anzahlPlaetze);
        _infoPreisProPlaetzeLabel.setText("" + preisProPlatz);
        _infoGesamtBetragLabel.setText("" + _gesamtbetrag);
        _infoRueckgeldLabel.setText("0000");
        
        _eintragBarField.setText(null);
        
        
        _frame.setVisible(true);
    }

    /**
     * Macht das Fenster für den Benutzer unsichtbar.
     */
    public void schliesseFenster()
    {
    	_frame.setVisible(false);
    	
    }
    
    /**
     * Aktualisiert das Rueckgeld.
     * 
     * @param rueckgeld Anzuzeigender String des Rückgelds
     */
    public void zeigeRueckgeld(String rueckgeld)
    {
    	 _infoRueckgeldLabel.setText(rueckgeld);
    }
    
    /**
     * Gibt den Ok-Button zurück.
     * 
     * @return Button zum Bestätigen des Kaufvorgangs
     */
    public JButton getokButton()
    {
        return _okButton;
    }
    
    /**
     * Gibt den Abbrechen-Button zurück.
     * 
     * @return Button zum Abbrechen des Kaufvorgangs
     */
    public JButton getabbrechenButton()
    {
        return _abbrechenButton;
    }
    
    /**
     * Gibt das Frame zurück, in dem die Widgets angeordnet sind.
     * 
     * @return Frame
     */
    public JFrame getUIframe()
    {
        return _frame;
    }
    
    /**
     * Gibt das Textfeld des Barbetrags zurück.
     * 
     * @return Textfeld des Barbetrags
     */
    public JTextField getTextField()
    {
    	return _eintragBarField;
    }
    
    public int getGesamtbetrag()
    {
    	return _gesamtbetrag;
    }
}
