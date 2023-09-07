package org.firstinspires.ftc.teamcode.Base.DigitalTwin;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Base.Controls.TeleOp.AckerBotTeleOp;

//@Disabled
@TeleOp(name = "Digital Twin TA")
public class DigitalTwinTeleAuto extends OpMode {

    //Conveyor Belt Variables
    double speedMultiply = 1.0;

    //Color Sensor Variables
    final float[] hsvValues = new float[3];

    //Servo Gate Variables
    public enum GateState {GATE_START, GATE_OPEN, GATE_SORT, GATE_REST}
    GateState gateState = GateState.GATE_START;

    public enum GateControl {AUTO, MANUAL}
    public GateControl gateControl = GateControl.AUTO;

    // Object Construction
    public ElapsedTime time = new ElapsedTime();
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
        controlServoGate();

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

    public void controlServoGate() {

        if (gamepad2.dpad_left) {
            gateControl = GateControl.AUTO;
        }
        if (gamepad2.dpad_right) {
            gateControl = GateControl.MANUAL;
        }

        if (gateControl == GateControl.AUTO) {

            switch (gateState) {
                case GATE_START:
                    if (gamepad2.dpad_up) {
                        DigiTwin.puckSorter.setPosition(DigiTwin.gateStopPosition);
                        gateState = GateState.GATE_SORT;
                    }
                    break;
                case GATE_SORT:
                    DigiTwin.sortPuck();
                    gateState = GateState.GATE_REST;

                    break;
                case GATE_REST:
                    if (gamepad2.dpad_down) {
                        DigiTwin.passPuck();
                        DigiTwin.puckSorter.setPosition(DigiTwin.gatePassPosition);
                        gateState = GateState.GATE_START;
                    }
                    break;
                default:
                    gateState = GateState.GATE_START;
            }

        }
        else if (gateControl == GateControl.MANUAL) {

            if (gamepad2.dpad_up  && DigiTwin.puckSorterCurrPos < DigiTwin.gatePassPosition) {
                DigiTwin.puckSorterCurrPos += DigiTwin.puckSortIncrement;
                DigiTwin.puckSorter.setPosition(DigiTwin.puckSorterCurrPos);
            }
            else {
                DigiTwin.puckSorter.setPosition(DigiTwin.puckSorterCurrPos);
            }

            if (gamepad2.dpad_down  && DigiTwin.puckSorterCurrPos > DigiTwin.gateStopPosition) {
                DigiTwin.puckSorterCurrPos -= DigiTwin.puckSortIncrement;
                DigiTwin.puckSorter.setPosition(DigiTwin.puckSorterCurrPos);

            }
            else {
                DigiTwin.puckSorter.setPosition(DigiTwin.puckSorterCurrPos);
            }

        }


    }


}
