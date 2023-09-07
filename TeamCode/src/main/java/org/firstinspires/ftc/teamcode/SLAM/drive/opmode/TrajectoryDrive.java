package org.firstinspires.ftc.teamcode.SLAM.drive.opmode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.SLAM.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.SLAM.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.SLAM.trajectorysequence.TrajectorySequence;

@Autonomous(name = "SLAM:Trajectory Drive", group = "SLAM")
public class TrajectoryDrive extends LinearOpMode {

    //Initialize any other Pose2d's as desired

    Pose2d initPose = new Pose2d(66,-36,Math.toRadians(180));
    Pose2d midWayPose = new Pose2d(12, -36, Math.toRadians(180)); //Choose the pose to move forward towards signal cone
    Vector2d midWayVector = new Vector2d(12,-36);
    Pose2d pickConePose = new Pose2d(12, -55, Math.toRadians(270)); //Choose the pose to move to the stack of cones
    Pose2d dropConePose0 = new Pose2d(12, -12, Math.toRadians(315)); //Choose the pose to move to the stack of cones
    Pose2d dropConePose1 = new Pose2d(11, -12, Math.toRadians(315)); //Choose the pose to move to the stack of cones
    Pose2d dropConePose2 = new Pose2d(10, -15, Math.toRadians(315)); //Choose the pose to move to the stack of cones

    Pose2d parkPose;

    //Initialize any other TrajectorySequences as desired
    TrajectorySequence trajectoryAuto, trajectoryParking ;

    @Override
    public void runOpMode() throws InterruptedException {

        SampleMecanumDrive driveTrain = new SampleMecanumDrive(hardwareMap);

        trajectoryAuto = driveTrain.trajectorySequenceBuilder(initPose)
                .lineToLinearHeading(midWayPose)

                //Uncomment following line to slow down turn if needed.
                //.lineToLinearHeading(dropConePose0)
//                .addDisplacementMarker(() -> {
//                    dropCone(0); //Drop preloaded Cone
//                })
                .lineToLinearHeading(pickConePose)
//                .addDisplacementMarker(() -> {
//                    pickCone(1); //Pick top cone from stack
//                })
            //    .lineToLinearHeading(dropConePose1)
//                .addDisplacementMarker(() -> {
//                    dropCone(1); //Drop cone on junction
//                })
           //     .lineToLinearHeading(pickConePose)
//                .addDisplacementMarker(() -> {
//                    pickCone(2); //Pick second cone from stack
//                })
//                .lineToLinearHeading(dropConePose2)
//                .addDisplacementMarker(() -> {
//                    dropCone(2); //Drop cone on junction
//                })
                .build();


        waitForStart();

        if( isStopRequested() ) return;

        driveTrain.followTrajectorySequence(trajectoryAuto);

//        Pose2d poseEstimate = driveTrain.getPoseEstimate();
//        telemetry.addData("finalX", poseEstimate.getX());
//        telemetry.addData("finalY", poseEstimate.getY());
//        telemetry.addData("finalHeading", poseEstimate.getHeading());
//        telemetry.update();

        while (!isStopRequested() && opModeIsActive()){

        };
    }
    //Write a method which is able to drop the cone depending on your subsystems
    public void dropCone(int coneCount){
        /*TODO: Add code to drop cone on junction*/
        if (coneCount == 0) {
            telemetry.addData("Dropped Cone", "Pre-loaded");
        } else {
            telemetry.addData("Dropped Cone: Stack", coneCount);
        }
        telemetry.update();
    }

    //Write a method which is able to pick the cone from the stack depending on your subsystems
    public void pickCone(int coneCount) {
        /*TODO: Add code to pick Cone 1 from stack*/
        telemetry.addData("Picked Cone: Stack", coneCount);
        telemetry.update();
    }
}
