package org.firstinspires.ftc.teamcode.BNI_Team.Connor.TeleOps;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.BNI_Team.Connor.Robots.CandyLaunchingBot_Connor;
import org.firstinspires.ftc.teamcode.BNI_Team.Connor.Robots.The_Mighty_and_All_Powerful_Hand;

@Disabled
@TeleOp(name = "CandyLaunchingBotJohnCena",group="iLab")

public class CandyLaunchingBotTeleOp_Connor extends OpMode{

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
    boolean reverseModeToggle = false;

    public ElapsedTime TeleOpTime = new ElapsedTime();

    public enum ArmControl {AUTO, MANUAL}
    public Tank_TeleOp_Connor.ArmControl armControl = Tank_TeleOp_Connor.ArmControl.MANUAL;

    public enum LazySusanControl {AUTO, MANUAL}
    public Tank_TeleOp_Connor.LazySusanControl lazySusanControl = Tank_TeleOp_Connor.LazySusanControl.MANUAL;
    public enum LazySusanEncoder {FORWARD, REVERSE, OFF}
    public Tank_TeleOp_Connor.LazySusanEncoder lazySusanEncoder = Tank_TeleOp_Connor.LazySusanEncoder.OFF;
    public double lazySusanTicks = 5000;
    public double lazySusanPower = 0.90;


    public CandyLaunchingBot_Connor CandyBot = new CandyLaunchingBot_Connor();
    public The_Mighty_and_All_Powerful_Hand Hand = new The_Mighty_and_All_Powerful_Hand();

    @Override
    public void init() {
        CandyBot.initRobot(hardwareMap);
    }

    @Override
    public void init_loop() { }

    @Override
    public void start() { }

    @Override
    public void loop() {
        driveMode();
       controlLauncher();
       handControl();
       controlCamPivot();
       telemetryOutput();

    }


    @Override
    public void stop() {
        Hand.closeHand();
        Hand.lowerArm();
    }

    public void drive () {

        if (reverseModeToggle) {

            leftStickYVal = -gamepad1.left_stick_y;
            leftStickYVal = Range.clip(leftStickYVal, -1, 1);
            leftStickXVal = gamepad1.left_stick_x;
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
                CandyBot.frontLeftMotor.setPower(frontLeftSpeed);
            } else {
                CandyBot.frontLeftMotor.setPower(frontLeftSpeed * speedMultiply);
            }

            if (frontRightSpeed <= powerThreshold && frontRightSpeed >= -powerThreshold){
                frontRightSpeed = 0;
                CandyBot.frontRightMotor.setPower(frontRightSpeed);
            } else {
                CandyBot.frontRightMotor.setPower(frontRightSpeed * speedMultiply);
            }

            if (rearLeftSpeed <= powerThreshold && rearLeftSpeed >= -powerThreshold) {
                rearLeftSpeed = 0;
                CandyBot.rearLeftMotor.setPower(rearLeftSpeed);
            } else {
                CandyBot.rearLeftMotor.setPower(rearLeftSpeed * speedMultiply);
            }

            if (rearRightSpeed <= powerThreshold && rearRightSpeed >= -powerThreshold){
                rearRightSpeed = 0;
                CandyBot.rearRightMotor.setPower(rearRightSpeed);
            } else {
                CandyBot.rearRightMotor.setPower(rearRightSpeed * speedMultiply);
            }
        }

        else {

            leftStickYVal = gamepad1.left_stick_y;
            leftStickYVal = Range.clip(leftStickYVal, -1, 1);
            leftStickXVal = gamepad1.left_stick_x;
            leftStickXVal = Range.clip(leftStickXVal, -1, 1);
            rightStickXVal = gamepad1.right_stick_x;
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
                CandyBot.frontLeftMotor.setPower(frontLeftSpeed);
            } else {
                CandyBot.frontLeftMotor.setPower(frontLeftSpeed * speedMultiply);
            }

            if (frontRightSpeed <= powerThreshold && frontRightSpeed >= -powerThreshold){
                frontRightSpeed = 0;
                CandyBot.frontRightMotor.setPower(frontRightSpeed);
            } else {
                CandyBot.frontRightMotor.setPower(frontRightSpeed * speedMultiply);
            }

            if (rearLeftSpeed <= powerThreshold && rearLeftSpeed >= -powerThreshold) {
                rearLeftSpeed = 0;
                CandyBot.rearLeftMotor.setPower(rearLeftSpeed);
            } else {
                CandyBot.rearLeftMotor.setPower(rearLeftSpeed * speedMultiply);
            }

            if (rearRightSpeed <= powerThreshold && rearRightSpeed >= -powerThreshold){
                rearRightSpeed = 0;
                CandyBot.rearRightMotor.setPower(rearRightSpeed);
            } else {
                CandyBot.rearRightMotor.setPower(rearRightSpeed * speedMultiply);
            }
        }



    }



    public void telemetryOutput() {
        telemetry.addLine("Rick Astley is never gonna give you up");
        telemetry.addData("LED", "TeleOp Time: " + CandyBot.currentTime);
        telemetry.addData("LED", "LED Timer: " + CandyBot.ledTimer);
        telemetry.addData("LED", "LED Counter: " + CandyBot.ledCounter);
        telemetry.addData("LED", "LED Pattern: " + CandyBot.ledPattern);
        telemetry.addData("LED", "LED Pattern: " + CandyBot.ledLights);
        telemetry.addData("pwr", "FL mtr: " + frontLeftSpeed);
        telemetry.addData("pwr", "FR mtr: " + frontRightSpeed);
        telemetry.addData("pwr", "RL mtr: " + rearLeftSpeed);
        telemetry.addData("pwr", "RR mtr: " + rearRightSpeed);
        telemetry.addData("Left joystick Y (gp2): ", gamepad2.left_stick_y);
        telemetry.addData("Right joystick Y (gp2): ", gamepad2.right_stick_y);
        if (lazySusanEncoder == lazySusanEncoder.FORWARD) {
            telemetry.addLine("Lazy Susan Encoder is FORWARD");
        }

        else if (lazySusanEncoder == lazySusanEncoder.REVERSE) {
            telemetry.addLine("Lazy Susan Encoder is REVERSE");
        }

        if (lazySusanControl == lazySusanControl.MANUAL) {
            telemetry.addLine("Lazy Susan Control MANUAL");
        }

        else if (lazySusanControl == lazySusanControl.AUTO) {
            telemetry.addLine("Lazy Susan Control AUTOMATIC");
        }

        telemetry.update();

    }

    public void controlLauncher() {
        if(gamepad1.right_trigger > 0.1) {
            CandyBot.runLauncher(1.0);
        }
        if (gamepad1.left_trigger > 0.1) {
            CandyBot.stopLauncher();
        }
        if (gamepad1.left_bumper) {
            CandyBot.closeCandyDoor();
        }
        if(gamepad1.right_bumper) {
            CandyBot.openCandyDoor();
        }
    }

    public void driveMode(){

        if (gamepad1.y) {
            speedMultiply = 0.5;
        }

        else if (gamepad1.a) {
            speedMultiply = 1.0;
        }

        if (gamepad1.b) {
           if (reverseModeToggle == false) {
                reverseModeToggle = true;
            }

           else {
               reverseModeToggle = false;
           }

        }

    }

    public void controlCamPivot() {

        if(gamepad2.left_bumper) {
            CandyBot.camLeft();
        }
        else if(gamepad2.right_bumper) {
            CandyBot.camRight();
        }
        else  {
            CandyBot.camCenter();
        }
    }


    public void handControl() {
        lazySusanControl();

        if (gamepad1.dpad_up) {
            Hand.raiseArm();
        }

       else if (gamepad1.dpad_down) {
            Hand.lowerArm();
        }

       else if (gamepad1.back) {
            Hand.openHand();
        }

       else if (gamepad1.start) {
           Hand.closeHand();
        }
    }


    public void lazySusanControl() {
        if (gamepad2.x) {
            if (lazySusanControl == lazySusanControl.MANUAL) {
                lazySusanControl = lazySusanControl.AUTO;
            }

            else  {
                lazySusanControl = lazySusanControl.MANUAL;
            }
        }

        if (lazySusanControl == lazySusanControl.MANUAL) {
            if (gamepad2.right_stick_x > 0.1) {
                CandyBot.lazySusanLeft(lazySusanPower);
            }

            else if (gamepad2.right_stick_x < -0.1) {
                CandyBot.lazySusanRight(lazySusanPower);
            }

            else{
                CandyBot.lazySusanStop();
            }
        }

        else if (lazySusanControl == lazySusanControl.AUTO) {
            if (gamepad1.dpad_left) {
                lazySusanEncoder = lazySusanEncoder.FORWARD;
                CandyBot.lazy_Susan.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                CandyBot.lazy_Susan.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            }

            if (gamepad1.dpad_right) {
                lazySusanEncoder = lazySusanEncoder.REVERSE;
                CandyBot.lazy_Susan.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                CandyBot.lazy_Susan.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            }

            if (lazySusanEncoder == lazySusanEncoder.FORWARD) {
                if (Math.abs(CandyBot.lazy_Susan.getCurrentPosition()) < lazySusanTicks ){
                    CandyBot.lazy_Susan.setPower(lazySusanPower);
                }
                else {
                    CandyBot.lazy_Susan.setPower(0);
                }
            }

            else if (lazySusanEncoder == lazySusanEncoder.REVERSE) {
                if (Math.abs(CandyBot.lazy_Susan.getCurrentPosition()) < lazySusanTicks ) {
                    CandyBot.lazy_Susan.setPower(-lazySusanPower);
                }
                else {
                    CandyBot.lazy_Susan.setPower(0);
                }
            }

        }
    }





}



