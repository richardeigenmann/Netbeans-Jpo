/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dyndns.richinet.JpoApi;

import jpo.dataModel.NodeNavigatorInterface;

/**
 * This class is passed around the Netbeans CentralLookup with the NodeNavigator
 * that allows interested subscribers to display the selected set of nodes.
 *
 * @author Richard Eigenmann
 */
public final class JpoNavigatorSelectionEvent {

    private NodeNavigatorInterface nodeNavigator = null;

    public JpoNavigatorSelectionEvent( NodeNavigatorInterface nodeNavigator ) {
        this.nodeNavigator = nodeNavigator;
    }

    public NodeNavigatorInterface getSelectionNavigator() {
        return nodeNavigator;
    }

}
