package org.firstinspires.ftc.teamcode.Base.Controls.TeleOp;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Base.Mechanisms.AuxMechanisms;
import org.firstinspires.ftc.teamcode.Base.Robot.TankBot;

@Disabled
@TeleOp(name = "TankBot Machanisms",group="iLab")

public class TankTeleOpDriveMechanism extends OpMode {

    //TeleOp Driving Behavior Variables
    public double speedMultiply = .75;
    public enum Style {
        ARCADE1, ARCADE2, TANK
    }
    public Style driverStyle = Style.ARCADE1;
    public double leftSidePower;
    public double rightSidePower;

    // GamePad Variables
    double leftStickYVal;
    double leftStickXVal;
    double rightStickXVal;
    double rightStickYVal;


    // Construct the Physical Bot based on the Robot Class
    public TankBot Bruno = new TankBot();
    public AuxMechanisms AuxMech = new AuxMechanisms();


    // TeleOp Initialize Method.  This is the Init Button on the Driver Station Phone
    @Override
    public void init() {

        Bruno.initRobot(hardwareMap);

        // In the Init Method, add in the aditional init methods based on your motors and servos
        //Replace "AuxMech" with your robot object name
        AuxMech.initWormGear(hardwareMap);
        AuxMech.initLinearHorizontal(hardwareMap);
        AuxMech.initLinearVertical(hardwareMap);
        AuxMech.initServos(hardwareMap);

    }

    // TeleOp Loop Method.  This start AFTER clicking the Play Button on the Driver Station Phone

    public void loop() {

        speedControl();
        driveControl();
        telemetryOutput();

        // In the Loop method, add in the control methods
        wormGearControl();
        linearHorizontalControl();
        linearVerticalControl();
        servoControl();


    }

    public void wormGearControl() {

        if (gamepad2.right_trigger > 0.1)  {
            AuxMech.wormGearRotateForward(.75);
        }
        else if (gamepad2.right_trigger > 0.1) {
            AuxMech.wormGearRotateReverse(.75);
        }
        else {
            AuxMech.wornGearMotor.setPower(0);
        }
    }

    public void linearHorizontalControl() {

        if (gamepad2.left_stick_y > 0.1)  {
            AuxMech.moveLinearForward(.75);
        }
        else if (gamepad2.right_trigger > 0.1) {
            AuxMech.wormGearRotateReverse(.75);
        }
        else {
            AuxMech.wornGearMotor.setPower(0);
        }
    }

    public void linearVerticalControl() {

    }

    public void servoControl() {
        if (gamepad2.a) {
            AuxMech.rotateLeft();
        }
        if (gamepad2.b) {
            AuxMech.rotateRight();
        }
        if (gamepad2.x) {
            AuxMech.extendFully();
        }
        if (gamepad2.y) {
            AuxMech.retract();
        }
    }


    public void telemetryOutput() {
        telemetry.addData("Drive Mode: ", driverStyle);
        telemetry.addData("Speed: ", speedMultiply);
        telemetry.addData("Front Left Motor Power: ", Bruno.frontLeftMotor.getPower());
        telemetry.addData("Rear Left Motor Power: ", Bruno.rearLeftMotor.getPower());
        telemetry.addData("Front Right Motor Power: ", Bruno.frontRightMotor.getPower());
        telemetry.addData("Rear Right Motor Power: ", Bruno.rearRightMotor.getPower());
        telemetry.update();

    }

    /**  ********  DRIVING METHODS USING GAMEPAD 1 *************      **/

    public void driveControl() {

        if (gamepad1.left_bumper) {
            driverStyle = Style.ARCADE1;
        }
        if (gamepad1.right_bumper) {
            driverStyle = Style.ARCADE2;
        }
        if (gamepad1.right_stick_button) {
            driverStyle = Style.TANK;
        }

        switch (driverStyle) {

            case ARCADE1:

                leftStickYVal = gamepad1.left_stick_y;
                leftStickYVal = Range.clip(leftStickYVal, -1, 1);
                leftStickXVal = gamepad1.left_stick_x;
                leftStickXVal = Range.clip(leftStickXVal, -1, 1);

                if (leftStickYVal < -0.1) {
                    Bruno.tankDriveForward(speedMultiply*leftStickYVal);
                } else if (leftStickYVal > 0.1) {
                    Bruno.tankDriveBackward(speedMultiply*leftStickYVal);
                } else if (leftStickXVal > 0.1) {
                    Bruno.tankTurnRight(speedMultiply*leftStickXVal);
                } else if (leftStickXVal < -0.1) {
                    Bruno.tankTurnLeft(speedMultiply*leftStickXVal);
                } else {
                    Bruno.stopMotors();
                }
                break;

            case ARCADE2:

                leftStickYVal = gamepad1.left_stick_y;
                leftStickYVal = Range.clip(leftStickYVal, -1, 1);
                leftStickXVal = gamepad1.left_stick_x;
                leftStickXVal = Range.clip(leftStickXVal, -1, 1);
                rightStickYVal = gamepad1.right_stick_y;
                rightStickYVal = Range.clip(rightStickYVal, -1, 1);
                rightStickXVal = gamepad1.right_stick_x;
                rightStickXVal = Range.clip(rightStickXVal, -1, 1);

                if (leftStickYVal < -0.1) {
                    Bruno.tankDriveForward(speedMultiply*leftStickYVal);
                } else if (leftStickYVal > 0.1) {
                    Bruno.tankDriveBackward(speedMultiply*leftStickYVal);
                } else {
                    Bruno.stopMotors();
                }
                if (rightStickXVal > 0.1) {
                    Bruno.tankTurnRight(speedMultiply*rightStickXVal);
                } else if (rightStickXVal < -0.1) {
                    Bruno.tankTurnLeft(speedMultiply*rightStickXVal);
                } else {
                    Bruno.stopMotors();
                }
                break;

            case TANK:

                leftStickYVal = gamepad1.left_stick_y;
                leftStickYVal = Range.clip(leftStickYVal, -1, 1);
                rightStickYVal = gamepad1.right_stick_y;
                rightStickYVal = Range.clip(rightStickYVal, -1, 1);

                leftSidePower = speedMultiply * leftStickYVal * (-1);
                rightSidePower = speedMultiply * rightStickYVal * (-1);
                Bruno.tankDrive(leftSidePower,rightSidePower);
                break;
        }
    }



    public void speedControl () {
            if (gamepad1.dpad_right) {
                speedMultiply = 0.25;
            } else if (gamepad1.dpad_down) {
                speedMultiply = 0.50;
            } else if (gamepad1.dpad_left) {
                speedMultiply = 0.75;
            } else if (gamepad1.dpad_up) {
                speedMultiply = 1.00;
            }
    }


}
