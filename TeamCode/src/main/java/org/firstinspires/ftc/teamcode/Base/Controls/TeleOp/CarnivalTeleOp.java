package org.firstinspires.ftc.teamcode.Base.Controls.TeleOp;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Base.Robot.CarnivalBot;

@Disabled
@TeleOp (name = "Carnival Bot")
public class CarnivalTeleOp extends OpMode {
    double leftStickYVal;
    double rightStickYVal;
    double speedMultiply = 1.0;
    public double leftSidePower;
    public double rightSidePower;

    public double wormGearPower = 0.90;

    public CarnivalBot bot = new CarnivalBot();


    @Override
    public void init () {
        bot.initDrive(hardwareMap);
        bot.initFlyWheels(hardwareMap);
        bot.initWormGear(hardwareMap);
        bot.initLinearActuator(hardwareMap);
        bot.initBalloonPopper(hardwareMap);
        bot.initDiscPusher(hardwareMap);
        bot.initServoTwo(hardwareMap);
    }

    @Override
    public void loop() {
        speedControl();
        driveTankControl();
        flyWheelControl();
        wormGearControl();
        linearActuatorControl();
        discPusherControl();
        balloonPopperControl();
        servoTwoControl();

    }


    // *********  Driver One Controls on GamePad 1  *************

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

    public void driveTankControl() {
        leftStickYVal = gamepad1.left_stick_y;
        leftStickYVal = Range.clip(leftStickYVal, -1, 1);

        rightStickYVal = gamepad1.right_stick_y;
        rightStickYVal = Range.clip(rightStickYVal, -1, 1);

        leftSidePower = speedMultiply * leftStickYVal * (1);
        rightSidePower = speedMultiply * rightStickYVal * (1);
        bot.tankDrive(leftSidePower, rightSidePower);

    }

    public void balloonPopperControl() {
        if (gamepad1.left_bumper) {
            bot.extendBalloonPopper();
        }

        if (gamepad1.right_bumper) {
            bot.retractBalloonPopper();
        }

    }

    // ********  Driver Two Controls on GamePad 2  **************

    public void flyWheelControl()
    {
        if (gamepad2.left_bumper) {
            bot.rotateFlyWheel1(1.0);
            bot.rotateFlyWheel2(-1.0);
        }

        if (gamepad2.right_bumper) {
            bot.stopFlyWheel1();
            bot.stopFlyWheel2();
        }

    }

    public void wormGearControl()
     {
         if (gamepad2.left_trigger > 0.1) {
             bot.wormGearRotateForward(wormGearPower);
         }
         else if (gamepad2.right_trigger > 0.1) {
             bot.wormGearRotateReverse(wormGearPower);
         }
         else
         {
             bot.wormGearStop();
         }
     }

     public void linearActuatorControl() {
         if (gamepad2.dpad_up) {
             bot.extendLinear(.90);
         }
         else if (gamepad2.dpad_up) {
             bot.retractLinear(90);
         }
         else
         {
             bot.stopLinear();
         }

     }

    public void discPusherControl() {
        if (gamepad2.y) {
            bot.extendFully();
        }

        if (gamepad2.x) {
            bot.retractFully();
        }

    }



    public void servoTwoControl() {
        if (gamepad1.a) {
            bot.extendBalloonPopper();
        }

        if (gamepad1.b) {
            bot.retractBalloonPopper();
        }

    }



}
