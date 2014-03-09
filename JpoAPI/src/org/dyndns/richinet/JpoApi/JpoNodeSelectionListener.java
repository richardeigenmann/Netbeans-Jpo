package org.dyndns.richinet.JpoApi;

import jpo.dataModel.SortableDefaultMutableTreeNode;

/**
 *
 * @author Richard Eigenmann
 */
public interface JpoNodeSelectionListener {

    /**
     * The implementing class would need to do respond to the selection of
     * a particular node in the JPO model.
     * 
     * @param node  The node which was selected in the JPO data model
     */
    void jpoNodeSelected( SortableDefaultMutableTreeNode node);
    
}
