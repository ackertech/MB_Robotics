package org.firstinspires.ftc.teamcode.Base.Controls.TeleOp;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Base.Robot.ChristmasBot;

@Disabled
@TeleOp (name = "Christmas Bot")
public class ChristmasTeleOp extends OpMode {
    double leftStickYVal;
    double rightStickYVal;
    double speedMultiply = 1.0;
    public double leftSidePower;
    public double rightSidePower;

    public double wormGearPower = 0.90;


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
        rotateWormGear();
        moveLinearMotor();
        moveRackGear();
        moveServos();
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


     public void rotateWormGear()
     {
         if (gamepad1.left_trigger > 0.1) {
             bot.wormGearRotateForward(wormGearPower);
         }

         else if (gamepad1.right_trigger > 0.1) {
             bot.wormGearRotateReverse(wormGearPower);
         }

         else
         {
             bot.wormGearStop();
         }
     }

     public void moveLinearMotor() {
         if (gamepad1.left_bumper) {
             bot.extendLinear(.90);
         }

         else if (gamepad1.right_bumper) {
             bot.extendLinear(.90);
         }
         else
         {
             bot.stopLinear();
         }

     }

    public void moveRackGear() {
        if (gamepad1.y) {
            bot.extendFully();
        }

        if (gamepad1.x) {
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
