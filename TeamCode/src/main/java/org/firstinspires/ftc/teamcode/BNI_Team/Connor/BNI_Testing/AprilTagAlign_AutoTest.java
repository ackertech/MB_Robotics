package org.firstinspires.ftc.teamcode.BNI_Team.Connor.BNI_Testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.BNI_Team.Connor.BNI_Testing.RipOffRoadrunner_Adapted.RipOffRoadrunner_Adapted_MecanumDrive;

@Autonomous (name = "APrilTagAllign_Test")
public class AprilTagAlign_AutoTest extends AprilTagALign_FORAUTOUSAGE {



    public double GYRO_PATH_SPD = .5;
    public    double GYRO_CORRECT_SPD = .21;
    public double MAX_SPD = 1.0;
    public  double FAST_SPD = .7;
    public double MED_SPD = .5;
    public double STRAFE_SPD = .8;
    public double LONG_STRAFE_SPD = 1;
    public  int SLEEP_GYRO = 150;
    public   int SLEEP_TIME = 100;

    @Override
    public void runOpMode() throws InterruptedException {
        Bot.initRobot(hardwareMap);
        Bot.setLinearOp(this);

        telemetry.addLine("Robot Awaiting Start Procedure");
        telemetry.update();



        waitForStart();


        while (opModeIsActive()) {
            Bot.resetHeading();

            telemetry.addLine("Robot Autonomous Control Initialized");

            Bot.speedAcceleration(0.5,FAST_SPD, RipOffRoadrunner_Adapted_MecanumDrive.driveDirections.DRIVE_FORWARD);
            sleep(500);
            telemetry.addLine("DRIVE FORWARD");
            Bot.rotateByGyro(GYRO_CORRECT_SPD,0);
            sleep(500);
            telemetry.addLine("GYRO CORRECT");
            Bot.rotateByGyro(GYRO_PATH_SPD,90);
            sleep(500);
            telemetry.addLine("TURN TARGET ANGLE 90");
            Bot.rotateByGyro(GYRO_CORRECT_SPD,90);
            sleep(500);
            telemetry.addLine("GYRO CORRECT");
            AprilTagAutoAdjust(2);
            sleep(100);
            telemetry.addLine("APRIL TAG ALIGNMENT");
            sleep(500);
            telemetry.update();

            requestOpModeStop();

        }

        idle();

    }


}
