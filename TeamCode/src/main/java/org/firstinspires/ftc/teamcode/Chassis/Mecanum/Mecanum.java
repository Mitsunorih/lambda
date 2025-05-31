package org.firstinspires.ftc.teamcode.Chassis.Mecanum;



import static org.firstinspires.ftc.teamcode.Core.util.Math.MathUtil.clamp;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.arcrobotics.ftclib.geometry.Translation2d;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.kinematics.wpilibkinematics.ChassisSpeeds;
import com.arcrobotics.ftclib.kinematics.wpilibkinematics.MecanumDriveWheelSpeeds;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoMechanism;
import org.firstinspires.ftc.teamcode.Core.Utils.EctoPathFollowing.teamcode.Localizer;
import org.firstinspires.ftc.teamcode.Core.Utils.EctoPathFollowing.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.Core.Utils.EctoPathFollowing.teamcode.ThreeDeadWheelLocalizer;
import org.firstinspires.ftc.teamcode.Core.util.Kinematics.MecanumDriveKinematics;
import org.firstinspires.ftc.teamcode.Core.util.Kinematics.MecanumDriveOdometry;
import org.firstinspires.ftc.teamcode.Core.util.Kinematics.MecanumDriveWheelPositions;

import java.util.LinkedList;

/*
What to do if this doesn't work
first check if the motor ids are correct
then check that all the motors and their respective encoders follow the right hand side rule
and that they all correlate to each other.

check if all the
 */

public class Mecanum extends EctoMechanism{

    private Motor fl, fr, bl, br;
    private IMU gyro;//IMU
    private MecanumConfig config;
    private Localizer localizer;
    private com.acmerobotics.roadrunner.Pose2d pose;
    private final LinkedList<com.acmerobotics.roadrunner.Pose2d> poseHistory = new LinkedList<>();


    public Mecanum(MecanumConfig config) {
        super("", "");
        this.config = config;
        pose = new com.acmerobotics.roadrunner.Pose2d(0, 0, 0);
    }

    MecanumDriveKinematics kinematics;

    MecanumDriveOdometry  odometry;
//
//    public MecanumDriveWheelPositions getWheelPositions() {
//        return new MecanumDriveWheelPositions(
//                fl.getPos(),
//                fr.getPos(),
//                bl.getPos(),
//                br.getPos()
//        );
//    }
//
//    void updateOdometry() {
//        odometry.update(new Rotation2d(yaw), getWheelPositions());
//    }

    public PoseVelocity2d updatePoseEstimate() {
        return localizer.update();
    }

    public void resetOdo(Rotation2d rotation, com.acmerobotics.roadrunner.Pose2d pose) {
        odometry.resetPosition(rotation, new MecanumDriveWheelPositions(), new com.arcrobotics.ftclib.geometry.Pose2d());
    }


//    public Pose2d getPose() {
//        return odometry.getPoseMeters();
//    }


    public void resetHeading(){
        pose = new com.acmerobotics.roadrunner.Pose2d(pose.position, 0);
    }

    public void setHeading(double heading){
        pose = new com.acmerobotics.roadrunner.Pose2d(pose.position, heading);
    }

    public double getYaw(){
        return pose.heading.toDouble();
    }
//
//    public void setVelocity(MecanumDriveWheelSpeeds speeds){
//        fl.set(speeds.frontLeftMetersPerSecond, ControlMode.VELOCITY);
//        fr.set(speeds.frontRightMetersPerSecond, ControlMode.VELOCITY);
//        bl.set(speeds.rearLeftMetersPerSecond, ControlMode.VELOCITY);
//        br.set(speeds.rearRightMetersPerSecond, ControlMode.VELOCITY);
//    }
//
//    public void setVoltage(MecanumDriveWheelSpeeds speeds){
//        fl.set(speeds.frontLeftMetersPerSecond * .9, ControlMode.VOLTAGE);
//        fr.set(speeds.frontRightMetersPerSecond, ControlMode.VOLTAGE);
//        bl.set(speeds.rearLeftMetersPerSecond * .9, ControlMode.VOLTAGE);
//        br.set(speeds.rearRightMetersPerSecond, ControlMode.VOLTAGE);
//    }

    public void setPercent(ChassisSpeeds speeds){
        setPercent(speeds, new Translation2d());
    }

    public void setPercent(ChassisSpeeds speeds, Translation2d centerOfRotation) {
        MecanumDriveWheelSpeeds wheelSpeeds = kinematics.toWheelSpeeds(speeds, centerOfRotation);
        fl.set(wheelSpeeds.frontLeftMetersPerSecond);
        fr.set(wheelSpeeds.frontRightMetersPerSecond);
        bl.set(wheelSpeeds.rearLeftMetersPerSecond);
        br.set(wheelSpeeds.rearRightMetersPerSecond);

    }


    public MecanumDriveWheelSpeeds toWheelSpeeds(ChassisSpeeds speeds){
        return kinematics.toWheelSpeeds(speeds);
    }


//    public double getVel(){
//        return fr.getVel();
//    }

    @Override
    public void initMechanism() {
//        gyro = new EctoNavX(hardwareMap); // Angle units en grados
        localizer = new ThreeDeadWheelLocalizer(hardwareMap, MecanumDrive.PARAMS.inPerTick, new Pose2d(0,0,0));
        kinematics =  new MecanumDriveKinematics(
                new Translation2d(config.trackWidth / 2,config.trackLength / 2),
                new Translation2d(config.trackWidth / 2,-config.trackLength / 2),
                new Translation2d(-config.trackWidth / 2,config.trackLength / 2),
                new Translation2d(-config.trackWidth / 2,-config.trackLength / 2)
        );

        fl = new Motor(hardwareMap, "fl");
        fl.setInverted(false);
        fl.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        fr = new Motor(hardwareMap, "fr");
        fr.setInverted(true);
        fr.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        bl = new Motor(hardwareMap, "bl");
        bl.setInverted(false);
        bl.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        br = new Motor(hardwareMap, "br");
        br.setInverted(true);
        br.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

//
//        odometry = new MecanumDriveOdometry(
//                kinematics,
//                new Rotation2d(yaw),
//                new MecanumDriveWheelPositions()
//        );

    }

    @Override
    public void startMechanism() {
//        resetOdo(new Rotation2d(), new Pose2d());
        resetHeading();
    }

    @Override
    public void updateMechanism() {
        PoseVelocity2d robotVelRobot = updatePoseEstimate();

    }

    @Override
    public void stopMechanism(){ ; }
}

