package org.firstinspires.ftc.teamcode.Core.Utils.Sensors;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoIMU;

public class UniversalIntegratedIMU extends EctoIMU {

    public UniversalIntegratedIMU(HardwareMap hardwareMap, String moduleName ) {
        super(moduleName, "Sensor");
        imu = hardwareMap.get(IMU.class, moduleName);
        multiplier = 1;
    }

    private final IMU imu;

    private int multiplier;


    public void initIMU(){
        imu.initialize(new IMU.Parameters(new RevHubOrientationOnRobot(
            RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
            RevHubOrientationOnRobot.UsbFacingDirection.DOWN
            )));}

    public double getHeading() {
        return imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES) * multiplier;
    }


    public void invertGyro() {
        multiplier *= -1;
    }


    public void resetHeading() {
        imu.resetYaw();
    }

}
