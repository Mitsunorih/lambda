package org.firstinspires.ftc.teamcode.Core.Control.EctoServo;

import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.trajectory.TrapezoidProfile;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class EctoServo {
    private ServoEx servo;
    private boolean isInverted;
    private double maxVel, maxAccel;

    TrapezoidProfile.Constraints constraints;
    TrapezoidProfile.State lastProfiledState = new TrapezoidProfile.State(0, 0);
    TrapezoidProfile.State goal = new TrapezoidProfile.State(0.0, 0.0);
    double setPoint = 0;
    public EctoServo(HardwareMap hardwareMap,
                     String servoId,
                     double minAngle,
                     double maxAngle,
                     boolean isInverted,
                     double maxVel,
                     double maxAccel) {
        servo = new SimpleServo(
                hardwareMap,
                servoId,
                minAngle,
                maxAngle,
                AngleUnit.RADIANS
        );

        servo.setInverted(isInverted);

        constraints = new TrapezoidProfile.Constraints(maxVel, maxAccel);
        this.isInverted = isInverted;
        this.maxVel = maxVel;
        this.maxAccel = maxAccel;

    }

    public void setInverted(boolean isInverted) {
        this.isInverted = isInverted;
    }

    public boolean getInverted() {
        return isInverted;
    }

    public void set(double rad) {
        setPoint = rad;
        goal = new TrapezoidProfile.State(rad, 0);
    }

    public void setConstraints(double maxVel, double maxAccel) {
        constraints = new TrapezoidProfile.Constraints(maxVel, maxAccel);
        this.maxVel = maxVel;
        this.maxAccel = maxAccel;
    }

    public void resetController(double pose, double vel) {
        lastProfiledState = new TrapezoidProfile.State(pose, vel);
    }

    public double getPose() {
        return lastProfiledState.position;
    }

    public double getVel() {
        return lastProfiledState.velocity;
    }

    public boolean isInTol(double tol) {
        return isInTolOf(tol, getPose());
    }

    public boolean isInTolOf(double tol, double of) {
        return Math.abs(of -getPose()) < tol;
    }

    public void update() {
        lastProfiledState = new TrapezoidProfile(constraints, goal, lastProfiledState).calculate(20);
//        System.out.println(String.format("SetPose: %.8f", lastProfiledState.position));
        servo.turnToAngle(lastProfiledState.position, AngleUnit.RADIANS);
    }
}
