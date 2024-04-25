package org.firstinspires.ftc.teamcode.Base.Controls.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Base.Drivetrains.MecanumDrive;
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

            updateTelemetry("Driving Forward");
            Bot.speedAccelerationwithOdometry(15, 1, MecanumDrive.driveDirections.DRIVE_FORWARD);
            Bot.stopMotors();
//            telemetry.addData("Encoder Counts FR: ", Bot.frontRightMotor.getCurrentPosition());
//            telemetry.addData("Encoder Counts FL: ", Bot.frontLeftMotor.getCurrentPosition());
//            telemetry.addData("Encoder Counts BL: ", Bot.rearLeftMotor.getCurrentPosition());
//            telemetry.addData("Encoder Counts BR: ", Bot.rearRightMotor.getCurrentPosition());
//            telemetry.update();
//
            requestOpModeStop();
        }
        idle();
    }

    public void updateTelemetry(String comment) {
        telemetry.addLine(comment);
        telemetry.addData("Front Lef Motor: ", Bot.frontLeftMotor.getPower());
        telemetry.addData("Encoder Counts FR: ", Bot.frontRightMotor.getCurrentPosition());
        telemetry.addData("Encoder Counts FL: ", Bot.frontLeftMotor.getCurrentPosition());
        telemetry.addData("Encoder Counts BL: ", Bot.rearLeftMotor.getCurrentPosition());
        telemetry.addData("Encoder Counts BR: ", Bot.rearRightMotor.getCurrentPosition());
        telemetry.update();

    }
}