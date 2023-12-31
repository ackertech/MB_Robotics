package org.firstinspires.ftc.teamcode.Base.Controls.Testing;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Disabled
@TeleOp(name = "Test - Spinner Motor", group = "Sandbox")

public class SpinnerTwoMotorTest extends OpMode {
    DcMotor spinnerIntakeL;
    DcMotor spinnerIntakeR;
    @Override
    public void init() {
        spinnerIntakeL = hardwareMap.dcMotor.get("candy_launcher_left");
        spinnerIntakeR = hardwareMap.dcMotor.get("candy_launcher_right");
        spinnerIntakeL.setDirection(DcMotorSimple.Direction.FORWARD);
        spinnerIntakeR.setDirection(DcMotorSimple.Direction.REVERSE);
        spinnerIntakeL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        spinnerIntakeR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    @Override
    public void loop() {
        if (gamepad1.right_trigger > .1) {
            spinnerIntakeL.setPower(gamepad1.right_trigger);
            spinnerIntakeR.setPower(gamepad1.right_trigger);
        }
        else if (gamepad1.left_trigger > .1) {
            spinnerIntakeL.setPower(-gamepad1.left_trigger);
            spinnerIntakeR.setPower(-gamepad1.left_trigger);
        }
        else {
            spinnerIntakeL.setPower(0);
            spinnerIntakeR.setPower(0);
        }
    }
}
