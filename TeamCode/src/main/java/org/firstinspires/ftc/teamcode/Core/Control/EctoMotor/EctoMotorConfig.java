package org.firstinspires.ftc.teamcode.Core.Control.EctoMotor;

import java.util.function.DoubleSupplier;

public class EctoMotorConfig {
    public String id = "";
    public MotorControlMode controlMode = MotorControlMode.kPERCENT;
    public boolean enableBrakingOnIdle = true;
    public boolean enableCurrentLimit = false;
    public double currentLimit = 20;
    public boolean isInverted = false;
    public boolean enableForwardSoftLimit = true; //default to force people to check them
    public double forwardSoftLimit = 1;
    public boolean enableReverseSoftLimit = true;
    public double reverseSoftLimit = 1;
    public double internalMotorGearRatio = 1; //when requesting the position of the motor this determines if it is at the shaft or after the geareduction
    public double ticksPerRev = 1; //req for rads transform
    public DoubleSupplier poseSupplier;
    public DoubleSupplier velSupplier;
    public MotorFeedbackMode feedbackMode = MotorFeedbackMode.kINTERNAL;
    /*
    HOW TO SETUP A MOTOR

    Ensure that the correct id is used.

    The normal control mode is percent, this is basically the default,
    and the PIDs, to use position control run in the mechanism themselves this simplifies things.

    The current limit is currently untested more things need to be done to test it and limiting spikes.

    The soft limits are tried and tested, the only thing is that they rely on you to set them up correctly
    correct direction when using MotorFeedbackMode.kEXTERNAL and test them with reduced limits before actually
    setting up the good ones

    When using the external feedback mode the pose supplier is used, to ensure that the direction of the sensor is correct
    this means that when positive voltage is applied, then the sensor should move into positive numbers, and vise versa

    The ticks per rev is used to transform the motor into rotation and then into radians.

    The internalMotorGearRatio helps transform from the raw motor rotation the shaft if it has any attached gearboxes

     */

    public EctoMotorConfig withId(String id) {
        this.id = id;
        return this;
    }

    public EctoMotorConfig withControlMode(MotorControlMode controlMode) {
        this.controlMode = controlMode;
        return this;
    }

    public EctoMotorConfig withEnableBrakingOnIdle(boolean enableBrakingOnIdle) {
        this.enableBrakingOnIdle = enableBrakingOnIdle;
        return this;
    }

    public EctoMotorConfig withEnableCurrentLimit(boolean enableCurrentLimit) {
        this.enableCurrentLimit = enableCurrentLimit;
        return this;
    }

    public EctoMotorConfig withCurrentLimit(double currentLimit) {
        this.currentLimit = currentLimit;
        return this;
    }

    public EctoMotorConfig withIsInverted(boolean isInverted) {
        this.isInverted = isInverted;
        return this;
    }

    public EctoMotorConfig withEnableForwardSoftLimit(boolean enableForwardSoftLimit) {
        this.enableForwardSoftLimit = enableForwardSoftLimit;
        return this;
    }

    public EctoMotorConfig withForwardSoftLimit(double forwardSoftLimit) {
        this.forwardSoftLimit = forwardSoftLimit;
        return this;
    }

    public EctoMotorConfig withEnableReverseSoftLimit(boolean enableReverseSoftLimit) {
        this.enableReverseSoftLimit = enableReverseSoftLimit;
        return this;
    }

    public EctoMotorConfig withReverseSoftLimit(double reverseSoftLimit) {
        this.reverseSoftLimit = reverseSoftLimit;
        return this;
    }

    public EctoMotorConfig withInternalMotorGearRatio(double internalMotorGearRatio) {
        this.internalMotorGearRatio = internalMotorGearRatio;
        return this;
    }

    public EctoMotorConfig withTicksPerRev(double ticksPerRev) {
        this.ticksPerRev = ticksPerRev;
        return this;
    }

    public EctoMotorConfig withPoseSupplier(DoubleSupplier supplier) {
        this.poseSupplier = supplier;
        return this;
    }

    public EctoMotorConfig withVelSupplier(DoubleSupplier supplier) {
        this.velSupplier = supplier;
        return this;
    }

    public EctoMotorConfig withFeedbackMode(MotorFeedbackMode feedbackMode) {
        this.feedbackMode = feedbackMode;
        return this;
    }


}
