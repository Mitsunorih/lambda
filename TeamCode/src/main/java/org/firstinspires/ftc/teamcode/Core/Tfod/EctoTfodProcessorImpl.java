package org.firstinspires.ftc.teamcode.Core.Tfod;


import android.graphics.Bitmap;
import android.graphics.Canvas;

import org.firstinspires.ftc.robotcore.internal.camera.calibration.CameraCalibration;
import org.opencv.android.Utils;
import org.opencv.core.Mat;

import java.util.List;

class EctoTfodProcessorImpl extends EctoTfodProcessor implements FrameGenerator
{

    protected final TfodParameters parameters;
    protected final TFObjectDetector tfObjectDetector;
    protected final Object frameConsumerLock = new Object();
    protected FrameConsumer frameConsumer;
    protected Bitmap bitmap;
    protected int width;
    protected int height;
    protected float fx, fy = 100; // dummy

    public EctoTfodProcessorImpl(TfodParameters parameters)
    {
        this.parameters = parameters;
        tfObjectDetector = ClassFactoryTFOD.getInstance().createTFObjectDetector(parameters, this);
    }

    @Override
    public void init(int width, int height, CameraCalibration calibration)
    {
        this.width = width;
        this.height = height;

        if (calibration != null)
        {
            fx = calibration.focalLengthX;
            fy = calibration.focalLengthY;
        }

        tfObjectDetector.activate();
    }

    @Override
    public Object processFrame(Mat frame, long captureTimeNanos)
    {
        FrameConsumer frameConsumerSafe;
        Bitmap bitmapSafe;

        synchronized (frameConsumerLock)
        {
            if (frameConsumer == null)
            {
                return null;
            }
            frameConsumerSafe = frameConsumer;
            bitmapSafe = bitmap;
        }

        Utils.matToBitmap(frame, bitmapSafe);
        return frameConsumerSafe.processFrame();
    }

    @Override
    public void onDrawFrame(Canvas canvas, int onscreenWidth, int onscreenHeight, float scaleBmpPxToCanvasPx, float scaleCanvasDensity, Object userContext)
    {
        if (userContext != null)
        {
            ((CanvasAnnotator) userContext).draw(canvas, onscreenWidth, onscreenHeight, scaleBmpPxToCanvasPx, scaleCanvasDensity);
        }
    }

    @Override
    public CameraInformation getCameraInformation()
    {
        return new CameraInformation(width, height, 0, fx, fy);
    }

    @Override
    public void setFrameConsumer(FrameConsumer frameConsumer)
    {
        synchronized (frameConsumerLock)
        {
            this.frameConsumer = frameConsumer;

            if (frameConsumer != null)
            {
                bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                frameConsumer.init(bitmap);
            }
        }
    }

    @Override
    public void setMinResultConfidence(float minResultConfidence) {
        tfObjectDetector.setMinResultConfidence(minResultConfidence);
    }

    @Override
    public void setClippingMargins(int left, int top, int right, int bottom)
    {
        tfObjectDetector.setClippingMargins(left, top, right, bottom);
    }

    @Override
    public void setZoom(double magnification)
    {
        tfObjectDetector.setZoom(magnification);
    }

    @Override
    public List<Recognition> getRecognitions()
    {
        return tfObjectDetector.getRecognitions();
    }

    @Override
    public List<Recognition> getFreshRecognitions()
    {
        return tfObjectDetector.getUpdatedRecognitions();
    }

    @Override
    public void shutdown()
    {
        tfObjectDetector.shutdown();
    }
}
