package org.dyndns.richinet.JpoApi;

import java.util.Collection;
import java.util.Iterator;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;

/**
 * Class used to house anything one might want to store in a central lookup
 * which can affect anything within the application. It can be thought of as a
 * central context where any application data may be stored and watched.
 *
 * A singleton instance is created using @see getDefault(). This class is as
 * thread safe as Lookup. Lookup appears to be safe.
 *
 * @author Wade Chandler
 * @version 1.0
 */
public class CentralLookup extends AbstractLookup {

    private InstanceContent content = null;

    private static CentralLookup def = new CentralLookup();

    public CentralLookup( InstanceContent content ) {
        super( content );
        this.content = content;
    }

    public CentralLookup() {
        this( new InstanceContent() );
    }

    public void add( Object instance ) {
        content.add( instance );
    }

    public void remove( Object instance ) {
        content.remove( instance );
    }

    /**
     * Removes all objects of the supplied class and adds the supplied object
     *
     * @param instance
     */
    public void replace( Object instance ) {
        Collection infos = lookupAll( instance.getClass() );
        if ( !infos.isEmpty() ) {
            Iterator it = infos.iterator();
            while ( it.hasNext() ) {
                Object info = it.next();
                remove( info );
            }
        }
        add( instance );
    }

    public static CentralLookup getDefault() {
        return def;
    }
}
