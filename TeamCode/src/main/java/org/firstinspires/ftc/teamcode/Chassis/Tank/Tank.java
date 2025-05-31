package org.firstinspires.ftc.teamcode.Chassis.Tank;


import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.arcrobotics.ftclib.kinematics.wpilibkinematics.ChassisSpeeds;
import com.arcrobotics.ftclib.kinematics.wpilibkinematics.DifferentialDriveOdometry;
import com.arcrobotics.ftclib.kinematics.wpilibkinematics.DifferentialDriveWheelSpeeds;
import com.arcrobotics.ftclib.kinematics.wpilibkinematics.DifferentialDriveKinematics;
import com.arcrobotics.ftclib.trajectory.TrapezoidProfile;

import org.firstinspires.ftc.teamcode.Chassis.Tank.TankModule.TankModule;
import org.firstinspires.ftc.teamcode.Chassis.Tank.TankModule.TankModuleConfig;
import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoMechanism;
import org.firstinspires.ftc.teamcode.Core.Control.DifferentialDriveWheelPoses.DifferentialDriveWheelPoses;
import org.firstinspires.ftc.teamcode.Core.Control.PIDConfig;
import org.firstinspires.ftc.teamcode.Core.Managers.MechanismManager;
import org.firstinspires.ftc.teamcode.RobotOpModes.TeleOperated.RobotConfiguration;
import org.firstinspires.ftc.teamcode.Chassis.ControlMode.ControlMode;
import org.firstinspires.ftc.teamcode.Chassis.Gyro.Gyro;

public class Tank extends EctoMechanism {
    MechanismManager mechanismManager = MechanismManager.getInstance();
//    MechanismManager mechanismManager = new MechanismManager();

    Gyro gyro;
    private double getHeading = 0.0;
    TankConfig config;
    final TankModuleConfig rightConfig = new TankModuleConfig(""); //CHANGE THIS
    final TankModuleConfig leftConfig = new TankModuleConfig(""); //CHANGE THIS
    TankModule right, left;


    public Tank(String moduleName, String moduleType, TankConfig config) {
        super(moduleName, moduleType);
        this.config = config;



        //TODO Check Config
        rightConfig.motorTPR = 28.0;
        rightConfig.gearRatio = 1.0;
        rightConfig.wheelCircumference = 0; //CHANGE THIS
        rightConfig.internalGearRatio = 15.1147;
        rightConfig.pidConfig = new PIDConfig(0.4,0,0,0.35, 0.02, 0, 0);
        rightConfig.constraints = new TrapezoidProfile.Constraints(2.71, 12.0);
        rightConfig.invertSide = true;
        rightConfig.multiplier = 0.5;
        leftConfig.motorTPR = 28.0;
        leftConfig.gearRatio = 1.0;
        leftConfig.wheelCircumference = 0; //CHANGE THIS
        leftConfig.internalGearRatio = 15.1147;
        leftConfig.pidConfig = new PIDConfig(0.4,0,0,0.35, 0.02, 0, 0);
        leftConfig.constraints = new TrapezoidProfile.Constraints(2.71, 12.0);
        leftConfig.invertSide = false;
        leftConfig.multiplier = 0.5;


        right = new TankModule("RightModule", "TankModule", rightConfig);
        left = new TankModule("LeftModule", "TankModule", leftConfig);
        mechanismManager.addMechanism(right);
        mechanismManager.addMechanism(left);
    }

    DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(
           0 //CHANGE THIS
    );

    DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(
            new Rotation2d(getHeading),
            new Pose2d()
    );

    public DifferentialDriveWheelSpeeds getWheelSpeeds() {
        return new DifferentialDriveWheelSpeeds(
                left.getVel(),right.getVel()
        );
    }

    public DifferentialDriveWheelPoses getWheelPose() {
        return new DifferentialDriveWheelPoses(left.getPose(), right.getPose());
    }

    public ChassisSpeeds getChassisSpeeds() {
        return kinematics.toChassisSpeeds(getWheelSpeeds());
    }

    public void resetEncoder(){
        left.resetEncoders();
        right.resetEncoders();
    }
    void updateOdometry() {
        odometry.update(new Rotation2d(getHeading), left.getPose(), right.getPose());
    }


    public void resetOdo(Pose2d pose, Rotation2d rotation) {
        odometry.resetPosition(pose, rotation);
    }



    public Pose2d getPose() {
        return odometry.getPoseMeters();
    }

    public double getVel() {
        return right.getVel();
    }


    public void resetHeading(){
        gyro.resetHeading();
    }

    public double getYaw(){
        double yaw = gyro.getYaw();
        System.out.println(String.format("Yaw: %.8f", yaw));
        return yaw;
    }

    public void setVelocity(ChassisSpeeds speeds){
        DifferentialDriveWheelSpeeds wheelSpeeds = kinematics.toWheelSpeeds(speeds);
//        wheelSpeeds = desaturateWheelSpeeds(wheelSpeeds,2.71);
        left.set(wheelSpeeds.leftMetersPerSecond, ControlMode.VELOCITY);
        right.set(wheelSpeeds.rightMetersPerSecond, ControlMode.VELOCITY);
    }

    public void setVoltage(ChassisSpeeds speeds){
        DifferentialDriveWheelSpeeds wheelSpeeds = kinematics.toWheelSpeeds(speeds);
//        wheelSpeeds = desaturateWheelSpeeds(wheelSpeeds,12);
        left.set(wheelSpeeds.leftMetersPerSecond, ControlMode.VOLTAGE);
        right.set(wheelSpeeds.rightMetersPerSecond, ControlMode.VOLTAGE);

    }

    public void setPercent(ChassisSpeeds speeds){
        DifferentialDriveWheelSpeeds wheelSpeeds = kinematics.toWheelSpeeds(speeds);
        DifferentialDriveWheelSpeeds outTest = desaturateWheelSpeeds(wheelSpeeds, 1);
        System.out.println("outTest" + outTest.toString());
        System.out.println("wheel Speeds" + wheelSpeeds.toString());
        left.set(wheelSpeeds.leftMetersPerSecond, ControlMode.PERCENT);
        right.set(wheelSpeeds.rightMetersPerSecond, ControlMode.PERCENT);
    }

    public void setDesaturatedPercent(ChassisSpeeds speeds) {
        DifferentialDriveWheelSpeeds wheelSpeeds = kinematics.toWheelSpeeds(speeds);
        DifferentialDriveWheelSpeeds outTest = desaturateWheelSpeeds(wheelSpeeds, 1);
        System.out.println("outTest" + outTest.toString());
        System.out.println("wheel Speeds" + wheelSpeeds.toString());
        left.set(outTest.leftMetersPerSecond, ControlMode.PERCENT);
        right.set(outTest.rightMetersPerSecond, ControlMode.PERCENT);

    }

    DifferentialDriveWheelSpeeds desaturateWheelSpeeds(DifferentialDriveWheelSpeeds speeds, double attainableSpeed) {
        double aLeft = Math.abs(speeds.leftMetersPerSecond);
        double aRight = Math.abs(speeds.leftMetersPerSecond);
        double maxSpeed = Math.max(aLeft, aRight);
        double left = speeds.leftMetersPerSecond / (maxSpeed * attainableSpeed);
        double right = speeds.rightMetersPerSecond / (maxSpeed * attainableSpeed);
        return new DifferentialDriveWheelSpeeds(left, right);
    }


    @Override
    public void initMechanism() {
        gyro = new Gyro(hardwareMap); // Angle units en grados
    }


    @Override
    public void startMechanism() {

    }
    @Override
    public void updateMechanism() {
        getHeading = gyro.getYaw();
        updateOdometry();
    }

    @Override
    public void stopMechanism(){
    }





}
