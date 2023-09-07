package org.firstinspires.ftc.teamcode.BNI_Team.Connor.CompetitionRobot.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.BNI_Team.Connor.CompetitionRobot.Robots.CompetitionBot;

@Autonomous (name = "Connor_LeftCompAuto")
public class Connor_LeftCompAuto extends Connor_AutoMain {

    CompetitionBot FixitsBot = new CompetitionBot();

    public ParkingPosition_Connor parkPosition = ParkingPosition_Connor.NONE;




    @Override
    public void runOpMode() throws InterruptedException {

        FixitsBot.initRobot(hardwareMap);
        FixitsBot.setLinearOp(this);

        initPipeline();

        telemetry.addLine("Robot Awaiting Start Procedure");
        while (opModeIsActive()) {
            while (!isStarted() && !isStopRequested()) {
                findTag();
                sleep(20);
            }
        telemetry.update();


        waitForStart();



           detectTags();

            parkingTelemetry();

            FixitsBot.gyroReset();


            FixitsBot.clawClose();

            FixitsBot.driveForward(1,5.1);
            sleep(500);
           // FixitsBot.gyroCorrection(0.2,-45);
            sleep(500);
            FixitsBot.rotateRight(.5,1);
            sleep(500);
            FixitsBot.linearSlideUp(0.7,3);
            sleep(500);
            FixitsBot.driveForward(0.5,.31);
            sleep(500);
            FixitsBot.clawOpen();
            sleep(500);
            FixitsBot.driveBack(0.5, 0.6);
            FixitsBot.linearSlideDown(0.7,3);
            FixitsBot.rotateLeft(.5,1);
            sleep(500);
            FixitsBot.driveBack(1,4.89);
            sleep(500);



            if (parkPosition == ParkingPosition_Connor.RIGHT) {

                telemetryUpdate("Park Right");
                FixitsBot.driveForward(1, 7.25);
                sleep(200);
                FixitsBot.gyroCorrection(.5,-45);
                sleep(200);
                FixitsBot.strafeRight(1,4);
                sleep(200);
                FixitsBot.gyroCorrection(.5,-45);
                sleep(200);
            }
            else if (parkPosition == ParkingPosition_Connor.MIDDLE) {

                telemetryUpdate("Park Center");
                FixitsBot.driveForward(1,7.4);
                sleep(200);
                FixitsBot.gyroCorrection(.5,0);
                sleep(200);
            }

            else if (parkPosition == ParkingPosition_Connor.LEFT) {

                telemetryUpdate("Park Left");
                FixitsBot.driveForward(1,7.4);
                sleep(200);
                FixitsBot.gyroCorrection(.5,-45);
                sleep(200);
                FixitsBot.strafeLeft(1,4);
                sleep(200);
                FixitsBot.gyroCorrection(.5,-45);
                sleep(200);
            }

            else if (parkPosition == ParkingPosition_Connor.NONE){
                telemetryUpdate("Cannot Park - Park Position = NONE");

            }
        }
        requestOpModeStop();

        idle();
    }

    public void telemetryUpdate(String comment) {
        telemetry.addLine(comment);
        parkingTelemetry();
        telemetry.addData("Front Lef Motor:", FixitsBot.frontLeftMotor.getPower());
        telemetry.addData("Front Rig Motor:", FixitsBot.frontRightMotor.getPower());
        telemetry.addData("Rear Lef Motor:", FixitsBot.rearLeftMotor.getPower());
        telemetry.addData("Rear Rig Motor:", FixitsBot.rearRightMotor.getPower());
        telemetry.addData("Encoder Count: ", FixitsBot.frontLeftMotor.getCurrentPosition());
        telemetry.addLine("LONG LIVE TACO");
        telemetry.update();
    }
}