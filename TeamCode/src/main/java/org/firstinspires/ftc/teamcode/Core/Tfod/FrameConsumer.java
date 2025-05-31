package org.firstinspires.ftc.teamcode.Core.Tfod;

import android.graphics.Bitmap;

import org.firstinspires.ftc.teamcode.Core.Tfod.CanvasAnnotator;


/**
 * FrameConsumer is an interface that is implemented by the FTC TensorFlow object detector engine
 * in order to consume frames from a FrameGenerator.
 *
 * <p>This class is deprecated and will be removed in v10.0.
 */
public interface FrameConsumer {
    /**
     * init should be called from a FrameGenerator once, immediately after the bitmap has been
     * created. When called, the FTC TensorFlow object detector engine will initialize the internal
     * pipeline that will process frames.
     */
    void init(Bitmap input);

    /**
     * processFrame should be called from a FrameGenerator each time the bitmap (previously passed to
     * init) contains new data from the camera. It is expected that processFrame be called from the
     * same thread that previously called init.
     */
    CanvasAnnotator processFrame();
}
