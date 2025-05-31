package org.firstinspires.ftc.teamcode.Core.Input;

import com.arcrobotics.ftclib.command.button.Trigger;

public class JoystickExpo {
    private final double expo;
    private final double deadZone;
    private final double maxValue = 1.0;
    private double slope = 0.0;
    private double offset = 0.0;

    private double val = 0.0;

    public JoystickExpo(double expo, double deadZone) {
        this.expo = expo;
        this.deadZone = deadZone;

        if (deadZone < 0 || deadZone > maxValue) {
            throw new RuntimeException("deadzone invalid");
        }

        if (expo < 0 || expo > maxValue) {
            throw new RuntimeException("exponent value in joystick is invalid, 0 - 1");
        }

        slope = maxValue / (maxValue - deadZone);
        offset = -(deadZone * slope);

    }

    public double applyDeadZone(double val) {
        return Math.abs(val) >= deadZone ? val : 0.0;
    }

    public double applyExpo(double val) {
        double tmp = applyDeadZone(val);
        double linear = 0.0;
        if (tmp > 0) {
            linear = tmp * slope + offset;
        } else if (tmp < 0) {
            linear = tmp * slope - offset;
        }

        return expo * Math.pow(linear, 3.0) + (1.0 - expo) * linear;
    }

    public void update(double val) {
        this.val = val;
    }

    public double get() {
        return applyExpo(val);
    }

}
