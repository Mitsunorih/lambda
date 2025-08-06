package org.firstinspires.ftc.teamcode.RobotOpModes.TeleOperated;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.OperationModes.EctoOpMode;
import org.firstinspires.ftc.teamcode.Mechanisms.SampleMechanism.ArmMotor;
import org.firstinspires.ftc.teamcode.Mechanisms.SampleMechanism.Tank;
import org.firstinspires.ftc.teamcode.Mechanisms.ServoClaws1;


@TeleOp(name = "RobotTest")

public class RobotTest extends EctoOpMode {


        Tank tank;
        ServoClaws1 servoClaws;
        ArmMotor armMotor;


        GamepadEx gamepad;
    @Override
    public void initRobotClasses() {
        //control
        gamepad = new GamepadEx(gamepad1);
        //tank
        tank = new Tank("Tank", "Mechanism");
        mechanismManager.addMechanism(tank);
        //claws
        servoClaws = new ServoClaws1("ServoClaws", "Mechanism");
        mechanismManager.addMechanism(servoClaws);
        //arm
        armMotor = new ArmMotor("ArmMotor", "Mechanism");
        mechanismManager.addMechanism(armMotor);



    }

    @Override
    public void initRobot() {

    }

    @Override
    public void startRobot() {
        servoClaws.set(0);
    }

    @Override
    public void updateRobot(Double timeStep) {
        //arm
        if (gamepad.getButton(RobotConfiguration.Buttons.leftBumper)) {
            armMotor.set(35);
        }else if (gamepad.getButton(RobotConfiguration.Buttons.dPadDown)) {
            armMotor.set(2);
        //cone
        }else if (gamepad.getButton(RobotConfiguration.Buttons.rightBumper)) {
            armMotor.set(80);
        }else if (gamepad.getButton(RobotConfiguration.Buttons.dPadUp)) {
            armMotor.set(15);


        }
        //claws
        if (gamepad.getButton(RobotConfiguration.Buttons.dPadRight)){
            servoClaws.set(20);

        }else if (gamepad.getButton(RobotConfiguration.Buttons.dPadLeft)) {
            servoClaws.set(0);
        }
        //tank
        tank.set(gamepad.getRightX() - gamepad.getLeftY(), gamepad.getRightX() + gamepad.getLeftY());
        //claws in 0
        if (gamepad.getButton(RobotConfiguration.Buttons.a)){
            servoClaws.set(0);

        }

        telemetry.addData("Pose arm", armMotor.getPoseDeg());
    }
}
