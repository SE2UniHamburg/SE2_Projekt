package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.barzahlung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.uni_hamburg.informatik.swt.se2.kino.materialien.Vorstellung;
import de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.ObservableSubwerkzeug;

/**
 * Mit diesem Werkzeug können Plätze verkauft werden. Es wird aufgerufen, sobald
 * die im KassenWerkzeug ausgewählten Plätze verkauft werden sollen. Es arbeitet
 * auf einer Vorstellung als Material. Mit ihm kann angezeigt werden, in welcher
 * Vorstellung wie viele Plätze ausgewählt wurden und wie viel ein einzelner
 * Sitzplatz und alle zusammen kosten sollen. Außerdem kann der Benutzer einen
 * bezahlten Barbetrag eingeben, woraus automatisch das zu zahlende Rückgeld
 * berechnet wird.
 * 
 * Dieses Werkzeug ist ein eingebettetes Subwerkzeug.
 * 
 * @author SE2-Team
 * @version SoSe 2017
 */
public class BarzahlungsWerkzeug extends ObservableSubwerkzeug
{
    // Die aktuelle Vorstellung, deren Plätze ausgewählt wurden.
    private Vorstellung _vorstellung;
    private int _anzahlPlaetze;
    private int _preisProPlatz;
    
    private BarzahlungsWerkzeugUI _ui;


    /**
     * Initialisiert das BarzahlungsWerkzeug.
     */
    public BarzahlungsWerkzeug()
    {
        _ui = new BarzahlungsWerkzeugUI();
        registriereUIAktionen();
    }
    
    /**
     * Initialisiert das BarzahlungsWerkzeug und macht das Fenster sichtbar.
     * 
     * @param vorstellung Die Vorstellung der zu buchenden Plätze.
     * @param anzahlPlaetze Die Anzahl der zu buchenden Plätze:
     * @param preisProPlatz Der Preis für einen der Plätze.
     */
    public void zeigeFenster(Vorstellung vorstellung, int anzahlSitzplaetze,
            int preisProSitzplatz)
    {
        _vorstellung = vorstellung;
        _anzahlPlaetze = anzahlSitzplaetze;
        _preisProPlatz = preisProSitzplatz;
        _ui.zeigeFenster(_vorstellung, _anzahlPlaetze, _preisProPlatz);
    }
    
    /**
     * Fügt der UI die Funktionalität hinzu mit entsprechenden Listenern.
     */
    private void registriereUIAktionen()
    {
        _ui.getokButton().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                reagiereAufOkButton();
            }
        });
        
        _ui.getabbrechenButton().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                reagiereAufAbbrechenButton();
            }
        });
        
        _ui.getTextField().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                reagiereAufTextFeld();
            }
        });
    }
    
    /**
     * Informiert das KassenWerkzeug darüber, dass die Karten bezahlt wurden
     * und macht das Fenster anschließend unsichtbar.
     */
    private void reagiereAufOkButton()
    {
        informiereUeberAenderung();
        _ui.schliesseFenster();
    }
    
    /**
     * Macht das Fenster unsichtbar.
     */
    private void reagiereAufAbbrechenButton()
    {
        _ui.schliesseFenster();
    }
    
    /**
     * Berechnet das Rückgabegeld und lässt die UI es anzeigen. Wird das
     * eingegebene bereits bezahlte Bargeld nicht akzeptiert, so wird kein
     * Rückgeld ausgegeben.
     */
    private void reagiereAufTextFeld()
    {
    	//TODO reg Ausdruck
        String bar = _ui.getTextField().getText();
        if (bar.matches("[0-9]+")) //[0-9]^+)
        {
        	int bar1 = Integer.parseInt(_ui.getTextField().getText());
            int rueckgabe = 0;
            //rueckgabe = bar - _ui.getGesamtbetrag();
			_ui.zeigeRueckgeld(""+ rueckgabe);

        }
        else
        {
            _ui.zeigeRueckgeld("");
        }
    }
}
