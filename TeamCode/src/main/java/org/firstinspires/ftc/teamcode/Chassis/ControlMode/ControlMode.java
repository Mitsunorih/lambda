
package org.firstinspires.ftc.teamcode.Chassis.ControlMode;
public enum ControlMode {
    VELOCITY,
    PERCENT,
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