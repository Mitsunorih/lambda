package org.firstinspires.ftc.teamcode.RobotOpModes.TeleOperated;

import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.OperationModes.EctoOpMode;
import org.firstinspires.ftc.teamcode.Mechanisms.ServoClaws1;

public class Claw2Test extends EctoOpMode {

    ServoClaws1 servoClaws2;
    GamepadEx gamepad;

    @Override
    public void initRobotClasses() {
        servoClaws2 = new ServoClaws1("ServoClaws2", "Mechanism");
        mechanismManager.addMechanism(servoClaws2);

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
            servoClaws2.set(40);

        }else if (gamepad.getButton(RobotConfiguration.Buttons.dPadLeft)) {
            servoClaws2.set(-40);
        }else {
            servoClaws2.set(0);
        }


    }}
