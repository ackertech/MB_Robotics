package org.firstinspires.ftc.teamcode.Base.Controls.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.BNI_Team.Connor.Robots.The_Mighty_and_All_Powerful_Hand;
import org.firstinspires.ftc.teamcode.BNI_Team.Connor.Wall_E.WalleBot;

//@Disabled
@TeleOp(name = "Christmas: WALL-E",group="iLab")
public class WALL_E_TeleOp_Basic extends OpMode {

    public double speedMultiply = 0.50;

    public double leftSidePower;
    public double rightSidePower;

    public double linearMotorPower = 0.85;
    boolean clawOpen = false;

    public double lazySusanPower = 0.90;

    public The_Mighty_and_All_Powerful_Hand Hand = new The_Mighty_and_All_Powerful_Hand();

    double leftStickYVal;
    double leftStickXVal;
    double rightStickYVal;
    double rightStickXVal;

    public WalleBot WALL_E = new WalleBot();

    @Override
    public void init() {
        WALL_E.initRobot(hardwareMap);
    }

    public void loop() {
        dpadControl();
        stickControls();
        headControl();
        clawControl();
    }

    public void dpadControl() {

        if (gamepad1.dpad_right == true) {
            speedMultiply = 0.50;
        } else if (gamepad1.dpad_down == true) {
            speedMultiply = 0.75;
        } else if (gamepad1.dpad_left == true) {
            speedMultiply = 1.00;
        } else if (gamepad1.dpad_up == true) {
            speedMultiply = 0.25;
        }


    }

    public void stickControls() {

        leftStickYVal = gamepad1.left_stick_y;
        leftStickYVal = Range.clip(leftStickYVal, -1, 1);

        rightStickYVal = gamepad1.right_stick_y;
        rightStickYVal = Range.clip(rightStickYVal, -1, 1);

        leftSidePower = speedMultiply * leftStickYVal * (-1);
        rightSidePower = speedMultiply * rightStickYVal * (-1);
        WALL_E.tankDrive(leftSidePower, rightSidePower);

    }
        public void headControl() {
            if (gamepad1.right_stick_x < -0.1) {
                WALL_E.lazySusanLeft(lazySusanPower);
            } else if (gamepad1.right_stick_x > 0.1) {
                WALL_E.lazySusanRight(lazySusanPower);
            } else {
                WALL_E.lazySusanStop();
            }
        }


    public void clawControl() {

                if (gamepad1.right_trigger > 0.2) {
                    WALL_E.rightLinearActuatorForward(linearMotorPower);
                }
                if (gamepad1.right_bumper) {
                    WALL_E.rightLinearActuatorBack(linearMotorPower);
                } else {
                    WALL_E.rightLinearActuatorStop();
                }


                if (gamepad1.left_trigger > 0.2) {
                    WALL_E.leftLinearActuatorForward(linearMotorPower);
                } else if (gamepad1.left_bumper) {
                    WALL_E.leftLinearActuatorBack(linearMotorPower);
                } else {
                    WALL_E.leftLinearActuatorStop();
                }

                if (gamepad1.y) {
                    clawOpen = true;

                } else {
                    clawOpen = false;
                }
                if (clawOpen == true) {
                    WALL_E.leftClawOpen();
                } else if (clawOpen == false) {
                    WALL_E.leftClawClose();
                }

    }





    }


