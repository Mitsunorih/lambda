package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Mechanisms.SampleMechanism.Tank;

public class SampleCommand extends CommandBase {

    private final Tank mechanism;

    public SampleCommand(Tank mechanism) {
        this.mechanism = mechanism;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        ;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end (boolean interrupted) {
        ;
    }


}