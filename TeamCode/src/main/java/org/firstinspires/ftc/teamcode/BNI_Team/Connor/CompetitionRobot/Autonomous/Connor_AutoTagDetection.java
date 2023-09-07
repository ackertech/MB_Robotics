package org.firstinspires.ftc.teamcode.BNI_Team.Connor.CompetitionRobot.Autonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Connor_DetectSleeveDraw")
public class Connor_AutoTagDetection extends Connor_AutoMain {



    @Override
    public void runOpMode() throws InterruptedException {



        initPipeline();

        while (!isStarted() && !isStopRequested()) {
            findTag();
            sleep(20);
        }

        while (opModeIsActive()) {

            parkingTelemetry();
            sleep(6000);


            requestOpModeStop();
        }

        idle();
    }
}
