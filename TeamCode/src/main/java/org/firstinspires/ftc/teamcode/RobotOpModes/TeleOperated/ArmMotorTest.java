package org.firstinspires.ftc.teamcode.RobotOpModes.TeleOperated;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.OperationModes.EctoOpMode;
import org.firstinspires.ftc.teamcode.Mechanisms.SampleMechanism.ArmMotor;

@TeleOp(name = "ArMMotor")
public class ArmMotorTest extends EctoOpMode {

    ArmMotor armMotor;

    GamepadEx gamepad;
    @Override
    public void initRobotClasses() {
        armMotor = new ArmMotor("RollerMotor", "Mechanism");
        mechanismManager.addMechanism(armMotor);

        //control
        gamepad = new GamepadEx(gamepad1);
    }

    @Override
    public void initRobot() {

    }

    @Override
    public void startRobot() {

    }

    @Override
    public void updateRobot(Double timeStep) {

        if (gamepad.getButton(RobotConfiguration.Buttons.rightBumper)) {
            armMotor.set(30);
        }else if (gamepad.getButton(RobotConfiguration.Buttons.leftBumper)) {
            armMotor.set(-30);
        }else {
            armMotor.set(0);
            }


    }
}
