package org.firstinspires.ftc.teamcode.Base.Controls.Vision;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Base.Sensors.TagSleeveDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import java.util.ArrayList;

@Disabled
@TeleOp(name="Vision: AprilTag Example" )
public class AprilTagDetectionExample extends LinearOpMode {

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
    AprilTagDetection tagOfInterest = null;

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
                    if(tag.id == ID_TAG_OF_INTEREST) {
                        tagOfInterest = tag;
                        tagFound = true;
                        break;
                    }
                }

                if(tagFound) {
                    telemetry.addLine("Tag of interest is in sight!\n\nLocation data:");
                    tagTelemetry(tagOfInterest);
                }
                else {
                    telemetry.addLine("Don't see tag of interest :(");

                    if(tagOfInterest == null) {
                        telemetry.addLine("(The tag has never been seen)");
                    }
                    else {
                        telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                        tagTelemetry(tagOfInterest);
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
                    tagTelemetry(tagOfInterest);
                }

            }

            telemetry.update();
            sleep(20);
        }

       // Start Button Has Been Pressed

        if(tagOfInterest != null) {
            telemetry.addLine("Tag snapshot:\n");
            tagTelemetry(tagOfInterest);
            telemetry.update();
        }
        else {
            telemetry.addLine("No tag snapshot available, it was never sighted during the init loop :(");
            telemetry.update();
        }


        if(tagOfInterest == null) {
            telemetry.addLine("Immediatley Park. No tag detected");
            telemetry.update();
        }
        else {
            /*
             * Insert your autonomous code here, probably using the tag pose to decide your configuration.
             */

            // e.g.
            if(tagOfInterest.pose.x <= 20) {
                // do something
            }
            else if(tagOfInterest.pose.x >= 20 && tagOfInterest.pose.x <= 50) {
                // do something else
            }
            else if(tagOfInterest.pose.x >= 50) {
                // do something else
            }
        }


        /* You wouldn't have this in your autonomous, this is just to prevent the sample from ending */
        while (opModeIsActive()) {sleep(20);}
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
