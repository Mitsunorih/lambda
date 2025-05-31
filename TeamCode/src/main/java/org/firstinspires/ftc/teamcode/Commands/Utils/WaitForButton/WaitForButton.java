package org.firstinspires.ftc.teamcode.Commands.Utils.WaitForButton;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.hardware.ams.AMSColorSensor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.OperationModes.EctoOpMode;
import org.firstinspires.ftc.teamcode.Core.Utils.LatchedBool.LatchedBool;

import java.util.function.BooleanSupplier;

public class WaitForButton extends CommandBase {
    private final BooleanSupplier supplier;
    private final LatchedBool button_val = new LatchedBool();

    public WaitForButton(BooleanSupplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public void initialize() {System.out.println("wating for button");}

    @Override
    public void execute() {
        System.out.println(supplier.getAsBoolean());
    }

    @Override
    public boolean isFinished() {
        return button_val.update(supplier.getAsBoolean());
    }

    public void end (boolean interrupted) {System.out.println("button Pressed");}

}
