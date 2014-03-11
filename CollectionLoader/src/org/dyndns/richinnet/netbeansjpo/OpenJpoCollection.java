/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dyndns.richinnet.netbeansjpo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Logger;
import jpo.dataModel.GroupNavigator;
import jpo.dataModel.Settings;
import org.dyndns.richinet.JpoApi.CentralLookup;
import org.dyndns.richinet.JpoApi.JpoNodeSelectionEvent;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle.Messages;

@ActionID(
         category = "File",
        id = "org.dyndns.richinnet.netbeansjpo.OpenJpoCollection"
)
@ActionRegistration(
         displayName = "#CTL_OpenJpoCollection"
)
@ActionReference( path = "Loaders/text/xml/Actions", position = 0 )
@Messages( "CTL_OpenJpoCollection=Open JPO Collection" )
public final class OpenJpoCollection implements ActionListener {

    private static final Logger LOGGER = Logger.getLogger( OpenJpoCollection.class.getName() );

    private final fileHandlerDataObject context;

    private final CentralLookup centralLookup = CentralLookup.getDefault();

    public OpenJpoCollection( fileHandlerDataObject context ) {
        this.context = context;
        context.associateLookup();
    }

    @Override
    public void actionPerformed( ActionEvent ev ) {
        FileObject f = context.getPrimaryFile();
        File xmlFile = FileUtil.toFile( f );
        try {
            Settings.pictureCollection.fileLoad( xmlFile );
            JpoNodeSelectionEvent event = new JpoNodeSelectionEvent( new GroupNavigator( Settings.pictureCollection.getRootNode() ) );
            centralLookup.add( event );
        } catch ( FileNotFoundException ex ) {
            Exceptions.printStackTrace( ex );
        }

    }

}
