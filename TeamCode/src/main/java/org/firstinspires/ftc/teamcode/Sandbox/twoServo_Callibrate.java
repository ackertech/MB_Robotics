package org.firstinspires.ftc.teamcode.Sandbox;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

//@Disabled
@TeleOp(name = "Test:Two Servos",group="Lab")


public class twoServo_Callibrate extends OpMode {

    private Servo servo1 = null;
    private double servo1Pos = 0.4;
    private double incVal = 0.001;
    private Servo servo2 = null;
    private double servo2Pos = 0.6;


    @Override
    public void init () {
        servo1 = hardwareMap.servo.get("servo_1");
        servo1.setPosition(servo1Pos);
        servo2 = hardwareMap.servo.get("servo_2");
        servo2.setPosition((servo2Pos));
    }

    @Override
    public void loop () {
        if (gamepad1.right_bumper) {
            servo1Pos += incVal;
            servo1Pos = Range.clip(servo1Pos,0,1);
            telemetry.addLine("Increase Servo Pos!");
        }

        if (gamepad1.left_bumper){
            servo1Pos -= incVal;
            servo1Pos = Range.clip(servo1Pos, 0,  1);
            telemetry.addLine( "Decrease Servo Pos!");
        }
        if (gamepad1.x) {
            servo1.setPosition(.90);
        }

        if (gamepad1.y) {
            servo1.setPosition(.10);
        }


        servo1.setPosition(servo1Pos);

        if (gamepad2.right_bumper) {
            servo2Pos += incVal;
            servo2Pos = Range.clip(servo2Pos,0,1);
            telemetry.addLine("Increase Servo Pos!");
        }

        if (gamepad2.left_bumper){
            servo2Pos -= incVal;
            servo2Pos = Range.clip(servo2Pos, 0,  1);
            telemetry.addLine( "Decrease Servo Pos!");
        }

        if (gamepad2.x) {
            servo2.setPosition(.90);
        }

        if (gamepad2.y) {
            servo2.setPosition(.10);
        }


        servo2.setPosition(servo2Pos);
        updateTelemetry();
    }


    public void updateTelemetry () {
        telemetry.addLine("RB: increase, LB: Decrease");
        telemetry.addLine("x = set to .90, y = set to 0.10");
        telemetry.addData("Grabber Left Arm Position:", servo1.getPosition());
        telemetry.addData("Grabber Left Arm Position:", servo1Pos);
        telemetry.addData("Grabber Right Arm Position", servo2.getPosition());
        telemetry.addData("Grabber Right Arm Position", servo2Pos);
        telemetry.update();
    }
}
