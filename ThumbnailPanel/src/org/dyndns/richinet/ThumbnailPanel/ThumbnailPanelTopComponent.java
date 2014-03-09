/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dyndns.richinet.ThumbnailPanel;

import java.util.Collection;
import java.util.logging.Logger;
import jpo.gui.ThumbnailPanelController;
import org.dyndns.richinet.JpoApi.JpoEvent;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.NbBundle.Messages;
import org.openide.util.Utilities;
import org.openide.windows.TopComponent;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
         dtd = "-//org.dyndns.richinet.ThumbnailPanel//ThumbnailPanel//EN",
        autostore = false
)
@TopComponent.Description(
         preferredID = "ThumbnailPanelTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration( mode = "editor", openAtStartup = true )
@ActionID( category = "Window", id = "org.dyndns.richinet.ThumbnailPanel.ThumbnailPanelTopComponent" )
@ActionReference( path = "Menu/Window" /*, position = 333 */ )
@TopComponent.OpenActionRegistration(
         displayName = "#CTL_ThumbnailPanelAction",
        preferredID = "ThumbnailPanelTopComponent"
)
@Messages( {
    "CTL_ThumbnailPanelAction=ThumbnailPanel",
    "CTL_ThumbnailPanelTopComponent=ThumbnailPanel Window",
    "HINT_ThumbnailPanelTopComponent=This is a ThumbnailPanel window"
} )
//@ServiceProvider( service = JpoNodeSelectionListener.class )
public final class ThumbnailPanelTopComponent extends TopComponent implements LookupListener {

    private static final Logger LOGGER = Logger.getLogger( ThumbnailPanelTopComponent.class.getName() );

    private final ThumbnailPanelController myPanelController;

    public ThumbnailPanelTopComponent() {
        LOGGER.severe( "Creating a ThumbnailPanelTopComponent" );
        //Thread.dumpStack();
        initComponents();
        myPanelController = new ThumbnailPanelController( rootJScrollPane );
        setName( Bundle.CTL_ThumbnailPanelTopComponent() );
        setToolTipText( Bundle.HINT_ThumbnailPanelTopComponent() );

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rootJScrollPane = new javax.swing.JScrollPane();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rootJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rootJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane rootJScrollPane;
    // End of variables declaration//GEN-END:variables

    private Lookup.Result<JpoEvent> result = null;

    @Override
    public void componentOpened() {
        result = Utilities.actionsGlobalContext().lookupResult( JpoEvent.class );
        result.addLookupListener( this );
    }

    @Override
    public void componentClosed() {
        result.removeLookupListener( this );
    }

    
    void writeProperties( java.util.Properties p ) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty( "version", "1.0" );
        // TODO store your settings
    }

    void readProperties( java.util.Properties p ) {
        String version = p.getProperty( "version" );
        // TODO read your settings according to their version
    }

    @Override
    public void resultChanged( LookupEvent le ) {
        Collection<? extends JpoEvent> allEvents = result.allInstances();
        if (!allEvents.isEmpty()) {
            JpoEvent event = allEvents.iterator().next();
            LOGGER.info("resultChanged received: " + event.toString());
        } else {
        }
    }

}
