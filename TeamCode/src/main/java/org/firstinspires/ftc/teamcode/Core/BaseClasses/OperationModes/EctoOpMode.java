//
// Created by Neil Rodriguez 10/28/2021
//

package org.firstinspires.ftc.teamcode.Core.BaseClasses.OperationModes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Core.Managers.MechanismManager;

public abstract class EctoOpMode extends OpMode {




  ElapsedTime runtime = new ElapsedTime();


  Double lastTimeRunned = 0.0;
  int updateRate = 10; // Milliseconds

  public CommandScheduler commandScheduler = CommandScheduler.getInstance();
  public MechanismManager mechanismManager = MechanismManager.getInstance();
//  public MechanismManager mechanismManager = new MechanismManager();
  public final FtcDashboard dashboard = FtcDashboard.getInstance();


  @Override
  public final void init() {
    initRobotClasses();
  }

  @Override
  public final void init_loop() {
    initRobot();
    telemetry.setMsTransmissionInterval(updateRate);
    MultipleTelemetry mTelemetry =  new MultipleTelemetry(telemetry, dashboard.getTelemetry());
    mTelemetry.setMsTransmissionInterval(updateRate);
    mechanismManager.telemetry = mTelemetry;
    mechanismManager.hardwareMap = hardwareMap;
    mechanismManager.initMechanisms();
  }

  @Override
  public final void start() {
    runtime.reset();
    runtime.startTime();
    lastTimeRunned = getRuntime();
    mechanismManager.startMechanisms();
    commandScheduler.enable();
    startRobot();
  }

  @Override
  public final void loop() {
    mechanismManager.updateMechanisms();
    double timeStep = getRuntime() - lastTimeRunned;
    lastTimeRunned = getRuntime();
    commandScheduler.run();
    updateRobot(timeStep);

  }

  /*
  - Runs Once Press Stop
  - Stops Everything.
  */
  @Override
  public final void stop() {
    mechanismManager.stopMechanisms();
    telemetry.clear();
    commandScheduler.disable();
    commandScheduler.reset();
  }

  /*
  - Runs Once You Select The OP Mode On The Driver Station
  - Used To Create The Objects Used In The Tele-Op Mode
  */
  public abstract void initRobotClasses();

  /*
  - Runs When You Click "Init" On The Driver Station
  - Used To Add Mechanism To The Mechanism Manager
  */
  public abstract void initRobot();

  /*
  - Runs When You click "Start" On The Driver Station
  - Used To Move Certain Mechanims To Their Starting Point
  */
  public abstract void startRobot();

  /*
  - Runs After You Click "Start" On The Driver Station
  - Used To Get The Input From The Controllers and "Control" The Mechanisms
  - Runs Every 10 Miliseconds
  */
  public abstract void updateRobot(Double timeStep);
}
