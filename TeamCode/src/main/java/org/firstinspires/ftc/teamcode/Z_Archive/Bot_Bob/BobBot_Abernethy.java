package org.firstinspires.ftc.teamcode.Z_Archive.Bot_Bob;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class BobBot_Abernethy extends FourMotorDrive_Lainaaa {


    //Declare Robot Variables
    public Servo flag = null;
    public HardwareMap hwBot = null;
    public DcMotor launchMotor = null;

    // LED Variables
    public RevBlinkinLedDriver ledLights;
    public RevBlinkinLedDriver.BlinkinPattern ledPattern;


    //Default Constructor for the Robot
    public BobBot_Abernethy() {
    }

    //Method to Initialize the Robot Hardware when User presses the Init button
    public void initRobot(HardwareMap hwMap) {

        //initialize Launcher motor
        launchMotor = hwBot.dcMotor.get("launch_motor");
        launchMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        launchMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        launchMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        launchMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        hwBot = hwMap;
        //Hardware Mapping Between Name on robot and Variable name in Code
        frontLeftMotor = hwBot.dcMotor.get("front_left_motor");
        frontRightMotor = hwBot.dcMotor.get("front_right_motor");
        rearLeftMotor = hwBot.dcMotor.get("rear_left_motor");
        rearRightMotor = hwBot.dcMotor.get("rear_right_motor");

        //Directions of Motors as placed on the Robot
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rearLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rearRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        //Using method from Drivetrain Class to set the motor run modes
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //Motor behavior when no power is applied
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //Initializing Servos that is used for any robot mechanisms
        flag = hwBot.get(Servo.class, "flag");
        flag.setDirection(Servo.Direction.FORWARD);

        //Define & Initialize LEDTester Lights
        ledLights = hwBot.get(RevBlinkinLedDriver.class, "led_strip");
        ledPattern = RevBlinkinLedDriver.BlinkinPattern.COLOR_WAVES_FOREST_PALETTE;
        ledLights.setPattern(ledPattern);


    }

    public void setLedPattern (RevBlinkinLedDriver.BlinkinPattern patternName) {
        ledLights.setPattern(patternName);
    }
    //Robot Methods for Raising, Lowering, and Waving the flag
    public void lowerFlag() {
        flag.setPosition(0.1);
    }

    public void popBalloon() {
        flag.setPosition(.99);
    }

    public void hideDagger() {
        flag.setPosition(0.8);
    }



}