package org.firstinspires.ftc.teamcode.Base.DigitalTwin;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.teamcode.Base.DigitalTwin.DigitalTwin;

@Disabled
@Autonomous(name = "Digital Twin A")

public class AutoDigitalTwin extends LinearOpMode {

    DigitalTwin DigiTwin = new DigitalTwin();
    final float[] hsvValues = new float[3];



    @Override
    public void runOpMode() throws InterruptedException {

        DigiTwin.initRobot(hardwareMap);
        DigiTwin.setLinearOp(this);

        telemetry.addLine("Awaiting Start");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()){

        DigiTwin.conveyorBeltMotor.setPower(.80);

        DigiTwin.conveyorBeltMotor.setPower(.80);

        requestOpModeStop();

        }

        idle();

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
