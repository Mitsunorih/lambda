package org.firstinspires.ftc.teamcode.Core.Control;


// https://en.wikipedia.org/wiki/PID_controller
public class PIDConfig {
    public PIDConfig(double kP, double kI, double kD, double kF) {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.kF = kF;
    }

    public PIDConfig(double kP, double kI, double kD) {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
    }

    public PIDConfig(double kP, double kI, double kD, double kS, double kV, double kA){
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.kS = kS;
        this.kV = kV;
        this.kA = kA;
    };
    public PIDConfig(double kP,double kI,double kD, double kF,double kS, double kV, double kA){
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.kF = kF;
        this.kS = kS;
        this.kV = kV;
        this.kA = kA;
    }

    public PIDConfig(){;}

    public double kP;
    public double kI;
    public double kD;
    public double kF;
    public double kS;
    public double kA;
    public double kV;
    public double iZone;
    public double minOut;
    public double maxOut;
    public double tolerance;
//    public double
}
