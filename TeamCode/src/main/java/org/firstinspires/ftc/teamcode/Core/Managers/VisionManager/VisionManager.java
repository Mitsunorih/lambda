package org.firstinspires.ftc.teamcode.Core.Managers.VisionManager;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoMechanism;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

public class VisionManager extends EctoMechanism {
    OpenCvWebcam camera;
    private final VisionManagerConfig config;

    public VisionManager(VisionManagerConfig config) {
        super("Vision", "Manager");
        this.config = config;
    }

    public void visionInit() {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                camera.startStreaming(320,240, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int i) {

            }
        });
    }


    @Override
    public void initMechanism() {

    }

    @Override
    public void startMechanism() {

    }

    @Override
    public void updateMechanism() {
//        updateTelemetry();
    }

    @Override
    public void stopMechanism() {

    }
    private void updateTelemetry() {
        telemetry.addData("cameraFPS", camera.getFps());
    }
}
