package org.firstinspires.ftc.teamcode.Z_Archive.Bot_Roadkill;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class FourMotorDrive_Truxtun {

    public DcMotor frontLeftMotor;
    public DcMotor frontRightMotor;
    public DcMotor rearLeftMotor;
    public DcMotor rearRightMotor;

    public LinearOpMode linearOp = null;
    public void setLinearOp(LinearOpMode linearModeOp) {
            this.linearOp = linearOp;    }


public void setMotorRunModes (DcMotor.RunMode mode) {
    frontLeftMotor.setMode(mode);
    frontRightMotor.setMode(mode);
    rearLeftMotor.setMode(mode);
    rearRightMotor.setMode(mode);
        }
        //stop motors
public void stopMotors () {
    frontLeftMotor.setPower(0);
    frontRightMotor.setPower(0);
    rearLeftMotor.setPower(0);
    rearRightMotor.setPower(0);

}
//drive forward
public void driveForward (double power) {
    frontLeftMotor.setPower(power);
    frontRightMotor.setPower(power);
    rearLeftMotor.setPower(power);
    rearRightMotor.setPower(power);
}
//Drive Backwards
public void driveBackward (double power) {
    frontLeftMotor.setPower(-power);
    frontRightMotor.setPower(-power);
    rearLeftMotor.setPower(-power);
    rearRightMotor.setPower(-power);
}
//rotate Left
public void rotateLeft (double power) {
    frontLeftMotor.setPower(-power);
    frontRightMotor.setPower(power);
    rearLeftMotor.setPower(-power);
    rearRightMotor.setPower(-power);
}
//Rotate Right
public void rotateRight (double power) {
    frontLeftMotor.setPower(power);
    frontRightMotor.setPower(-power);
    rearLeftMotor.setPower(power);
    rearRightMotor.setPower(-power);
}
}