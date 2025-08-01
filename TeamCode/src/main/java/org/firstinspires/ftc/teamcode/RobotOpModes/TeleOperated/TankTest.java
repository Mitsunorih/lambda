package org.firstinspires.ftc.teamcode.RobotOpModes.TeleOperated;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.OperationModes.EctoOpMode;
import org.firstinspires.ftc.teamcode.Mechanisms.SampleMechanism.Tank;

@TeleOp(name = "Tank")
public class TankTest extends EctoOpMode {

    Tank tank;

    GamepadEx gamepad;

    @Override
    public void initRobotClasses() {
        tank = new Tank("Tank","Mechanism");
        mechanismManager.addMechanism(tank);
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
        tank.set(gamepad.getRightY()*0.8, gamepad.getLeftY()*0.8);

    }
}
