package org.firstinspires.ftc.teamcode.Base.Sensors;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.apriltag.AprilTagDetectorJNI;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;

public class TagSleeveDetection_NoDraw extends OpenCvPipeline {

    public long nativeApriltagPtr;
    public Mat grey = new Mat();
    public ArrayList<AprilTagDetection> detections = new ArrayList<>();
    public ArrayList<AprilTagDetection> detectionsUpdate = new ArrayList<>();

    public final Object detectionsUpdateSync = new Object();

    // Webcam Lens Intrinsics
    double fx, fy, cx, cy;

    // April Tag Dimensions
    double tagsize, tagsizeX, tagsizeY;

    // Constructor
    public TagSleeveDetection_NoDraw(double tagsize, double fx, double fy, double cx, double cy) {
        this.tagsize = tagsize;
        this.tagsizeX = tagsize;
        this.tagsizeY = tagsize;
        this.fx = fx;
        this.fy = fy;
        this.cx = cx;
        this.cy = cy;


        // Allocate a native context object. See the corresponding deletion in the finalizer
        nativeApriltagPtr = AprilTagDetectorJNI.createApriltagDetector(AprilTagDetectorJNI.TagFamily.TAG_36h11.string, 3, 3);
    }

    @Override
    public void finalize() {

        // Might be null if createApriltagDetector() threw an exception
        if (nativeApriltagPtr != 0) {
            // Delete the native context we created in the constructor
            AprilTagDetectorJNI.releaseApriltagDetector(nativeApriltagPtr);
            nativeApriltagPtr = 0;
        }
        else {
            System.out.println("AprilTagDetectionPipeline.finalize(): nativeApriltagPtr was NULL");
        }
    }

    @Override
    public Mat processFrame (Mat input) {

        // Convert to greyscale
        Imgproc.cvtColor(input, grey, Imgproc.COLOR_RGBA2GRAY);

        // Run AprilTag
        detections = AprilTagDetectorJNI.runAprilTagDetectorSimple(nativeApriltagPtr, grey, tagsize, fx, fy, cx, cy);

        synchronized (detectionsUpdateSync) {

            detectionsUpdate = detections;
        }

        return input;
    }

    public ArrayList<AprilTagDetection> getLatestDetections() {
        return detections;
    }


}
