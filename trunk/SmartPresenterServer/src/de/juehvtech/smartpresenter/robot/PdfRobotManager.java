/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.juehvtech.smartpresenter.robot;

import de.juehvtech.smartpresenter.connection.server.GuiLogger;
import java.awt.AWTException;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JUehV
 */
public class PdfRobotManager extends RobotManager {

    public PdfRobotManager(GuiLogger logScreen) throws AWTException {
        super(logScreen);
    }

    @Override
    public void gotoNextSlide() {
        if (fileOpen) {
            robot.keyPress(KeyEvent.VK_RIGHT);
            robot.keyRelease(KeyEvent.VK_RIGHT);
        }
    }

    @Override
    public void gotoPreviousSlide() {
        if (fileOpen) {
            robot.keyPress(KeyEvent.VK_LEFT);
            robot.keyRelease(KeyEvent.VK_LEFT);
        }
    }

    @Override
    public void fadeToBlack() {
        String msg = " Adobe Reader doesn't support fade to black...";
        Logger.getLogger(RobotManager.class.getName()).log(Level.INFO, msg);
        logScreen.addLogMsg(RobotManager.class.getName() + msg);
    }

    @Override
    public void toggleFullscreen() {
        if (fileOpen) {
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_L);
            robot.keyRelease(KeyEvent.VK_L);
            robot.keyRelease(KeyEvent.VK_CONTROL);
        }
    }

    @Override
    public boolean openPresentation(String filePath) {
        boolean result = openFile(filePath);
        if (result) {
            toggleFullscreen();
        }
        return result;
    }
}
