package org.firstinspires.ftc.teamcode.Sandbox.SLAM.drive.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Sandbox.SLAM.drive.SampleMecanumDrive;


//@Config
@Autonomous(name = "SLAM:Turn Drive", group = "SLAM")
public class TurnTest extends LinearOpMode {

    public static double ANGLE = 180; // deg

    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        waitForStart();

        if (isStopRequested()) return;

        drive.turn(Math.toRadians(ANGLE));
    }
}
