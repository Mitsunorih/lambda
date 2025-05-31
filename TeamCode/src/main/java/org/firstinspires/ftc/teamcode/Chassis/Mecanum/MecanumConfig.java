package org.firstinspires.ftc.teamcode.Chassis.Mecanum;

public class MecanumConfig {
    public String imuId;//not used at the moment TODO use

    public double gearRatio = 0.5;
    public double internalGearRatio = 10.5;
    public double ticksPerRev = 28;

    public double wheelCircumference = 0.096;

    public double trackWidth = 1.0;
    public double trackLength = 1.0;
    public double maxAttainableSpeed = 1;


    public double maxVel = 1;
    public double maxAccel = 1;


    public MecanumConfig withIMUId(String id) {
        this.imuId = id;
        return this;
    }

    public MecanumConfig withGearRatio(double ratio) {
        this.gearRatio = ratio;
        return this;
    }

    public MecanumConfig withInternalGearRatio(double ratio) {
        this.internalGearRatio = ratio;
        return this;
    }

    public MecanumConfig withTPR(double tpr) {
        this.ticksPerRev = tpr;
        return this;
    }

    public MecanumConfig withWheelCircumference(double circumference) {
        this.wheelCircumference = circumference;
        return this;
    }

    public MecanumConfig withChassisDimensions(double trackWidth, double trackLength) {
        this.trackWidth = trackWidth;
        this.trackLength = trackLength;
        return this;
    }

    public MecanumConfig withMaxAttainableSpeed(double speed) {
        this.maxAttainableSpeed = speed;
        return this;
    }
}

