//
// Created by Neil Rodriguez 10/28/2021
//

package org.firstinspires.ftc.teamcode.Core.Managers;

import android.os.SystemClock;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoMechanism;

import java.util.ArrayList;
import java.util.Arrays;

public class MechanismManager {

    private static MechanismManager INSTANCE;
    //CONSTRUCTORS
    private MechanismManager() {

    }

    public static synchronized MechanismManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MechanismManager();
        }
        return INSTANCE;
    }

    //VARIABLES
    public HardwareMap hardwareMap;
    public MultipleTelemetry telemetry;


    ArrayList<EctoMechanism> mechanisms = new ArrayList<EctoMechanism>();
    ArrayList<Double> lastTimeRunned = new ArrayList<>();

    //VOIDS/ FUNCTIONS
//    public void addMechanism(EctoMechanism... mechanisms) {
//        for (EctoMechanism mechanism : mechanisms) {
//            addMechanism(mechanism);
//            lastTimeRunned.add(0.0);
//        }
//    }

    public void addMechanism(EctoMechanism mechanism) {
        mechanisms.add(mechanism);
        lastTimeRunned.add(0.0);
    }

    public void initMechanisms() {
        for (int i = 0; i < mechanisms.size(); i++) {
            mechanisms.get(i).initMechanism(hardwareMap, telemetry);
            mechanisms.get(i). initMechanism();
            lastTimeRunned.set(i, (double) SystemClock.elapsedRealtime());
        }
    }

    public void startMechanisms() {
        for (int i = 0; i < mechanisms.size(); i++) {
            mechanisms.get(i).startMechanism();
        }
    }

    public void updateMechanisms() {
        for (int i = 0; i < mechanisms.size(); i++) {
            Double timeStep = SystemClock.elapsedRealtime() / 1000.0 - lastTimeRunned.get(i);
            lastTimeRunned.set(i, (double) SystemClock.elapsedRealtime());
            mechanisms.get(i).updateMechanism();
        }
    }

    public void stopMechanisms() {
        for (int i = 0; i < mechanisms.size(); i++) {
            mechanisms.get(i).stopMechanism();
        }
        mechanisms = new ArrayList<EctoMechanism>();
    }


}
