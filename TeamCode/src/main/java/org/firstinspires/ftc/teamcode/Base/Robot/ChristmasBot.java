package org.firstinspires.ftc.teamcode.Base.Robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Base.Drivetrains.Christmas_Drive;

public class ChristmasBot extends Christmas_Drive {

    public HardwareMap hwBot = null;

    // Motors
    public DcMotor wormGear;
    public DcMotor linerarMotor;

    // Servos
    public Servo rackGear = null;
    public Servo servoOne;
    public Servo servoTwo;


    public ChristmasBot() {}

    // **** Initialize Drivetrain ****
    public void initDrive(HardwareMap hwMap) {
        hwBot = hwMap;

        // Drive Motors
        frontLeftMotor = hwBot.dcMotor.get("front_left_motor"); //Port 0
        frontRightMotor = hwBot.dcMotor.get("front_right_motor");// Port 2
        rearLeftMotor = hwBot.dcMotor.get("rear_left_motor");// Port 1
        rearRightMotor = hwBot.dcMotor.get("rear_right_motor");// Port 3

        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rearLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rearRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rearLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rearRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

    }

    // **** Initialize Worm Gear Mechanism ****
    public void initIgloo(HardwareMap hwMap) {

        hwBot = hwMap;
        wormGear = hwBot.dcMotor.get("worm_gear");
        wormGear.setDirection(DcMotorSimple.Direction.FORWARD);
        wormGear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    // **** Initialize Linear Actuator  ****
    public void initPenguin(HardwareMap hwMap) {

        hwBot = hwMap;

        linerarMotor = hwBot.dcMotor.get("horizontal_motor");
        linerarMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        linerarMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linerarMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        linerarMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    // **** Initialize Servos ****
    public void initBear(HardwareMap hwMap) {

        hwBot = hwMap;

        rackGear = hwBot.get(Servo.class, "rackgear_servo");
        rackGear.setDirection(Servo.Direction.FORWARD);

        servoOne = hwBot.get(Servo.class, "servo_1");
        servoOne.setDirection(Servo.Direction.FORWARD);

        servoTwo = hwBot.get(Servo.class, "servo_2");
        servoTwo.setDirection(Servo.Direction.FORWARD);

    }

    // **** Movement Methods for Worm Gear ****
    public void wormGearRotateForward (double power) {

        wormGear.setPower(Math.abs(power));
    }

    public void wormGearRotateReverse (double power) {

        wormGear.setPower(-Math.abs(power));
    }

    public void wormGearStop () {

        wormGear.setPower(0);
    }

    // **** Movement Methods for Linear Actuator ****
    public void extendLinear (double power) {

        linerarMotor.setPower(Math.abs(power));
    }

    public void retractLinear (double power) {

        linerarMotor.setPower(-Math.abs(power));
    }

    public void stopLinear () {

        linerarMotor.setPower(0);
    }


    // **** Movement Methods for Normal Servo One ****

    public void rotateRightOne() {
        servoOne.setPosition(0.8);
    }

    public void rotateLeftOne() {
        servoOne.setPosition(0.1);
    }

    public void rotateMidOne() {
        servoOne.setPosition(0.2);
    }


    // **** Movement Methods for Normal Servo Two ****

    public void rotateRightTwo() {
        servoOne.setPosition(0.8);
    }

    public void rotateLeftTwo() {
        servoOne.setPosition(0.2);
    }

    public void rotateMidTwo() {
        servoOne.setPosition(0.5);
    }


    // **** Movement Methods for Rack Gear Servo ****

    public void extendFully() {
        rackGear.setPosition(0.8);
    }

    public void extendPartially() {
        rackGear.setPosition(0.5);
    }

    public void retractFully() {
        rackGear.setPosition(0.2);
    }


}