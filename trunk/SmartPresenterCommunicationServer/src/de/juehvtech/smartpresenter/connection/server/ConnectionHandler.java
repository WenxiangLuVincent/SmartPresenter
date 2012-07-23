/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.juehvtech.smartpresenter.connection.server;

import de.juehvtech.smartpresenter.connection.commands.CommandSet;
import de.juehvtech.smartpresenter.connection.server.build.GuiLogger;
import de.juehvtech.smartpresenter.connection.server.build.SmartPresenterCommunicatonServer;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JUehV
 */
public class ConnectionHandler implements Runnable {

    private final ServerSocket server;
    private final GuiLogger logScreen;
    private String downloadPath = null;
    private final SmartPresenterCommunicatonServer commandHandler;

    public ConnectionHandler(int port, GuiLogger logScreen, SmartPresenterCommunicatonServer commandHandler) throws IOException {
        this.logScreen = logScreen;
        this.commandHandler = commandHandler;
        try {
            this.server = new ServerSocket(port);
        } catch (IOException ex) {
            String msg = " Error while createing server socket --> NO CONNECTION POSSIBLE";
            Logger.getLogger(ConnectionHandler.class.getName()).log(Level.SEVERE, msg, ex);
            logScreen.addLogMsg(ConnectionHandler.class.getName() + msg);
            throw ex;
        }
    }

    private void pointMode(InputStream in) throws IOException {
        int factor1;
        while ((factor1 = in.read()) > 0){
            if (factor1 > 1){
                
            }
        }
    }

    private String downloadMode(InputStream in) {
        return null;
    }

    private void handleConnection(Socket client) throws IOException {
        InputStream in = client.getInputStream();

        int factor1;
        int count = 0;
        while (((factor1 = in.read()) > 0) || count < 50) {
            if (factor1 > 0) {
                //handle command
                count = 0;
                switch (CommandSet.fromOrdinal(factor1)) {
                    case USB_STICK:
                        logScreen.addLogMsg("Start download");
                        downloadPath = downloadMode(in);
                        logScreen.addLogMsg("End download: " + downloadPath);
                        break;
                    case OPEN:
                        if (downloadPath != null) {
                            logScreen.addLogMsg("Open: " + downloadPath);
                            commandHandler.openPresentation(downloadPath);
                        }
                        break;
                    case CLOSE:
                        logScreen.addLogMsg("Close");
                        commandHandler.closePresentation();
                        break;
                    case NEXT:
                        logScreen.addLogMsg("Next");
                        commandHandler.gotoNextSlide();
                        break;
                    case PREV:
                        logScreen.addLogMsg("Prev");
                        commandHandler.gotoPreviousSlide();
                        break;
                    case POINT:
                        logScreen.addLogMsg("Start point mode");
                        pointMode(in);
                        break;
                    case FADE:
                        logScreen.addLogMsg("Fade to black");
                        commandHandler.fadeToBlack();
                        break;
                    default:
                        break;
                }
            } else {
                try {
                    //semi active wait
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ConnectionHandler.class.getName()).log(Level.SEVERE, "Problem while go to sleep", ex);
                }
                count++;
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            Socket client = null;
            try {
                client = server.accept();
                handleConnection(client);
            } catch (IOException ex) {
                String msg = " Error while handling the streams";
                Logger.getLogger(ConnectionHandler.class.getName()).log(Level.SEVERE, msg, ex);
                logScreen.addLogMsg(ConnectionHandler.class.getName() + msg);
            } finally {
                if (client != null) {
                    try {
                        client.close();
                    } catch (IOException e) {
                        String msg = " Error while closing the client connection";
                        Logger.getLogger(ConnectionHandler.class.getName()).log(Level.SEVERE, null);
                        logScreen.addLogMsg(ConnectionHandler.class.getName() + msg);
                    }
                }
            }
        }
    }
}
