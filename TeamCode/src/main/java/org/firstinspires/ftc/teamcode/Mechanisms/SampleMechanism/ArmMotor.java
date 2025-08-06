package org.firstinspires.ftc.teamcode.Mechanisms.SampleMechanism;

import com.arcrobotics.ftclib.controller.PIDController;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoMechanism;
import org.firstinspires.ftc.teamcode.Core.Control.EctoMotor.EctoMotor;
import org.firstinspires.ftc.teamcode.Core.Control.EctoMotor.EctoMotorConfig;
import org.firstinspires.ftc.teamcode.Core.Utils.EctoMath;

public class ArmMotor extends EctoMechanism {
    EctoMotor armMotor;

    PIDController pidMotor;
    EctoMotorConfig motorrConfig;

    public ArmMotor(String moduleName, String moduleType) {
        super(moduleName, moduleType);
    }

    public void set(double degrees) {
        pidMotor.setSetPoint(EctoMath.degToRad(degrees));

    }

    public double getPoseDeg() {
        return EctoMath.radToDeg(armMotor.getPose_rad());
    }


    @Override
    public void initMechanism() {
        motorrConfig = new EctoMotorConfig();
        motorrConfig.withId("ArmMotor");
        motorrConfig.withEnableForwardSoftLimit(false);
        motorrConfig.withEnableReverseSoftLimit(false);
        motorrConfig.withTicksPerRev(4);
        motorrConfig.withInternalMotorGearRatio( (72.0/1.0) * (125.0 / 45.0) );
        armMotor = new EctoMotor(hardwareMap,motorrConfig);
        pidMotor = new PIDController(1.4,0.5,0.03);

    }

    @Override
    public void startMechanism() {

    }

    @Override
    public void updateMechanism() {
        armMotor.update();
        double out;
        out = pidMotor.calculate(armMotor.getPose_rad());
        armMotor.set(out);


    }

    @Override
    public void stopMechanism() {

    }
}



