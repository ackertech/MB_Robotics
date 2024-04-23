package org.firstinspires.ftc.teamcode.Base.Controls.TeleOp;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Base.Robot.CarnivalBot;

@Disabled
@TeleOp (name = "Carnival Bot")
public class CarnivalTeleOp extends OpMode {

    // Drive Control Instance variables
    double leftStickYVal;
    double leftStickXVal;
    double rightStickYVal;
    double rightStickXVal;
    double speedMultiply = 1.0;
    public double leftSidePower;
    public double rightSidePower;

    // Driver Style Control Instance variables
    public boolean arcade2StickDrive = false;
    public boolean arcade1StickDrive = false;
    public boolean tankDrive = true;

    // Mechanism Speed variables
    public double wormGearPower = 0.90;

    // Constructor
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
        driverStyleControl();
        driveControl();
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


    public void balloonPopperControl() {
        if (gamepad1.left_bumper) {
            bot.extendBalloonPopper();
        }

        if (gamepad1.right_bumper) {
            bot.retractBalloonPopper();
        }

    }

    public void driverStyleControl() {
        if (gamepad1.a) {
            arcade2StickDrive = false;
            arcade1StickDrive = false;
            tankDrive = true;
        }

        if (gamepad1.b) {
            arcade2StickDrive = true;
            arcade1StickDrive = false;
            tankDrive = false;
        }

        if (gamepad1.y) {
            arcade2StickDrive = false;
            arcade1StickDrive = true;
            tankDrive = false;
        }

    }

    public void driveControl() {

        leftStickYVal = gamepad1.left_stick_y;
        leftStickYVal = Range.clip(leftStickYVal, -1, 1);

        leftStickXVal = gamepad1.left_stick_x;
        leftStickXVal = Range.clip(leftStickXVal, -1, 1);

        rightStickYVal = gamepad1.right_stick_y;
        rightStickYVal = Range.clip(rightStickYVal, -1, 1);

        rightStickXVal = gamepad1.right_stick_x;
        rightStickXVal = Range.clip(rightStickXVal, -1, 1);


        if (arcade1StickDrive) {

            if (gamepad1.left_stick_y < -0.1) {
                bot.driveForward(speedMultiply * leftStickYVal);
            } else if (gamepad1.left_stick_y > 0.1) {
                bot.driveBack(speedMultiply * leftStickYVal);
            } else if (gamepad1.left_stick_x > 0.1) {
                bot.rotateRight(speedMultiply * leftStickXVal);
            } else if (gamepad1.left_stick_x < -0.1) {
                bot.rotateLeft(speedMultiply * leftStickXVal);
            } else {
                bot.stopMotors();
            }
        }
        else if (arcade2StickDrive) {

            if (gamepad1.left_stick_y < -0.1) {
                bot.driveForward(speedMultiply * leftStickYVal);
            } else if (gamepad1.left_stick_y > 0.1) {
                bot.driveBack(speedMultiply * leftStickYVal);
            } else if (gamepad1.right_stick_x > 0.1) {
                bot.rotateRight(speedMultiply * rightStickXVal);
            } else if (gamepad1.right_stick_x < -0.1) {
                bot.rotateLeft(speedMultiply * rightStickXVal);
            } else {
                bot.stopMotors();
            }

        }
        else if (tankDrive) {

            leftSidePower = speedMultiply * leftStickYVal * (1);
            rightSidePower = speedMultiply * rightStickYVal * (1);
            bot.tankDrive(leftSidePower, rightSidePower);

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
