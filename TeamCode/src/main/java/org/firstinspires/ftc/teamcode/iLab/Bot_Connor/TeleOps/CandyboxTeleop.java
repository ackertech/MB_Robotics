package org.firstinspires.ftc.teamcode.iLab.Bot_Connor.TeleOps;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.iLab.Bot_Connor.Robots.TankBot_Connor;
import org.firstinspires.ftc.teamcode.iLab.Bot_Connor.Robots.The_Mighty_and_All_Powerful_Hand;

@Disabled
@TeleOp(name = "CandyBoxTeleOpConnor")
public class CandyboxTeleop extends OpMode {

    public double leftSidePower;
    public double rightSidePower;
    double leftStickYVal;
    double leftStickXVal;
    double rightStickYVal;
    double rightStickXVal;

    public enum ArmControl {AUTO, MANUAL}

    public Tank_TeleOp_Connor.ArmControl armControl = Tank_TeleOp_Connor.ArmControl.MANUAL;

    public enum LazySusanControl {AUTO, MANUAL}

    public Tank_TeleOp_Connor.LazySusanControl lazySusanControl = Tank_TeleOp_Connor.LazySusanControl.MANUAL;

    public enum LazySusanEncoder {FORWARD, REVERSE, OFF}

    public Tank_TeleOp_Connor.LazySusanEncoder lazySusanEncoder = Tank_TeleOp_Connor.LazySusanEncoder.OFF;
    public double lazySusanTicks = 5000;
    public double lazySusanPower = 0.90;


    public TankBot_Connor Thomas_The_Tank = new TankBot_Connor();
    public The_Mighty_and_All_Powerful_Hand Hand = new The_Mighty_and_All_Powerful_Hand();


    public void init() {
        Thomas_The_Tank.initRobot(hardwareMap);
        Hand.initThe_Mighty_and_All_Powerful_Hand(hardwareMap);
    }

    public void loop() {
        lazySusanControl();
        CandyBoxControls();
        telemetryOutput();
    }

    public void lazySusanControl() {
        if (gamepad2.x) {
            if (lazySusanControl == lazySusanControl.MANUAL) {
                lazySusanControl = lazySusanControl.AUTO;
            } else {
                lazySusanControl = lazySusanControl.MANUAL;
            }
        }

        if (lazySusanControl == lazySusanControl.AUTO) {
            lazySusanControl = lazySusanControl.MANUAL;
        }

        if (lazySusanControl == lazySusanControl.MANUAL) {
            if (gamepad2.right_stick_x > 0.1) {
                Thomas_The_Tank.lazySusanLeft(lazySusanPower);
            } else if (gamepad2.right_stick_x < -0.1) {
                Thomas_The_Tank.lazySusanRight(lazySusanPower);
            } else {
                Thomas_The_Tank.lazySusanStop();
            }
        }

    }

    public void CandyBoxControls() {
        lazySusanControl();

        if (gamepad2.left_trigger > 0.1) {
            Hand.raiseArm();
        }

        else if (gamepad2.right_trigger > 0.1) {
            Hand.lowerArm();
        }

        else if (gamepad2.left_bumper) {
            Hand.openHand();
        }

        else if (gamepad2.right_bumper) {
            Hand.closeHand();
        }

        else if (gamepad2.dpad_left) {
            Hand.wristLeft();
        }

        else if (gamepad2.dpad_right) {
            Hand.wristRight();
        }

        else if (gamepad2.dpad_up) {
            Hand.wristMiddle();
        }

    }

    public void telemetryOutput() {
        telemetry.addLine("Welcome To Titan's Candy Box!");
        telemetry.addLine("When you press the left trigger, the arm is raised");
        telemetry.addLine("When you press the right trigger, the arm is lowered");
        telemetry.addLine("When you press the left bumper, the hand opens");
        telemetry.addLine("When you press the right bumper, the hand closes");
        telemetry.addLine("When you press the left dpad button, the wrist turns to the left");
        telemetry.addLine("When you press the right dpad button, the wrist turns to the right");
        telemetry.addLine("When you press the up dpad button, the wrist turns to the middle");
        telemetry.addLine("WHen you move the right stick left and right, the base of the arm moves.");
        telemetry.addLine("When you press and hold the A button, nothing happens, so just DO NOT PRESS IT!!! ");
        telemetry.addLine("We hope you have fun! -Audrey Hunt, Olivia Tindall, Connor Reddington, and Titan the honeybee");
        if (gamepad2.a) {
            telemetry.addLine("See, we told you nothing would happen. Well, this message popped up on your screen, but other than that nothing happened.");
        }
        telemetry.update();
    }

}
