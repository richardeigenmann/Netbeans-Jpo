/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dyndns.richinet.JpoApi;

import jpo.dataModel.SortableDefaultMutableTreeNode;

/**
 * This class is passed around the Netbeans CentralLookup with the Node
 * that allows interested subscribers to display stuff about the selected node
 *
 * @author Richard Eigenmann
 */
public final class JpoNodeSelectionEvent {

    private SortableDefaultMutableTreeNode node = null;

    public JpoNodeSelectionEvent( SortableDefaultMutableTreeNode nodeNavigator ) {
        this.node = nodeNavigator;
    }

    public SortableDefaultMutableTreeNode getSelectionNavigator() {
        return node;
    }

}
