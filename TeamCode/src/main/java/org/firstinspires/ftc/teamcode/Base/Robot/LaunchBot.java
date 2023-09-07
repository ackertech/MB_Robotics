package org.firstinspires.ftc.teamcode.Base.Robot;


import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Base.Drivetrains.LabBot_FourMotorDrive;
import org.firstinspires.ftc.teamcode.Base.Drivetrains.LaunchBot_FourMotorDrive;

public class LaunchBot extends LaunchBot_FourMotorDrive {

    // Define variable here
    public DcMotor launchMotor = null;
    public Servo flag = null;

    //LED variables and stuff
    public RevBlinkinLedDriver ledLights;
    public RevBlinkinLedDriver.BlinkinPattern ledPattern;
    public HardwareMap hwBot = null;


    public LaunchBot() {

    }

    public void initRobot (HardwareMap hwMap) {

        hwBot = hwMap;

        //Initialize DriveTrain Motors

        frontLeftMotor = hwBot.dcMotor.get("front_left_motor");
        frontRightMotor = hwBot.dcMotor.get("front_right_motor");
        rearLeftMotor = hwBot.dcMotor.get("rear_left_motor");
        rearRightMotor = hwBot.dcMotor.get("rear_right_motor");

        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rearLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rearRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //Initialize Launcher Motor
        launchMotor = hwBot.dcMotor.get("launch_motor");
        launchMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        launchMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        launchMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        launchMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        //initialize servo here
        flag = hwBot.get(Servo.class, "loader");
        flag.setDirection(Servo.Direction.FORWARD);

        //initialize LED in here
        ledLights = hwBot.get(RevBlinkinLedDriver.class, "led_strip");
        ledPattern = RevBlinkinLedDriver.BlinkinPattern.RED;
        ledLights.setPattern(ledPattern);


    }

    public void setLedPattern(RevBlinkinLedDriver.BlinkinPattern patternName) {
        ledLights.setPattern(patternName);
    }

    public void loadDisc() {
        flag.setPosition(0.9);
    }

    public void reset() {
        flag.setPosition(0.1);
    }


}
