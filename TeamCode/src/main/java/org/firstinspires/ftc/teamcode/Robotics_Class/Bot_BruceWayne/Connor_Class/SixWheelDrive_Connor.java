package org.firstinspires.ftc.teamcode.Robotics_Class.Bot_BruceWayne.Connor_Class;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class SixWheelDrive_Connor {


    public DcMotor frontLeftMotor;
    public DcMotor frontRightMotor;
    public DcMotor rearLeftMotor;
    public DcMotor rearRightMotor;


    public LinearOpMode LinearOp = null;

    public static final double TICKS_PER_ROTATION = 386.3;


    public SixWheelDrive_Connor (){}






    public void setLinearOp(LinearOpMode LinearOp) {this.LinearOp = LinearOp;}



    public void setMotorRunModes(DcMotor.RunMode mode) {

        frontLeftMotor.setMode(mode);
        frontRightMotor.setMode(mode);
        rearLeftMotor.setMode(mode);
        rearRightMotor.setMode(mode);
    }

    public void stopMotors() {
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        rearRightMotor.setPower(0);
        rearLeftMotor.setPower(0);
    }

    public void driveForward(double speed) {
        frontLeftMotor.setPower(-speed);
        frontRightMotor.setPower(-speed);
        rearLeftMotor.setPower(-speed);
        rearRightMotor.setPower(-speed);
    }

    public void driveBack(double speed) {
        frontLeftMotor.setPower(speed);
        frontRightMotor.setPower(speed);
        rearLeftMotor.setPower(speed);
        rearRightMotor.setPower(speed);
    }

    public void rotateLeft(double speed) {
        frontLeftMotor.setPower(speed);
        frontRightMotor.setPower(-speed);
        rearLeftMotor.setPower(speed);
        rearRightMotor.setPower(-speed);

    }

    public void rotateRight(double speed) {
        frontLeftMotor.setPower(-speed);
        frontRightMotor.setPower(speed);
        rearLeftMotor.setPower(-speed);
        rearRightMotor.setPower(speed);

    }

    public void driveForward(double speed, double rotations) {

        double ticks = rotations  * TICKS_PER_ROTATION;
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while ((Math.abs(frontLeftMotor.getCurrentPosition() ) < ticks && LinearOp.opModeIsActive()) ) {
            driveForward(speed);
        }
        stopMotors();
    }

    public void driveBack (double speed, double rotations) {
        double ticks = rotations  * TICKS_PER_ROTATION;
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while ((Math.abs(frontLeftMotor.getCurrentPosition() ) < ticks && LinearOp.opModeIsActive() ) ){
            driveBack(speed);
        }
        stopMotors();

    }

    public void rotateRight(double speed, double rotations) {
        double ticks = rotations * TICKS_PER_ROTATION;
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while ((Math.abs(frontRightMotor.getCurrentPosition() ) < ticks) && LinearOp.opModeIsActive()) {
            rotateRight(speed);
        }
        stopMotors();
    }

    public void rotateLeft(double speed, double rotations) {
        double ticks = rotations * TICKS_PER_ROTATION;
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while ((Math.abs(frontLeftMotor.getCurrentPosition() ) < ticks ) && LinearOp.opModeIsActive()) {
            rotateLeft(speed);
        }
        stopMotors();


    }

    public void  tankDrive (double leftPower, double rightPower) {
        frontLeftMotor.setPower(leftPower);
        rearLeftMotor.setPower(leftPower);

        frontRightMotor.setPower(rightPower);
        rearRightMotor.setPower(rightPower);
    }
}


