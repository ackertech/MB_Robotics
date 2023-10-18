package org.firstinspires.ftc.teamcode.BNI_Team.Connor.BNI_Testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.BNI_Team.Connor.Drivetrains.MecanumDrive_Connor;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.ArrayList;
import java.util.List;

public abstract class Test_AUTOMAINCenterStage_Connor extends LinearOpMode {

    public enum TagPosition {BLUE_LEFT, BLUE_MIDDLE, BLUE_RIGHT, RED_LEFT, RED_MIDDLE, RED_RIGHT, NONE}
    public TagPosition tagPosition = TagPosition.NONE;
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
        if (tagPosition == TagPosition.BLUE_LEFT) {
            telemetry.addLine("Tag Position = BLUE_LEFT");
        }
        else if (tagPosition == TagPosition.BLUE_MIDDLE) {
            telemetry.addLine("Tag Position = BLUE_MIDDLE");
        }
        else if (tagPosition == TagPosition.BLUE_RIGHT) {
            telemetry.addLine("Tag Position = BLUE_RIGHT");
        }
        else if (tagPosition == TagPosition.RED_LEFT) {
            telemetry.addLine("Tag Position = RED_LEFT");
        }
        else if (tagPosition == TagPosition.RED_MIDDLE){
            telemetry.addLine("Tag Position = RED_MIDDLE ");
        }
        else if (tagPosition == TagPosition.RED_RIGHT) {
            telemetry.addLine("Tag Position = RED_RIGHT");
        }
        else if (tagPosition == TagPosition.NONE) {
            telemetry.addLine("Tag Position = NONE");
        }


    }

    public void findTag() {
        initAprilTag();
        List<org.firstinspires.ftc.vision.apriltag.AprilTagDetection> currentDetections = aprilTag.getDetections();


        telemetry.addData("# AprilTags Detected", currentDetections.size());


        if (tagOfInterest.id ==  1 ){
            tagPosition = TagPosition.BLUE_LEFT;
        }
        else if (tagOfInterest.id == 2) {
            tagPosition = TagPosition.BLUE_MIDDLE;
        }
        else if (tagOfInterest.id == 3) {
            tagPosition = TagPosition.BLUE_RIGHT;
        }
        else if (tagOfInterest.id == 4) {
            tagPosition = TagPosition.RED_LEFT;
        }
        else if (tagOfInterest.id == 5) {
            tagPosition = TagPosition.RED_MIDDLE;
        }
        else if (tagOfInterest.id == 6) {
            tagPosition = TagPosition.RED_RIGHT;
        }
        else {
            tagPosition = TagPosition.NONE;
        }

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
