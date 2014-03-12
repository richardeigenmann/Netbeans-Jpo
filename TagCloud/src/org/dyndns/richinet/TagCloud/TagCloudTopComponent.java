/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dyndns.richinet.TagCloud;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import jpo.TagCloud.TagClickListener;
import jpo.TagCloud.TagCloud;
import jpo.dataModel.ArrayListNavigator;
import jpo.dataModel.DescriptionWordMap;
import jpo.dataModel.GroupNavigator;
import jpo.dataModel.Settings;
import jpo.dataModel.SortableDefaultMutableTreeNode;
import jpo.gui.InfoPanelController;
import jpo.gui.Jpo;
import org.dyndns.richinet.JpoApi.CentralLookup;
import org.dyndns.richinet.JpoApi.JpoNodeSelectionEvent;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
         dtd = "-//org.dyndns.richinet.TagCloud//TagCloud//EN",
        autostore = false
)
@TopComponent.Description(
         preferredID = "TagCloudTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration( mode = "properties", openAtStartup = true )
@ActionID( category = "Window", id = "org.dyndns.richinet.TagCloud.TagCloudTopComponent" )
@ActionReference( path = "Menu/Window" /*, position = 333 */ )
@TopComponent.OpenActionRegistration(
         displayName = "#CTL_TagCloudAction",
        preferredID = "TagCloudTopComponent"
)
@Messages( {
    "CTL_TagCloudAction=TagCloud",
    "CTL_TagCloudTopComponent=TagCloud Window",
    "HINT_TagCloudTopComponent=This is a TagCloud window"
} )
public final class TagCloudTopComponent extends TopComponent {

    private final TagCloud tagCloud = new TagCloud();
    private static final Logger LOGGER = Logger.getLogger( TagCloudTopComponent.class.getName() );
    private Lookup.Result lookupResult = null;

    public TagCloudTopComponent() {
        initComponents();
        setName( Bundle.CTL_TagCloudTopComponent() );
        setToolTipText( Bundle.HINT_TagCloudTopComponent() );
        rootPanel.add( tagCloud, BorderLayout.CENTER );
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rootPanel = new javax.swing.JPanel();

        rootPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(rootPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rootPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel rootPanel;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        CentralLookup cl = CentralLookup.getDefault();
        Lookup.Template template = new Lookup.Template( JpoNodeSelectionEvent.class );
        lookupResult = cl.lookup( template );
        lookupResult.addLookupListener( new JpoNodeSelectionLookupListener() );
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
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

    private class JpoNodeSelectionLookupListener implements LookupListener {

        @Override
        public void resultChanged( LookupEvent event ) {
            LOGGER.info( "Recieved a LookupEvent" );
            Object object = event.getSource();
            if ( object != null ) {
                Lookup.Result r = (Lookup.Result) object;
                Collection<JpoNodeSelectionEvent> nodeSelectionEvents = r.allInstances();
                if ( nodeSelectionEvents.isEmpty() ) {
                    LOGGER.info( "Empty result set" );
                } else {
                    Iterator<JpoNodeSelectionEvent> it = nodeSelectionEvents.iterator();
                    while ( it.hasNext() ) {
                        LOGGER.info( "Iterating through the nodeSelectionEvents" );
                        JpoNodeSelectionEvent nodeSelectionEvent = it.next();
                        tagCloud.setMaxWordsToShow( Settings.tagCloudWords );
                        dwm = new DescriptionWordMap( nodeSelectionEvent.getSelectionNavigator() );
                        tagCloud.setWordMap( dwm );
                        tagCloud.addTagClickListener( new MyTagClickListener() );
                        tagCloud.showWords();
                    }
                }
            }
        }
    }

    private DescriptionWordMap dwm;

    private class MyTagClickListener implements TagClickListener {

        @Override
        public void tagClicked( final String key ) {
            HashSet<SortableDefaultMutableTreeNode> hs = dwm.getWordNodeMap().get( key );
            ArrayList<SortableDefaultMutableTreeNode> set = new ArrayList<SortableDefaultMutableTreeNode>( hs );
            ArrayListNavigator alb = new ArrayListNavigator( key, set );
            final JpoNodeSelectionEvent event = new JpoNodeSelectionEvent( alb );

            Runnable r = new Runnable() {
                @Override
                public void run() {
                    CentralLookup cl = CentralLookup.getDefault();
                    Collection infos = cl.lookupAll( JpoNodeSelectionEvent.class );
                    if ( !infos.isEmpty() ) {
                        Iterator it = infos.iterator();
                        while ( it.hasNext() ) {
                            Object info = it.next();
                            cl.remove( info );
                        }
                    }

                    cl.add( event );
                }
            };
            SwingUtilities.invokeLater( r );
        }

    }

}
