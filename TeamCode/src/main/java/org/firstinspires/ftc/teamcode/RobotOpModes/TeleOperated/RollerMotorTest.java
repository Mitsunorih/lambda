package org.firstinspires.ftc.teamcode.RobotOpModes.TeleOperated;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.OperationModes.EctoOpMode;
import org.firstinspires.ftc.teamcode.Mechanisms.SampleMechanism.RollerMotor;

public class RollerMotorTest extends EctoOpMode {

    RollerMotor rollerMotor;

    GamepadEx gamepad;
    @Override
    public void initRobotClasses() {
        rollerMotor = new RollerMotor("RollerMotor", "Mechanism");
        mechanismManager.addMechanism(rollerMotor);

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
            rollerMotor.set(30);
        }else if (gamepad.getButton(RobotConfiguration.Buttons.leftBumper)) {
            rollerMotor.set(-30);
        }else {
            rollerMotor.set(0);
            }


    }
}
