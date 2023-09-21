package org.firstinspires.ftc.teamcode.Robotics_Class.Bot_BruceWayne.Connor_Class;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

@TeleOp (name = "SixWheelTeleOp_CONNOR")
public class SixWheelTeleOp_Connor extends OpMode {
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

    public double leftSidePower;
    public double rightSidePower;
    public enum DrivingMode {ONESTICK, TANK, REVERSEONESTICK, REVERSETANK}
    public DrivingMode drivingMode = DrivingMode.ONESTICK;


    public SixWheelBot_Connor sixWheelBot = new SixWheelBot_Connor();


    @Override
    public void init () {

        sixWheelBot.initRobot(hardwareMap);


    }


    public void loop() {
        speedControl();
        drivingMode();
        drive();
    }


    public void speedControl () {
        if (gamepad1.dpad_right == true) {
            speedMultiply = 0.50;}
        else if (gamepad1.dpad_down == true) {
            speedMultiply = 0.60;}
        else if (gamepad1.dpad_left == true) {
            speedMultiply = 0.75;}
        else if (gamepad1.dpad_up == true){
            speedMultiply = 0.25;}
        else if (gamepad1.a == true){
            speedMultiply = 1.00;}
    }





    public void drive() {

        switch (drivingMode) {
            case ONESTICK:


                leftStickYVal = gamepad1.left_stick_y;
                leftStickYVal = Range.clip(leftStickYVal, -1, 1);

                leftStickXVal = gamepad1.left_stick_x;
                leftStickXVal = Range.clip(leftStickXVal, -1, 1);


                if (gamepad1.left_bumper) {
                    if (leftStickYVal < -0.1) {
                        sixWheelBot.driveForward(speedMultiply * leftStickYVal);
                    } else if (leftStickYVal > 0.1) {
                        sixWheelBot.driveBack(speedMultiply * leftStickYVal);
                    } else if (leftStickXVal > 0.1) {
                        sixWheelBot.rotateRight(speedMultiply * leftStickXVal);
                    } else if (leftStickXVal < -0.1) {
                        sixWheelBot.rotateLeft(speedMultiply * leftStickXVal);
                    } else {
                        sixWheelBot.stopMotors();
                    }

                }



                break;
            case TANK:

                leftStickYVal = gamepad1.left_stick_y;
                leftStickYVal = Range.clip(leftStickYVal, -1, 1);

                rightStickYVal = gamepad1.right_stick_y;
                rightStickYVal = Range.clip(rightStickYVal, -1, 1);

                leftSidePower = speedMultiply * leftStickYVal * (-1);
                rightSidePower = speedMultiply * rightStickYVal * (-1);
                sixWheelBot.tankDrive(leftSidePower, rightSidePower);
                break;

            case REVERSEONESTICK:
                leftStickYVal = gamepad1.left_stick_y;
                leftStickYVal = Range.clip(leftStickYVal, -1, 1);

                leftStickXVal = gamepad1.left_stick_x;
                leftStickXVal = Range.clip(leftStickXVal, -1, 1);


                if (gamepad1.left_bumper) {
                    if (leftStickYVal < 0.1) {
                        sixWheelBot.driveForward(speedMultiply * leftStickYVal);
                    } else if (leftStickYVal > -0.1) {
                        sixWheelBot.driveBack(speedMultiply * leftStickYVal);
                    } else if (leftStickXVal > -0.1) {
                        sixWheelBot.rotateRight(speedMultiply * leftStickXVal);
                    } else if (leftStickXVal < 0.1) {
                        sixWheelBot.rotateLeft(speedMultiply * leftStickXVal);
                    } else {
                        sixWheelBot.stopMotors();
                    }

                }
                break;
            case REVERSETANK:
                leftStickYVal = gamepad1.left_stick_y;
                leftStickYVal = Range.clip(leftStickYVal, -1, 1);

                rightStickYVal = gamepad1.right_stick_y;
                rightStickYVal = Range.clip(rightStickYVal, -1, 1);

                leftSidePower = speedMultiply * leftStickYVal * (1);
                rightSidePower = speedMultiply * rightStickYVal * (1);
                sixWheelBot.tankDrive(leftSidePower, rightSidePower);

        }
    }

    public void drivingMode() {

        if (gamepad1.a) {
            drivingMode = DrivingMode.ONESTICK;
        }

        else if (gamepad1.b) {
            drivingMode= DrivingMode.TANK;
        }

        else if (gamepad1.x) {
            drivingMode = DrivingMode.REVERSETANK;
        }

        else if (gamepad1.y) {
            drivingMode = DrivingMode.REVERSEONESTICK;
        }
    }
}
