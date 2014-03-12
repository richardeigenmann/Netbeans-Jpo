/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dyndns.richinet.viewer;

import java.awt.Image;
import java.beans.IntrospectionException;
import javax.swing.ImageIcon;
import jpo.dataModel.GroupInfo;
import jpo.dataModel.Settings;
import jpo.dataModel.SortableDefaultMutableTreeNode;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Children;

/**
 *
 * @author richi
 */
public class SortableDefaultMutableTreeNetbeansNode extends BeanNode {

    private final SortableDefaultMutableTreeBean bean;
    
    public SortableDefaultMutableTreeNetbeansNode( SortableDefaultMutableTreeBean bean ) throws IntrospectionException {
        super( bean, Children.create( new PartChildFactory( bean ), true ) );
        this.bean = bean;
        setDisplayName( bean.getNodeDescription() );
        setShortDescription( bean.getNodeDescription() );
    }

    @Override
    public Image getIcon( int type ) {
        SortableDefaultMutableTreeNode realNode = bean.getSortableDefaultMutableTreeNode();
        if ( realNode.getUserObject() instanceof GroupInfo ) {
            return CLOSED_FOLDER_ICON;
        } else {
            return PICTURE_ICON;
        }
    }

    @Override
    public Image getOpenedIcon( int type ) {
        SortableDefaultMutableTreeNode realNode = bean.getSortableDefaultMutableTreeNode();
        if ( realNode.getUserObject() instanceof GroupInfo ) {
            return OPEN_FOLDER_ICON;
        } else {
            return PICTURE_ICON;
        }
    }

    /**
     * Icon of a closed folder to be used on groups that are not expanded in the
     * JTree.
     */
    private static final Image CLOSED_FOLDER_ICON = new ImageIcon( Settings.cl.getResource( "jpo/images/icon_folder_closed.gif" ) ).getImage();

    /**
     * Icon of an open folder to be used on groups that are expanded in the
     * JTree.
     */
    private static final Image OPEN_FOLDER_ICON = new ImageIcon( Settings.cl.getResource( "jpo/images/icon_folder_open.gif" ) ).getImage();

    /**
     * Icon of a picture for use on picture bearing nodes in the JTree.
     */
    private static final Image PICTURE_ICON = new ImageIcon( Settings.cl.getResource( "jpo/images/icon_picture.gif" ) ).getImage();
}
