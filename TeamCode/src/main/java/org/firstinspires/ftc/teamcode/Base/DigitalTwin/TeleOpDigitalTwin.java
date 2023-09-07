package org.firstinspires.ftc.teamcode.Base.DigitalTwin;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

@Disabled
@TeleOp(name = "Digital Twin T")
public class TeleOpDigitalTwin extends OpMode {

    double speedMultiply = 1.0;
    final float[] hsvValues = new float[3];

    public DigitalTwin DigiTwin = new DigitalTwin();

    @Override
    public void init() {

        DigiTwin.initRobot(hardwareMap);

    }

    @Override
    public void loop () {

        conveyorBeltControl();
        speedControl();
        puckSorterControl();
        colorDetectionControl();

    }

    public void conveyorBeltControl() {
        if (gamepad1.left_bumper) {
            DigiTwin.conveyorBeltMotor.setPower(speedMultiply);
        }
        if (gamepad1.right_bumper) {
            DigiTwin.conveyorBeltMotor.setPower(0.0);
        }

    }


    public void puckSorterControl() {
        if (gamepad1.a) {
            DigiTwin.sortPuck();
        }
        if (gamepad1.b) {
            DigiTwin.passPuck();
        }
        if (gamepad1.x) {
            DigiTwin.stopPuck();
        }
    }
    public void speedControl() {

        if (gamepad1.dpad_right) {
            speedMultiply = 0.40;
        }
        else if (gamepad1.dpad_down) {
            speedMultiply = 0.60;
        }
        else if (gamepad1.dpad_left) {
            speedMultiply = 0.80;
        }
        else if (gamepad1.dpad_up) {
            speedMultiply = 1.0;
        }

    }

    public void colorDetectionControl() {
        NormalizedRGBA colors = DigiTwin.colorSensor.getNormalizedColors();
        Color.colorToHSV(colors.toColor(), hsvValues);
        telemetry.addLine()
                .addData("Red", "%.3f", colors.red)
                .addData("Green", "%.3f", colors.green)
                .addData("Blue", "%.3f", colors.blue);
        telemetry.addLine()
                .addData("Hue", "%.3f", hsvValues[0])
                .addData("Saturation", "%.3f", hsvValues[1])
                .addData("Value", "%.3f", hsvValues[2]);
        telemetry.addData("Alpha", "%.3f", colors.alpha);

    }
}
