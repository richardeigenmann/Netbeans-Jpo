/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dyndns.richinnet.netbeansjpo;

import java.util.logging.Logger;
import jpo.dataModel.Settings;
import org.openide.modules.ModuleInstall;

public class Installer extends ModuleInstall {

    private final static Logger LOGGER = Logger.getLogger( Installer.class.getName() );

    @Override
    public void restored() {
        Settings.loadSettings();
    }

}
