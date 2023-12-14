package org.firstinspires.ftc.teamcode.Sandbox.AutoLab;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Vision:AutoTester",group = "iLab")

public class AutoBlueAudience extends AutoBlueAlliance {

    @Override
    public void runOpMode() throws InterruptedException {

        //Bot.initRobot(hardwareMap);
        //Bot.setLinearOp(this);

        telemetry.addLine("Awaiting Start");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()){

            //telemetry.addLine("Driving Forward from Start");
            //telemetry.update();
            //Bot.driveForward(1);
            //sleep(2000);
            //Bot.stopMotors();

            requestOpModeStop();

        }

        idle();

    }




}
