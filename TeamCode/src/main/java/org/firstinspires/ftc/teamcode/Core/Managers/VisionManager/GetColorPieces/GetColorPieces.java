package org.firstinspires.ftc.teamcode.Core.Managers.VisionManager.GetColorPieces;

import com.acmerobotics.roadrunner.Math;

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


public class GetColorPieces extends OpenCvPipeline {
    private final Scalar lowerBound;
    private final Scalar upperBound;


    public GetColorPieces(Scalar lowerBound, Scalar upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    @Override
    public Mat processFrame(Mat mat) {
        Mat range = new Mat();
        Imgproc.cvtColor(mat, range, Imgproc.COLOR_RGB2HSV);
        Core.inRange(range, lowerBound, upperBound, range);

        Mat erodeElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3, 3));
        Mat dilateElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(6, 6));

        //Preprocessing
        Mat cannyH = new Mat();
        Imgproc.GaussianBlur(mat, mat, new Size(5,5), 0.5);

        //Canny
        Imgproc.Canny(mat, cannyH, 140, 35);

        //Morphing
        Imgproc.dilate(cannyH, cannyH, dilateElement);
        Imgproc.dilate(cannyH, cannyH, dilateElement);
        Imgproc.erode(cannyH, cannyH, erodeElement);

        //Invert
        Core.bitwise_not(cannyH,cannyH);

        //Filter Color
        Core.bitwise_and(cannyH, range, cannyH);

        //Contours
        ArrayList<MatOfPoint> contours = new ArrayList<>();
        Imgproc.findContours(cannyH, contours, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_NONE);
        for( int i = 0; i< contours.size(); i++ )
        {
            if(Imgproc.contourArea(contours.get(i)) < 30) continue;
            Moments mu = Imgproc.moments(contours.get(i));
            Point mc = new Point(mu.m10 / (mu.m00 + 1e-5), mu.m01 / (mu.m00 + 1e-5));
            Imgproc.circle(mat,mc,4, new Scalar(255,255, 255),-1);
            Imgproc.drawContours( mat, contours, i, new Scalar(0,0,255), 2, 8);
        }

        //Draw rotatedRect
        for (MatOfPoint contour : contours) {
            if(Imgproc.contourArea(contour) < 30) continue;
            Point[] points = contour.toArray();
            MatOfPoint2f contour2f = new MatOfPoint2f(points);
            RotatedRect rotatedRect = Imgproc.minAreaRect(contour2f);

            // Get the box points (corners of the rectangle)
            Point[] vertices = new Point[4];
            rotatedRect.points(vertices);

            // Draw the rotated rectangle on the original image
            for (int i = 0; i < 4; i++) {
                Imgproc.line(mat, vertices[i], vertices[(i + 1) % 4], new Scalar(0, 255, 0), 2);
            }
        }


        range.release();
        return mat;
    }
}
