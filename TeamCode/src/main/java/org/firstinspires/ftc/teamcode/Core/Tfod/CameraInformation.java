package org.firstinspires.ftc.teamcode.Core.Tfod;

import java.util.Objects;

/**
 * Class representing information about the camera needed for object detection.
 *
 * <p>This class is deprecated and will be removed in v10.0.
 *
 * @author lizlooney@google.com (Liz Looney)
 */

public class CameraInformation {
    /**
     * The width of frames from the camera, in pixels.
     */
    public final int width;
    /**
     * The height of frames from the camera, in pixels.
     */
    public final int height;
    /**
     * The rotation of the camera, in degrees. Must be 0, 90, 180, or 270.
     */
    public final int rotation;
    /**
     * The horizontal focal length of the camera, in pixels. The focal length is used by a
     * Recognition to estimate the angle to the detected object.
     */
    public final float horizontalFocalLength;
    /**
     * The vertical focal length of the camera, in pixels. The focal length is used by a
     * Recognition to estimate the angle to the detected object.
     */
    public final float verticalFocalLength;

    /**
     * Constructs a CameraInformation instance with the given parameters.
     */
    public CameraInformation(int width, int height, int rotation,
                             float horizontalFocalLength, float verticalFocalLength) {
        this.width = width;
        this.height = height;
        this.rotation = rotation;
        this.horizontalFocalLength = horizontalFocalLength;
        this.verticalFocalLength = verticalFocalLength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CameraInformation ci = (CameraInformation) o;
        return width == ci.width
                && height == ci.height
                && rotation == ci.rotation
                && horizontalFocalLength == ci.horizontalFocalLength
                && verticalFocalLength == ci.verticalFocalLength;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height, rotation, horizontalFocalLength, verticalFocalLength);
    }
}
