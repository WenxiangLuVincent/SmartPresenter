/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.juehvtech.smartpresenter.robot;

import de.juehvtech.smartpresenter.connection.server.build.GuiLogger;
import de.juehvtech.smartpresenter.files.FileType;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JUehV
 */
public abstract class RobotManager {

    protected GuiLogger logScreen;
    protected Robot robot;
    protected boolean fileOpen = false;

    /**
     * Creates a new PDF presenter. Sets the fullscreen wait period to 5
     * seconds.
     *
     * @throws AWTException thrown, if keyboard inputs are not supported.
     */
    public RobotManager(GuiLogger logScreen) throws AWTException {
        this.logScreen = logScreen;
        robot = new Robot();
    }

    public static FileType getFileType(String filePath) {
        String fileExtention = filePath.substring(filePath.lastIndexOf('.') + 1);
        if (fileExtention.equalsIgnoreCase("pdf")) {
            return FileType.PDF;
        } else if (fileExtention.equalsIgnoreCase("ppt")) {
            return FileType.PPT;
        } else if (fileExtention.equalsIgnoreCase("prezi")) {
            return FileType.PREZI;
        } else {
            return FileType.UNKNOWN;
        }
    }

    protected boolean openFile(String filePath) {
        try {
            Desktop.getDesktop().open(new File(filePath));
            fileOpen = true;
            return true;
        } catch (Exception ex) {
            String msg = " Error while opening the presentation file.";
            Logger.getLogger(RobotManager.class.getName()).log(Level.SEVERE, msg, ex);
            logScreen.addLogMsg(RobotManager.class.getName() + msg);
            return false;
        }
    }

    public void closePresentation() {
        if (fileOpen) {
            robot.keyPress(KeyEvent.VK_ALT);
            robot.keyPress(KeyEvent.VK_F4);
            robot.keyRelease(KeyEvent.VK_F4);
            robot.keyRelease(KeyEvent.VK_ALT);
        }
    }

    public void pointAt(float x, float y) {
        if (fileOpen) {
            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            robot.mouseMove((int) (dimension.getWidth() * x), (int) (dimension.getHeight() * y));
        }
    }

    public abstract boolean openPresentation(String filePath);

    public abstract void gotoNextSlide();

    public abstract void gotoPreviousSlide();

    public abstract void fadeToBlack();

    protected abstract void toggleFullscreen();
}
