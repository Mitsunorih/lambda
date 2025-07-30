package org.firstinspires.ftc.teamcode.RobotOpModes.TeleOperated;

import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.OperationModes.EctoOpMode;
import org.firstinspires.ftc.teamcode.Mechanisms.ServoClaws1;

public class ClawTest extends EctoOpMode {

ServoClaws1 servoClaws;
GamepadEx gamepad;

    @Override
    public void initRobotClasses() {
        servoClaws = new ServoClaws1("ServoClaws", "Mechanism");
        mechanismManager.addMechanism(servoClaws);

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
        if (gamepad.getButton(RobotConfiguration.Buttons.dPadRight)){
            servoClaws.set(40);

        }else if (gamepad.getButton(RobotConfiguration.Buttons.dPadLeft)) {
            servoClaws.set(-40);
        }else {
            servoClaws.set(0);
        }


        }}
