package org.firstinspires.ftc.teamcode.BNI_Team.Connor.CompetitionRobot.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.BNI_Team.Connor.CompetitionRobot.Robots.CompetitionBot;
@Disabled
@Autonomous (name = "BNI:Connor_AutoParkingNoCam", group="BNI")

public class Connor_AutoMecParkingNoCam extends LinearOpMode {

    CompetitionBot FixitsBot = new CompetitionBot();

    public enum ParkingPosition {
        RIGHT,
        CENTER,
        LEFT,
        IDLE
    }

   public ParkingPosition parkingPosition = ParkingPosition.CENTER;





    @Override
    public void runOpMode() throws InterruptedException {

        FixitsBot.initRobot(hardwareMap);
        FixitsBot.setLinearOp(this);


        telemetry.addLine("Robot Awaiting Start Procedure");
        telemetry.update();

        waitForStart();


        while (opModeIsActive()) {

            FixitsBot.gyroReset();

            if (parkingPosition == ParkingPosition.RIGHT) {

                telemetryUpdate("Park Right");
                FixitsBot.driveForward(1, 7.25);
                sleep(200);
                FixitsBot.gyroCorrection(.5,0);
                sleep(200);
                FixitsBot.strafeRight(1,4);
                sleep(200);
                FixitsBot.gyroCorrection(.5,0);
                sleep(200);
            }

            else if (parkingPosition == ParkingPosition.CENTER) {

                telemetryUpdate("Park Center");
                FixitsBot.driveForward(1,7.4);
                sleep(200);
                FixitsBot.gyroCorrection(.5,0);
                sleep(200);
            }

            else if (parkingPosition == ParkingPosition.LEFT) {

                telemetryUpdate("Park Left");
                FixitsBot.driveForward(1,7.4);
                sleep(200);
                FixitsBot.gyroCorrection(.5,0);
                sleep(200);
                FixitsBot.strafeLeft(1,4);
                sleep(200);
                FixitsBot.gyroCorrection(.5,0);
                sleep(200);
            }

            else {
                telemetryUpdate("Cannot Park");
                parkingPosition = ParkingPosition.IDLE;
            }


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

//hi

}
