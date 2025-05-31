package org.firstinspires.ftc.teamcode.Core.Tfod;

import android.graphics.Canvas;


/**
 * CanvasAnnotator is an interface used to draw the recognized objects onto a canvas.
 *
 * <p>This class is deprecated and will be removed in v10.0.
 */
public interface CanvasAnnotator {
    void draw(Canvas canvas, int onscreenWidth, int onscreenHeight, float scaleBmpPxToCanvasPx, float scaleCanvasDensity);
}
