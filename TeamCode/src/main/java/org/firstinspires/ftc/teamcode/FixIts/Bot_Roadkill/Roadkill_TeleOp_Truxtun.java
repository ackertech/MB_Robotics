package org.firstinspires.ftc.teamcode.FixIts.Bot_Roadkill;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Roadkill_TeleOp_Truxtun extends OpMode {

    public double speedMultiply = 0.50;
    public Roadkill_Truxtun Bot = new Roadkill_Truxtun();

    @Override
    public void init() {

        Bot.initRobot(hardwareMap);
    }

    @Override
    public void loop() {
        drive();
        speedControl();
        launchControl();
    }

public void speedControl() {

    if (gamepad1.dpad_right) {
        speedMultiply = .25;
    } else if (gamepad1.dpad_down) {
        speedMultiply = .50;
    } else if (gamepad1.dpad_left) {
        speedMultiply = .75;
    } else if (gamepad1.dpad_up) {
        speedMultiply = 1.0;
    }
}


    public void drive() {
        if (gamepad1.left_stick_y > 0.1) {
            Bot.driveForward(speedMultiply * gamepad1.left_stick_y);
        }
        else if (gamepad1.left_stick_y < -0.1) {

            Bot.driveBackward(speedMultiply * gamepad1.left_stick_y);
        }
        else if (gamepad1.left_stick_x > 0.1) {

            Bot.rotateRight(speedMultiply * gamepad1.left_stick_x);

        }
        else if (gamepad1.left_stick_x < -0.1) {
            Bot.rotateLeft(speedMultiply * gamepad1.left_stick_x);
        }

        else {
            Bot.stopMotors();
        }
    }
public void launchControl () {
        if(gamepad2.left_bumper) {
            Bot.launchMotor.setPower(1.0);
        }
        if (gamepad2.right_bumper) {
            Bot.launchMotor.setPower(0.0);
        }
}
}
