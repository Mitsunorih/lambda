package org.firstinspires.ftc.teamcode.Core.Tfod;


import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.hardware.camera.CameraName;
import org.firstinspires.ftc.robotcore.external.hardware.camera.CameraManager;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Core.Tfod.FrameGenerator;
import org.firstinspires.ftc.teamcode.Core.Tfod.TFObjectDetector;
import org.firstinspires.ftc.teamcode.Core.Tfod.TfodParameters;
import java.util.List;


@SuppressWarnings("WeakerAccess")
public abstract class ClassFactoryTFOD
{
    //----------------------------------------------------------------------------------------------
    // Construction
    //----------------------------------------------------------------------------------------------

    public static ClassFactoryTFOD getInstance()
    {
        return ClassFactoryTFOD.InstanceHolder.theInstance;
    }

    //----------------------------------------------------------------------------------------------
    // Accessing
    //----------------------------------------------------------------------------------------------

    /**
     * createTFObjectDetector returns an instance of the TensorFlow object detector engine
     * configured with the indicated set of parameters and using the given FrameGenerator to
     * supply the camera frames
     *
     * @param parameters the parameters used to configure the instance of the engine
     * @param frameGenerator the FrameGenerator that will be used to supply camera frames
     * @return an instance of the TensorFlow object detector engine.
     *
     * @see TFObjectDetector
     */
    public abstract TFObjectDetector createTFObjectDetector(TfodParameters parameters,
                                                            FrameGenerator frameGenerator);

    /**
     * Returns a {@link CameraManager} which can be used to access the USB webcams
     * attached to the robot controller.
     * @see CameraManager
     */
    public abstract CameraManager getCameraManager();

    /**
     * Returns a name of a virtual camera comprised of all the webcams configured in the given
     * hardware map.
     */
    public static CameraName createSwitchableCameraNameForAllWebcams(HardwareMap hardwareMap)
    {
        List<WebcamName> list = hardwareMap.getAll(WebcamName.class);
        CameraName[] allWebcams = list.toArray(new CameraName[list.size()]);
        return getInstance().getCameraManager().nameForSwitchableCamera(allWebcams);
    }

    //----------------------------------------------------------------------------------------------
    // Internal
    //----------------------------------------------------------------------------------------------

    protected static class InstanceHolder
    {
        public static ClassFactoryTFOD theInstance = null;
    }
}
