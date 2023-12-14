package org.firstinspires.ftc.teamcode.Base.Drivetrains;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Tank_WallE {

    // Defines the motors
    public DcMotor frontLeftMotor;
    public DcMotor frontRightMotor;
    public DcMotor rearLeftMotor;
    public DcMotor rearRightMotor;



    public LinearOpMode LinearOp = null;
    public static final double TICKS_PER_ROTATION = 386.3;
    // This part is just required- try to memorize
    public LinearOpMode linearOp = null;
    public void setLinearOp(LinearOpMode linearOp) {this.linearOp =linearOp;}

    public void setMotorRunModes (DcMotor.RunMode mode) {

        frontLeftMotor.setMode(mode);
        frontRightMotor.setMode(mode);
        rearLeftMotor.setMode(mode);
        rearRightMotor.setMode(mode);
    }

    //Common Method To Tell Robot To Stop
    public void stopMotors () {
            frontLeftMotor.setPower(0);
            frontRightMotor.setPower(0);
            rearLeftMotor.setPower(0);
            rearRightMotor.setPower(0);
    }

    // Common Method To Move Forward

    public void driveForward (double power) {

        double ABSpower = Math.abs(power);
        frontLeftMotor.setPower(ABSpower);
        frontRightMotor.setPower(ABSpower);
        rearLeftMotor.setPower(ABSpower);
        rearRightMotor.setPower(ABSpower);
    }

    public void driveBackwards (double power) {

        double ABSpower = Math.abs(power);
        frontLeftMotor.setPower(-ABSpower);
        frontRightMotor.setPower(-ABSpower);
        rearLeftMotor.setPower(-ABSpower);
        rearRightMotor.setPower(-ABSpower);

    }

    public void driveForward (double power, double rotations) {

        double ticks = rotations  * TICKS_PER_ROTATION;
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while ((Math.abs(rearRightMotor.getCurrentPosition() ) < ticks && LinearOp.opModeIsActive()) ) {
            driveForward(power);
        }
        stopMotors();
    }

    public void driveBackwards (double power, double rotations) {
        double ticks = rotations  * TICKS_PER_ROTATION;
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while ((Math.abs(rearRightMotor.getCurrentPosition() ) < ticks && LinearOp.opModeIsActive() ) ){
            driveBackwards(power);
        }
        stopMotors();
    }

    public void rotateRight ( double power , double rotations) {
        double ticks = rotations * TICKS_PER_ROTATION;
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while ((Math.abs(rearRightMotor.getCurrentPosition() ) < ticks) && LinearOp.opModeIsActive()) {
            rotateRight(power);
        }
        stopMotors();
    }

    public void rotateLeft(double power, double rotations ) {
        double ticks = rotations * TICKS_PER_ROTATION;
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while ((Math.abs(rearRightMotor.getCurrentPosition() ) < ticks ) && LinearOp.opModeIsActive()) {
            rotateLeft(power);
        }
        stopMotors();

    }

    public void rotateLeft (double power) {

        double ABSpower = Math.abs(power);
        frontLeftMotor.setPower(-ABSpower);
        frontRightMotor.setPower(ABSpower);
        rearLeftMotor.setPower(-ABSpower);
        rearRightMotor.setPower(ABSpower);
    }

    public void rotateRight (double power) {

        double ABSpower = Math.abs(power);
        frontLeftMotor.setPower(ABSpower);
        frontRightMotor.setPower(-ABSpower);
        rearLeftMotor.setPower(ABSpower);
        rearRightMotor.setPower(-ABSpower);

    }

    public void  tankDrive (double leftPower, double rightPower) {
        frontLeftMotor.setPower(leftPower);
        rearLeftMotor.setPower(leftPower);

        frontRightMotor.setPower(rightPower);
       rearRightMotor.setPower(rightPower);
    }

}

