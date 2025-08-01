package org.firstinspires.ftc.teamcode.Mechanisms.SampleMechanism;

import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoMechanism;

public class Tank extends EctoMechanism {
    private  Motor motorR;
    private  Motor motorL;

    public Tank(String moduleName, String moduleType) {
        super(moduleName, moduleType);
    }

    public  void set(double speedR, double speedL){
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