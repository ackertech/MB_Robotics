package org.firstinspires.ftc.teamcode.Base.Controls.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Base.Robot.MechBot;

//@Disabled
@Autonomous(name = "MechBot Gyro Drive Straight", group = "iLab")

public class AutoMechBotGyroDriveStraight extends LinearOpMode {

    MechBot Bot = new MechBot();

    @Override
    public void runOpMode() throws InterruptedException {

        Bot.initRobot(hardwareMap);
        Bot.setLinearOp(this);

        telemetry.addLine("Awaiting Start");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()){

            // Drive forward and then correct the angle at the end

           // Drive by rotations and keep straight... kick me
           Bot.driveGyroStraight(4000,.7, "FORWARD");
           Bot.rotateByGyro(.5, -90);
           Bot.driveGyroStraight(4000, .7, "FORWARD");
           Bot.rotateByGyro(.5, -90);
           Bot.driveGyroStraight(4000, .7, "FORWARD");
           Bot.rotateByGyro(.5, -90);
           Bot.driveGyroStraight(4000, .7, "FORWARD");
           Bot.rotateByGyro(.5, -90);


            requestOpModeStop();

        }

        idle();

    }

    public void updateTelemetry(String comment) {
        telemetry.addLine(comment);
        telemetry.addData("Front Lef Motor: ", Bot.frontLeftMotor.getPower());
        telemetry.addData("Encoder Counts: ", Bot.frontLeftMotor.getCurrentPosition());
        telemetry.update();
    }

}
