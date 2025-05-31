//
// Created by Neil Rodriguez 10/28/2021
//

package org.firstinspires.ftc.teamcode.Core.BaseClasses.OperationModes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Core.Managers.MechanismManager;

abstract public class EctoLinearOpMode extends LinearOpMode {

    public MechanismManager mechanismManager = MechanismManager.getInstance();
//public MechanismManager mechanismManager = new MechanismManager();

    public final FtcDashboard dashboard = FtcDashboard.getInstance();

    int updateRate = 10; //Miliseconds

    @Override
    public final void runOpMode(){
        //Poner Mechanisms
        initRobotClasses();

        //Poner Trajectories
        initTrajectories();

        //Agregar a el mechanism manager
        initRobot();
        telemetry.setMsTransmissionInterval(updateRate);
        MultipleTelemetry mTelemetry =  new MultipleTelemetry(telemetry, dashboard.getTelemetry());
        mTelemetry.setMsTransmissionInterval(updateRate);
        mechanismManager.telemetry = mTelemetry;
        mechanismManager.hardwareMap = hardwareMap;
        mechanismManager.initMechanisms();

        waitForStart();

        mechanismManager.startMechanisms();

        if (isStopRequested())
            return;


        mechanismManager.updateMechanisms();
        startRobotTrajectories();

    }

    protected abstract void initRobot();

    protected abstract void initTrajectories();

    /*
    - Runs Once You Select The Autonomous OP Mode On The Driver Station
    - Used To Create The Objects Used In The Tele-Op Mode
    */
    abstract public void initRobotClasses();

    /*
    - Runs When You click "Start" On The Driver Station
    - Used To Move Certain Mechanims To Their Starting Point
    */
    abstract public void startRobotTrajectories();

}
