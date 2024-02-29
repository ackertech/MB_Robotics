package org.firstinspires.ftc.teamcode.Robotics_Class.Bot_Barbenheimer;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class DriveTrain_Olivia {

    // Declare Variables & DcMotor is the type of motor
    // public is who it's accessible to
    public DcMotor frontLeftMotor;
    public DcMotor frontRightMotor;
    public DcMotor rearLeftMotor;
    public DcMotor rearRightMotor;

    public DcMotor penguinSlide;
    public DcMotor iglooTurn;

    public Servo bearLift;

    //Required as part of First SDK
    //Null: empty/not defined
    public LinearOpMode linearOp = null;

    //method defines a procedure
    //void means no parameters
    public void stopMotors () {
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        rearLeftMotor.setPower(0);
        rearRightMotor.setPower(0);
    }

    //parameter = making a method reusable,
    // if at 100% power, the 1 passes through all the methods and sets them to 100%
    //setPower is a method someone else made
    public void driveForward (double power) {
        frontLeftMotor.setPower(power);
        frontRightMotor.setPower(power);
        rearLeftMotor.setPower(power);
        rearRightMotor.setPower(power);

    }

    public void driveBackward(double power) {
        frontLeftMotor.setPower(-power);
        frontRightMotor.setPower(-power);
        rearLeftMotor.setPower(-power);
        rearRightMotor.setPower(-power);
    }

    public void rotateLeft (double power) {
        frontLeftMotor.setPower(-power);
        frontRightMotor.setPower(power);
        rearLeftMotor.setPower(-power);
        rearRightMotor.setPower(power);
    }

    public void rotateRight (double power) {
        frontLeftMotor.setPower(power);
        frontRightMotor.setPower(-power);
        rearLeftMotor.setPower(power);
        rearRightMotor.setPower(-power);
    }

    //can be used in Tele and Auto
    //mode is defined in a later robot class
    public void setMotorRunModes (DcMotor.RunMode mode) {
        frontLeftMotor.setMode(mode);
        frontRightMotor.setMode(mode);
        rearLeftMotor.setMode(mode);
        rearRightMotor.setMode(mode);

    }

    //Just required as a part of FIRST SDK
    public void setLinearOp (LinearOpMode linearOpModeOp) {

        this.linearOp = linearOp;
    }

    public void  tankDrive (double leftPower, double rightPower) {
        frontLeftMotor.setPower(leftPower);
        rearLeftMotor.setPower(leftPower);

        frontRightMotor.setPower(rightPower);
        rearRightMotor.setPower(rightPower);
    }

}


