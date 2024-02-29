package org.firstinspires.ftc.teamcode.Robotics_Class.Bot_Barbenheimer;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Base.Robot.ChristmasBot;


@TeleOp(name = "Christmas: Barbenheimer", group = "iLab")
public class TeleOp_Barbenheimer_Olivia extends OpMode {

    double leftStickYVal;
    double rightStickYVal;
    double speedMultiply = 1.0;
    public double leftSidePower;
    public double rightSidePower;

    public double iglooPower = 0.90;


    public ChristmasBot bot = new ChristmasBot();


    @Override
    public void init () {
        bot.initDrive(hardwareMap);
        bot.initIgloo(hardwareMap);
        bot.initPenguin(hardwareMap);
        bot.initBear(hardwareMap);
    }


    public void loop() {
        speedControl();
        rotateIgloo();
        movePenguin();
        moveBear();
        //moveServos();
        drive();
    }


    public void speedControl ()
    {
        if (gamepad1.dpad_up) {
            speedMultiply = 1.0;
        }
        else if (gamepad1.dpad_right) {
            speedMultiply = 0.75;
        }
        else if (gamepad1.dpad_down) {
            speedMultiply = 0.50;
        }
        else if (gamepad1.dpad_left){
            speedMultiply = .25;
        }

    }

    public void drive() {

        leftStickYVal = gamepad1.left_stick_y;
        leftStickYVal = Range.clip(leftStickYVal, -1, 1);

        rightStickYVal = gamepad1.right_stick_y;
        rightStickYVal = Range.clip(rightStickYVal, -1, 1);

        leftSidePower = speedMultiply * leftStickYVal * (1);
        rightSidePower = speedMultiply * rightStickYVal * (1);
        bot.tankDrive(leftSidePower, rightSidePower);

    }


    public void rotateIgloo()
    {
        if (gamepad1.left_trigger > 0.1) {
            bot.wormGearRotateForward(iglooPower);
        }

        else if (gamepad1.right_trigger > 0.1) {
            bot.wormGearRotateReverse(iglooPower);
        }

        else
        {
            bot.wormGearStop();
        }
    }

    public void movePenguin() {
        if (gamepad1.left_bumper) {
            bot.extendLinear(.90);
        }

        else if (gamepad1.right_bumper) {
            bot.retractLinear(.90);
        }
        else
        {
            bot.stopLinear();
        }

    }

    public void moveBear() {
        if (gamepad1.y) {
            bot.extendFully();
        }
        if (gamepad1.b) {
            bot.extendPartially();
        }

        if (gamepad1.a) {
            bot.retractFully();
        }

    }

    public void moveServos() {
        if (gamepad1.a) {
            bot.rotateLeftOne();
        }

        if (gamepad1.b) {
            bot.rotateLeftTwo();
        }

    }

}
