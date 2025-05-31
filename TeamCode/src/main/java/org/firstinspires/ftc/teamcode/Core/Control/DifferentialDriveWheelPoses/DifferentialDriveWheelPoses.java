package org.firstinspires.ftc.teamcode.Core.Control.DifferentialDriveWheelPoses;

public class DifferentialDriveWheelPoses {
    public double leftPose;
    public double rightPose;

    public DifferentialDriveWheelPoses(double left, double right) {
        this.leftPose = left;
        this.rightPose = right;
    }

    public DifferentialDriveWheelPoses() {}

    public DifferentialDriveWheelPoses getWheelPoses() {
        return new DifferentialDriveWheelPoses(leftPose, rightPose);
    }

}
