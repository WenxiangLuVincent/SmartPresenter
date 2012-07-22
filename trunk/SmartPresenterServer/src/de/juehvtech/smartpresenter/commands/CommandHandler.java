/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.juehvtech.smartpresenter.commands;

import de.juehvtech.smartpresenter.connection.server.build.GuiLogger;
import de.juehvtech.smartpresenter.connection.server.build.SmartPresenterCommunicatonServer;
import de.juehvtech.smartpresenter.robot.PdfRobotManager;
import de.juehvtech.smartpresenter.robot.RobotManager;
import java.awt.AWTException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JUehV
 */
public class CommandHandler implements SmartPresenterCommunicatonServer {

    GuiLogger logScreen;
    RobotManager robotManager;

    public CommandHandler(GuiLogger logger) {
        this.logScreen = logger;
    }

    @Override
    public void openPresentation(String filePath) {
        try {
            switch (RobotManager.getFileType(filePath)) {
                case PDF:
                    robotManager = new PdfRobotManager(logScreen);
                    break;
                case PPT:
                    //TODO implement
                    robotManager = null;
                    break;
                case PREZI:
                    //TODO maybe fallback will be enough
                    //fallback to standard keys
                    robotManager = null;
                    break;
                case UNKNOWN:
                    //fallback to standard keys
                    robotManager = null;
                    break;

            }
            if (robotManager != null) {
                robotManager.openPresentation(filePath);
            }
        } catch (AWTException ex) {
            String msg = " Error while opening the presentation file.";
            Logger.getLogger(CommandHandler.class.getName()).log(Level.SEVERE, msg, ex);
            logScreen.addLogMsg(CommandHandler.class.getName() + msg);
        }
    }

    @Override
    public void closePresentation() {
        if (robotManager != null) {
            robotManager.closePresentation();
        }
    }

    @Override
    public void gotoNextSlide() {
        if (robotManager != null) {
            robotManager.gotoNextSlide();
        }
    }

    @Override
    public void gotoPreviousSlide() {
        if (robotManager != null) {
            robotManager.gotoPreviousSlide();
        }
    }

    @Override
    public void pointAt(float x, float y) {
        if (robotManager != null) {
            robotManager.pointAt(x, y);
        }
    }

    @Override
    public void fadeToBlack() {
        if (robotManager != null) {
            robotManager.fadeToBlack();
        }
    }

    @Override
    public GuiLogger getGuiLogger() {
        return logScreen;
    }
}
