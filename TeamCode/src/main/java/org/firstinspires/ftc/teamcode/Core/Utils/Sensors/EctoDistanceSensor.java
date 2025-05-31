package org.firstinspires.ftc.teamcode.Core.Utils.Sensors;

import com.arcrobotics.ftclib.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class EctoDistanceSensor implements HardwareDevice {

    private final DistanceSensor distanceSensor;

    public EctoDistanceSensor(DistanceSensor distanceSensor) {
        this.distanceSensor = distanceSensor;
    }

    public EctoDistanceSensor(HardwareMap hardwareMap, String name) {
        this(hardwareMap.get(DistanceSensor.class, name));

    }

    public double getDistance() {
        return distanceSensor.getDistance(DistanceUnit.CM);
    }

    @Override
    public void disable() {
        distanceSensor.close();
    }

    @Override
    public String getDeviceType() {
        return "distanceSensor";
    }
}
