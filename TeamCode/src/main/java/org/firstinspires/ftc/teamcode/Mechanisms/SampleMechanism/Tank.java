package org.firstinspires.ftc.teamcode.Mechanisms.SampleMechanism;

import com.arcrobotics.ftclib.hardware.motors.Motor;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoMechanism;


public class Tank extends EctoMechanism {
    private static Motor motorR;
    private static Motor motorL;

    public Tank(String moduleName, String moduleType) {
        super(moduleName, moduleType);
    }

    public static void set(double speedR, double speedL){
        motorR.set(speedR);
        motorL.set(speedL);
    }

    @Override
    public void initMechanism() {
        motorR = new Motor(hardwareMap, "MOTORR");
        motorL = new Motor(hardwareMap, "MOTORL");
    }
    @Override
    public void startMechanism() {;}

    @Override
    public void updateMechanism() {;}

    @Override
    public void stopMechanism() {;}
}