/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dyndns.richinet.viewer;

import java.beans.IntrospectionException;
import java.util.Enumeration;
import java.util.List;
import jpo.dataModel.Settings;
import jpo.dataModel.SortableDefaultMutableTreeNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;

/**
 *
 * @author richi
 */
public class SortableDefaultMutableTreeNodeChildFactory extends ChildFactory<SortableDefaultMutableTreeBean> {

    @Override
    protected boolean createKeys( List<SortableDefaultMutableTreeBean> list ) {
        SortableDefaultMutableTreeNode rootNode = Settings.pictureCollection.getRootNode();
        Enumeration<SortableDefaultMutableTreeNode> e = rootNode.children();
        while ( e.hasMoreElements() ) {
            SortableDefaultMutableTreeNode node = e.nextElement();
            list.add( new SortableDefaultMutableTreeBean( node ) );
        }
        return true;

    }

    @Override
    protected Node createNodeForKey( SortableDefaultMutableTreeBean key ) {
        SortableDefaultMutableTreeNetbeansNode netbeansNode = null;
        try {
            netbeansNode = new SortableDefaultMutableTreeNetbeansNode( key );
        } catch ( IntrospectionException ex ) {
            Exceptions.printStackTrace( ex );
        }
        return netbeansNode;
    }

}
