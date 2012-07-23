package de.juehvtech.smartpresenter.connection.server.build;

import de.juehvtech.smartpresenter.connection.server.build.GuiLogger;

/**
 * Interface for the server side of the SmartPresenter-System
 *
 * @author Jens Heuschkel
 */
public interface SmartPresenterCommunicatonServer {

    /**
     * Gives you a gui logger.
     *
     * @return the gui logger
     */
    GuiLogger getGuiLogger();

    /**
     * Opens the presentation in fullscreen mode. You have to take care, that
     * the file is present.
     *
     * @param filePath specifies the local file path (at the computer) to the
     * presentation
     */
    void openPresentation(String filePath);

    /**
     * Closes the presentation. (And server app)
     */
    void closePresentation();

    /**
     * Goes to the next slide.
     */
    void gotoNextSlide();

    /**
     * Goes to the previous slide.
     */
    void gotoPreviousSlide();

    /**
     * Points at the specified point. Remember that 0,0 is the left upper
     * corner. The right down point is 1,1.
     *
     * @param x specifies the x point
     * @param y specifies the y point
     */
    void pointAt(float x, float y);

    /**
     * Fades the screen to black or goes back to presentation if it was already
     * black.
     */
    void fadeToBlack();
}
