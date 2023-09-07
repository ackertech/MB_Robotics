package org.firstinspires.ftc.teamcode.FixIts.Bot_Fernando;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name= "Fernando:Daniel")
public class Fernando_TeleOp_Daniel extends OpMode {

    public double speedMultiply = 1.0;

    public Fernando_Daniel Bot = new Fernando_Daniel();

    @Override
    public void init() {

        Bot.initRobot(hardwareMap);

    }

    @Override
    public void loop() {

        drive();
        speedControl();
        ledControl();
        launchControl();
    }

    public void ledControl() {
        if (gamepad1.left_trigger > 0.1){
            Bot.setLedPattern(RevBlinkinLedDriver.BlinkinPattern. STROBE_BLUE);
        } else if (gamepad1.right_trigger > 0.1) {
            Bot.setLedPattern(RevBlinkinLedDriver.BlinkinPattern.STROBE_BLUE);
        }
    }

    public void speedControl() {
        if (gamepad1.dpad_right) {
            speedMultiply = 0.25;
        } else if (gamepad1.dpad_down) {
            speedMultiply = 0.50;
        } else if (gamepad1.dpad_left) {
            speedMultiply = 0.75;
        } else if (gamepad1.dpad_up) {
            speedMultiply = 1.0;
        }

    }
    public void launchControl() {
        if (gamepad2.left_bumper) {
            Bot.launchMotor1.setPower(1.0);
            Bot.launchMotor2.setPower(1.0);
        }
        if (gamepad2.right_bumper) {
            Bot.launchMotor1.setPower(0.0);
            Bot.launchMotor2.setPower(0.0);
        }

    }
    public void drive(){
        if (gamepad1.left_stick_y >0.1) {
            Bot.driveForward(speedMultiply * gamepad1.left_stick_y);
        }
        else if(gamepad1.left_stick_y < -0.1) {
            Bot.driveBackward(speedMultiply *gamepad1.left_stick_y);
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



}
