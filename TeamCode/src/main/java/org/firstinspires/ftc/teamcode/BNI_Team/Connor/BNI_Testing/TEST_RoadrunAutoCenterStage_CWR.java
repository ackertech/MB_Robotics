package org.firstinspires.ftc.teamcode.BNI_Team.Connor.BNI_Testing;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.BNI_Team.Connor.CompetitionRobot_7th.Robots.RoadRunnerCompBot;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.List;


@Autonomous (name = "ROADRUNAuto_CWR")
public  class TEST_RoadrunAutoCenterStage_CWR extends Test_AUTOMAINCenterStage_Connor {






        public static double ANGLE1 = 90;

        @Override
        public void runOpMode() {

            initAprilTag();
            telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
            RoadRunnerCompBot drive = new RoadRunnerCompBot(hardwareMap);

            Trajectory traj1 = drive.trajectoryBuilder(new Pose2d())
                    .splineToSplineHeading(new Pose2d(40, 40, Math.toRadians(90)), Math.toRadians(0))
                    .build();

            Trajectory traj2 = drive.trajectoryBuilder(new Pose2d())
                    .strafeTo(new Vector2d(40, 40))
                    .build();


            waitForStart();

            while (opModeIsActive()) {
                List<AprilTagDetection> currentDetections = aprilTag.getDetections();

                telemetryAprilTag();

                detectTags();
                for (org.firstinspires.ftc.vision.apriltag.AprilTagDetection detection : currentDetections) {
                    if (detection.metadata != null) {
                        if (detection.id == 1) {
                            drive.followTrajectory(traj1);
                        } else if (detection.id == 2) {
                            drive.followTrajectory(traj2);
                        }
                    }

                }


                requestOpModeStop();
            }

            visionPortal.close();

            idle();

        }


}

