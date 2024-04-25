package org.firstinspires.ftc.teamcode.BNI_Team.Connor.BNI_Testing.PurePursuit;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.ExposureControl;
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.GainControl;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.BNI_Team.Connor.BNI_Testing.ProgramingBot;
import org.firstinspires.ftc.teamcode.BNI_Team.Connor.BNI_Testing.RipOffRoadrunner_Adapted.RipOffRoadrunner_Adapted_MecanumDrive;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class RobotMovement extends LinearOpMode{

    public static void goToPosition_( double x, double y, double movementSpeed) {

    }






    public ProgramingBot Bot = new ProgramingBot();


    boolean isRobotAlligned = false;



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






    double drive = 0;        // Desired forward power/speed (-1 to +1)
    double strafe = 0;        // Desired strafe power/speed (-1 to +1)
    double turn = 0;        // Desired turning power/speed (-1 to +1)

    double startDrive = 0;
    double currentDrive = 0;
    double startStrafe = 0;
    double currentStrafe = 0;
    double startTurn = 0;
    double currentTurn = 0;

    //field dimentions = 12 ft by 12 feet


    public DcMotor frontLeftMotor;
    public DcMotor frontRightMotor;
    public DcMotor rearLeftMotor;
    public DcMotor rearRightMotor;

    public DcMotor leftEncoder;
    public DcMotor rightEncoder;
    public DcMotor centerEncoder;

    public LinearOpMode LinearOp = null;

    public static final double TICKS_PER_ROTATION = 386.3;
    public static final double ODO_TICKS_PER_ROTATION = 2000;

    // Instance Variables for IMU
    public IMU imu = null;
    public double headingTolerance = 0.5;
    public double currentHeading = 0;

    public double getHeading() {
        YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
        return orientation.getYaw(AngleUnit.DEGREES);


    }

    // Helper Method to reset the IMU Yaw Heading
    public void resetHeading() {
        imu.resetYaw();
    }



    /**
     * Move robot according to desired axes motions
     * <p>
     * Positive X is forward
     * <p>
     * Positive Y is strafe left
     * <p>
     * Positive Yaw is counter-clockwise
     */


    public void moveRobot(double x, double y, double yaw) {
        // Calculate wheel powers.
        double leftFrontPower    =  x -y -yaw;
        double rightFrontPower   =  x +y +yaw;
        double leftBackPower     =  x +y -yaw;
        double rightBackPower    =  x -y +yaw;

        // Normalize wheel powers to be less than 1.0
        double max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
        max = Math.max(max, Math.abs(leftBackPower));
        max = Math.max(max, Math.abs(rightBackPower));

        if (max > 1.0) {
            leftFrontPower /= max;
            rightFrontPower /= max;
            leftBackPower /= max;
            rightBackPower /= max;
        }

        // Send powers to the wheels.
        Bot.frontLeftMotor.setPower(leftFrontPower);
        Bot.frontRightMotor.setPower(rightFrontPower);
        Bot.rearLeftMotor.setPower(leftBackPower);
        Bot.rearRightMotor.setPower(rightBackPower);
    }


    public void goToPosition(double drive, double strafe, double turn, double max){
        boolean objectNotDetectedAfterScan = false;

        resetEncoders();



//        double  drive           = 0;        // Desired forward power/speed (-1 to +1)
//        double  strafe          = 0;        // Desired strafe power/speed (-1 to +1)
//        double  turn            = 0;        // Desired turning power/speed (-1 to +1)



        // Step through the list of detected tags and look for a matching tag




        // Tell the driver what we see, and what to do.
//        if (targetFound) {
//            telemetry.addData("Target", "ID %d (%s)", desiredTag.id, desiredTag.metadata.name);
//            telemetry.addData("Range",  "%5.1f inches", desiredTag.ftcPose.range);
//            telemetry.addData("Bearing","%3.0f degrees", desiredTag.ftcPose.bearing);
//            telemetry.addData("Yaw","%3.0f degrees", desiredTag.ftcPose.yaw);
//        } else {
//            telemetry.addData(">","Valid target\n not found");
//        }




            // Determine heading, range and Yaw (tag image rotation) error so we can use them to control the robot automatically.
           // double  rangeError      = (drive - );
            double  headingError    = desiredTag.ftcPose.bearing;
            double  yawError        = desiredTag.ftcPose.yaw;

            // Use the speed and turn "gains" to calculate how we want the robot to move.
       //     drive  = Range.clip(rangeError * SPEED_GAIN, -MAX_AUTO_SPEED, MAX_AUTO_SPEED);
            turn   = Range.clip(headingError * TURN_GAIN, -MAX_AUTO_TURN, MAX_AUTO_TURN) ;
            strafe = Range.clip(-yawError * STRAFE_GAIN, -MAX_AUTO_STRAFE, MAX_AUTO_STRAFE);

            telemetry.addData("Auto","Drive %5.2f, Strafe %5.2f, Turn %5.2f ", drive, strafe, turn);

        telemetry.update();

        // Apply desired axes motions to the drivetrain.
        moveRobot(-drive, -strafe, -turn);

        sleep(50);
        stopCamera();

    }


    /**
     * Initialize the AprilTag processor.
     */
    public void initAprilTag() {


        aprilTag = new AprilTagProcessor.Builder().build();

        if (USE_WEBCAM) {
            visionPortal = new VisionPortal.Builder()
                    .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                    .addProcessor(aprilTag)
                    .build();
        } else {
            visionPortal = new VisionPortal.Builder()
                    .setCamera(BuiltinCameraDirection.BACK)
                    .addProcessor(aprilTag)
                    .build();
        }




    }

    public void stopCamera() {
        visionPortal.stopStreaming();
        visionPortal.close();
    }


    //This can only be called AFTER calling initAprilTag()

    public void setManualExposure(int exposureMS, int gain) {
        // Wait for the camera to be open, then use the controls

        if (visionPortal == null) {
            return;
        }

        // Make sure camera is streaming before we try to set the exposure controls
        if (visionPortal.getCameraState() != VisionPortal.CameraState.STREAMING) {
            telemetry.addData("Camera", "Waiting");
            telemetry.update();
            while (!isStopRequested() && (visionPortal.getCameraState() != VisionPortal.CameraState.STREAMING)) {
                sleep(20);
            }
            telemetry.addData("Camera", "Ready");
            telemetry.update();
        }

        // Set camera controls unless we are stopping.
        if (!isStopRequested())
        {
            ExposureControl exposureControl = visionPortal.getCameraControl(ExposureControl.class);
            if (exposureControl.getMode() != ExposureControl.Mode.Manual) {
                exposureControl.setMode(ExposureControl.Mode.Manual);
                sleep(50);
            }
            exposureControl.setExposure((long)exposureMS, TimeUnit.MILLISECONDS);
            sleep(20);
            GainControl gainControl = visionPortal.getCameraControl(GainControl.class);
            gainControl.setGain(gain);
            sleep(20);
        }
    }


    public double getEncoderAvgDistanceX() {
        double average = (
                Math.abs(leftEncoder.getCurrentPosition()) +
                        Math.abs(rightEncoder.getCurrentPosition())
        ) / 2.0;
        return Math.abs(average);
    }

    public double getEncoderAvgDistanceY() {
        return Math.abs(centerEncoder.getCurrentPosition());
    }

    public void resetEncoders() {
        leftEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        centerEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftEncoder.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightEncoder.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        centerEncoder.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }



}
