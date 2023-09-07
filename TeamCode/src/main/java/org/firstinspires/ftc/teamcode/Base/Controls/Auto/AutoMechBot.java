package org.firstinspires.ftc.teamcode.Base.Controls.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Base.Robot.LabBot_4Motor;
import org.firstinspires.ftc.teamcode.Base.Robot.MechBot;

//@Disabled
@Autonomous(name = "MechBot Auto", group = "iLab")

public class AutoMechBot extends LinearOpMode {

    MechBot Bot = new MechBot();

    @Override
    public void runOpMode() throws InterruptedException {

        Bot.initRobot(hardwareMap);
        Bot.setLinearOp(this);

        telemetry.addLine("Awaiting Start");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()){

           // Bot.driveForwardPID(4,0.8,0,0);

//            updateTelemetry("Drive Forwards");
//            Bot.driveDirection(1, 2,"FWD");
//            sleep(1000);
//
//            updateTelemetry("Drive Backwards");
//            Bot.driveDirection(1, 2,"RWD");
//            sleep(1000);
//
//            updateTelemetry("Strafe Right");
//            Bot.driveDirection(1, 2,"STR");
//            sleep(1000);
//
//            updateTelemetry("Strafe Left");
//            Bot.driveDirection(1, 2,"STL");
//            sleep(1000);
//
//            updateTelemetry("Rotate Left");
//            Bot.driveDirection(1, 1,"RL");
//            sleep(1000);
//
//            updateTelemetry("Rotate Right");
//            Bot.driveDirection(1, 1,"RR");
//            sleep(1000);
//
//            updateTelemetry("Drive Backwards");
//            Bot.driveDirection(1, 2,"RWD");
//            sleep(5000);


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
