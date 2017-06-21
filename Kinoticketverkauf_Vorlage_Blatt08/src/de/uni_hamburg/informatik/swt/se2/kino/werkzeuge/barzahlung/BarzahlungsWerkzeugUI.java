package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.barzahlung;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

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
	private JPanel _infoPanel;
    private JPanel _infoPanel1;
    private JPanel _infoPanel2;
    private JPanel _infoPanel3;
    private JPanel _bezahlPanel;
    private JPanel _bezahlPanel1;
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
        
	    //_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    _frame.getContentPane().setLayout(new BorderLayout());
	
	    /**
	     * Methoden, um die einzelnen Panel zu erstellen
	     */
	    erstelle_infoPanel();
	    erstelle_infoPanel1();
	    erstelle_infoPanel2();
	    erstelle_infoPanel3();
	    erstelle_bezahlPanel();
	    erstelle_bezahlPanel1();
	    erstelle_auswahlPanel();
	    
	    /**
	    JComponent infoPanel = erstelle_infoPanel();
	    JComponent bezahlPanel = erstelle_bezahlPanel();
	    JComponent auswahlPanel = erstelle_auswahlPanel();
	    */
	    /**
	     * die Splitter splitten einige Panelbereiche, wodurch mehr als 5 Panel (für Nord, Süd, Ost; West, Zentrum) 
	     * im Fenster erscheinen können
	     */
	    
	    //JSplitPane split = newJSplitPane(JSplitPane.VERTICAL_SPLIT, splitter, splitter1);
	    JSplitPane splitter = new JSplitPane(JSplitPane.VERTICAL_SPLIT, _infoPanel, _infoPanel1);
	    _frame.getContentPane().add(splitter, BorderLayout.NORTH);
	    JSplitPane splitter1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, _infoPanel2, _infoPanel3);
	    _frame.getContentPane().add(splitter1, BorderLayout.CENTER);
	    JSplitPane splitter2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, _bezahlPanel, _bezahlPanel1);
	    _frame.getContentPane().add(splitter2, BorderLayout.CENTER);

	    _frame.getContentPane().add(_auswahlPanel, BorderLayout.SOUTH);
	}

    /**
     * Panel, das den 1. Teil der Informationen darstellt, anstatt "" kommt bei _infovorstellungLabel später die Info
     * zu der ausgesuchten Vorstellung.
     * @return
     */
    private JPanel erstelle_infoPanel()
    {
        _infoPanel = new JPanel(new BorderLayout());
        
        _vorstellungLabel = new JLabel("Vorstellung");
        _infoPanel.add(_vorstellungLabel, BorderLayout.WEST);
        
        _infovorstellungLabel = new JLabel ("");
        _infoPanel.add(_infovorstellungLabel, BorderLayout.CENTER);
        return _infoPanel;
    }
    private JPanel erstelle_infoPanel1()
    {
    	_infoPanel1 = new JPanel(new BorderLayout());
        _anzahlPlaetzeLabel = new JLabel("Anzahl Plaetze");
        _infoPanel1.add(_anzahlPlaetzeLabel, BorderLayout.WEST);
        
        _infoAnzahlPlaetzeLabel = new JLabel("");
        _infoPanel1.add(_infoAnzahlPlaetzeLabel, BorderLayout.CENTER);
        return _infoPanel1;
    }
     
    private JPanel erstelle_infoPanel2()
    {
    	_infoPanel2 = new JPanel(new BorderLayout());
        _preisProPlaetzeLabel = new JLabel("Preis pro Plaetze");
        _infoPanel2.add(_preisProPlaetzeLabel, BorderLayout.WEST);  
        
        _infoPreisProPlaetzeLabel = new JLabel("");
        _infoPanel2.add(_infoPreisProPlaetzeLabel, BorderLayout.CENTER);
        
        _euroLabel = new JLabel("Euro");
        _infoPanel2.add(_euroLabel, BorderLayout.EAST);
        
        
        return _infoPanel2;
        
    }
    private JPanel erstelle_infoPanel3()
       {
    	_infoPanel3 = new JPanel(new BorderLayout());
        _gesamtBetragLabel = new JLabel("gesamter Betrag");
        _infoPanel3.add(_gesamtBetragLabel, BorderLayout.WEST);
        
        _infoGesamtBetragLabel = new JLabel("");
        _infoPanel3.add(_infoGesamtBetragLabel, BorderLayout.CENTER);
        
        _euroLabel = new JLabel("Euro");
        _infoPanel3.add(_euroLabel, BorderLayout.EAST);
        
        //_infoPanel3.setBorder(BorderFactory.createEmptyBorder(5,10,5,0));
        //_frame.add(_infoPanel3, BorderLayout.NORTH);
        return _infoPanel3;
    }
    
    private JPanel erstelle_bezahlPanel()
    {
        _bezahlPanel = new JPanel(new BorderLayout());
        
        _barLabel = new JLabel("Bezahlt");
        //_barLabel.setFont(new Font("Courier", Font.BOLD, 36));
        _bezahlPanel.add(_barLabel, BorderLayout.WEST);
        
        //_eintragBarField = new JTextField("");
        //_bezahlPanel.add(_eintragBarField, BorderLayout.CENTER);
        
        _euroLabel = new JLabel("Euro");
        _bezahlPanel.add(_euroLabel, BorderLayout.EAST);
        
       // _frame.add(_bezahlPanel, BorderLayout.CENTER);
         return _bezahlPanel;
   }
    
    private JPanel erstelle_bezahlPanel1()
    {
        
        _bezahlPanel1 = new JPanel(new BorderLayout());
        
        _rueckgeldLabel = new JLabel("Rueckgeld");
        _bezahlPanel.add(_rueckgeldLabel, BorderLayout.WEST);
        
        _infoRueckgeldLabel = new JLabel("");
        _bezahlPanel.add(_rueckgeldLabel, BorderLayout.CENTER);
        
        _euroLabel = new JLabel("Euro");
        _bezahlPanel.add(_euroLabel, BorderLayout.EAST);
        
        
        //_frame.add(_bezahlPanel1, BorderLayout.CENTER);
        return _bezahlPanel1;

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
     * Zeigt das Fenster an.
     */
    public void zeigeFenster()
    {
        _frame.setSize(500, 500);
        _frame.setVisible(true);
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

