package org.firstinspires.ftc.teamcode.Sandbox;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
@Disabled
@TeleOp(name = "Test:Servo Calibration", group = "Lab")

public class SingleServo_Callibrate extends OpMode {

    private Servo myServo = null;
    private double servoPos = 0.5;
    private double incVal = 0.001;

    @Override
    public void init () {
        myServo = hardwareMap.servo.get("servo");
        myServo.setPosition(servoPos);
    }

    @Override
    public void loop () {
        if (gamepad1.right_bumper) {
            servoPos += incVal;
            servoPos = Range.clip(servoPos,0,1);
            telemetry.addLine("Increase Servo Pos!");
        }

        if (gamepad1.left_bumper){
            servoPos -= incVal;
            servoPos = Range.clip(servoPos, 0,  1);
            telemetry.addLine( "Decrease Servo Pos!");
        }

        myServo.setPosition(servoPos);
        updateTelemetry();
    }

    public void updateTelemetry () {
        telemetry.addLine("RB: increase, LB: Decrease");
        telemetry.addLine("x = set to .90, y = set to 0.10");
        telemetry.addData("TestS ervo Positiom: ", myServo.getPosition());
        telemetry.addData("Servo Variable Position: ", servoPos);
        telemetry.update();
    }
}


//mag arm down = 0
// mag up = 0.171