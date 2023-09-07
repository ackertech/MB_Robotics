package org.firstinspires.ftc.teamcode.Base.Controls.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Base.Robot.LabBot_4Motor;
import org.firstinspires.ftc.teamcode.Base.Robot.LaunchBot;

//@Disabled
@TeleOp(name = "LaunchBot")
public class TeleOpLauncher extends OpMode {

    public double speedMultiply = 0.50;
    public boolean arcade2StickDrive = false;
    public boolean arcade1StickDrive = true;
    boolean pressed_A = false;

    public LaunchBot Bot = new LaunchBot();

    @Override
    public void init() {

        Bot.initRobot(hardwareMap);

    }

    @Override
    public void loop () {

        drive();
        speedControl();
        driveControl();
        gamepadStates();
        launchControl();

    }

    public void launchControl() {
        if (gamepad2.left_bumper) {
            Bot.launchMotor.setPower(1.0);
        }
        if (gamepad2.right_bumper) {
            Bot.launchMotor.setPower(0.0);
        }

    }


    public void gamepadStates() {
        pressed_A = gamepad1.a;

    }

    public void drive() {

        if (arcade1StickDrive) {

            if (gamepad1.left_stick_y < -0.1) {
                Bot.driveForward(speedMultiply * gamepad1.left_stick_y);
            } else if (gamepad1.left_stick_y > 0.1) {
                Bot.driveBackward(speedMultiply * gamepad1.left_stick_y);
            } else if (gamepad1.left_stick_x > 0.1) {
                Bot.rotateRight(speedMultiply * gamepad1.left_stick_x);
            } else if (gamepad1.left_stick_x < -0.1) {
                Bot.rotateLeft(speedMultiply * gamepad1.left_stick_x);
            } else {
                Bot.stopMotors();
            }
        }
        else if (arcade2StickDrive) {

            if (gamepad1.left_stick_y < -0.1) {
                Bot.driveForward(speedMultiply * gamepad1.left_stick_y);
            } else if (gamepad1.left_stick_y > 0.1) {
                Bot.driveBackward(speedMultiply * gamepad1.left_stick_y);
            }
            else {
                Bot.stopMotors();
            }
            if (gamepad1.right_stick_x > 0.1) {
                Bot.rotateRight(speedMultiply * gamepad1.right_stick_x);
            } else if (gamepad1.right_stick_x < -0.1) {
                Bot.rotateLeft(speedMultiply * gamepad1.right_stick_x);
            }
            else {
                Bot.stopMotors();
            }

        }
    }


    public void speedControl() {

        if (gamepad1.dpad_right) {
            speedMultiply = 0.25;
        }
        else if (gamepad1.dpad_down) {
            speedMultiply = 0.50;
        }
        else if (gamepad1.dpad_left) {
            speedMultiply = 0.75;
        }
        else if (gamepad1.dpad_up) {
            speedMultiply = 1.00;
        }

    }

    public void driveControl () {

        if (gamepad1.left_bumper) {
            arcade1StickDrive = true;
            arcade2StickDrive = false;
        }
        if (gamepad1.right_bumper) {
            arcade1StickDrive = false;
            arcade2StickDrive = true;
        }
    }


}
