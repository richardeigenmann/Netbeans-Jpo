/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dyndns.richinet.viewer;

import jpo.dataModel.SortableDefaultMutableTreeNode;

/**
 *
 * @author Richard Eigenmann
 */
public class SortableDefaultMutableTreeBean {

    public String getNodeDescription() {
        return node.toString();
    }

    private final SortableDefaultMutableTreeNode node;
    
    public SortableDefaultMutableTreeNode getSortableDefaultMutableTreeNode() {
        return node;
    }

    public SortableDefaultMutableTreeBean( SortableDefaultMutableTreeNode node ) {
        this.node = node;
    }

}
