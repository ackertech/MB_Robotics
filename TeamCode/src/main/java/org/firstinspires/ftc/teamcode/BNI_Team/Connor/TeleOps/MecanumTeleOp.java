//package org.firstinspires.ftc.teamcode.BNI_Team.Connor.TeleOps;
//
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.util.Range;
//
//import org.firstinspires.ftc.teamcode.BNI_Team.Connor.Robots.ProgramingBot;
//
//
//@TeleOp (name = "MecanumTeleOp_Connor",group="iLab")
//public class MecanumTeleOp extends OpMode {
//
//    double leftStickYVal;
//    double leftStickXVal;
//    double rightStickXVal;
//    double rightStickYVal;
//
//    double frontLeftSpeed;
//    double frontRightSpeed;
//    double rearLeftSpeed;
//    double rearRightSpeed;
//
//    double powerThreshold = 0;
//    double speedMultiply = 1;
//
//
//
//    public ProgrammingBot MecanumBot = new ProgramingBot();
//
//    public void init () {
//        MecanumBot.initRobot(hardwareMap);
//    }
//    public void init_loop() {  }
//
//
//    public void start() {
//
//    }
//
//    public void loop(){
//        speedControl();
//        drive();
//        telemetryOutput();
//    }
//
//    public void drive() {
//
//        leftStickYVal = gamepad1.left_stick_y;
//        leftStickYVal = Range.clip(leftStickYVal, -1, 1);
//        leftStickXVal = -gamepad1.left_stick_x;
//        leftStickXVal = Range.clip(leftStickXVal, -1, 1);
//        rightStickXVal = -gamepad1.right_stick_x;
//        rightStickXVal = Range.clip(rightStickXVal, -1, 1);
//
//        frontLeftSpeed = leftStickYVal + leftStickXVal + rightStickXVal;
//        frontLeftSpeed = Range.clip(frontLeftSpeed, -1, 1);
//
//        frontRightSpeed = leftStickYVal - leftStickXVal - rightStickXVal;
//        frontRightSpeed = Range.clip(frontRightSpeed, -1, 1);
//
//        rearLeftSpeed = leftStickYVal - leftStickXVal + rightStickXVal;
//        rearLeftSpeed = Range.clip(rearLeftSpeed, -1, 1);
//
//        rearRightSpeed = leftStickYVal + leftStickXVal - rightStickXVal;
//        rearRightSpeed = Range.clip(rearRightSpeed, -1, 1);
//
//        if (frontLeftSpeed <= powerThreshold && frontLeftSpeed >= -powerThreshold) {
//            frontLeftSpeed = 0;
//            MecanumBot.frontLeftMotor.setPower(frontLeftSpeed);
//        } else {
//            MecanumBot.frontLeftMotor.setPower(frontLeftSpeed * speedMultiply);
//        }
//
//        if (frontRightSpeed <= powerThreshold && frontRightSpeed >= -powerThreshold){
//            frontRightSpeed = 0;
//            MecanumBot.frontRightMotor.setPower(frontRightSpeed);
//        } else {
//            MecanumBot.frontRightMotor.setPower(frontRightSpeed * speedMultiply);
//        }
//
//        if (rearLeftSpeed <= powerThreshold && rearLeftSpeed >= -powerThreshold) {
//            rearLeftSpeed = 0;
//            MecanumBot.rearLeftMotor.setPower(rearLeftSpeed);
//        } else {
//            MecanumBot.rearLeftMotor.setPower(rearLeftSpeed * speedMultiply);
//        }
//
//        if (rearRightSpeed <= powerThreshold && rearRightSpeed >= -powerThreshold){
//            rearRightSpeed = 0;
//            MecanumBot.rearRightMotor.setPower(rearRightSpeed);
//        } else {
//            MecanumBot.rearRightMotor.setPower(rearRightSpeed * speedMultiply);
//        }
//    }
//    public void telemetryOutput() {
//
//        telemetry.addLine("LONG LIVE TACO");
//        telemetry.addData("pwr", "FL mtr: " + frontLeftSpeed);
//        telemetry.addData("pwr", "FR mtr: " + frontRightSpeed);
//        telemetry.addData("pwr", "RL mtr: " + rearLeftSpeed);
//        telemetry.addData("pwr", "RR mtr: " + rearRightSpeed);
//        telemetry.update();
//    }
//
//    public void speedControl() {
//
//        if (gamepad1.dpad_up) {
//            speedMultiply = 0.5;
//        }
//
//        else if (gamepad1.dpad_down) {
//            speedMultiply = 1;
//        }
//    }
//
//}
