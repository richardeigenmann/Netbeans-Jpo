/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.dyndns.richinet.JpoApi;

import java.util.Date;

public final class JpoEvent {

    private final Date date = new Date();
    private static int count = 0;
    private final int index;

    public JpoEvent() {
        index = count++;
    }

    public Date getDate() {
        return date;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return index + " - " + date;
    }

}