package org.firstinspires.ftc.teamcode.BNI_Team.Olivia.Bots;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.BNI_Team.Olivia.Bots.MechBot_Olivia;

//@Disabled
@Autonomous(name = "Olivia Auto", group = "iLab")

public class AutoMechBot_Olivia extends LinearOpMode {

    MechBot_Olivia Bot = new MechBot_Olivia();

    @Override
    public void runOpMode() throws InterruptedException {

        Bot.initRobot(hardwareMap);
        Bot.setLinearOp(this);

        telemetry.addLine("Awaiting Start");
        telemetry.update();

        waitForStart();

        while (opModeIsActive())
        {

            //Below #s are all guesstimates, need to be properly calculated
            telemetry.addLine("Driving Forward");
            telemetry.update();
            Bot.driveForward(1, 233.5);
            sleep(1000);
           Bot.rotateLeft(1, 90);
           //How to calculate the degree of the turn in an auto mecanum?
            sleep(1000);
            Bot.driveForward(1, 1623);
            sleep(1000);
            Bot.rotateRight(1, 90);
            sleep(1000);
            Bot.driveForward(1, 350);
            sleep(1000);
            Bot.rotateLeft(1, 350);
            sleep(1000);
            Bot.driveForward(1, 350);
            sleep(1000);
            Bot.stopMotors();



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
