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
import jpo.dataModel.Settings;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
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

    private final fileHandlerDataObject context;

    public OpenJpoCollection( fileHandlerDataObject context ) {
        this.context = context;
    }

    @Override
    public void actionPerformed( ActionEvent ev ) {
        FileObject f = context.getPrimaryFile();
        File xmlFile = FileUtil.toFile( f );
        try {
            Settings.pictureCollection.fileLoad( xmlFile );
            NotifyDescriptor nd = new NotifyDescriptor.Message( "File loading done." );
            DialogDisplayer.getDefault().notify( nd );
        } catch ( FileNotFoundException ex ) {
            Exceptions.printStackTrace( ex );
        }

    }
}
