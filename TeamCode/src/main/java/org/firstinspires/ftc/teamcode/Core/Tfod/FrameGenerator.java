package org.firstinspires.ftc.teamcode.Core.Tfod;
/**
 * FrameGenerator provides a unified interface for receiving consecutive frames from a sequence.
 *
 * <p>Typically, consecutive frames will be different (as if generated from a video, camera, or
 * other time-dependent sequence), but this is not required by the interface.
 *
 * <p>This class is deprecated and will be removed in v10.0.
 *
 * @author Vasu Agrawal
 * @author lizlooney@google.com (Liz Looney)
 */
public interface FrameGenerator {

    /**
     * Returns the CameraInformation for this FrameGenerator.
     */
    CameraInformation getCameraInformation();

    /**
     * Attach the given FrameConsumer to this FrameGenerator. If frameConsumer is null, detach the
     * previous attached FrameConsumer.
     * The FrameConsumer will accept frames and send them to the TensorFlow object detector engine
     * and an object tracker (if enabled).
     */
    void setFrameConsumer(FrameConsumer frameConsumer);
}
