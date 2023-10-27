package org.firstinspires.ftc.teamcode.BNI_Team.Olivia.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.BNI_Team.Olivia.Bots.ProgrammingBot_Olivia;

@TeleOp (name = "MecanumTeleOp_Olivia")
public class MecanumTeleOp_Olivia extends OpMode {

    double leftStickYVal;
    double leftStickXVal;
    double rightStickYVal;
    double rightStickXVal;

    double frontLeftSpeed;
    double frontRightSpeed;
    double rearLeftSpeed;
    double rearRightSpeed;

    double speedMultiply = 1;
    double powerThreshold = 0;

    public ProgrammingBot_Olivia PBot = new ProgrammingBot_Olivia();

    public void init (){PBot.initRobot(hardwareMap);}
    public void init_loop () { }

    public void start (){}

    public void loop (){
        speedControls();
        drive();
        telemetry();
    }



    public void drive () {


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
            PBot.frontLeftMotor.setPower(frontLeftSpeed);
        } else {
            PBot.frontLeftMotor.setPower(frontLeftSpeed * speedMultiply);
        }

        if (frontRightSpeed <= powerThreshold && frontRightSpeed >= -powerThreshold){
            frontRightSpeed = 0;
            PBot.frontRightMotor.setPower(frontRightSpeed);
        } else {
            PBot.frontRightMotor.setPower(frontRightSpeed * speedMultiply);
        }

        if (rearLeftSpeed <= powerThreshold && rearLeftSpeed >= -powerThreshold) {
            rearLeftSpeed = 0;
            PBot.rearLeftMotor.setPower(rearLeftSpeed);
        } else {
            PBot.rearLeftMotor.setPower(rearLeftSpeed * speedMultiply);
        }

        if (rearRightSpeed <= powerThreshold && rearRightSpeed >= -powerThreshold){
            rearRightSpeed = 0;
            PBot.rearRightMotor.setPower(rearRightSpeed);
        } else {
            PBot.rearRightMotor.setPower(rearRightSpeed * speedMultiply);
        }
    }

    public void telemetry() {
        telemetry.addLine("Stats");
        telemetry.addData("Power - ","Front Left Motor: " + frontLeftSpeed);
        telemetry.addData("Power - ", "Front Right Motor" + frontRightSpeed);
        telemetry.addData("Power - ", "Right Left Motor: " + rearLeftSpeed);
        telemetry.addData("Power - ", "Right Right Motor: " + rearRightSpeed);
        telemetry.update();

    }

    public void speedControls() {
        if (gamepad1.dpad_left) {
            speedMultiply = 0.5;
        }
        else if (gamepad1.dpad_down) {
            speedMultiply = 0.25;
        }

        else if (gamepad1.dpad_up) {
            speedMultiply = 0.65;
        }

        else if (gamepad1.dpad_right) {
            speedMultiply = 1;
        }
    }


}
