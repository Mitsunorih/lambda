package org.firstinspires.ftc.teamcode.Core.RobotState;


import org.firstinspires.ftc.teamcode.Core.Utils.EctoMath;

public final class RobotStates {

    public State initPose;
    public State intakeShort;
    public State intakeFirstSpecimen;
    public State outtake;
    public State intakeMid;
    public State intakeLong;
    public State intakeDoubleSpecimen;
    public State transition;
    public State liftSpecimen;
    public State StartPose;
    public State idle;
    public State intakeSuperLong;
    public State queueBucket;
    public State scoreHighBucket;
    public State scoreLowBucket;
    public State queueSpecimen;
    public State scoreHighSpecimen;
    public State touchLeve1;
    public State climbExtended;
    public State climbContracted;




    public RobotStates(){;}

    public static RobotStates get() {
         double clawOpen = Math.toRadians(37);
         double clawClosed = Math.toRadians(117);
         double clawQueue = Math.toRadians(112);

         double elevatorIdle = .000;
         double elevatorClimbExtended = 0.6;
         double elevatorClimbContracted = 0.2;
         double elevatorQueueBucket = 0.1;
         double elevatorScoreHighBucket = 0.8;
         double elevatorScoreLowBucket = 0.55;
         double elevatorSpecimen = 0.068;

         double shoulderIdle = Math.toRadians(-118.5);
         double shoulderTransition = Math.toRadians(-110);
         double shoulderScoreHighBucket = Math.toRadians(103);
         double shoulderScoreLowBucket = Math.toRadians(100);
         double shoulderQueueSpecimen = Math.toRadians(120);
         double shoulderScoreSpecimen = Math.toRadians(60);

         double wristIdle = Math.toRadians(82);//65
         double wristIntake = Math.toRadians(100);
         double wristTransition = Math.toRadians(-50);
         double wristQueueBucket = Math.toRadians(84);
         double wristZero = Math.toRadians(0);
         double wristScoreLowBucket = Math.toRadians(84);

         double slidesIdle = 0.01;
         double slidesMid = 0.2;
        double slidesLong = 0.3;//0.44
        double slidesSuperLong = 0.42;
         double slidesShort = 0.11;

         double intakeWristQueue = Math.toRadians(-18);
         double intakeWristIdle = Math.toRadians(-10);
         double intakeWristOuttake = Math.toRadians(100);
         double intakeWristIntake = Math.toRadians(110);
         double intakeWristTransition = Math.toRadians(40);

         double rollersIntake = -0.65;
         double rollersOutake = 1;
         double rollersBucket = 0.6;
         double rollersIdle = 0;

        RobotStates states = new RobotStates();

        states.initPose = new State(
                shoulderIdle,
                wristIdle,
                elevatorIdle,
                slidesIdle,
                intakeWristIdle,
                rollersIdle,
                clawClosed,
                false
        );

        states.idle = new State(//done
                shoulderIdle,
                wristIdle,
                elevatorIdle,
                slidesIdle,
                intakeWristIdle,
                rollersIdle,
                clawClosed,
                false
        );

        states.climbExtended = new State(
                shoulderIdle,
                wristIdle,
                elevatorClimbExtended,
                slidesIdle,
                intakeWristIdle,
                rollersIdle,
                clawClosed,
                false,
                0.17
        );

        states.climbContracted = new State(
                shoulderIdle,
                wristIdle,
                elevatorClimbContracted,
                slidesIdle,
                intakeWristIdle,
                rollersIdle,
                clawClosed,
                false,
                0.12
        );


        states.outtake = new State(
                shoulderIdle,
                wristIntake,
                elevatorIdle,
                slidesIdle,
                intakeWristOuttake,
                rollersOutake,
                clawOpen,
                false
        );

        states.intakeMid = new State(
                shoulderIdle,
                wristIntake,
                elevatorIdle,
                slidesMid,
                intakeWristIntake,
                rollersIntake,
                clawOpen,
                false
        );

        states.intakeSuperLong = new State(
                shoulderIdle,
                wristIntake,
                elevatorIdle,
                slidesSuperLong,
                intakeWristIntake,
                rollersIntake,
                clawOpen,
                false
        );
        states.intakeLong = new State(
                shoulderIdle,
                wristIntake,
                elevatorIdle,
                slidesLong,
                intakeWristIntake,
                rollersIntake,
                clawOpen,
                false
        );

        states.intakeShort = new State(
                shoulderIdle,
                wristIntake,
                elevatorIdle,
                slidesShort,
                intakeWristIntake,
                rollersIntake,
                clawOpen,
                false
        );


        states.transition = new State(
                shoulderTransition,
                wristTransition,
                elevatorIdle,
                slidesIdle,
                intakeWristTransition,
                rollersIdle,
                clawOpen,
                false
        );
        states.queueBucket = new State(
                elevatorIdle,
                wristQueueBucket,
                elevatorQueueBucket,
                slidesIdle,
                intakeWristIdle,
                rollersBucket,
                clawQueue,
                false
        );
        states.scoreHighBucket = new State(
                shoulderScoreHighBucket,
                wristZero,
                elevatorScoreHighBucket,
                slidesIdle,
                intakeWristIdle,
                rollersIdle,
                clawOpen,
                false
        );
        states.scoreLowBucket = new State(
                shoulderScoreLowBucket,
                wristScoreLowBucket,
                elevatorScoreLowBucket,
                slidesIdle,
                intakeWristIdle,
                rollersIdle,
                clawOpen,
                false
        );

        states.queueSpecimen = new State(
                shoulderQueueSpecimen,
                wristZero,
                elevatorSpecimen,
                slidesIdle,
                intakeWristQueue,
               rollersBucket,
                clawQueue,
                false
        );



        states.scoreHighSpecimen = new State(
                shoulderScoreSpecimen,
                wristZero,
                elevatorSpecimen,
                slidesIdle,
                intakeWristIdle,
                rollersIdle,
                clawClosed,
                false
        );






        return states;
    }
}
