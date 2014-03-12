/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dyndns.richinet.ThumbnailPanel;

import java.util.logging.Logger;
import jpo.dataModel.Settings;
import jpo.gui.Jpo;
import jpo.gui.ThumbnailCreationFactory;
import org.openide.modules.ModuleInstall;

public class Installer extends ModuleInstall {

    private final static Logger LOGGER = Logger.getLogger( Installer.class.getName() );
    
    @Override
    public void restored() {
        LOGGER.info( "Starting up the ThumbnailCreationThreads");
        for ( int i = 1; i <= Settings.numberOfThumbnailCreationThreads; i++ ) {
            Jpo.THUMBNAIL_FACTORIES.add( new ThumbnailCreationFactory() );
        }
    }

}
