package org.firstinspires.ftc.teamcode.BNI_Team.Connor.BNI_Testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.BNI_Team.Connor.Drivetrains.MecanumDrive_Connor;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.openftc.apriltag.AprilTagDetection;

import java.util.ArrayList;
import java.util.List;

public abstract class Test_AUTOMAINCenterStage_Connor extends LinearOpMode {
int ID_TAG_LIST [] = {0,1,2,3,4,5,6,7};
public AprilTagDetection tagOfInterest = null;

    public static final boolean USE_WEBCAM = true;


    public AprilTagProcessor aprilTag;


    public VisionPortal visionPortal;



    public void initAprilTag(){

        aprilTag = new AprilTagProcessor.Builder()
                .build();



        VisionPortal.Builder builder = new VisionPortal.Builder();

        if (USE_WEBCAM) {
            builder.setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"));
        }   else{
            builder.setCamera(BuiltinCameraDirection.BACK);
        }

        builder.addProcessor(aprilTag);

        visionPortal = builder.build();

    }

    public void telemetryAprilTag() {

        List<org.firstinspires.ftc.vision.apriltag.AprilTagDetection> currentDetections = aprilTag.getDetections();
        telemetry.addData("# AprilTags Detected", currentDetections.size());


        for (org.firstinspires.ftc.vision.apriltag.AprilTagDetection detection : currentDetections) {
            if (detection.metadata != null) {
                telemetry.addLine(String.format("\n==== (ID %d) %s", detection.id, detection.metadata.name));
                telemetry.addLine(String.format("XYZ %6.1f %6.1f %6.1f  (inch)", detection.ftcPose.x, detection.ftcPose.y, detection.ftcPose.z));
                telemetry.addLine(String.format("PRY %6.1f %6.1f %6.1f  (deg)", detection.ftcPose.pitch, detection.ftcPose.roll, detection.ftcPose.yaw));
                telemetry.addLine(String.format("RBE %6.1f %6.1f %6.1f  (inch, deg, deg)", detection.ftcPose.range, detection.ftcPose.bearing, detection.ftcPose.elevation));
            } else {
                telemetry.addLine(String.format("\n==== (ID %d) Unknown", detection.id));
                telemetry.addLine(String.format("Center %6.0f %6.0f   (pixels)", detection.center.x, detection.center.y));
            }
        }


        telemetry.addLine("\nkey:\nXYZ = X (Right), Y (Forward), Z (Up) dist.");
        telemetry.addLine("PRY = Pitch, Roll & Yaw (XYZ Rotation)");
        telemetry.addLine("RBE = Range, Bearing & Elevation");
        telemetry.addLine("LONG LIVE TACO");

    }

    public void findTag() {
        initAprilTag();
        List<org.firstinspires.ftc.vision.apriltag.AprilTagDetection> currentDetections = aprilTag.getDetections();


        telemetry.addData("# AprilTags Detected", currentDetections.size());


    }

    public void detectTags() {
        findTag();

        sleep(500);

        findTag();

        sleep(500);

        findTag();

        sleep(500);

        visionPortal.close();

        sleep(500);
    }
}
