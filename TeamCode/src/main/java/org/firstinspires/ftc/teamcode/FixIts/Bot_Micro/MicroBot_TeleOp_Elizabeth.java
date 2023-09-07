package org.firstinspires.ftc.teamcode.FixIts.Bot_Micro;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Drive Micro: Elizabeth")

public class MicroBot_TeleOp_Elizabeth extends OpMode {

public double speedMultiply = 0.50;

public MicroBot_Elizabeth Bot = new MicroBot_Elizabeth();

@Override
    public void init() {

    Bot.initRobot(hardwareMap);

}
@Override
public void loop () {
    drive();
    speedControl();
    controlFlag();
    ledControl();
    launchControl();
}

public void ledControl(){
    if (gamepad1.left_trigger > 0.1) {
        Bot.setLedPattern(RevBlinkinLedDriver.BlinkinPattern.COLOR_WAVES_OCEAN_PALETTE);
    } else if (gamepad1.right_trigger > 0.1) {
        Bot.setLedPattern(RevBlinkinLedDriver.BlinkinPattern.COLOR_WAVES_OCEAN_PALETTE);
    }

}

public void speedControl() {
    if (gamepad1.dpad_right == true) {
        speedMultiply = 0.25;
    }
else if (gamepad1.dpad_down == true) {
    speedMultiply = 0.50;
    }
else if (gamepad1.dpad_left == true) {
    speedMultiply = 0.75;
    }
else if (gamepad1.dpad_up == true) {
    speedMultiply = 1.0;
    }
}

public void drive () {
    if (gamepad1.left_stick_y < -0.1) {
        Bot.driveForward(speedMultiply * gamepad1.left_stick_y);
    }
    else if (gamepad1.left_stick_y > 0.1) {
        Bot.driveBackward(speedMultiply * gamepad1.left_stick_y);
    }
    else if (gamepad1.left_stick_x > 0.1) {
        Bot.rotateRight(speedMultiply * gamepad1.left_stick_x);
    }
    else if (gamepad1.left_stick_x < -0.1) {
            Bot.rotateLeft(speedMultiply * gamepad1.left_stick_x);
        }
    else{
            Bot.stopMotors();
        }
    }

    public void controlFlag() {
    if (gamepad1.right_trigger > 0.01) {
        Bot.raiseFlag();
    }
    else if (gamepad1.left_trigger > 0.01) {
        Bot.lowerFlag();
    }
    else Bot.lowerFlag();
    }

    public void launchControl() {
    if (gamepad2.left_bumper) {
        Bot.launchMotor.setPower(1.0);
    }
    if (gamepad2.right_bumper) {
        Bot.launchMotor.setPower(0.0);
    }

    }
}



