package org.firstinspires.ftc.teamcode.BNI_Team.Connor.CompetitionRobot.Autonomous;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.BNI_Team.Connor.CompetitionRobot.Robots.RoadRunnerCompBot;


@Autonomous (name = "ROADRUNAuto_Connor")
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
                        .strafeLeft(STRAFEDISTANCE)
                                .build();

        Trajectory traj3 = drive.trajectoryBuilder(new Pose2d())
                        .splineToSplineHeading(new Pose2d(40,40, Math.toRadians(90)), Math.toRadians(0))
                                .build();











                waitForStart();





                while (opModeIsActive()) {
                    drive.followTrajectory(traj3);
//                drive.followTrajectory(traj1);
//                drive.turn(Math.toRadians(ANGLE2));
//                 drive.followTrajectory(traj2);
//                 drive.turn(Math.toRadians(ANGLE));
//                 drive.clawOpen();
                 requestOpModeStop();

                }
        idle();

    }

}
