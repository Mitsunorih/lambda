package org.firstinspires.ftc.teamcode.Core.Control.EctoMotor;

import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.Core.Utils.EctoMath;

import java.util.function.DoubleSupplier;

import androidx.annotation.NonNull;


public class EctoMotor {
    private final Motor motor;
    private final DcMotorEx currentSource;
    private final EctoMotorConfig config;

    double u = 0;


    public EctoMotor(@NonNull HardwareMap hardwareMap, @NonNull EctoMotorConfig config) {
        this.config = config;
        motor = new Motor(hardwareMap, config.id);
        motor.setInverted(config.isInverted);
        motor.setZeroPowerBehavior(config.enableBrakingOnIdle ? Motor.ZeroPowerBehavior.BRAKE : Motor.ZeroPowerBehavior.FLOAT);
        currentSource = hardwareMap.get(DcMotorEx.class, config.id);

        if (config.feedbackMode.equals(MotorFeedbackMode.kINTERNAL)) {
            config.poseSupplier = () -> {return EctoMath.ticksToRads(getPose_tick(), config.ticksPerRev, config.internalMotorGearRatio);};
            config.velSupplier = () -> {return EctoMath.ticksToRads(getVel_tick(), config.ticksPerRev, config.internalMotorGearRatio);};
        }
    }

    public void set(double set, MotorControlMode controlMode) {
        switch (controlMode) {
            case kVOLTAGE:
                u = set / 12;
                return;
            case kCURRENT:
                throw new RuntimeException("invalid control mode with ecto motor, not yet implemented");
            default:
                u = set;
        }
    }

    public void set(double set) {
        set(set, MotorControlMode.kPERCENT);
    }

    public void reset_encoder(double set){
        motor.resetEncoder();
    }

    public void reset_encoder() {
        reset_encoder(0.0);
    }

    public double getPose_rad() {
        return config.poseSupplier.getAsDouble();
    }

    public int getPose_tick() {
        return motor.getCurrentPosition();

    }

    public double getVel_rad() {
        return config.velSupplier.getAsDouble();
    }

    public double getVel_tick() {
        return motor.getCorrectedVelocity();
    }

    public double getAccel_tick() {
        return motor.encoder.getAcceleration();
    }

    public double getCurrent() {
        return currentSource.getCurrent(CurrentUnit.AMPS);
    }

    public void update() {
        if (u >= 0 && getPose_rad() >= config.forwardSoftLimit && config.enableForwardSoftLimit) u = 0;
        if (u <= 0 && getPose_rad() <= config.reverseSoftLimit && config.enableReverseSoftLimit) u = 0;
        if (getCurrent() >= config.currentLimit && config.enableCurrentLimit) u -= 0.25;
        motor.set(u);
    }

}
