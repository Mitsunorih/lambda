package org.firstinspires.ftc.teamcode.Core.BaseClasses;


import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Core.Utils.EctoMath;

public class ServoAsMotor {

    ServoEx servo;

    boolean isInverted = false;

    double setPoint = 0;

    public ServoAsMotor(HardwareMap hardwareMap, String id) {
        servo = new SimpleServo(
                hardwareMap,
                id,
                Math.toRadians(-135),
                Math.toRadians(135),
                AngleUnit.RADIANS
        );
    }

    //The motor as servo recives values as a percent and is then mapped
    //the servo has to be used as continuos and continous accepts as if it was a pose servo
    // just linking the pose to percentage of vel the inverse of what we are doing.
    public void set(double prcnt) {
        if (isInverted) prcnt *= -1;
        servo.turnToAngle(EctoMath.map(prcnt, -1, 1, -135, 135));
    }

    public void setInverted(boolean isInverted) {
        this.isInverted = isInverted;
    }

}
