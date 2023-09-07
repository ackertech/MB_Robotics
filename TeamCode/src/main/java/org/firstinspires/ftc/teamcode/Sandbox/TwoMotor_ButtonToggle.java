package org.firstinspires.ftc.teamcode.Sandbox;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

//Class is used for testing a single motor, using trigger for variable power.
@Disabled
@TeleOp(name = "Test:Two Motors:No Encoders", group="Lab")

public class TwoMotor_ButtonToggle extends OpMode {
    private DcMotor motor_left = null;
    private DcMotor motor_right = null;
    double r_trigger;
    double l_trigger;

    boolean forward = true;

    boolean toggleLaunch = false;

    double speed = 0.6;

    @Override
    public void init() {
        motor_left = hardwareMap.dcMotor.get("motor_1");
        motor_left.setDirection(DcMotorSimple.Direction.FORWARD);
        motor_left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor_left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        motor_right = hardwareMap.dcMotor.get("motor_2");
        motor_right.setDirection(DcMotorSimple.Direction.FORWARD);
        motor_right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor_right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        forward = true;

        telemetry.addLine("Press 'a' to engage motor.");
        telemetry.addLine("Press 'b' to stop motor.");
        telemetry.addLine("Press d-pad up for motor to go forward");
        telemetry.addLine("Press d-down up for motor to go reverse");
        telemetry.update();
    }


    @Override
    public void loop() {
        if (toggleLaunch && forward == true) {
            motor_left.setPower(speed);
            motor_right.setPower(speed);
        }
// 1)
//        Make motor go reverse? Valid values of motors are [-1, +1]
        if(toggleLaunch && forward == false){
            motor_left.setPower(-speed);
            motor_right.setPower(-speed);
        }

        if (gamepad1.a == true) {
            toggleLaunch = true;
        }

        if(gamepad1.b == true){
            motor_left.setPower(0);
            motor_right.setPower(0);
        }


        //2) Stop motor if press b


        if (gamepad1.dpad_up == true) {
            forward = true;
        }

        if (gamepad1.dpad_down == true) {
            forward = false;
        }

        update_telemetry();
    }

    public void update_telemetry () {
        telemetry.addData("Forward mode? ", forward);
        telemetry.addData("A being pressed? ", gamepad1.a);
        telemetry.addData("left motor power: ", motor_left.getPower());
        telemetry.addData("right motor power: ", motor_right.getPower());
    }
}
