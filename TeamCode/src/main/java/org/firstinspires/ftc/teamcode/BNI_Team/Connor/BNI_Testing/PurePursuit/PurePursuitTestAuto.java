package org.firstinspires.ftc.teamcode.BNI_Team.Connor.BNI_Testing.PurePursuit;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous (name = "PurePursuit - TEST AUTO")
public class PurePursuitTestAuto extends RobotMovement{


    @Override
    public void runOpMode(){
        Bot.initRobot(hardwareMap);
        Bot.setLinearOp(this);

        telemetry.addLine("Robot Awaiting Start Procedure");
        telemetry.update();





        waitForStart();


        while (opModeIsActive()) {

            telemetry.addLine("Robot Autonomous Control Initialized");
            goToPosition(2,1.3, 90);
            sleep(500);

            telemetry.addLine("Robot Autonomous Operations Completed")      ;
            telemetry.update();



            requestOpModeStop();

        }

        idle();








        }
    }

