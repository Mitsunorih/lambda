package org.firstinspires.ftc.teamcode.Core.Control;

public class FFConfig {

    public FFConfig(double Ks, double Kv, double Ka) {
        this.Ks = Ks;
        this.Kv = Kv;
        this.Ka = Ka;
    }

    public FFConfig(double Ks, double Kg, double Kv, double Ka) {
        this.Ks = Ks;
        this.Kg = Kg;
        this.Kv = Kv;
        this.Ka = Ka;
    }

    public FFConfig (double Kv, double Ka) {
        this.Kv = Kv;
        this.Ka = Ka;
    }
    public double Ks = 0;
    public double Kg = 0;
    public double Kv = 0;
    public double Ka = 0;
}
