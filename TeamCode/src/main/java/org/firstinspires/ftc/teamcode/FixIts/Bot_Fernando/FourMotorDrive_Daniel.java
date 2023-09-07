package org.firstinspires.ftc.teamcode.FixIts.Bot_Fernando;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class FourMotorDrive_Daniel {

    //Variables

    public DcMotor frontLeftMotor;
    public DcMotor frontRightMotor;
    public DcMotor rearLeftMotor;
    public DcMotor rearRightMotor;

    //SDK

    public LinearOpMode linearOp = null;
    public void setLinearOp (LinearOpMode LinearOpModeOP) {
        this.linearOp = linearOp;
    }

    public void setMotorRunModes (DcMotor.RunMode mode) {
        frontLeftMotor.setMode(mode);
        frontRightMotor.setMode(mode);
        rearLeftMotor.setMode(mode);
        rearRightMotor.setMode(mode);
    }

    //Stop

    public void stopMotors () {
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        rearLeftMotor.setPower(0);
        rearRightMotor.setPower(0);
    }

    //Drive Forward

    public void driveForward (double power) {
        power = Math.abs(power) ;
        frontLeftMotor.setPower(power);
        frontRightMotor.setPower(power);
        rearLeftMotor.setPower(power);
        rearRightMotor.setPower(power);
    }

    //Drive Backwards

    public void driveBackward (double power) {
        power = Math.abs(power) ;
        frontLeftMotor.setPower(-power);
        frontRightMotor.setPower(-power);
        rearLeftMotor.setPower(-power);
        rearRightMotor.setPower(-power);
    }

    //Rotate Left

    public void rotateLeft (double power) {
        power = Math.abs(power) ;
        frontLeftMotor.setPower(power);
        frontRightMotor.setPower(-power);
        rearLeftMotor.setPower(power);
        rearRightMotor.setPower(-power);
    }

    //Rotate Right

    public void rotateRight (double power) {
        power = Math.abs(power) ;
        frontLeftMotor.setPower(-power);
        frontRightMotor.setPower(power);
        rearLeftMotor.setPower(-power);
        rearRightMotor.setPower(power);
    }

}
