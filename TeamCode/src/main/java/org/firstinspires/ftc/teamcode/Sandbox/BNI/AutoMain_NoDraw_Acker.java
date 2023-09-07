package org.firstinspires.ftc.teamcode.Sandbox.BNI;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Base.Sensors.TagSleeveDetection_NoDraw;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import java.util.ArrayList;

// April Tag Detection. No drawing of bounding boxes or cubes

public abstract class AutoMain_NoDraw_Acker extends LinearOpMode {

    // Camera Detection Variables
    OpenCvCamera camera;
    TagSleeveDetection_NoDraw tagPipeline;
    public ParkingPosition_Acker parkPosition = ParkingPosition_Acker.NONE;

    // WebCam Lens intrinsics based in pixels
    double fx = 578.272;
    double fy = 578.272;
    double cx = 402.145;
    double cy = 221.506;

    // April Tags are from 36h11 family
    static final double FEET_PER_METER = 3.28084;
    int ID_TAG_LIST [] = {0,1,2};
    double tagsize = 0.0445;  // Tag is 1.75 inches
    public AprilTagDetection tagOfInterest = null;

    // Method to Initialize Webcam and Start Pipeline
    public void initializePipeline () {

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        tagPipeline = new TagSleeveDetection_NoDraw(tagsize, fx, fy, cx, cy);

        camera.setPipeline(tagPipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {

            @Override
            public void onOpened() {
                camera.startStreaming(800,448, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) { }

        });

    }

    //Method to Find the April Tags based on the tag family
    public void findTag () {

        ArrayList<AprilTagDetection> currentDetections = tagPipeline.getLatestDetections();

        if(currentDetections.size() != 0) {
            boolean tagFound = false;
            for(AprilTagDetection tag : currentDetections) {
                for (int i = 0; i < ID_TAG_LIST.length; i++) {
                    if(tag.id == ID_TAG_LIST[i]) {
                        tagOfInterest = tag;
                        tagFound = true;
                        break;
                    }
                }
            }

            if(tagFound) {
                if (tagOfInterest.id == ID_TAG_LIST[0]) {
                    parkPosition = ParkingPosition_Acker.ONE;

                }
                else if (tagOfInterest.id == ID_TAG_LIST[1]) {
                    parkPosition = ParkingPosition_Acker.TWO;
                }
                else if (tagOfInterest.id == ID_TAG_LIST[2]) {
                    parkPosition = ParkingPosition_Acker.THREE;
                }
                else {
                    parkPosition = ParkingPosition_Acker.NONE;
                }

                telemetry.addLine("Tag Found!");
                parkingTelemetry();
                telemetry.update();
            }
            else {
                telemetry.addLine("No Tag Found...");

                if(tagOfInterest == null) {
                    telemetry.addLine("Tag has not been found at all.");
                }
                else {
                    telemetry.addLine("\nTag was found before.Last seen at:");
                    parkingTelemetry();
                }
            }

        }
        else {
            telemetry.addLine("No Tag Found...");

            if(tagOfInterest == null) {
                telemetry.addLine("Tag has not been found at all.");
            }
            else {
                telemetry.addLine("\n Tag was found before.Last seen at:");
                parkingTelemetry();
            }

        }
        telemetry.update();

    }

    // Method for Parking Telemetry
    public void parkingTelemetry() {
        telemetry.addData("Tag ID: ", tagOfInterest.id);
        telemetry.addData("Parking Location: ", parkPosition);
    }


}
