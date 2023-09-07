package org.firstinspires.ftc.teamcode.Sandbox;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Disabled
@TeleOp(name = "Test:Single Motor Test + Color Sensor", group = "Lab")


public class SingleMotor_ColorSensor_DriverControl extends OpMode {

    private DcMotor motor = null;
    double power;
    double powerControl = 0.7;

    ColorSensor sensorColor;

    // hsvValues is an array that will hold the hue, saturation, and value information.
    float hsvValues[] = {0F, 0F, 0F};

    // values is a reference to the hsvValues array.
    final float values[] = hsvValues;

    // sometimes it helps to multiply the raw RGB values with a scale factor
    // to amplify/attentuate the measured values.
    final double SCALE_FACTOR = 255;

    final double RED_THRESHOLD_HUE = 60;
    final double BLUE_THRESHOLD_HUE = 180;



    @Override
    public void init() {
        motor = hardwareMap.dcMotor.get("motor");
        motor.setDirection(DcMotorSimple.Direction.FORWARD);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        sensorColor = hardwareMap.get(ColorSensor.class, "sensor_color_distance_1");



        telemetry.addLine("Right Trigger to go 'forward'");
        telemetry.addLine("Left Trigger to go 'reverse'");
        telemetry.update();
    }

    @Override
    public void loop() {



        Color.RGBToHSV((int) (sensorColor.red() * SCALE_FACTOR),
                (int) (sensorColor.green() * SCALE_FACTOR),
                (int) (sensorColor.blue() * SCALE_FACTOR),
                hsvValues);

        // OPENS LINEAR
        if (gamepad1.right_trigger > 0.1 && hsvValues[0] > RED_THRESHOLD_HUE) {
            power = powerControl;
        }
        // CLOSES EXTENSION
        else if (gamepad1.left_trigger > 0.1 && hsvValues[0] < BLUE_THRESHOLD_HUE) {
            power = -powerControl;
        }
        else {
            power = 0;
        }

        motor.setPower(power);



        update_telemetry();
    }

    public void update_telemetry () {
        telemetry.addData("Right Trigger Value: ", gamepad1.right_trigger);
        telemetry.addData("Left Trigger Value: ", gamepad1.left_trigger);
        telemetry.addData("POWER: ", power);
        telemetry.addData("Hue", hsvValues[0]);
    }


}
