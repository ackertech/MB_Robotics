package org.firstinspires.ftc.teamcode.BNI_Team.Connor.CompetitionRobot_7th.Autonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.BNI_Team.Connor.CompetitionRobot_7th.Robots.CompetitionBot;
@Disabled
@Autonomous (name = "BNI:MechBotAuto", group="BNI")

public class AutoMec_Connor extends LinearOpMode {

    CompetitionBot FixitsBot = new CompetitionBot();

    @Override
    public void runOpMode() throws InterruptedException {

        FixitsBot.initRobot(hardwareMap);
        FixitsBot.setLinearOp(this);



        telemetry.addLine("Robot Awaiting Start Procedure");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            telemetryUpdate("Rotate Left");
            FixitsBot.rotateLeft(.7,3.4);
            sleep(1000);



            telemetryUpdate("Drive Forward");
            FixitsBot.driveForward(1,3.9);
            sleep(1000);

            telemetryUpdate("Grabbing Cone");
            FixitsBot.claw.setPosition(.25);
            sleep(1000);

            telemetryUpdate("Strafing Right");
            FixitsBot.strafeRight(1,7.8);
            sleep(1000);



            telemetryUpdate("Turning");
            FixitsBot.rotateRight(.7,2.5);
            sleep(1000);

            telemetryUpdate("Linear Slide Up");
            FixitsBot.linearSlideUp(.7,1);


            requestOpModeStop();
        }

        idle();
    }



    public void telemetryUpdate(String comment) {
        telemetry.addLine("LONG LIVE TACO");
        telemetry.addLine(comment);
        telemetry.addData("Front Lef Motor:", FixitsBot.frontLeftMotor.getPower());
        telemetry.addData("Front Rig Motor:", FixitsBot.frontRightMotor.getPower());
        telemetry.addData("Rear Lef Motor:", FixitsBot.rearLeftMotor.getPower());
        telemetry.addData("Rear Rig Motor:", FixitsBot.rearRightMotor.getPower());
        telemetry.addData("Encoder Count: ", FixitsBot.frontLeftMotor.getCurrentPosition());
        telemetry.update();
    }


}
