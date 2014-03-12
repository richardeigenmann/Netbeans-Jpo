package org.dyndns.richinet.viewer;

import java.beans.IntrospectionException;
import java.util.Enumeration;
import java.util.List;
import jpo.dataModel.SortableDefaultMutableTreeNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;

/**
 *
 * @author richi
 */
class PartChildFactory extends ChildFactory< SortableDefaultMutableTreeBean> {

    private final SortableDefaultMutableTreeBean bean;

    public PartChildFactory( SortableDefaultMutableTreeBean bean ) {
        this.bean = bean;
    }

    @Override
    protected boolean createKeys( List<SortableDefaultMutableTreeBean> list ) {
        SortableDefaultMutableTreeNode realNode = bean.getSortableDefaultMutableTreeNode();
        Enumeration<SortableDefaultMutableTreeNode> e = realNode.children();
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
