package org.firstinspires.ftc.teamcode.Base.Controls.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Base.Mechanisms.RoboticHand;
import org.firstinspires.ftc.teamcode.Base.Robot.AckerBot;


@Disabled
@TeleOp(name = "AckerBot", group="iLab")

public class AckerBotTeleOp extends OpMode {


    // Variables & Constants specific to TeleLabBot
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
    public double lazySusanPower = 0.90;
    boolean reverseModeToggle = false;

    //Arm & Elbow Variables
    public enum GateState {ARM_START, ARM_RAISE, ARM_REST, ARM_RETRACT}
    GateState armState = GateState.ARM_START;
    public enum ArmControl {AUTO, MANUAL}
    public ArmControl armControl = ArmControl.AUTO;

    // Object Construction
    public ElapsedTime TeleOpTime = new ElapsedTime();
    public AckerBot Bot = new AckerBot();
    public RoboticHand Handy = new RoboticHand();


    // Runs ONCE when driver presses INIT
    @Override
    public void init() {

        //Hardware Initialization from Robot Class

        Bot.initRobot(hardwareMap);
        Handy.initRoboticHand(hardwareMap);
        Handy.closePartial();


    }


    // Runs Repeatedly when driver presses INIT but before pressing PLAY
    @Override
    public void init_loop() { }

    // Runs ONCE when driver presses PLAY
    @Override
    public void start() { }


    // RUNS Repeatedly after driver presses PLAY
    @Override
    public void loop() {

        drive();
        driveMode();
        controlLauncher();
        controlHand();
        controlElbow();
        controlLazySusan();
        telemetryOutput();
    }

    // Code to run ONCE after the driver presses STOP
    @Override
    public void stop() {

     // Bot.openHand();

    }

    // TeleOp Functions

    public void telemetryOutput() {
        telemetry.addData("LED", "TeleOp Time: " + Bot.currentTime);
        telemetry.addData("LED", "LED Timer: " + Bot.ledTimer);
        telemetry.addData("LED", "LED Counter: " + Bot.ledCounter);
        telemetry.addData("LED", "LED Pattern: " + Bot.ledPattern);
        telemetry.addData("LED", "LED Pattern: " + Bot.ledLights);
        telemetry.addData("Elbow", "Elbow: " + Handy.elbowCurrPos);
        telemetry.addData("pwr", "FL mtr: " + frontLeftSpeed);
        telemetry.addData("pwr", "FR mtr: " + frontRightSpeed);
        telemetry.addData("pwr", "RL mtr: " + rearLeftSpeed);
        telemetry.addData("pwr", "RR mtr: " + rearRightSpeed);
        telemetry.addData("Left joystick Y (gp2): ", gamepad2.left_stick_y);
        telemetry.addData("Right joystick Y (gp2): ", gamepad2.right_stick_y);
        telemetry.update();

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
                Bot.frontLeftMotor.setPower(frontLeftSpeed);
            } else {
                Bot.frontLeftMotor.setPower(frontLeftSpeed * speedMultiply);
            }

            if (frontRightSpeed <= powerThreshold && frontRightSpeed >= -powerThreshold){
                frontRightSpeed = 0;
                Bot.frontRightMotor.setPower(frontRightSpeed);
            } else {
                Bot.frontRightMotor.setPower(frontRightSpeed * speedMultiply);
            }

            if (rearLeftSpeed <= powerThreshold && rearLeftSpeed >= -powerThreshold) {
                rearLeftSpeed = 0;
                Bot.rearLeftMotor.setPower(rearLeftSpeed);
            } else {
                Bot.rearLeftMotor.setPower(rearLeftSpeed * speedMultiply);
            }

            if (rearRightSpeed <= powerThreshold && rearRightSpeed >= -powerThreshold){
                rearRightSpeed = 0;
                Bot.rearRightMotor.setPower(rearRightSpeed);
            } else {
                Bot.rearRightMotor.setPower(rearRightSpeed * speedMultiply);
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
                Bot.frontLeftMotor.setPower(frontLeftSpeed);
            } else {
                Bot.frontLeftMotor.setPower(frontLeftSpeed * speedMultiply);
            }

            if (frontRightSpeed <= powerThreshold && frontRightSpeed >= -powerThreshold){
                frontRightSpeed = 0;
                Bot.frontRightMotor.setPower(frontRightSpeed);
            } else {
                Bot.frontRightMotor.setPower(frontRightSpeed * speedMultiply);
            }

            if (rearLeftSpeed <= powerThreshold && rearLeftSpeed >= -powerThreshold) {
                rearLeftSpeed = 0;
                Bot.rearLeftMotor.setPower(rearLeftSpeed);
            } else {
                Bot.rearLeftMotor.setPower(rearLeftSpeed * speedMultiply);
            }

            if (rearRightSpeed <= powerThreshold && rearRightSpeed >= -powerThreshold){
                rearRightSpeed = 0;
                Bot.rearRightMotor.setPower(rearRightSpeed);
            } else {
                Bot.rearRightMotor.setPower(rearRightSpeed * speedMultiply);
            }
        }

    }

    public void driveMode () {
        if (gamepad1.dpad_up) {
            speedMultiply = 1.0;
        }
        else if (gamepad1.dpad_down) {
            speedMultiply = 0.5;
        }

        if (gamepad1.dpad_right) {
            reverseModeToggle = true;
        }
        if (gamepad1.dpad_left) {
            reverseModeToggle = false;
        }

    }

    public void controlLauncher() {
        if(gamepad1.y) {
            Bot.runLauncher(1.0);
        }
        if (gamepad1.b) {
            Bot.stopLauncher();
        }
        if (gamepad1.x) {
            Bot.openTrapDoor();
        }
        if(gamepad1.a) {
            Bot.closeTrapDoor();
        }
    }



    public void controlHand() {

        if (gamepad2.a) {
            Handy.wristLeft();
        }
        else if (gamepad2.b) {
            Handy.wristMiddle();
        }
        else if (gamepad2.x) {
            Handy.wristRight();
        }

    }

    public void controlElbow() {

        if (gamepad2.dpad_left) {
            armControl = ArmControl.AUTO;
        }
        if (gamepad2.dpad_right) {
            armControl = ArmControl.MANUAL;
        }

        if (armControl == ArmControl.AUTO) {

            switch (armState) {
                case ARM_START:
                    if (gamepad2.dpad_up) {
                        Handy.elbow.setPosition(Handy.elbowMaxPos);
                        armState = GateState.ARM_RAISE;
                    }
                    break;
                case ARM_RAISE:
                    Handy.closePartial();
                    armState = GateState.ARM_REST;

                    break;
                case ARM_REST:
                    if (gamepad2.dpad_down) {
                        Handy.closePartial();;
                        Handy.elbow.setPosition(Handy.elbowMinPos);
                        armState = GateState.ARM_RETRACT;
                    }
                    break;
                case ARM_RETRACT:
                    Handy.closePartial();
                    armState = GateState.ARM_START;

                    break;
                default:
                    armState = GateState.ARM_START;
            }

        }
        else if (armControl == ArmControl.MANUAL) {

            if (gamepad2.dpad_up  && Handy.elbowCurrPos < Handy.elbowMaxPos) {
                Handy.elbowCurrPos += Handy.elbowIncrements;
                Handy.elbow.setPosition(Handy.elbowCurrPos);
            }
            else {
                Handy.elbow.setPosition(Handy.elbowCurrPos);
            }

            if (gamepad2.dpad_down  && Handy.elbowCurrPos > Handy.elbowMinPos) {
                Handy.elbowCurrPos -= Handy.elbowIncrements;
                Handy.elbow.setPosition(Handy.elbowCurrPos);

            }
            else {
                Handy.elbow.setPosition(Handy.elbowCurrPos);
            }

        }


    }

    public void controlLazySusan() {
        if (gamepad2.right_stick_x > 0.1) {
            Handy.lazySusanLeft(lazySusanPower);
        } else if (gamepad2.right_stick_x < -0.1) {
            Handy.lazySusanRight(lazySusanPower);
        } else {
            Handy.lazySusanStop();
        }
    }

}