package org.firstinspires.ftc.teamcode.Mechanisms;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoMechanism;
import org.firstinspires.ftc.teamcode.Core.Control.EctoServo.EctoServo;
import org.firstinspires.ftc.teamcode.Core.Utils.EctoMath;

public class ServoClaws2 extends EctoMechanism {

    EctoServo ServoClaws2;

    public ServoClaws2(String moduleName, String moduleType) {
        super(moduleName, moduleType);
    }
    //config pend


    public void set(int deg) {
        ServoClaws2.set(EctoMath.degToRad(deg));
    }

    @Override
    public void initMechanism() {
        ServoClaws2 = new EctoServo(hardwareMap, "ServoClaws2",EctoMath.degToRad(0),EctoMath.degToRad(135),false, 1.0, 1.0);

    }

    @Override
    public void startMechanism() {

    }

    @Override
    public void updateMechanism() {
        ServoClaws2.update();
    }

    @Override
    public void stopMechanism() {

    }
}

