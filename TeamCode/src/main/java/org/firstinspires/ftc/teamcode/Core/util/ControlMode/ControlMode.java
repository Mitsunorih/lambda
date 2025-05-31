package org.firstinspires.ftc.teamcode.Core.util.ControlMode;

public enum ControlMode {
    VELOCITY,
    PERCENT,
    ATTAINABLEVELOCITY,
    VOLTAGE;

    public static String toString(ControlMode mode){
        switch (mode) {
            case VELOCITY:
                return "velocity";
            case PERCENT:
                return "percent";
            case VOLTAGE:
                return "Voltage";
            default:
                return "invalid Entry";
        }
    }
}
