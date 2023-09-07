package org.firstinspires.ftc.teamcode.Sandbox.SLAM.drive.opmode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Sandbox.SLAM.drive.SampleMecanumDrive;


@Autonomous(name="SLAM:Spline Drive 2", group = "SLAM")
public class TrajectoryDrive2 extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        waitForStart();

        if (isStopRequested()) return;

        Trajectory traj = drive.trajectoryBuilder(new Pose2d(45,-72, 0))
                .splineToConstantHeading(new Vector2d(  72, -45), 0)
                .build();

        drive.followTrajectory(traj);

        sleep(1000);


    }
}
