package org.firstinspires.ftc.teamcode.Robotics_Class.Bot_BruceWayne.Connor_Class;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

@TeleOp (name = "Christmas: Bruce Wayne", group = "iLab")
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

    DcMotor spinnerIntakeL;
    DcMotor spinnerIntakeR;

    boolean isLauncherOn = false;


    public SixWheelBot_Connor sixWheelBot = new SixWheelBot_Connor();


    @Override
    public void init () {

        sixWheelBot.initRobot(hardwareMap);

        spinnerIntakeL = hardwareMap.dcMotor.get("candy_launcher_left");
        spinnerIntakeR = hardwareMap.dcMotor.get("candy_launcher_right");
        spinnerIntakeL.setDirection(DcMotorSimple.Direction.FORWARD);
        spinnerIntakeR.setDirection(DcMotorSimple.Direction.REVERSE);
        spinnerIntakeL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        spinnerIntakeR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        sixWheelBot.rackgear.setPosition(1);


    }


    public void loop() {
        telemetry();
        speedControl();
        drivingMode();
        candyLauncher();
        lazySusan();
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
//        else if (gamepad1.a == true){
//            speedMultiply = 1.00;}
    }





    public void drive() {

        switch (drivingMode) {
            case ONESTICK:


//                leftStickYVal = gamepad1.left_stick_y;
//                leftStickYVal = Range.clip(leftStickYVal, -1, 1);
//
//                leftStickXVal = gamepad1.left_stick_x;
//                leftStickXVal = Range.clip(leftStickXVal, -1, 1);
//
//
//
//                    if (leftStickYVal < -0.1) {
//                        sixWheelBot.driveForward(speedMultiply * leftStickYVal);
//                    } else if (leftStickYVal > 0.1) {
//                        sixWheelBot.driveBack(speedMultiply * leftStickYVal);
//                    } else if (leftStickXVal > 0.1) {
//                        sixWheelBot.rotateRight(speedMultiply * leftStickXVal);
//                    } else if (leftStickXVal < -0.1) {
//                        sixWheelBot.rotateLeft(speedMultiply * leftStickXVal);
//                    } else {
//                        sixWheelBot.stopMotors();
//                    }




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



     public void lazySusan(){
         if (gamepad1.right_stick_x > 0.1) {
             sixWheelBot.lazySusanLeft(lazySusanPower);
         }

         else if (gamepad1.right_stick_x < -0.1) {
             sixWheelBot.lazySusanRight(lazySusanPower);
         }

         else{
             sixWheelBot.lazySusanStop();
         }
     }

     public void candyLauncher(){
//        if (gamepad1.a) {
//            if (isLauncherOn == false) {
//                isLauncherOn = true;
//            }
//
//            else {
//                isLauncherOn = false;
//            }
//        }
//        if (isLauncherOn == false ){
//            sixWheelBot.launcherOff();
//        }
//        else if (isLauncherOn == true) {
//            sixWheelBot.launcherOn();
//        }

//         if (gamepad2.right_trigger > .1) {
//             sixWheelBot.candyLauncherRight.setPower(gamepad1.right_trigger);
//             sixWheelBot.candyLauncherLeft.setPower(gamepad1.right_trigger);
//         }
//         else if (gamepad2.left_trigger > .1) {
//             sixWheelBot.candyLauncherRight.setPower(-gamepad1.left_trigger);
//             sixWheelBot.candyLauncherLeft.setPower(-gamepad1.left_trigger);
//         }
//         else {
//             sixWheelBot.candyLauncherRight.setPower(0);
//             sixWheelBot.candyLauncherLeft.setPower(0);
//         }

//         if (gamepad1.a) {
//             sixWheelBot.candyLauncherRight.setPower(-.7);
//             sixWheelBot.candyLauncherLeft.setPower(-.7);
//         }
//         else if (gamepad2.y) {
//             sixWheelBot.candyLauncherRight.setPower(0);
//             sixWheelBot.candyLauncherLeft.setPower(0);
//         }


//         if (gamepad2.right_trigger > .1) {
//             spinnerIntakeL.setPower(-gamepad1.left_trigger);
//             spinnerIntakeR.setPower(-gamepad1.left_trigger);
//         }
//         else {
//             spinnerIntakeL.setPower(0);
//             spinnerIntakeR.setPower(0);
//         }

         if (gamepad1.a) {
             spinnerIntakeL.setPower(-.55);
             spinnerIntakeR.setPower(-.55);
         }
         else if (gamepad1.y) {
             spinnerIntakeL.setPower(-1);
             spinnerIntakeR.setPower(-1);
         }
         else {
             spinnerIntakeL.setPower(0);
             spinnerIntakeR.setPower(0);
         }

         if (gamepad1.left_bumper) {
             sixWheelBot.rackgear.setPosition(1.0);
         }
         else if (gamepad1.right_bumper) {
             sixWheelBot.rackgear.setPosition(0.1);
         }//left is 1

     }




    public void drivingMode() {

        if (gamepad1.x) {
            drivingMode = DrivingMode.ONESTICK;
        }

        else if (gamepad1.b) {
            drivingMode= DrivingMode.TANK;
        }

//        else if (gamepad1.x) {
//            drivingMode = DrivingMode.REVERSETANK;
//        }
//
//        else if (gamepad1.y) {
//            drivingMode = DrivingMode.REVERSEONESTICK;
//        }
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

        telemetry.update();
    }
}
