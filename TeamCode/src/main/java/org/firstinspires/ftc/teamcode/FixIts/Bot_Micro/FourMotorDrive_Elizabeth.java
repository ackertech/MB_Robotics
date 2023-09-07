package org.firstinspires.ftc.teamcode.FixIts.Bot_Micro;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class FourMotorDrive_Elizabeth {
    //Declared Variables for our Motors
    public DcMotor frontLeftMotor;
    public DcMotor frontRightMotor;
    public DcMotor rearLeftMotor;
    public DcMotor rearRightMotor;

    //This is just required as part of the FIRST SDK. Memorize it!!
    public LinearOpMode linearOp = null;
    public void setLinearOp (LinearOpMode linearOpModeOp) {
        this.linearOp = linearOp;
    }

    //Common method that can be used in our TeleOp and Autonomous programs
    public void setMotorRunModes (DcMotor.RunMode mode) {
        frontLeftMotor.setMode(mode);
        frontRightMotor.setMode(mode);
        rearLeftMotor.setMode(mode);
        rearRightMotor.setMode(mode);
    }

    //Stops the Motors
    public void stopMotors () {
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        rearLeftMotor.setPower(0);
        rearRightMotor.setPower(0);
    }

    //Drives Forward
    public void driveForward (double power) {
        power = Math.abs(power);
        frontLeftMotor.setPower(power);
        frontRightMotor.setPower(power);
        rearLeftMotor.setPower(power);
        rearRightMotor.setPower(power);
    }

    //Drives Backwards
    public void driveBackward (double power) {
        power = Math.abs(power);
        frontLeftMotor.setPower(-power);
        frontRightMotor.setPower(-power);
        rearLeftMotor.setPower(-power);
        rearRightMotor.setPower(-power);
    }

    //Rotates Left
    public void rotateLeft (double power) {
        power = Math.abs(power);
        frontLeftMotor.setPower(-power);
        frontRightMotor.setPower(power);
        rearLeftMotor.setPower(-power);
        rearRightMotor.setPower(power);
    }

    //Rotates Right
    public void rotateRight (double power) {
        power = Math.abs(power);
        frontLeftMotor.setPower(power);
        frontRightMotor.setPower(-power);
        rearLeftMotor.setPower(power);
        rearRightMotor.setPower(-power);
    }
}
