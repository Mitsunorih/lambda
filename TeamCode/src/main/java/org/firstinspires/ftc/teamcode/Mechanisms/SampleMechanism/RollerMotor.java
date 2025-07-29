package org.firstinspires.ftc.teamcode.Mechanisms.SampleMechanism;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoMechanism;
import org.firstinspires.ftc.teamcode.Core.Control.EctoMotor.EctoMotor;
import org.firstinspires.ftc.teamcode.Core.Control.EctoMotor.EctoMotorConfig;

public class RollerMotor extends EctoMechanism {
    EctoMotor rollerMotor;
    EctoMotorConfig motorrConfig;

    public RollerMotor(String moduleName, String moduleType) {
        super(moduleName, moduleType);
    }

    public void set(double percentage) {
        rollerMotor.set(percentage);
    }


    @Override
    public void initMechanism() {
        motorrConfig = new EctoMotorConfig();
        motorrConfig.withId("RollerMotor");
        motorrConfig.withEnableForwardSoftLimit(false);
        motorrConfig.withEnableReverseSoftLimit(false);
        rollerMotor = new EctoMotor(hardwareMap,motorrConfig);

    }

    @Override
    public void startMechanism() {

    }

    @Override
    public void updateMechanism() {
        rollerMotor.update();

    }

    @Override
    public void stopMechanism() {

    }
}



