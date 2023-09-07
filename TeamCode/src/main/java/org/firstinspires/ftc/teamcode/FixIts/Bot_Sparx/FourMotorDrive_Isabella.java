package org.firstinspires.ftc.teamcode.FixIts.Bot_Sparx;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class FourMotorDrive_Isabella {

    //Declared variables for our motors//
    public DcMotor frontLeftMotor;
    public DcMotor frontRightMotor;
    public DcMotor rearLeftMotor;
    public DcMotor rearRightMotor;
    //This is required as part of the FIRST SDK. Memorize it//
    public LinearOpMode linearOp = null;
    public void setLinearOp (LinearOpMode linearOpModeOp) {
        this.linearOp = linearOp;
    }
    //common method that can be used in our TeleOp and Autonomous programs//
    public void setMotorRunModes (DcMotor.RunMode mode){
        frontLeftMotor.setMode (mode);
        frontRightMotor.setMode (mode);
        rearLeftMotor.setMode (mode);
        rearRightMotor.setMode (mode);
    }
    //stops motors//
    public void stopMotors () {
        frontLeftMotor.setPower (0);
        frontRightMotor.setPower (0);
        rearLeftMotor.setPower (0);
        rearRightMotor.setPower (0);
    }
    //Drives forward//
    public void driveForward (double power) {
        power = Math.abs(power);
        frontLeftMotor.setPower (power);
        frontRightMotor.setPower (power);
        rearLeftMotor.setPower (power);
        rearRightMotor.setPower (power);
    }
    //Drives backward//
    public void driveBackward (double power) {
        power = Math.abs(power);
        frontLeftMotor.setPower (-power);
        frontRightMotor.setPower (-power);
        rearLeftMotor.setPower (-power);
        rearRightMotor.setPower (-power);
    }
    //rotates left//
    public void rotateLeft (double power) {
        power = Math.abs(power);
        frontLeftMotor.setPower (power);
        frontRightMotor.setPower (-power);
        rearLeftMotor.setPower (power);
        rearRightMotor.setPower (-power);
    }
    //rotates right//
    public void rotateRight (double power) {
        power = Math.abs(power);
        frontLeftMotor.setPower (-power);
        frontRightMotor.setPower (power);
        rearLeftMotor.setPower (-power);
        rearRightMotor.setPower (power);
    }
}
