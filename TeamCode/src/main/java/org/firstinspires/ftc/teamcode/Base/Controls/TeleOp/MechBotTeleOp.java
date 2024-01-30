package org.firstinspires.ftc.teamcode.Base.Controls.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Base.Robot.AckerBot;
import org.firstinspires.ftc.teamcode.Base.Robot.MechBot;


//@Disabled
@TeleOp(name = "LabBot Mecanum",group="iLab")

public class MechBotTeleOp extends OpMode {

    // Variables & Constants specific to TelLabBot
    double leftStickYVal,leftStickXVal, rightStickXVal,rightStickYVal;
    double frontLeftSpeed, frontRightSpeed, rearLeftSpeed, rearRightSpeed;

    double powerThreshold = 0;
    double speedMultiply = 1;

    // Constants for driver profiles
    private static final int PROFILE_1 = 1;
    private static final int PROFILE_2 = 2;
    private int currentProfile = PROFILE_1;

    // Object Construction
    public ElapsedTime TeleOpTime = new ElapsedTime();
    public MechBot Bot = new MechBot();

    // Runs ONCE when driver presses INIT
    @Override
    public void init() {
        //Hardware Initialization from Robot Class
        Bot.initRobot(hardwareMap);

    }

    // Runs Repeatedly when driver presses INIT but before pressing PLAY
    @Override
    public void init_loop() { }

    // Runs ONCE when driver presses PLAY
    @Override
    public void start() { }


    // RUNS Repeatedly after driver presses PLAY
    @Override
    public void loop() {
        drive();
        driveMode();
        //telemetryOutput();
    }

    // Code to run ONCE after the driver presses STOP
    @Override
    public void stop() {}


    // TeleOp Functions

    public void drive () {

        // Joystick values
        double leftStickYVal = -gamepad1.left_stick_y;
        leftStickYVal = Range.clip(leftStickYVal, -1, 1);
        //double rightStickYVal = gamepad1.right_stick_y;
        //rightStickYVal = Range.clip(rightStickYVal, -1, 1);

        double leftStickXVal = gamepad1.left_stick_x;
        leftStickXVal = Range.clip(leftStickXVal, -1, 1);
        double rightStickXVal = gamepad1.right_stick_x;
        rightStickXVal = Range.clip(rightStickXVal, -1, 1);

        switch (currentProfile) {

            // Name of Driver using Profile 1
            case PROFILE_1:
                // leftStickXVal controls rotation, and rightStickXVal controls strafing.
                frontLeftSpeed = leftStickYVal + rightStickXVal + leftStickXVal;
                frontRightSpeed = leftStickYVal - rightStickXVal - leftStickXVal;
                rearLeftSpeed = leftStickYVal - rightStickXVal + leftStickXVal;
                rearRightSpeed = leftStickYVal + rightStickXVal - leftStickXVal;
                break;
            // Name of Driver using Profile 2
            case PROFILE_2:
                //leftStickXVal controls strafing, and rightStickXVal controls rotation.
                frontLeftSpeed = leftStickYVal + leftStickXVal + rightStickXVal;
                frontRightSpeed = leftStickYVal - leftStickXVal - rightStickXVal;
                rearLeftSpeed = leftStickYVal - leftStickXVal + rightStickXVal;
                rearRightSpeed = leftStickYVal + leftStickXVal - rightStickXVal;
                break;

            // Default Driver Profile
            default:
                frontLeftSpeed = 0;
                frontRightSpeed = 0;
                rearLeftSpeed = 0;
                rearRightSpeed = 0;
                break;
        }

        // Clipping motor speeds to [-1, 1]
        frontLeftSpeed = Range.clip(frontLeftSpeed, -1, 1);
        frontRightSpeed = Range.clip(frontRightSpeed, -1, 1);
        rearLeftSpeed = Range.clip(rearLeftSpeed, -1, 1);
        rearRightSpeed = Range.clip(rearRightSpeed, -1, 1);

        // Setting motor powers (with threshold check)
        setMotorPower(Bot.frontLeftMotor, frontLeftSpeed, powerThreshold, speedMultiply);
        setMotorPower(Bot.frontRightMotor, frontRightSpeed, powerThreshold, speedMultiply);
        setMotorPower(Bot.rearLeftMotor, rearLeftSpeed, powerThreshold, speedMultiply);
        setMotorPower(Bot.rearRightMotor, rearRightSpeed, powerThreshold, speedMultiply);
    }


    public void setMotorPower(DcMotor motor, double speed, double threshold, double multiplier) {
        if (speed <= threshold && speed >= -threshold) {
            motor.setPower(0);
        } else {
            motor.setPower(speed * multiplier);
        }
    }

    public void driveMode () {
        if (gamepad1.dpad_up) {
            speedMultiply = 1.0;
        }
        else if (gamepad1.dpad_down) {
            speedMultiply = 0.5;
        }

        if (gamepad1.dpad_right) {
            currentProfile = PROFILE_1;
        }
        else if (gamepad1.dpad_left) {
            currentProfile = PROFILE_2;
        }


    }

    public void telemetryOutput() {
        telemetry.addData("LED", "TeleOp Time: " + Bot.currentTime);
        telemetry.addData("Gamepad", "LeftY: " + leftStickYVal);
        telemetry.addData("Gamepad", "LeftX: " + leftStickXVal);
        telemetry.addData("PWR", "FL: " + frontLeftSpeed);
        telemetry.addData("PWR", "FR: " + frontRightSpeed);
        telemetry.addData("PWR", "RL: " + rearLeftSpeed);
        telemetry.addData("PWR", "RR: " + rearRightSpeed);
        telemetry.update();

    }

}