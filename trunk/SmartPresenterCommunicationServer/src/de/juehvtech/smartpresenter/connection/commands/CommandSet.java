/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.juehvtech.smartpresenter.connection.commands;

/**
 *
 * @author JUehV
 */
public enum CommandSet {
    UNKNOWN,USB_STICK,OPEN,CLOSE,NEXT,PREV,POINT,FADE;
    
    public static CommandSet fromOrdinal(int ordinal){
        for (CommandSet item : CommandSet.values()){
            if (item.ordinal() == ordinal){
                return item;
            }
        }
        return UNKNOWN;
    }
}
