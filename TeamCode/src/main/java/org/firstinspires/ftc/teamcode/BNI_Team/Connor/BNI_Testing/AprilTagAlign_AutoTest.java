package org.firstinspires.ftc.teamcode.BNI_Team.Connor.BNI_Testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.BNI_Team.Connor.BNI_Testing.RipOffRoadrunner_Adapted.RipOffRoadrunner_Adapted_MecanumDrive;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

@Autonomous (name = "APrilTagAllign_Test")
public class AprilTagAlign_AutoTest extends TestingAutoMain {



    public double GYRO_PATH_SPD = .5;
    public    double GYRO_CORRECT_SPD = .21;
    public double MAX_SPD = 1.0;
    public  double FAST_SPD = .7;
    public double MED_SPD = .5;
    public double STRAFE_SPD = .8;
    public double LONG_STRAFE_SPD = 1;
    public  int SLEEP_GYRO = 150;
    public   int SLEEP_TIME = 100;

    final double DESIRED_DISTANCE = 12.0; //  this is how close the camera should get to the target (inches)

    //  Set the GAIN constants to control the relationship between the measured position error, and how much power is
    //  applied to the drive motors to correct the error.
    //  Drive = Error * Gain    Make these values smaller for smoother control, or larger for a more aggressive response.
    final double SPEED_GAIN  =  0.02  ;   //  Forward Speed Control "Gain". eg: Ramp up to 50% power at a 25 inch error.   (0.50 / 25.0)
    final double STRAFE_GAIN =  0.015 ;   //  Strafe Speed Control "Gain".  eg: Ramp up to 25% power at a 25 degree Yaw error.   (0.25 / 25.0)
    final double TURN_GAIN   =  0.01  ;   //  Turn Control "Gain".  eg: Ramp up to 25% power at a 25 degree error. (0.25 / 25.0)

    final double MAX_AUTO_SPEED = 0.5;   //  Clip the approach speed to this max value (adjust for your robot)
    final double MAX_AUTO_STRAFE= 0.5;   //  Clip the approach speed to this max value (adjust for your robot)
    final double MAX_AUTO_TURN  = 0.3;   //  Clip the turn speed to this max value (adjust for your robot)



    private static final boolean USE_WEBCAM = true;  // Set true to use a webcam, or false for a phone camera
    private static final int DESIRED_TAG_ID = 2;     // Choose the tag you want to approach or set to -1 for ANY tag.
    private VisionPortal visionPortal;               // Used to manage the video source.
    private AprilTagProcessor aprilTag;              // Used for managing the AprilTag detection process.
    private AprilTagDetection desiredTag = null;


    @Override
    public void runOpMode() throws InterruptedException {
        Bot.initRobot(hardwareMap);
        Bot.setLinearOp(this);




        initAprilTag();

        if (USE_WEBCAM)
            setManualExposure(6,250);
       //for april tag stuff

        telemetry.addLine("Robot Awaiting Start Procedure");
        telemetry.update();





        waitForStart();


        while (opModeIsActive()) {
            Bot.resetHeading();

            telemetry.addLine("Robot Autonomous Control Initialized");

            Bot.speedAcceleration(2.5,FAST_SPD, RipOffRoadrunner_Adapted_MecanumDrive.driveDirections.DRIVE_FORWARD);
            sleep(500);

            telemetry.addLine("DRIVE FORWARD");
            Bot.gyroTurn(GYRO_CORRECT_SPD,0);
            sleep(500);
            telemetry.addLine("GYRO CORRECT");
            Bot.gyroTurn(GYRO_PATH_SPD,90);
            sleep(500);
            telemetry.addLine("TURN TARGET ANGLE 90");
            Bot.gyroTurn(GYRO_CORRECT_SPD,90);
            sleep(500);
            telemetry.addLine("GYRO CORRECT");
            AutoAlign();
            sleep(100);
            telemetry.addLine("APRIL TAG ALIGNMENT");
            Bot.gyroTurn(GYRO_CORRECT_SPD,90);
            sleep(500);

//            if (isRobotAlligned == true) {
//                telemetry.addLine("Robot is Alligned");
//            }
//            else if (isRobotAlligned == false) {
//                telemetry.addLine("RObot is NOT alligned");
//            }

            telemetry.update();


        
            requestOpModeStop();

        }

        idle();

    }


}
