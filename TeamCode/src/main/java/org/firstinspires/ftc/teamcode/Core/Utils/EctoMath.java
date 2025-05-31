package org.firstinspires.ftc.teamcode.Core.Utils;

import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.arcrobotics.ftclib.geometry.Translation2d;
import com.arcrobotics.ftclib.geometry.Vector2d;

import java.io.IOException;


public class EctoMath {

    private static final double pi = Math.PI;
    private static final double epsilon = 1e-12;

    public static double degToRad(double deg) {
        return deg * (pi / 180.0);
    }

    public static double radToDeg(double rad) {
        return rad * (180.0 / pi);
    }

    public static double addTwoAngles(double first, double second) {
        return wrapAngle(first + second);
    }

    public static boolean epsilonEquals(double a, double b, double epsilon) {
        return (a - epsilon <= b) && (a + epsilon >= b);
    }

    public static boolean epsilonEquals(double a, double b) {
        return epsilonEquals(a, b, epsilon);
    }

    public static double shortestAngleTurn(double current, double target) {
        double angleToTurn = target - current;

        if (Math.abs(angleToTurn) > pi) {
            if (current > 0) {
                angleToTurn = angleToTurn - pi;
            } else {
                angleToTurn = angleToTurn + (2 * pi);
            }
        } else {
            if (angleToTurn > 0) {
                angleToTurn = angleToTurn - (2 * pi);
            } else {
                angleToTurn = angleToTurn + pi;
            }
        }

        return angleToTurn;
    }

    public static Translation2d polarToCartesian(double r, double th){
        double x = r * Math.cos(th);
        double y = r * Math.sin(th);

        return new Translation2d(x, y);
    }

    public static double cartesianToPolar(double x, double y){
        double th = Math.atan2(y,x);
        return th;
    }

    public static double wrapAngle(double rads){
        double angle = Math.copySign((rads % (2 * pi)), rads);

        if (angle > pi) {
            angle -= 2 * pi;
        }else if (angle < -pi) {
            angle += 2 * pi;
        }

        return angle;
    }

    public static double dewrapAngle(double angle) {
        if (angle < 0) {
            angle += 360;
        }
        return angle;
    }

    public static double map(double x, double inMin, double inMax, double outMin, double outMax) {
        return (x - inMin) * (outMax - outMin) / (inMax - inMin) + outMin;
    }

    public static double extrapolate(double in, double x1, double y1, double x2, double y2) {
        return y1 + ((in - x1) / (x2 - x1)) * (y2 - y1);
    }

    public static double ticksToRads(double ticks, double tpr, double internalGearRatio){
        return ((ticks / tpr) * 2 * pi) / internalGearRatio;
    }

    public static double radsToMeters(double rads, double gearRatio, double circumference) {
        return ((rads / (2 * pi)) / gearRatio) * circumference;
    }

    public static double max(double... nums) {
        double max = nums[0];
        for (double num : nums) {
            max = Math.max(num, max);
        }
        return max;
    }

    public static double inputModulus(double input, double minInput, double maxInput) {
        double modulus = maxInput - minInput;

        // Wrap input if it's above the maximum input
        int numMax = (int) ((input - minInput) / modulus);
        input -= numMax * modulus;

        // Wrap input if it's below the minimum input
        int numMin = (int) ((input - maxInput) / modulus);
        input -= numMin * modulus;

        return input;
    }

    public static double angleModulus(double rads) {
        return inputModulus(rads, -Math.PI, Math.PI);
    }

    public static double clamp(double in, double min, double max){
        if (in < min) return min;
        return Math.min(in, max);

    }

    public static double inchToMeters(double inches){
        return inches * .0254;
    }

    public static double triangleAngleFromSideLengths(double a, double b, double c, String desiredAngle) {
        switch (desiredAngle) {
            case "A":
                return Math.acos(Math.abs((a * a) - (c * c) - (b * b) + (2 * c * b)));

            case "B":
                return Math.acos((b * b) - (c * c) - (a * a) + (2 * c * a));

            case "C":
                return Math.acos((c * c) - (b * b) - (a * a) + (2 * b * a));

            default:
                throw new RuntimeException("not an angle in a triangle, EctoMath.riangleAngleFromSideLengths");

        }
    }

    public static Pose2d RR2FTCLib(com.acmerobotics.roadrunner.Pose2d pose) {
        return new Pose2d(pose.position.x, pose.position.y, new Rotation2d(pose.heading.toDouble()));
    }

    public static double CW2CCW(double cwAngle) {
        //assuming that it comes as from 0 to 2pi
        return -(cwAngle - 2 * Math.PI);
    }

    public static double distance(com.acmerobotics.roadrunner.Vector2d pose, com.acmerobotics.roadrunner.Vector2d goal) {
        return Math.hypot(pose.x - goal.x, pose.y - goal.y);
    }


}
