package org.firstinspires.ftc.teamcode.Core.Managers.VisionManager;

import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;

import edu.wpi.first.math.Pair;


public class VisionManagerConfig {
    String cameraId = "";
    Pair<Integer, Integer> streamResolution;
    OpenCvCameraRotation streamRotation;
    boolean usePipeline = false;
    OpenCvPipeline pipeline;


    public VisionManagerConfig withCameraId(String id) {
        this.cameraId = id;
        return this;
    }

    public VisionManagerConfig withStreamResolution(int x, int y) {
        this.streamResolution = new Pair<>(x, y);
        return this;
    }

    public VisionManagerConfig withStreamRotation(OpenCvCameraRotation rotation) {
        this.streamRotation = rotation;
        return this;
    }

    public VisionManagerConfig withPipeline(OpenCvPipeline pipeline) {
        this.pipeline = pipeline;
        this.usePipeline = true;
        return this;
    }
}
