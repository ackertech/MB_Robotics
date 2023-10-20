package org.firstinspires.ftc.teamcode.Base.Drivetrains;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class LabBot_FourMotorDrive_Adv_Acker {

    // Declared Variables for our Motors
    public DcMotor frontLeftMotor;
    public DcMotor frontRightMotor;
    public DcMotor rearLeftMotor;
    public DcMotor rearRightMotor;


    // This is just required as part of the FIRST SDK.  Memorize it!!!
    public LinearOpMode linearOp = null;
    public void setLinearOp (LinearOpMode linearOpModeOp) {

        this.linearOp = linearOp;
    }

    // Declared two constants for our motor run modes
   public final DcMotor.RunMode currentMotorRunMode = DcMotor.RunMode.RUN_WITHOUT_ENCODER;
    public static final double TICKS_PER_ROTATION = 538;

    // Stops the Motors
    public void stopMotors () {
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        rearLeftMotor.setPower(0);
        rearRightMotor.setPower(0);
    }


    // Drivetrain Behaviors (aka Movement)

    // Drives Forward Method
    public void driveForward (double power) {

        frontLeftMotor.setPower(power);
        frontRightMotor.setPower(power);
        rearLeftMotor.setPower(power);
        rearRightMotor.setPower(power);
    }

    // Drives Backwards Method
    public void driveBackward (double power) {

        frontLeftMotor.setPower(-power);
        frontRightMotor.setPower(-power);
        rearLeftMotor.setPower(-power);
        rearRightMotor.setPower(-power);
    }

    //Rotates Left
    public void rotateLeft (double power) {

        frontLeftMotor.setPower(-power);
        frontRightMotor.setPower(power);
        rearLeftMotor.setPower(-power);
        rearRightMotor.setPower(power);

    }

    //Rotates Right
    public void rotateRight (double power) {

        frontLeftMotor.setPower(power);
        frontRightMotor.setPower(-power);
        rearLeftMotor.setPower(power);
        rearRightMotor.setPower(-power);
    }

    //Common method that can be used in our TeleOp and Autonomous programs
    public void setMotorRunModes (DcMotor.RunMode mode) {

        frontLeftMotor.setMode(mode);
        frontRightMotor.setMode(mode);
        rearLeftMotor.setMode(mode);
        rearRightMotor.setMode(mode);
    }


    //******Drive without Encoder Methods********
    public void driveForward( double power, double rotations) {

        double ticks = rotations * TICKS_PER_ROTATION;
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(currentMotorRunMode);
        while (frontLeftMotor.getCurrentPosition() < ticks && linearOp.opModeIsActive()) {
            driveForward(power);
            linearOp.telemetry.addData("Forward Ticks: ", frontLeftMotor.getCurrentPosition());
            linearOp.telemetry.update();
        }
        stopMotors();
    }

    public void driveBackward ( double power, double rotations){

        double ticks = rotations * (-1) * TICKS_PER_ROTATION;
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(currentMotorRunMode);

        while (frontLeftMotor.getCurrentPosition() > ticks && linearOp.opModeIsActive()) {
            driveBackward(power);
            linearOp.telemetry.addData("Backward Ticks: ", frontLeftMotor.getCurrentPosition());
            linearOp.telemetry.update();
        }
        stopMotors();
    }

    public void rotateLeft (double power, double rotations) {
        double ticks = Math.abs(rotations) * (-1) * TICKS_PER_ROTATION; //strafing left moves encoder towards positive infinity
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(currentMotorRunMode);

        while (frontLeftMotor.getCurrentPosition() > ticks && linearOp.opModeIsActive()) {
            rotateLeft(power);
            linearOp.telemetry.addData("Turning Left Ticks: ", frontLeftMotor.getCurrentPosition());
            linearOp.telemetry.update();
        }
        stopMotors();
    }



    public void rotateRight (double power, double rotations) {
        double ticks = Math.abs(rotations) * TICKS_PER_ROTATION; //strafing right moves encoder towards -infinity
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(currentMotorRunMode);

        while(frontLeftMotor.getCurrentPosition() < ticks && linearOp.opModeIsActive()) {
            rotateRight(power);
            linearOp.telemetry.addData("Turning Right Ticks: ", frontLeftMotor.getCurrentPosition());
            linearOp.telemetry.update();
        }
        stopMotors();
    }












}
