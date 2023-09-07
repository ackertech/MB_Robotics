package org.firstinspires.ftc.teamcode.Sandbox;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
@TeleOp(name = "Test:One Motor+Servo", group="Lab")

public class Motor_and_Servo_Test extends OpMode {
    private DcMotor motor = null;
    public Servo servoA = null;
    double r_trigger;
    double l_trigger;
    boolean forward;

    @Override
    public void init() {
        motor = hardwareMap. dcMotor.get("motor");
        motor.setDirection(DcMotorSimple.Direction.FORWARD);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        servoA = hardwareMap.servo.get("servoA");
        servoA.setDirection(Servo.Direction.FORWARD);

        forward = true;


    }

    @Override
    public void loop() {
        if (gamepad1.a == true && forward == true) {
            motor.setPower(1);
        }
        if (gamepad1.a == true && forward == false) {
            motor.setPower(-1);
        }
        if (gamepad1.b == true){
            motor.setPower(0);
        }
        if  (gamepad1.dpad_up == true){
            forward = true;
        }
        if (gamepad1.dpad_down == true){
            forward = false;
        }
        if (gamepad1.x == true){
            servoA.setPosition(0.2);
        }
        if (gamepad1.y == true){
            servoA.setPosition(0.8);
        }
        update_telemetry();
    }

    private void update_telemetry() {
        telemetry.addLine("Running servo");
    }
    }
