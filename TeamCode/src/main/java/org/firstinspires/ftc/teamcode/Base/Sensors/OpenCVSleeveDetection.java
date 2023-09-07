package org.firstinspires.ftc.teamcode.Base.Sensors;

import org.firstinspires.ftc.teamcode.Sandbox.BNI.ParkingPosition_Acker;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class OpenCVSleeveDetection extends OpenCvPipeline {

    // TOPLEFT anchor point for the bounding box
    private static Point SLEEVE_TOPLEFT_ANCHOR_POINT = new Point(200, 150);

    // Width and height for the bounding box
    public static int REGION_WIDTH = 400;
    public static int REGION_HEIGHT = 280;

    // Declaration for Lower and upper boundaries for colors in HSV (Hue is 50% in OpenCV)
    public static final Scalar

            lower_yellow_bounds  = new Scalar(200, 200, 0, 255),
            upper_yellow_bounds  = new Scalar(255, 255, 130, 255),
            lower_green_bounds = new Scalar(0, 200, 200, 255),
            upper_green_bounds = new Scalar(150, 255, 255, 255),
            lower_purple_bounds = new Scalar(170, 0, 170, 255),
            upper_purple_bounds = new Scalar(255, 60, 255, 255);

//            //HSV SCALAR
//            lower_yellow_bounds  = new Scalar(22, 0, 0),
//            upper_yellow_bounds  = new Scalar(31, 255, 255),
//            lower_green_bounds = new Scalar(40, 0, 0),
//            upper_green_bounds = new Scalar(80, 255, 255),
//            lower_purple_bounds = new Scalar(130, 0, 0),
//            upper_purple_bounds = new Scalar(170, 255, 255);

    // Declaration for RGB Color Constants
    public final Scalar
            YELLOW  = new Scalar(255, 255, 0),
            GREEN    = new Scalar(0, 255, 0),
            PURPLE = new Scalar(255, 0, 255);

    // Declation of Color Percents and Mats
    public double yellowPercent, greenPercent, purplePercent;
    public Mat yellowImage = new Mat();
    public Mat greenImage = new Mat();
    public Mat purpleImage = new Mat();
    public Mat blurredImage = new Mat();
    public Mat kernel  = new Mat();
    //public Mat matHSV = new Mat();


    // Anchor point definitions
    Point sleeve_pointA = new Point(
            SLEEVE_TOPLEFT_ANCHOR_POINT.x,
            SLEEVE_TOPLEFT_ANCHOR_POINT.y);
    Point sleeve_pointB = new Point(
            SLEEVE_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
            SLEEVE_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);

    // Running variable storing the parking position
    public ParkingPosition_Acker parkPosition = ParkingPosition_Acker.NONE;

    @Override
    public Mat processFrame(Mat input) {
        //Color Conversion
       // Imgproc.cvtColor(input, matHSV, Imgproc.COLOR_RGB2HSV);

        // Noise reduction
        Imgproc.blur(input, blurredImage, new Size(5, 5));
        blurredImage = blurredImage.submat(new Rect(sleeve_pointA, sleeve_pointB));

//        // Apply Morphology
//        kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3, 3));
//        Imgproc.morphologyEx(blurredImage, blurredImage, Imgproc.MORPH_CLOSE, kernel);

        // Gets channels from given source mat
        Core.inRange(blurredImage, lower_yellow_bounds, upper_yellow_bounds, yellowImage);
        Core.inRange(blurredImage, lower_green_bounds, upper_green_bounds, greenImage);
        Core.inRange(blurredImage, lower_purple_bounds, upper_purple_bounds, purpleImage);

        // Gets color specific values
        yellowPercent = Core.countNonZero(yellowImage)/1000;
        greenPercent = Core.countNonZero(greenImage)/1000;
        purplePercent = Core.countNonZero(purpleImage)/1000;

        // Calculates the highest amount of pixels being covered on each side
        double maxPercent = Math.max(yellowPercent, Math.max(greenPercent, purplePercent));

        // Checks all percentages, will highlight bounding box in camera preview
        // based on what color is being detected
        if (maxPercent == yellowPercent) {
            parkPosition = ParkingPosition_Acker.TWO;
            Imgproc.rectangle(
                    input,
                    sleeve_pointA,
                    sleeve_pointB,
                    YELLOW,
                    2
            );
        } else if (maxPercent == greenPercent) {
            parkPosition = ParkingPosition_Acker.ONE;
            Imgproc.rectangle(
                    input,
                    sleeve_pointA,
                    sleeve_pointB,
                    GREEN,
                    2
            );
        } else if (maxPercent == purplePercent) {
            parkPosition = ParkingPosition_Acker.THREE;
            Imgproc.rectangle(
                    input,
                    sleeve_pointA,
                    sleeve_pointB,
                    PURPLE,
                    2
            );
        }

        // Memory cleanup
        blurredImage.release();
        yellowImage.release();
        greenImage.release();
        purpleImage.release();
        kernel.release();
        
        return input;
    }

    // Returns an enum being the current position where the robot will park
    public ParkingPosition_Acker getPosition() {
        return parkPosition;
    }
}