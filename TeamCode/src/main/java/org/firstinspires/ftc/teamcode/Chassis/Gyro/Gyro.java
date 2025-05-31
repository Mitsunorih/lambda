package org.firstinspires.ftc.teamcode.Chassis.Gyro;

import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
//Ecto for Gyro in testing
public class Gyro {

    IMU imu;
    double lastHeading = 0;



    //Initialization of the gyrp
    public Gyro(HardwareMap hardwareMap){
        imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters myIMUparameters;
        myIMUparameters = new IMU.Parameters(
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.UP,
                        RevHubOrientationOnRobot.UsbFacingDirection.LEFT
                )
        );
        imu.initialize(myIMUparameters);
    }

    public void resetHeading(){
        imu.resetYaw();
    }

    public YawPitchRollAngles getAngles(){
        return imu.getRobotYawPitchRollAngles();
    }
    /*
    The getHeading(), getXRotation(), and, getYRotation covert form YawPitchRollAngles to double units,
    this for using them as values in TeleOp
     */


    public double getYaw (){
        return imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

    }
/*
    public double getXRotation(){
        return robotOrientation.getPitch(AngleUnit.DEGREES);
    }

    public double getYRotation(){
        return robotOrientation.getRoll(AngleUnit.DEGREES);
    }


 */
}

