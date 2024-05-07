package org.firstinspires.ftc.teamcode.BNI_Team.Connor.BNI_Testing.PurePursuit;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.BNI_Team.Connor.BNI_Testing.ProgramingBot;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

public abstract class RobotMovement extends LinearOpMode{

//    public static void goToPosition_( double x, double y, double movementSpeed) {
//
//    }






    public ProgramingBot Bot = new ProgramingBot();




    //  Set the GAIN constants to control the relationship between the measured position error, and how much power is
    //  applied to the drive motors to correct the error.
    //  Drive = Error * Gain    Make these values smaller for smoother control, or larger for a more aggressive response.
    final double SPEED_GAIN  =  0.02  ;   //  Forward Speed Control "Gain". eg: Ramp up to 50% power at a 25 inch error.   (0.50 / 25.0)
    final double STRAFE_GAIN =  0.015 ;   //  Strafe Speed Control "Gain".  eg: Ramp up to 25% power at a 25 degree Yaw error.   (0.25 / 25.0)
    final double TURN_GAIN   =  0.01  ;   //  Turn Control "Gain".  eg: Ramp up to 25% power at a 25 degree error. (0.25 / 25.0)

    final double MAX_AUTO_SPEED = 0.5;   //  Clip the approach speed to this max value (adjust for your robot)
    final double MAX_AUTO_STRAFE= 0.5;   //  Clip the approach speed to this max value (adjust for your robot)
    final double MAX_AUTO_TURN  = 0.3;   //  Clip the turn speed to this max value (adjust for your robot)


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





    public LinearOpMode LinearOp = null;

    public static final double TICKS_PER_ROTATION = 386.3;
    public static final double ODO_TICKS_PER_ROTATION = 2000;
    public static final double ODO_Ticks_Per_Foot = 1;

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


    public double maxDistance = 12;
    public double minDistance = 0;



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


    public void goToPosition(double drive, double strafe, double turn){
        resetEncoders();
//        double  drive           = 0;        // Desired forward power/speed (-1 to +1)
//        double  strafe          = 0;        // Desired strafe power/speed (-1 to +1)
//        double  turn            = 0;        // Desired turning power/speed (-1 to +1)

        double targetDrive = drive * ODO_Ticks_Per_Foot;
        double targetStrafe = strafe * ODO_Ticks_Per_Foot;
        double targetTurn = turn * ODO_Ticks_Per_Foot;


            // Determine heading, range and Yaw (tag image rotation) error so we can use them to control the robot automatically.
            double  rangeError      = (targetDrive);
            double  headingError    = targetStrafe;
            double  yawError        = targetTurn;

            // Use the speed and turn "gains" to calculate how we want the robot to move.
            drive  = Range.clip(rangeError * SPEED_GAIN, -MAX_AUTO_SPEED, MAX_AUTO_SPEED);
            turn   = Range.clip(headingError * TURN_GAIN, -MAX_AUTO_TURN, MAX_AUTO_TURN) ;
            strafe = Range.clip(-yawError * STRAFE_GAIN, -MAX_AUTO_STRAFE, MAX_AUTO_STRAFE);

            telemetry.addData("Auto","Drive %5.2f, Strafe %5.2f, Turn %5.2f ", drive, strafe, turn);

        telemetry.update();

        // Apply desired axes motions to the drivetrain.

        if (drive > 12 || strafe > 12) {
            Bot.frontLeftMotor.setPower(0);
            Bot.frontRightMotor.setPower(0);
            Bot.rearLeftMotor.setPower(0);
            Bot.rearRightMotor.setPower(0);
        }

        else {
            moveRobot(-drive, -strafe, -turn);
        }



        sleep(50);


    }





    public double getEncoderAvgDistanceX() {
        double average = (
                Math.abs(Bot.leftEncoder.getCurrentPosition()) +
                        Math.abs(Bot.rightEncoder.getCurrentPosition())
        ) / 2.0;
        return Math.abs(average);
    }

    public double getEncoderAvgDistanceY() {
        return Math.abs(Bot.centerEncoder.getCurrentPosition());
    }

    public void resetEncoders() {
        Bot.leftEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Bot.rightEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Bot.centerEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        Bot.leftEncoder.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
       Bot.rightEncoder.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Bot.centerEncoder.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }



}
