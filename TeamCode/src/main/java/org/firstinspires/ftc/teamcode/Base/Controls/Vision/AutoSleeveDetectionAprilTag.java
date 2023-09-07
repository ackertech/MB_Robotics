package org.firstinspires.ftc.teamcode.Base.Controls.Vision;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Base.Sensors.TagSleeveDetection;
import org.firstinspires.ftc.teamcode.BNI.ParkingPosition_Acker;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.util.ArrayList;

@Disabled
@TeleOp(name="Vision - Sleeve Detection")
public class AutoSleeveDetectionAprilTag extends LinearOpMode {

    OpenCvCamera camera;
    TagSleeveDetection tagPipeline;

    // Lens intrinsics based in pixels
    double fx = 578.272;
    double fy = 578.272;
    double cx = 402.145;
    double cy = 221.506;

    // UNITS ARE METERS
    static final double FEET_PER_METER = 3.28084;
    double tagsize = 0.166;

    // Tag ID 18 from the 36h11 family
    int ID_TAG_OF_INTEREST = 18;
    int ID_TAG_LIST [] = {0,1,2};
    int tagPosition;

    public AprilTagDetection tagOfInterest = null;
    public ParkingPosition_Acker parkPosition = ParkingPosition_Acker.NONE;

    @Override
    public void runOpMode() {

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        tagPipeline = new TagSleeveDetection(tagsize, fx, fy, cx, cy);

        camera.setPipeline(tagPipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {

            @Override
            public void onOpened() {
                camera.startStreaming(800,448, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {

            }
        });

        telemetry.setMsTransmissionInterval(50);

        // Init Phase Before Start Button
        while (!isStarted() && !isStopRequested()) {

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

                    telemetry.addLine("Tag of interest is in sight!");
                    tagTelemetryBrief();
                }
                else {
                    telemetry.addLine("Don't see tag of interest :(");

                    if(tagOfInterest == null) {
                        telemetry.addLine("(The tag has never been seen)");
                    }
                    else {
                        telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                        tagTelemetryBrief();
                    }
                }

            }
            else {
                telemetry.addLine("Don't see tag of interest :(");

                if(tagOfInterest == null) {
                    telemetry.addLine("(The tag has never been seen)");
                }
                else {
                    telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                    tagTelemetryBrief();
                }

            }

            telemetry.update();
            sleep(20);
        }

        // Start Button Has Been pressed
            if (tagOfInterest != null) {
                tagTelemetryBrief();

            } else {
                telemetry.addLine("No tag snapshot available, it was never sighted during the init loop :(");
                telemetry.update();
            }


            if (tagOfInterest == null) {
                telemetry.addLine("No tag snapshot available, it was never sighted during the init loop :(");
                telemetry.update();
            } else {
                tagTelemetryBrief();
            }




        /* You wouldn't have this in your autonomous, this is just to prevent the sample from ending */
        while (opModeIsActive()) {sleep(20);}

    }

    void tagTelemetryBrief() {
        telemetry.addData("Detected tag ID: ", tagOfInterest.id);
        telemetry.addData("Parking Location: ", parkPosition);
        telemetry.update();

    }

    void tagTelemetry(AprilTagDetection detection)
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
