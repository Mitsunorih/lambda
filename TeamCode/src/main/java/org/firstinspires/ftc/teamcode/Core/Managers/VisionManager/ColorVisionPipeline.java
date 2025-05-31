package org.firstinspires.ftc.teamcode.Core.Managers.VisionManager;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Moments;
import org.openftc.easyopencv.OpenCvPipeline;
import org.opencv.core.Core;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.RotatedRect;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
public class ColorVisionPipeline extends OpenCvPipeline {

    Mat blueThresholdMat = new Mat();
    Mat redThresholdMat = new Mat();

    public double minArea = 50;

    Mat Firstred = new Mat();
    Mat Secondred = new Mat();

    Mat yellowThresholdMat = new Mat();

    Mat morphedBlueThreshold = new Mat();
    Mat morphedRedThreshold = new Mat();
    Mat morphedYellowThreshold = new Mat();

    Mat contoursOnPlainImageMat = new Mat();
    Mat contoursBlue = new Mat();
    Mat contoursRed = new Mat();
    Mat contoursYellow = new Mat();


    public Scalar redLowBound1 = new Scalar(0, 120, 120);
    public Scalar redHighBound1 = new Scalar(10, 255, 255);

    public Scalar redLowBound2 = new Scalar(160, 100, 95);
    public Scalar redHighBound2 = new Scalar(180, 255, 255);

    public Scalar bluelowBound = new Scalar(70,70,110);
    public Scalar blueHighBound = new Scalar(122,255,255);

    public Scalar yellowlowBound = new Scalar(17, 150, 190);
    public Scalar yellowHighBound = new Scalar(30, 255, 255);

    //Elements for noise reduction
    Mat erodeElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(6, 6));
    Mat dilateElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(5, 5));

    //Colors
    static final Scalar RED = new Scalar(255, 0, 0);
    static final Scalar BLUE = new Scalar(0, 0, 255);
    static final Scalar YELLOW = new Scalar(255, 255, 0);

    static class AnalyzedSamples
    {
        double angle;
        String color;
    }

    ArrayList<AnalyzedSamples> internalSampleList = new ArrayList<>();
    volatile ArrayList<AnalyzedSamples> clientSampleList = new ArrayList<>();

    //Viewport stages
    enum Stage
    {
        FINAL,
        Blue,
        Red,
        Yellow,
        CONTOURS,
        MASKS,
        MASKS_NR,

    }

    Stage[] stages = Stage.values();
    int stageNum = 0;

    Telemetry telemetry;

    @Override
    public void onViewportTapped()
    {
        int nextStageNum = stageNum + 1;

        if(nextStageNum >= 5)
        {
            nextStageNum = 0;
        }

        stageNum = nextStageNum;
    }

    @Override
    public Mat processFrame(Mat input)
    {
        internalSampleList.clear();

        // Run the image processing
        findContours(input);

        Mat out = new Mat();
        clientSampleList = new ArrayList<>(internalSampleList);

        //Decide which buffer to send to the viewport
        switch (stages[stageNum])
        {
            case Blue:
            {
                Core.bitwise_and(input, input, out, morphedBlueThreshold);
                Core.bitwise_or(out, contoursBlue, out);
                return out;
            }

            case Red:
            {
                Core.bitwise_and(input, input, out, morphedRedThreshold);
                Core.bitwise_or(out, contoursRed, out);
                return out;
            }

            case Yellow:
            {
                Core.bitwise_and(input, input, out, morphedYellowThreshold);
                Core.bitwise_or(out, contoursYellow, out);
                return out;
            }

            case FINAL:
            {
                return input;
            }

            case CONTOURS:
            {
                return contoursOnPlainImageMat;
            }

            default:
            {
                return input;
            }
        }
    }

    public ArrayList<AnalyzedSamples> getDetectedSamples()
    {
        return clientSampleList;
    }

    void findContours(Mat input)
    {
        Mat out = new Mat();
        // Convert the input image to YCrCb color space
        Imgproc.cvtColor(input, out, Imgproc.COLOR_RGB2HSV);

        Core.inRange(out, yellowlowBound, yellowHighBound, yellowThresholdMat);
        Core.inRange(out, bluelowBound, blueHighBound, blueThresholdMat);
        Core.inRange(out, redLowBound1, redHighBound1, Firstred);
        Core.inRange(out, redLowBound2, redHighBound2, Secondred);

        Core.bitwise_or(Firstred, Secondred, redThresholdMat);

        Imgproc.erode(blueThresholdMat, morphedBlueThreshold, erodeElement);
        Imgproc.erode(morphedBlueThreshold, morphedBlueThreshold, erodeElement);

        Imgproc.dilate(morphedBlueThreshold, morphedBlueThreshold, dilateElement);
        Imgproc.dilate(morphedBlueThreshold, morphedBlueThreshold, dilateElement);

        Imgproc.erode(redThresholdMat, morphedRedThreshold, erodeElement);
        Imgproc.erode(morphedRedThreshold, morphedRedThreshold, erodeElement);

        Imgproc.dilate(morphedRedThreshold, morphedRedThreshold, dilateElement);
        Imgproc.dilate(morphedRedThreshold, morphedRedThreshold, dilateElement);

        Imgproc.erode(yellowThresholdMat, morphedYellowThreshold, erodeElement);
        Imgproc.erode(morphedYellowThreshold, morphedYellowThreshold, erodeElement);

        Imgproc.dilate(morphedYellowThreshold, morphedYellowThreshold, dilateElement);
        Imgproc.dilate(morphedYellowThreshold, morphedYellowThreshold, dilateElement);


        ArrayList<MatOfPoint> blueContoursList = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(morphedBlueThreshold, blueContoursList, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_NONE);

        ArrayList<MatOfPoint> redContoursList = new ArrayList<>();
        Imgproc.findContours(morphedRedThreshold, redContoursList, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_NONE);

        ArrayList<MatOfPoint> yellowContoursList = new ArrayList<>();
        Imgproc.findContours(morphedYellowThreshold, yellowContoursList, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_NONE);

        contoursOnPlainImageMat = Mat.zeros(input.size(), input.type());
        contoursBlue = Mat.zeros(input.size(), input.type());
        contoursRed = Mat.zeros(input.size(), input.type());
        contoursYellow = Mat.zeros(input.size(), input.type());

        for(MatOfPoint contour : blueContoursList)
        {
            if(Imgproc.contourArea(contour) > minArea){
                Point[] points = contour.toArray();
                MatOfPoint2f contour2f = new MatOfPoint2f(points);

                // Fit a rotated rectangle to the contour and draw it
                RotatedRect rotatedRectFitToContour = Imgproc.minAreaRect(contour2f);

                drawRotatedRect(rotatedRectFitToContour, input, "Blue");
                drawRotatedRect(rotatedRectFitToContour, contoursOnPlainImageMat, "Blue");
                drawRotatedRect(rotatedRectFitToContour, contoursBlue, "Blue");
                findCenter(contour, input, "Blue");

                // Adjust the angle based on rectangle dimensions
                double rotRectAngle = rotatedRectFitToContour.angle;
                if (rotatedRectFitToContour.size.width < rotatedRectFitToContour.size.height)
                {
                    rotRectAngle += 90;
                }


                // Store the detected sample information
                AnalyzedSamples analyzedSamples = new AnalyzedSamples();
                analyzedSamples.angle = rotRectAngle;
                analyzedSamples.color = "Blue";
                internalSampleList.add(analyzedSamples);

            }
        }

        for(MatOfPoint contour : redContoursList)
        {
            if(Imgproc.contourArea(contour) > minArea){
                Point[] points = contour.toArray();
                MatOfPoint2f contour2f = new MatOfPoint2f(points);

                // Fit a rotated rectangle to the contour and draw it
                RotatedRect rotatedRectFitToContour = Imgproc.minAreaRect(contour2f);

                drawRotatedRect(rotatedRectFitToContour, input, "Red");
                drawRotatedRect(rotatedRectFitToContour, contoursOnPlainImageMat, "Red");
                drawRotatedRect(rotatedRectFitToContour, contoursRed, "Red");
                findCenter(contour, input, "Red");

                // Adjust the angle based on rectangle dimensions
                double rotRectAngle = rotatedRectFitToContour.angle;
                if (rotatedRectFitToContour.size.width < rotatedRectFitToContour.size.height)
                {
                    rotRectAngle += 90;
                }

                // Store the detected sample information
                AnalyzedSamples analyzedSamples = new AnalyzedSamples();
                analyzedSamples.angle = rotRectAngle;
                analyzedSamples.color = "Red";
                internalSampleList.add(analyzedSamples);
            }
        }

        for(MatOfPoint contour : yellowContoursList)
        {
            if(Imgproc.contourArea(contour) > minArea){
                Point[] points = contour.toArray();
                MatOfPoint2f contour2f = new MatOfPoint2f(points);

                // Fit a rotated rectangle to the contour and draw it
                RotatedRect rotatedRectFitToContour = Imgproc.minAreaRect(contour2f);

                drawRotatedRect(rotatedRectFitToContour, input, "Yellow");
                drawRotatedRect(rotatedRectFitToContour, contoursOnPlainImageMat, "Yellow");
                drawRotatedRect(rotatedRectFitToContour, contoursYellow, "Yellow");
                findCenter(contour, input, "Yellow");

                // Adjust the angle based on rectangle dimensions
                double rotRectAngle = rotatedRectFitToContour.angle;
                if (rotatedRectFitToContour.size.width < rotatedRectFitToContour.size.height)
                {
                    rotRectAngle += 90;
                }


                // Store the detected sample information
                AnalyzedSamples analyzedSamples = new AnalyzedSamples();
                analyzedSamples.angle = rotRectAngle;
                analyzedSamples.color = "Yellow";
                internalSampleList.add(analyzedSamples);
            }
        }
        out.release();
    }

    static void findCenter(MatOfPoint contour, Mat drawOn, String color)
    {
        /*
         * ENCUENTRA EL CENTRO DE MASA
         */

        //Imgproc.putText(drawOn, "Area: " + Imgproc.contourArea(contour), mc, Imgproc.FONT_HERSHEY_PLAIN, 1, colorScalar, 1);

    }

    static void drawRotatedRect(RotatedRect rect, Mat drawOn, String color)
    {
        /*
         * Draws a rotated rectangle by drawing each of the 4 lines individually
         */
        Point[] points = new Point[4];
        rect.points(points);

        Scalar colorScalar = getColorScalar(color);

        for (int i = 0; i < 4; ++i)
        {
            Imgproc.line(drawOn, points[i], points[(i + 1) % 4], colorScalar, 2);
        }
    }

    static Scalar getColorScalar(String color)
    {
        switch (color)
        {
            case "Blue":
                return BLUE;
            case "Yellow":
                return YELLOW;
            default:
                return RED;
        }
    }
}
