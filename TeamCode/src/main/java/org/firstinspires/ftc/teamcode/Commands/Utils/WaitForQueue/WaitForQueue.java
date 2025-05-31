package org.firstinspires.ftc.teamcode.Commands.Utils.WaitForQueue;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Core.Utils.LatchedBool.LatchedBool;

import java.util.function.BooleanSupplier;

public class WaitForQueue extends CommandBase {
    private BooleanSupplier supplier;
    public WaitForQueue(BooleanSupplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public void initialize() {System.out.println("wating for button");}

    @Override
    public void execute() {
        ;
    }

    @Override
    public boolean isFinished() {
        return supplier.getAsBoolean();
    }

    public void end (boolean interrupted) {System.out.println("button Pressed");}

}
