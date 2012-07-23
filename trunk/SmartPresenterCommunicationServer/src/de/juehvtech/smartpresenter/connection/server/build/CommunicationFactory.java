/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.juehvtech.smartpresenter.connection.server.build;

import de.juehvtech.smartpresenter.connection.server.ConnectionHandler;
import java.awt.Frame;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JUehV
 */
public class CommunicationFactory {
    SmartPresenterCommunicatonServer handler;

    private CommunicationFactory() {
    }
    
    //blocken mit thread.join
    public static void buildAndLaunch(SmartPresenterCommunicatonServer handler, Frame dialogOrigin, GuiLogger logger){
        try {
            Thread runner = new Thread(new ConnectionHandler(1234, logger, handler));
            runner.start();
            logger.addLogMsg("Server started.");
            runner.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(CommunicationFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CommunicationFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
