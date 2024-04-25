package org.firstinspires.ftc.teamcode.Robotics_Class.Bot_Barbenheimer;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name = "Carnival Madness: Barbenheimer", group = "iLab")
public class TeleOp_Barbenheimer_Olivia extends OpMode {

    double leftStickYVal;
    double rightStickYVal;
    double speedMultiply = 1.0;
    public double leftSidePower;
    public double rightSidePower;
    public double outtakePower = 1;



    public BarbBot bot = new BarbBot();


    @Override
    public void init () {
        bot.initDrive(hardwareMap);
    }


    public void loop() {
        speedControl();
        raiseLaunch();
        launchDisc();
        flipMace();
        launchCatapult();
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

    public void launchDisc() {
        if (gamepad1.right_trigger > 0.1) {bot.launchDisc(outtakePower);}

        else {bot.launcherStop();}

    }


    public void launchCatapult(){
        if(gamepad1.a){
            bot.launchCatapult();
        }
        else{
            bot.resetLaunchCatapult();
        }
    }
    public void flipMace(){
        if(gamepad1.b){
            bot.flipMace();
        }
        else if(gamepad1.left_bumper){
            bot.resetFlipMace();
        }

    }

    public void raiseLaunch(){
        if(gamepad1.y){
            bot.raiseLauncher(.75, 50);
        }
        else if(gamepad1.x){
            bot.lowerLauncher(.75, 48);
        }
        else{
            bot.stopLauncher();
        }
    }

}
