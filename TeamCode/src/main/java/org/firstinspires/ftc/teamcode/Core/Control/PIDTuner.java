package org.firstinspires.ftc.teamcode.Core.Control;

import com.acmerobotics.dashboard.config.Config;

@Config
public class PIDTuner {

    public PIDTuner(String name){
        this.name = name;
    }
    public final String name;
    public static double P = 0;
    public static double I = 0;
    public static double D = 0;
    public static double F = 0;
    public static double tol = 0;
    public static double kS = 0;
    public static double kA = 0;
    public static double kV = 0;
    public static double maxOut = 0;
    public static double minOut = 0;
    public static double setPoint = 0;
    public static double iZone = 0;
    public static double maxVel = 0;
    public static double maxAccel = 0;
}
