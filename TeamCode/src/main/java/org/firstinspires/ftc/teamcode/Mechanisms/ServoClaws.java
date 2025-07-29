package org.firstinspires.ftc.teamcode.Mechanisms;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoMechanism;
import org.firstinspires.ftc.teamcode.Core.Control.EctoMotor.EctoMotor;
import org.firstinspires.ftc.teamcode.Core.Control.EctoServo.EctoServo;
import org.firstinspires.ftc.teamcode.Core.Utils.EctoMath;

public class ServoClaws extends EctoMechanism {

    EctoServo ServoClaws;

    public ServoClaws(String moduleName, String moduleType) {
        super(moduleName, moduleType);
    }
    //config pend


    public void set(int deg) {
        ServoClaws.set(EctoMath.degToRad(deg));
    }

    @Override
    public void initMechanism() {
        ServoClaws = new EctoServo(hardwareMap, "ServoClaws",EctoMath.degToRad(0),EctoMath.degToRad(135),false, 1.0, 1.0);

    }

    @Override
    public void startMechanism() {

    }

    @Override
    public void updateMechanism() {
    ServoClaws.update();
    }

    @Override
    public void stopMechanism() {

    }
}
