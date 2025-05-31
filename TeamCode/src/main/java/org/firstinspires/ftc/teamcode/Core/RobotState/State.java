package org.firstinspires.ftc.teamcode.Core.RobotState;

public class State {
    public double elevatorShoulderAngle = 0;
    public double elevatorWristAngle = 0;
    public double elevatorHeight = 0;
    public double slidesExtension = 0;
    public double intakeWristAngle = 0;
    public double rollersPrcnt = 0;
    public double clawTightness = 0;
    public double climberHeight = 0;
    public boolean transitionThroughIdle = false;

    public State() { ; };

    public State(State state) {
        this.elevatorShoulderAngle = state.elevatorShoulderAngle;
        this.elevatorWristAngle = state.elevatorWristAngle;
        this.elevatorHeight = state.elevatorHeight;
        this.slidesExtension = state.slidesExtension;
        this.intakeWristAngle = state.intakeWristAngle;
        this.rollersPrcnt = state.rollersPrcnt;
        this.transitionThroughIdle = state.transitionThroughIdle;
    }

    public State(
            double elevatorShoulderAngle,
            double elevatorWristAngle,
            double elevatorHeight,
            double slidesExtension,
            double intakeWristAngle,
            double rollersPrcnt,
            double clawTightness,
            boolean transitionThroughIdle
    ) {
        this.elevatorShoulderAngle = elevatorShoulderAngle;
        this.elevatorWristAngle = elevatorWristAngle;
        this.elevatorHeight = elevatorHeight;
        this.slidesExtension = slidesExtension;
        this.intakeWristAngle = intakeWristAngle;
        this.rollersPrcnt = rollersPrcnt;
        this.clawTightness = clawTightness;
        this.transitionThroughIdle = transitionThroughIdle;
    }

    public State(
            double elevatorShoulderAngle,
            double elevatorWristAngle,
            double elevatorHeight,
            double slidesExtension,
            double intakeWristAngle,
            double rollersPrcnt,
            double clawTightness,
            boolean transitionThroughIdle,
            double climberHeight
    ) {
        this.elevatorShoulderAngle = elevatorShoulderAngle;
        this.elevatorWristAngle = elevatorWristAngle;
        this.elevatorHeight = elevatorHeight;
        this.slidesExtension = slidesExtension;
        this.intakeWristAngle = intakeWristAngle;
        this.rollersPrcnt = rollersPrcnt;
        this.clawTightness = clawTightness;
        this.transitionThroughIdle = transitionThroughIdle;
        this.climberHeight = climberHeight;
    }
}
