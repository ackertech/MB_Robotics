package org.firstinspires.ftc.teamcode.Base.DigitalTwin;


import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

public class DigitalTwin  {

    // Conveyor Belt Variables
    public DcMotor conveyorBeltMotor = null;

    // Gate Sorter Variables
    public Servo puckSorter = null;
    public double puckSorterCurrPos = 0.1;
    public double gatePassPosition = 0.9;
    public double gateSortPosition = 0.4;
    public double gateStopPosition = 0.1;
    public double puckSortIncrement = 0.001;

    // Color Sensor Variables
    public NormalizedColorSensor colorSensor;

    public HardwareMap hwBot = null;

    public LinearOpMode linearOp = null;

    public void setLinearOp(LinearOpMode linearOp) {

        this.linearOp = linearOp;
    }

    public DigitalTwin() {}

    public void initRobot (HardwareMap hwMap) {

        hwBot = hwMap;

        //Initialize Conveyor Belt Motor
        conveyorBeltMotor = hwBot.dcMotor.get("conveyor_motor");
        conveyorBeltMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        conveyorBeltMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        conveyorBeltMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        conveyorBeltMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        //initialize Puck Sorter
        puckSorter = hwBot.get(Servo.class, "sorter");
        puckSorter.setDirection(Servo.Direction.FORWARD);

        //Initialize Color Sensor
        colorSensor = hwBot.get(NormalizedColorSensor.class, "sensor_color");

    }


    public void passPuck() {

        puckSorter.setPosition(gatePassPosition);
        puckSorterCurrPos = puckSorter.getPosition();
    }

    public void sortPuck() {

        puckSorter.setPosition(gateSortPosition);
        puckSorterCurrPos = puckSorter.getPosition();
    }

    public void stopPuck() {

        puckSorter.setPosition(gateStopPosition);
        puckSorterCurrPos = puckSorter.getPosition();
    }
}

//
//
//
//
