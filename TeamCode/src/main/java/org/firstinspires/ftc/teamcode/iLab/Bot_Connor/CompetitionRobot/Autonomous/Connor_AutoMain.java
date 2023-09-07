package org.firstinspires.ftc.teamcode.iLab.Bot_Connor.CompetitionRobot.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Base.Sensors.TagSleeveDetection;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.util.ArrayList;

public abstract class Connor_AutoMain extends LinearOpMode {

    OpenCvCamera camera;
    TagSleeveDetection tagPipeline;
    public enum ParkingPosition_Connor {LEFT, MIDDLE, RIGHT, NONE}
    public ParkingPosition_Connor parkPosition = ParkingPosition_Connor.NONE;


    double fx = 578.272;
    double fy = 578.272;
    double cx = 402.145;
    double cy = 221.506;

    static final double FEET_PER_METER = 3.28084;
    int ID_TAG_LIST [] = {0,1,2};
    double tagsize = 0.0445;  // Tag is 1.75 inches
    public AprilTagDetection tagOfInterest = null;

    public void initPipeline() {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        tagPipeline = new TagSleeveDetection(tagsize, fx , fy, cx, cy);

        camera.setPipeline(tagPipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                camera.startStreaming(800,448, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {}
        });


    }

    public void findTag () {

        ArrayList<AprilTagDetection> currentDetections = tagPipeline.getLatestDetections();

        if (currentDetections.size() != 0) {
            boolean tagFound = false;
            for (AprilTagDetection tag : currentDetections) {
                for (int i = 0; i < ID_TAG_LIST.length; i++) {
                    if (tag.id == ID_TAG_LIST[i]) {
                        tagOfInterest = tag;
                        tagFound = true;
                        break;
                    }
                }
            }


            if (tagFound) {
                if (tagOfInterest.id == ID_TAG_LIST[0]) {
                    parkPosition = ParkingPosition_Connor.LEFT;
                } else if (tagOfInterest.id == ID_TAG_LIST[1]) {
                    parkPosition = ParkingPosition_Connor.MIDDLE;
                } else if (tagOfInterest.id == ID_TAG_LIST[2]) {
                    parkPosition = ParkingPosition_Connor.RIGHT;
                } else {
                    parkPosition = ParkingPosition_Connor.NONE;
                }

                telemetry.addLine("Tag Found ... Somehow");
                parkingTelemetry();
                tagTelemetry(tagOfInterest);
                telemetry.update();
            }

            else {
                telemetry.addLine("No Tag Found ...... Try Harder Next Time");

                if (tagOfInterest == null) {
                    telemetry.addLine("Tag has not been found at all.");
                }
                else {
                    telemetry.addLine("\nTag was found before. Last seen at:");
                    tagTelemetry(tagOfInterest);
                }

            }


        }

        else {
            telemetry.addLine("No Tag Found ...... Try Harder Next Time");

            if (tagOfInterest == null) {
                telemetry.addLine("Tag has not been found at all.");
            } else {
                telemetry.addLine("\nTag was found before. Last seen at:");
                tagTelemetry(tagOfInterest);
            }

        }

        telemetry.update();


    }

    public void stopCamera() {
        camera.stopRecordingPipeline();
        camera.stopStreaming();
        camera.closeCameraDevice();
    }


    public void detectTags() {

        findTag();

        sleep(500);

        findTag();

        sleep(500);

        findTag();

        sleep(500);

        stopCamera();

        sleep(500);
    }

        public void parkingTelemetry() {
            telemetry.addData("Tag ID", tagOfInterest.id);
            telemetry.addData("Parking Location: ", parkPosition);

        }


            public void tagTelemetry (AprilTagDetection detection)
           {
                telemetry.addLine(String.format("\nDetected tag ID=%d", detection.id));
                telemetry.addLine(String.format("Translation X: %.2f feet", detection.pose.x*FEET_PER_METER));
                telemetry.addLine(String.format("Translation Y: %.2f feet", detection.pose.y*FEET_PER_METER));
                telemetry.addLine(String.format("Translation Z: %.2f feet", detection.pose.z*FEET_PER_METER));
                telemetry.addLine(String.format("Rotation Yaw: %.2f degrees", Math.toDegrees(detection.pose.yaw)));
                telemetry.addLine(String.format("Rotation Pitch: %.2f degrees", Math.toDegrees(detection.pose.pitch)));
                telemetry.addLine(String.format("Rotation Roll: %.2f degrees", Math.toDegrees(detection.pose.roll)));

            }



}
