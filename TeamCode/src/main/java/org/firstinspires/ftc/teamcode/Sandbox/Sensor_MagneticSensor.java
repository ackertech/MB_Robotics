package org.firstinspires.ftc.teamcode.Sandbox;

import android.inputmethodservice.Keyboard;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.TouchSensor;
@Disabled
@TeleOp (name = "Test:Magnetic Sensor", group = "Lab")

public class Sensor_MagneticSensor extends OpMode {

    TouchSensor magSensor1;
    RevBlinkinLedDriver blinkinLedDriver;
    RevBlinkinLedDriver.BlinkinPattern pattern;

    @Override
    public void init() {
        magSensor1 = hardwareMap.get (TouchSensor.class, "MagSensor");

        blinkinLedDriver = hardwareMap.get(RevBlinkinLedDriver.class, "blinkin");
        pattern = RevBlinkinLedDriver.BlinkinPattern.RAINBOW_RAINBOW_PALETTE;
        blinkinLedDriver.setPattern(pattern);
    }

    @Override
    public void loop() {
        if (magSensor1.isPressed()) {
            pattern = RevBlinkinLedDriver.BlinkinPattern.RED;
        }
        else pattern = RevBlinkinLedDriver.BlinkinPattern.BLUE;

       // Keyboard keyboard;

        //keyboard.getKeys()



        telemetry.addData("SENSOR TRIPPED: ", magSensor1.isPressed());
        telemetry.update();
    }
}
