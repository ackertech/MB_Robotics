package org.firstinspires.ftc.teamcode.Robotics_Class.Bot_BruceWayne.Connor_Class;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

@TeleOp (name = "Bruce Wayne", group = "iLab")
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

    public double lazySusanTicks = 5000;
    public double lazySusanPower = 0.90;

    public double leftSidePower;
    public double rightSidePower;

    public enum DrivingMode {ONESTICK, TANK, REVERSEONESTICK, REVERSETANK}

    public DrivingMode drivingMode = DrivingMode.TANK;


    //  DcMotorEx spinnerIntakeR;


    public SixWheelBot_Connor sixWheelBot = new SixWheelBot_Connor();


    @Override
    public void init() {


        sixWheelBot.initRobot(hardwareMap);

        speedMultiply = 1;


        sixWheelBot.isLauncherOn = false;

        sixWheelBot.flywheel.setVelocityPIDFCoefficients(0, 0, 0, 0);


//
//
        //     sixWheelBot.rackgear.setPosition(1);


    }


    public void loop() {
        telemetry();
        speedControl();
        drivingMode();
        intakeControl();
        launcher();
        servoControl();
        linearSLideControl();
        //  candyLauncher();
        // lazySusan();
        //linearActuator();
        drive();
    }


    public void speedControl() {
//        if (gamepad1.dpad_right == true) {
//            speedMultiply = 0.50;
//        } else if (gamepad1.dpad_down == true) {
//            speedMultiply = 0.60;
//        } else if (gamepad1.dpad_left == true) {
//            speedMultiply = 0.75;
//        } else if (gamepad1.dpad_up == true) {
//            speedMultiply = 0.25;
//        }

        if (gamepad1.left_bumper) {
            speedMultiply = 0.3;
        } else if (gamepad2.right_bumper) {
            speedMultiply = 1;
        }
//        else if (gamepad1.a == true){
//            speedMultiply = 1.00;}
    }


    public void drive() {

        switch (drivingMode) {
            case ONESTICK:


                leftStickYVal = gamepad1.left_stick_y;
                leftStickYVal = Range.clip(leftStickYVal, -1, 1);

                leftStickXVal = gamepad1.left_stick_x;
                leftStickXVal = Range.clip(leftStickXVal, -1, 1);

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


                break;
            case TANK:

                leftStickYVal = gamepad1.left_stick_y;
                leftStickYVal = Range.clip(leftStickYVal, -1, 1);

                rightStickYVal = gamepad1.right_stick_y;
                rightStickYVal = Range.clip(rightStickYVal, -1, 1);

                leftSidePower = speedMultiply * leftStickYVal * (1);
                rightSidePower = speedMultiply * rightStickYVal * (1);
                sixWheelBot.tankDrive(leftSidePower, rightSidePower);
                break;

        }
    }


//     public void lazySusan(){
//         if (gamepad1.right_stick_x > 0.1) {
//             sixWheelBot.lazySusanLeft(lazySusanPower);
//         }
//
//         else if (gamepad1.right_stick_x < -0.1) {
//             sixWheelBot.lazySusanRight(lazySusanPower);
//         }
//
//         else{
//             sixWheelBot.lazySusanStop();
//         }
//     }

    public void intakeControl() {
        if (gamepad2.right_trigger > 0.2 && gamepad2.right_trigger < 0.55) {
            sixWheelBot.intakeHalfPower();
        } else if (gamepad2.right_trigger >= 0.6) {
            sixWheelBot.intakeFullSpeed();
        } else if (gamepad2.left_trigger > 0.2 && gamepad2.left_trigger < 0.55) {
            sixWheelBot.outtakeHalfPower();
        } else if (gamepad2.left_trigger >= 0.6) {
            sixWheelBot.outtakeFullSpeed();
        } else {
            sixWheelBot.intakeStop();
        }

    }

    public void servoControl() {
        if (gamepad2.dpad_up) {
            sixWheelBot.platformLaunchPosition();
        } else if (gamepad2.dpad_down) {
            sixWheelBot.platformIntakePosition();
        }

        if (gamepad2.right_bumper) {
            sixWheelBot.catapult.setPosition(0.5);
        } else if (gamepad2.left_bumper) {
            sixWheelBot.catapult.setPosition(0);
        }

        if (gamepad2.dpad_left) {
            sixWheelBot.ballonPopper.setPosition(0.45);
        } else if (gamepad2.dpad_right) {
            sixWheelBot.ballonPopper.setPosition(0);
        }
    }

    public void linearSLideControl() {
        leftStickYVal = gamepad2.left_stick_y;
        leftStickYVal = Range.clip(leftStickYVal, -1, 1);

        if (leftStickYVal > 0.1) {
            sixWheelBot.linearSlideUp(1);
        } else if (leftStickYVal < -0.1) {
            sixWheelBot.linearSlideDown(1);
        } else {
            sixWheelBot.linearSlideStop();
        }
    }


    public void launcher() {
        if (gamepad2.y) {

            sixWheelBot.flywheel.setPower(1);
        } else if (gamepad2.a) {

            sixWheelBot.flywheel.setPower(0);
//            gamepad1.rumble(1500);
//            gamepad1.stopRumble();
        }





        if (gamepad2.left_bumper) {
            sixWheelBot.discPusherArm.setPosition(1);

        }

        if (gamepad2.right_bumper) {
            sixWheelBot.discPusherArm.setPosition(0);
        }
    }








    public void drivingMode() {

        if (gamepad1.x) {
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

    public void telemetry(){
        telemetry.addLine("LONG LIVE TACO");
        telemetry.addData("FL pwr", frontLeftSpeed);
        telemetry.addData("FR pwr", frontRightSpeed);
        telemetry.addData("RL pwr", rearLeftSpeed);
        telemetry.addData("RR pwr", rearRightSpeed);

        if (drivingMode == DrivingMode.ONESTICK) {
            telemetry.addLine("ONESTICK DRIVE");
        }

        else if (drivingMode == DrivingMode.TANK) {
            telemetry.addLine("TANK DRIVE");
        }

        else if (drivingMode == DrivingMode.REVERSETANK) {
            telemetry.addLine("REVERSE TANK DRIVE");
        }

        else if (drivingMode == DrivingMode.REVERSEONESTICK) {
            telemetry.addLine("REVERSE ONESTICK DRIVE");
        }

      //  telemetry.addData("Platform Lift Encoder",sixWheelBot.linearActuator.getCurrentPosition());
        telemetry.addData("Flywheel Velo",sixWheelBot.flywheel.getVelocity());
        telemetry.addData("Linear SLide Motor A Position:",sixWheelBot.linearSlideMotorA.getCurrentPosition());
        telemetry.addData("LinearSlide Motor A Current:",sixWheelBot.linearSlideMotorA.getCurrent(CurrentUnit.MILLIAMPS));

      //  telemetry.addLine(String.format("Voltage: %.1f", sixWheelBot.voltageSensor.getVoltage()));


        telemetry.update();
    }
}
