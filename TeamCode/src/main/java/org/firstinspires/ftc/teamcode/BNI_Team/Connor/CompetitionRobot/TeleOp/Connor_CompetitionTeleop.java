package org.firstinspires.ftc.teamcode.BNI_Team.Connor.CompetitionRobot.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.BNI_Team.Connor.CompetitionRobot.Robots.CompetitionBot;
@Disabled
@TeleOp (name = "BNI:CompetitionTeleOp_Connor", group="BNI")

public class Connor_CompetitionTeleop extends OpMode {




    double leftStickYVal;
    double leftStickXVal;
    double rightStickXVal;
    double rightStickYVal;

    double frontLeftSpeed;
    double frontRightSpeed;
    double rearLeftSpeed;
    double rearRightSpeed;

    double powerThreshold = 0;
    double speedMultiply = 1;
    public ElapsedTime TeleOpTime = new ElapsedTime();

    public enum ControlOfCompClaw {OPEN, CLOSED}

    public double linearSlideTicks = 350; //537.6
    public double linearSlideMidTicks = 250;
    public double linearSlideLowTicks = 150;
    public double linearSlidePower = 1;

    public enum LazySusanEncoder {FORWARD, REVERSE, OFF}

    public double lazySusanTicks = 5000;
    public double lazySusanPower = 0.90;




    public CompetitionBot CompBot = new CompetitionBot();



    public void init() {
        CompBot.initRobot(hardwareMap);
        CompBot.claw.setPosition(0);
    }



    public void init_loop() {  }


    public void start() {

    }

    public void loop(){
        clawControl();
        mannualLinearSlideControl();
        lazySusanControl();
        drive();
        speedControl();
        telemetryOutput();

    }

    @Override
    public void stop() {  }

    public void drive() {

        leftStickYVal = gamepad1.left_stick_y;
        leftStickYVal = Range.clip(leftStickYVal, -1, 1);
        leftStickXVal = -gamepad1.left_stick_x;
        leftStickXVal = Range.clip(leftStickXVal, -1, 1);
        rightStickXVal = -gamepad1.right_stick_x;
        rightStickXVal = Range.clip(rightStickXVal, -1, 1);

        frontLeftSpeed = leftStickYVal + leftStickXVal + rightStickXVal;
        frontLeftSpeed = Range.clip(frontLeftSpeed, -1, 1);

        frontRightSpeed = leftStickYVal - leftStickXVal - rightStickXVal;
        frontRightSpeed = Range.clip(frontRightSpeed, -1, 1);

        rearLeftSpeed = leftStickYVal - leftStickXVal + rightStickXVal;
        rearLeftSpeed = Range.clip(rearLeftSpeed, -1, 1);

        rearRightSpeed = leftStickYVal + leftStickXVal - rightStickXVal;
        rearRightSpeed = Range.clip(rearRightSpeed, -1, 1);

        if (frontLeftSpeed <= powerThreshold && frontLeftSpeed >= -powerThreshold) {
            frontLeftSpeed = 0;
            CompBot.frontLeftMotor.setPower(frontLeftSpeed);
        } else {
            CompBot.frontLeftMotor.setPower(frontLeftSpeed * speedMultiply);
        }

        if (frontRightSpeed <= powerThreshold && frontRightSpeed >= -powerThreshold){
            frontRightSpeed = 0;
            CompBot.frontRightMotor.setPower(frontRightSpeed);
        } else {
            CompBot.frontRightMotor.setPower(frontRightSpeed * speedMultiply);
        }

        if (rearLeftSpeed <= powerThreshold && rearLeftSpeed >= -powerThreshold) {
            rearLeftSpeed = 0;
            CompBot.rearLeftMotor.setPower(rearLeftSpeed);
        } else {
            CompBot.rearLeftMotor.setPower(rearLeftSpeed * speedMultiply);
        }

        if (rearRightSpeed <= powerThreshold && rearRightSpeed >= -powerThreshold){
            rearRightSpeed = 0;
            CompBot.rearRightMotor.setPower(rearRightSpeed);
        } else {
            CompBot.rearRightMotor.setPower(rearRightSpeed * speedMultiply);
        }
    }


    public void clawControl(){

        if (gamepad2.right_trigger > 0.1) {
            CompBot.claw.setPosition(0);
        }

        if (gamepad2.left_trigger > 0.1) {
            CompBot.claw.setPosition(.5);
        }
    }









    public void  mannualLinearSlideControl () {
        leftStickYVal = gamepad2.left_stick_y;
        leftStickYVal = Range.clip(leftStickYVal, -1, 1);

        if (leftStickYVal > 0.1) {
            CompBot.linearSlideUp(linearSlidePower);
        }

        else if (leftStickYVal < -0.1) {
            CompBot.linearSlideDown(linearSlidePower);
        }

        else {
            CompBot.linearSlideStop();
        }
    }



public void lazySusanControl(){



    if (gamepad2.right_stick_x > 0.1) {
        CompBot.lazySusanLeft(lazySusanPower);
    }

    else if (gamepad2.right_stick_x < -0.1) {
        CompBot.lazySusanRight(lazySusanPower);
    }

    else{
        CompBot.lazySusanStop();
    }
}



    public void telemetryOutput() {

        telemetry.addLine("LONG LIVE TACO");
        telemetry.addData("pwr", "FL mtr: " + frontLeftSpeed);
        telemetry.addData("pwr", "FR mtr: " + frontRightSpeed);
        telemetry.addData("pwr", "RL mtr: " + rearLeftSpeed);
        telemetry.addData("pwr", "RR mtr: " + rearRightSpeed);
        telemetry.addData("linSLide", "hi",CompBot.linearSlide.getCurrentPosition());

        telemetry.update();
    }

    public void speedControl() {

        if (gamepad1.dpad_up) {
            speedMultiply = 0.5;
        }

       else if (gamepad1.dpad_down) {
           speedMultiply = 1;
        }
    }



}













