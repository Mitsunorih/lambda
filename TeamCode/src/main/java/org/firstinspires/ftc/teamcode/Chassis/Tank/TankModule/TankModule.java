package org.firstinspires.ftc.teamcode.Chassis.Tank.TankModule;

import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.controller.wpilibcontroller.SimpleMotorFeedforward;
import com.arcrobotics.ftclib.hardware.motors.Motor;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoMechanism;
import org.firstinspires.ftc.teamcode.Core.Utils.EctoMath;
import org.firstinspires.ftc.teamcode.RobotOpModes.TeleOperated.RobotConfiguration;
import org.firstinspires.ftc.teamcode.Chassis.ControlMode.ControlMode;

public class TankModule extends EctoMechanism {
    double setPoint, pidOut;
    double vel;
    double pos;
    ControlMode controlMode = ControlMode.PERCENT;
    Motor motor;
    TankModuleConfig config;
    PIDFController controller = new PIDFController(0, 0, 0,0);

    SimpleMotorFeedforward ff = new SimpleMotorFeedforward(0,0,0);

    public
    TankModule(String moduleName, String moduleType, TankModuleConfig config) {
        super(moduleName, moduleType);
        this.config = config;
    }

    public final double getPose(){
        double rads = EctoMath.ticksToRads(motor.getCurrentPosition(),config.motorTPR,config.internalGearRatio);
        return EctoMath.radsToMeters(rads, config.gearRatio, config.wheelCircumference);
    }

    public final double getVel() {
        double rads = EctoMath.ticksToRads(motor.getCorrectedVelocity(),config.motorTPR,config.internalGearRatio);
        return EctoMath.radsToMeters(rads, config.gearRatio, config.wheelCircumference);
    }

    public final void set(double set, ControlMode controlMode) {
        this.setPoint = set * config.multiplier;
        this.controlMode = controlMode;
    }
    public void resetEncoders(){
        motor.resetEncoder();
    }

    public final void set(double set) {
        set(set, ControlMode.PERCENT);
    }

    public void updateTelemetry() {
//        telemetry.addData(config.motorId + " setPoint", setPoint);
        telemetry.addData(config.motorId + " pose", getPose());
//        telemetry.addData(config.motorId + " ticks", motor.getCPR());
//        telemetry.addData(config.motorId + " vel", getVel());
//        telemetry.addData(config.motorId + "  controlMode", ControlMode.toString(controlMode));
//        telemetry.addData(config.motorId + " pidOut", pidOut);
//        telemetry.addData(config.motorId + "p", controller.getP());
//        telemetry.addData(config.motorId + "i", controller.getI());
//        telemetry.addData(config.motorId + "d", controller.getD());
//        telemetry.addData(config.motorId + "f", controller.getF());
    }

    @Override
    public void initMechanism() {
        motor = new Motor(hardwareMap, config.motorId);
        motor.setInverted(config.invertSide);
        controller = new PIDFController(config.pidConfig.kP, config.pidConfig.kI, config.pidConfig.kD, config.pidConfig.kF);
        ff = new SimpleMotorFeedforward(config.pidConfig.kS, config.pidConfig.kV, config.pidConfig.kA);
    }

    @Override
    public void startMechanism() {

    }

    @Override
    public void updateMechanism() {

        vel = getVel();
        pos = getPose();
        updateTelemetry();
        if (controlMode == ControlMode.VELOCITY){
            pidOut = controller.calculate(getVel(), setPoint);
            pidOut += ff.calculate(getVel());
            motor.set(pidOut);
            return;
        }

        if (controlMode == ControlMode.VOLTAGE) {
            motor.set(setPoint / 0); //CHANGE THIS
            return;
        }

        motor.set(setPoint);
    }

    @Override
    public void stopMechanism() {
    }
}
