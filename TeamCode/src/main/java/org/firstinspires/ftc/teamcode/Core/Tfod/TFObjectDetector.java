package org.firstinspires.ftc.teamcode.Core.Tfod;


import android.app.Activity;
import java.util.List;

/**
 * Interface for TensorFlow Object Detector.
 *
 * <p>This class is deprecated and will be removed in v10.0.
 *
 * @author Vasu Agrawal
 * @author lizlooney@google.com (Liz Looney)
 */

public interface TFObjectDetector {
    /**
     * Activates this TFObjectDetector so it starts recognizing objects.
     */
    void activate();

    /**
     * Deactivates this TFObjectDetector so it stops recognizing objects.
     */
    void deactivate();

    /**
     * Set the minimum confidence at which to keep recognitions.
     */
    public void setMinResultConfidence(float minResultConfidence);

    /**
     * Sets the number of pixels to obscure on the left, top, right, and bottom edges of each image
     * passed to the TensorFlow object detector. The size of the images are not changed, but the
     * pixels in the margins are colored black.
     */
    void setClippingMargins(int left, int top, int right, int bottom);

    /**
     * Indicates that only the zoomed center area of each image will be passed to the TensorFlow
     * object detector. For no zooming, set magnification to 1.0.
     */
    void setZoom(double magnification);

    /**
     * Returns the list of recognitions, but only if they are different than the last call to {@link #getUpdatedRecognitions()}.
     */
    List<Recognition> getUpdatedRecognitions();

    /**
     * Returns the list of recognitions.
     */
    List<Recognition> getRecognitions();

    /**
     * Perform whatever cleanup is necessary to release all acquired resources.
     */
    void shutdown();
}
