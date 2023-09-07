package org.firstinspires.ftc.teamcode.Base.Sensors;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;


public class OpenCVPipeline extends OpenCvPipeline {

   Scalar CAMERA_OUTLINE = new Scalar(0, 255, 255);

    // Scalar Initialization,
    public static Scalar scalarLowerYCrCb = new Scalar(  0.0, 150.0, 120.0);
    public static Scalar scalarUpperYCrCb = new Scalar(255.0, 255.0, 255.0);

    public boolean error = false;
    public Exception debug;

    //Declaration.  Final value are set during object instantiation and initialization
    private int borderLeftX   = 0;
    private int borderRightX  = 0;
    private int borderTopY    = 0;
    private int borderBottomY = 0;
    private int CAMERA_WIDTH = 0;
    private int CAMERA_HEIGHT = 0;

    // Declaration of counters used during process image looking for contours
    private int loopcounter = 0;
    private int ploopcounter = 0;

    private Mat imageYCrCb = new Mat();
    private Mat processed = new Mat();

    private Rect maxRect = new Rect();

    private double maxArea = 0;
    private boolean first = false;

    public void ConfigurePipeline(int borderLeftX, int borderRightX, int borderTopY, int borderBottomY, int CAMERA_WIDTH, int CAMERA_HEIGHT)
    {
        this.borderLeftX = borderLeftX;
        this.borderRightX = borderRightX;
        this.borderTopY = borderTopY;
        this.borderBottomY = borderBottomY;
        this.CAMERA_WIDTH = CAMERA_WIDTH;
        this.CAMERA_HEIGHT = CAMERA_HEIGHT;
    }

    public void ConfigureScalarLower(double Y, double Cr, double Cb) { scalarLowerYCrCb = new Scalar(Y, Cr, Cb); }
    public void ConfigureScalarUpper(double Y, double Cr, double Cb) { scalarUpperYCrCb = new Scalar(Y, Cr, Cb); }

    @Override
    public Mat processFrame(Mat input)
    {
        Mat output = input.clone();
        try
        {
            // Converting RGB Image to YcrCB image in order to handle lighting
            Imgproc.cvtColor(input, imageYCrCb, Imgproc.COLOR_RGB2YCrCb);
            Core.inRange(imageYCrCb, scalarLowerYCrCb, scalarUpperYCrCb, processed);

            // Remove Noise
            Imgproc.morphologyEx(processed, processed, Imgproc.MORPH_OPEN, new Mat());
            Imgproc.morphologyEx(processed, processed, Imgproc.MORPH_CLOSE, new Mat());

            // GaussianBlur
            Imgproc.GaussianBlur(processed, processed, new Size(5.0, 15.0), 0.00);

            // Find Contours  (RETR_EXTERNAL or RETR_CCOMP)
            List<MatOfPoint> contours = new ArrayList<>();
            Imgproc.findContours(processed, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);

            // Draw Contours
            Imgproc.drawContours(output, contours, -1, new Scalar(255, 0, 0));

            // Loop Through Contours
            for (MatOfPoint contour : contours) {
                Point[] contourArray = contour.toArray();

                // Bound Rectangle if Contour is Large Enough
                if (contourArray.length >= 15) {
                    MatOfPoint2f areaPoints = new MatOfPoint2f(contourArray);
                    Rect rect = Imgproc.boundingRect(areaPoints);

                    // if rectangle is larger than previous cycle or if rectangle is not larger than previous 6 cycles > then replace
                    if (rect.area() > maxArea
                            && rect.x > borderLeftX && rect.x + rect.width < CAMERA_WIDTH - borderRightX
                            && rect.y > borderTopY && rect.y + rect.height < CAMERA_HEIGHT - borderBottomY
                            || loopcounter - ploopcounter > 6)
                    {
                        maxArea = rect.area();
                        maxRect = rect;
                        ploopcounter++;
                        loopcounter = ploopcounter;
                        first = true;
                    }
                }
            }
            if (contours.isEmpty()) {
                maxRect = new Rect();
            }

            // Draw Rectangles If Area Is At Least 500
            if (first && maxRect.area() > 500) {
                Imgproc.rectangle(output, maxRect, new Scalar(0, 255, 0), 2);
            }

            // Draw Borders
            Imgproc.rectangle(output, new Rect(borderLeftX, borderTopY, CAMERA_WIDTH - borderRightX - borderLeftX, CAMERA_HEIGHT - borderBottomY - borderTopY), CAMERA_OUTLINE, 2);

            // Display Data
            Imgproc.putText(output, "Area: " + getRectArea() + " Midpoint: " + getRectMidpointXY().x + " , " + getRectMidpointXY().y, new Point(5, CAMERA_HEIGHT - 5), 0, 0.6, new Scalar(255, 255, 255), 2);

            loopcounter++;
        } catch (Exception e) {
            debug = e;
            error = true;
        }

        return output;
    }

    public int getRectHeight(){return maxRect.height;}
    public int getRectWidth(){ return maxRect.width; }
    public int getRectX(){ return maxRect.x; }
    public int getRectY(){ return maxRect.y; }
    public double getRectMidpointX(){ return getRectX() + (getRectWidth()/2.0); }
    public double getRectMidpointY(){ return getRectY() + (getRectHeight()/2.0); }
    public Point getRectMidpointXY(){ return new Point(getRectMidpointX(), getRectMidpointY());}
    public double getAspectRatio(){ return getRectArea()/(CAMERA_HEIGHT*CAMERA_WIDTH); }
    public double getRectArea(){ return maxRect.area(); }

}
