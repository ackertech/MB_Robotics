package org.firstinspires.ftc.teamcode.Sandbox;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@Disabled
@Autonomous (name = "Test:Motor + Color Sensor", group = "Lab")


public class SingleMotor_ColorSensor_Autonomous extends LinearOpMode
{

    ColorSensor sensorColor = null;
    private DcMotor motor = null;

    double power;
    double powerControl = 0.7;

    float hsvValues[] = {0F, 0F, 0F};

    // values is a reference to the hsvValues array.
    final float values[] = hsvValues;

    // sometimes it helps to multiply the raw RGB values with a scale factor
    // to amplify/attentuate the measured values.
    final double SCALE_FACTOR = 255;

    // HUE values for the linear extension slide to know when to stop.
    final double RED_THRESHOLD_HUE = 60;
    final double BLUE_THRESHOLD_HUE = 180;

    //time to close before STOP
    // 1000 == 1 second
    final double CLOSE_TIME_THRESHOLD = 200;
    final double OPEN_TIME_THRESHOLD = 200;

    ElapsedTime timer;


    @Override
    public void runOpMode() throws InterruptedException {

        motor = hardwareMap.dcMotor.get("motor");
        motor.setDirection(DcMotorSimple.Direction.FORWARD);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        sensorColor = hardwareMap.get(ColorSensor.class, "sensor_color_distance");

        timer = new ElapsedTime();


        telemetry.addLine("Right Trigger to go 'forward'");
        telemetry.addLine("Left Trigger to go 'reverse'");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            Color.RGBToHSV((int) (sensorColor.red() * SCALE_FACTOR),
                    (int) (sensorColor.green() * SCALE_FACTOR),
                    (int) (sensorColor.blue() * SCALE_FACTOR),
                    hsvValues);

            // OPENS LINEAR
            if (gamepad1.right_trigger > 0.1) {
                OpenExtender();
            }
            // CLOSES EXTENSION
            else if (gamepad1.left_trigger > 0.1) {
                CloseExtender();
            }
            else {
                power = 0;
            }
            update_telemetry();
//            motor.setPower(power);
        }
        requestOpModeStop();
    }

    public void update_telemetry () {
        telemetry.addLine("IN LOOP");
        telemetry.addData("POWER: ", power);
        telemetry.addData("Hue", hsvValues[0]);
        telemetry.addData("TIME (ms)", timer.milliseconds());
        telemetry.update();
    }

    public void OpenExtender () {
        timer.reset();
        while (hsvValues[0] > RED_THRESHOLD_HUE && timer.milliseconds() < OPEN_TIME_THRESHOLD && opModeIsActive()) {
            Color.RGBToHSV((int) (sensorColor.red() * SCALE_FACTOR),
                    (int) (sensorColor.green() * SCALE_FACTOR),
                    (int) (sensorColor.blue() * SCALE_FACTOR),
                    hsvValues);
            power = powerControl;
            motor.setPower(power);
            telemetry.addLine("OPENING EXTENDER");
            telemetry.addData("Hue", hsvValues[0]);
            telemetry.addData("TIME (ms)", timer.milliseconds());
            telemetry.update();
        }
        motor.setPower(0);
    }

    public void CloseExtender () {
        timer.reset();
        while (hsvValues[0] < BLUE_THRESHOLD_HUE && timer.milliseconds() < CLOSE_TIME_THRESHOLD && opModeIsActive()) {
             Color.RGBToHSV((int) (sensorColor.red() * SCALE_FACTOR),
                     (int) (sensorColor.green() * SCALE_FACTOR),
                     (int) (sensorColor.blue() * SCALE_FACTOR),
                     hsvValues);
            power = -powerControl;
            motor.setPower(power);
            telemetry.addLine("CLOSING EXTENDER");
            telemetry.addData("Hue", hsvValues[0]);
            telemetry.addData("TIME (ms)", timer.milliseconds());
            telemetry.update();
         }
         motor.setPower(0);
    }
}
