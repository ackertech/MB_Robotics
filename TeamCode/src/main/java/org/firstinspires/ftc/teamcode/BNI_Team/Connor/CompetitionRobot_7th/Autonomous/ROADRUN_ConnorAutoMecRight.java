package org.firstinspires.ftc.teamcode.BNI_Team.Connor.CompetitionRobot_7th.Autonomous;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.BNI_Team.Connor.CompetitionRobot_7th.Robots.RoadRunnerCompBot;

//@Disabled
@Autonomous (name = "BNI:ROADRUNAuto_Connor", group="BNI")
public class ROADRUN_ConnorAutoMecRight extends LinearOpMode {


    public static double ANGLE = -180;
    public static double ANGLE2 = 90;
    public static double STRAFEDISTANCE = 13;
    @Override
    public void runOpMode() throws InterruptedException{
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        RoadRunnerCompBot drive = new RoadRunnerCompBot(hardwareMap);

        Trajectory traj1 = drive.trajectoryBuilder(new Pose2d())
                .forward(60)
                .build();

        Trajectory traj2 = drive.trajectoryBuilder(new Pose2d())
                        .strafeLeft(40)
                                .build();

        Trajectory traj4 = drive.trajectoryBuilder(new Pose2d())
                .strafeRight(40)
                .build();

//        Trajectory traj3 = drive.trajectoryBuilder(new Pose2d())
//                        .splineToSplineHeading(new Pose2d(40,80, Math.toRadians(90)), Math.toRadians(0))
//                                .build();

        Trajectory traj5 = drive.trajectoryBuilder(new Pose2d())
                        .back(8)
                                .build();











                waitForStart();





                while (opModeIsActive()) {
//                    drive.followTrajectory(traj1);
//                   sleep(2000);
//                    drive.followTrajectory(traj5);
//                    sleep(2000);
//                    drive.followTrajectory(traj2);
//                    sleep(500);
                    drive.followTrajectory(traj4);
                    sleep(500);
                    drive.followTrajectory(traj2);
                    sleep(500);
//                drive.followTrajectory(traj1);
//                drive.turn(Math.toRadians(ANGLE2));
//                 drive.followTrajectory(traj2);
//                 drive.turn(Math.toRadians(ANGLE));
//                 drive.clawOpen();
                 requestOpModeStop();

                }
        idle();

    }
//driving back is +22 inches
}
