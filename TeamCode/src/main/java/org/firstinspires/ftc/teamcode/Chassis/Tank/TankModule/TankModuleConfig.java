package org.firstinspires.ftc.teamcode.Chassis.Tank.TankModule;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.trajectory.TrapezoidProfile;
import org.firstinspires.ftc.teamcode.Core.Control.PIDConfig;

@Config
public class TankModuleConfig {

    public TankModuleConfig(String motorId){
        this.motorId = motorId;
    };
    public String motorId = "";
    public double motorTPR;
    public double internalGearRatio;
    public double gearRatio;
    public double wheelCircumference;
    public boolean invertSide;
    public double multiplier;
    public PIDConfig pidConfig = new PIDConfig();
    public TrapezoidProfile.Constraints constraints = new TrapezoidProfile.Constraints();

}
