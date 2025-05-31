package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.AccelConstraint;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Arclength;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Pose2dDual;
import com.acmerobotics.roadrunner.PosePath;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.Rotation2d;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.VelConstraint;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.DriveShim;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import org.jetbrains.annotations.NotNull;

import java.util.Vector;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800, 60);
        double offset = 70;

        Pose2d startPose = new Pose2d(0+70, 0, Math.toRadians(0));
        Pose2d preScorePrecharged = new Pose2d(20,  25, Math.toRadians(45));
        Pose2d scorePrecharged  = new Pose2d(20,  60, Math.toRadians(45));
        Pose2d moveFirstSamplePose = new Pose2d(20, 100, Math.toRadians(95));

         Action pushPieces, pushPieces2;
        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build(); ;

        DriveShim drive = myBot.getDrive();



        pushPieces = drive.actionBuilder(startPose)
                .setReversed(true)
                .strafeTo(new Pose2d(-140+70,  0, Math.toRadians(0)).position)
                .turnTo(Math.toRadians(90))
                .strafeTo(new Pose2d(-140+70,  -70, Math.toRadians(90)).position, new TranslationalVelConstraint(20), new ProfileAccelConstraint(-10, 10))
                .build();

        pushPieces2 = drive.actionBuilder(new Pose2d(20, 100, Math.toRadians(90)))
                .setReversed(true)
                .splineTo(new Pose2d(-10,  25, Math.toRadians(90)).position, Math.toRadians(-90))
                .splineTo(new Pose2d(0,  0, Math.toRadians(0)).position,  Math.toRadians(-90))
                .build();









        myBot.runAction(pushPieces);





        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}