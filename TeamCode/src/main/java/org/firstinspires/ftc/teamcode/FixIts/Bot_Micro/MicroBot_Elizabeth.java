package org.firstinspires.ftc.teamcode.FixIts.Bot_Micro;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Base.Robot.LabBot_4Motor;

public class MicroBot_Elizabeth extends FourMotorDrive_Elizabeth {
    //Declare Robot Variables
    public Servo flag = null;
    public HardwareMap hwBot = null;
    public DcMotor launchMotor = null;

    //LED variables and stuff
    public RevBlinkinLedDriver ledLights;
    public RevBlinkinLedDriver.BlinkinPattern ledPattern;
    public HardwareMap HwBot = null;

    //Default Constructor for the Robot
    public MicroBot_Elizabeth () {}

    //Method to Initialize the Robot Hardware when User presses Init Button
    public void initRobot (HardwareMap hwMap) {
        hwBot = hwMap;
        //Hardware Mapping Between Name on Robot and Variable Name in Code
        frontLeftMotor = hwBot.dcMotor.get("front_left_motor");
        frontRightMotor = hwBot.dcMotor.get("front_right_motor");
        rearLeftMotor = hwBot.dcMotor.get("rear_left_motor");
        rearRightMotor = hwBot.dcMotor.get("rear_left_motor");

        //Direction of Motors as placed on the robot
        frontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rearLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rearRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        //Using Method from Drivetrain Class to set the motor run modes
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
//
        //Intialize LED
        ledLights = hwBot.get(RevBlinkinLedDriver.class, "led_strip");
        ledPattern = RevBlinkinLedDriver.BlinkinPattern.COLOR_WAVES_OCEAN_PALETTE;
        ledLights.setPattern(ledPattern);

        //Initialize Launcher Motor
        launchMotor = hwBot.dcMotor.get("launch_motor");
        launchMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        launchMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        launchMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        launchMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

    }

    public void setLedPattern(RevBlinkinLedDriver.BlinkinPattern patternName) {
        ledLights.setPattern(patternName);
    }


    //Robot Methods for Raising, Lowering, and Waving the Flag
    public void lowerFlag () {
        flag.setPosition(0);
    }

    public void raiseFlag () {
        flag.setPosition(0.475);
    }

    public void waveFlagLeft () {
        flag.setPosition(0.38);
    }

    public void waveFlagRight () {
        flag.setPosition(0.55);
    }

    public void initFlag () {
        flag.setPosition(.8);
    }




}
