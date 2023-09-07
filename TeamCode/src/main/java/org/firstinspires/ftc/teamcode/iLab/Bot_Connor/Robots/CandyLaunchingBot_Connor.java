package org.firstinspires.ftc.teamcode.iLab.Bot_Connor.Robots;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.iLab.Bot_Connor.Drivetrains.MecanumDrive_Connor;

public class CandyLaunchingBot_Connor extends MecanumDrive_Connor {

public HardwareMap hwBot = null;

public RevBlinkinLedDriver ledLights;
public RevBlinkinLedDriver.BlinkinPattern ledPattern;
public RevBlinkinLedDriver.BlinkinPattern patternArray [] = {
        RevBlinkinLedDriver.BlinkinPattern.BLUE,
        RevBlinkinLedDriver.BlinkinPattern.BLUE_GREEN,
        RevBlinkinLedDriver.BlinkinPattern.GRAY,
        RevBlinkinLedDriver.BlinkinPattern.GOLD,
        RevBlinkinLedDriver.BlinkinPattern.LIGHT_CHASE_BLUE,
        RevBlinkinLedDriver.BlinkinPattern.COLOR_WAVES_PARTY_PALETTE,

};

    public DcMotor lazy_Susan;

public ElapsedTime currentTime = new ElapsedTime();
public int ledTimer;
public int ledTimerIncrementer = 4;
public int ledCounter = 0;


public ElapsedTime timer = new ElapsedTime();
double waitTime = 2.0;

public DcMotor launcher;
public DcMotor camLift;
public Servo camPivot;
public Servo candyDoor;


    public Servo pinkyFinger = null;
    public Servo ringFinger = null;
    public Servo middleFinger = null;
    public Servo indexFinger = null;
    public Servo thumbFinger = null;


    //FTC SDK Requirement
    public LinearOpMode linearOp = null;
    public void setLinearOp (LinearOpMode Op) {
        linearOp = Op;
    }


    public CandyLaunchingBot_Connor() {

    }

public void initRobot(HardwareMap hwMap) {

        hwBot = hwMap;

    frontLeftMotor = hwBot.dcMotor.get("front_left_motor");
    frontRightMotor = hwBot.dcMotor.get("front_right_motor");
    rearLeftMotor = hwBot.dcMotor.get("rear_left_motor");
    rearRightMotor = hwBot.dcMotor.get("rear_right_motor");


    frontLeftMotor.setDirection(DcMotor.Direction.FORWARD);
    rearLeftMotor.setDirection(DcMotor.Direction.FORWARD);
    frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
    rearRightMotor.setDirection(DcMotor.Direction.REVERSE);


    setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    rearRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    rearLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


    launcher = hwBot.dcMotor.get("launcher");
    launcher.setDirection(DcMotor.Direction.REVERSE);
    launcher.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

    camLift = hwBot.dcMotor.get("cam_lift");
    camLift.setDirection(DcMotor.Direction.FORWARD);
    camLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


    candyDoor = hwBot.get(Servo.class, "trap_door");
    candyDoor.setDirection(Servo.Direction.REVERSE);
    closeCandyDoor();

    camPivot= hwBot.get(Servo.class, "cam_pivot");
    camPivot.setDirection(Servo.Direction.FORWARD);
    camCenter();


    ledLights = hwBot.get(RevBlinkinLedDriver.class, "led_strip");
    ledPattern = RevBlinkinLedDriver.BlinkinPattern.COLOR_WAVES_PARTY_PALETTE;   //https://www.revrobotics.com/content/docs/REV-11-1105-UM.pdf
    ledLights.setPattern(ledPattern);

    currentTime.reset();


    ledCounter = 0 ;

}




    public void setLedPattern (RevBlinkinLedDriver.BlinkinPattern patternName) {
        ledLights.setPattern(patternName);

    }


    public void stopLauncher () {
        launcher.setPower(0);
        launcher.setPower(0);

    }

    public void runLauncher (double power) {
        double ABSpower = Math.abs(power);
        launcher.setPower(ABSpower);

    }

    public void openCandyDoor () {
        candyDoor.setPosition(.6);

    }

    public void closeCandyDoor () {

        candyDoor.setPosition(.7);
    }

    public void camLeft () {
        camPivot.setPosition(.1);

    }

    public void camRight () {

        camPivot.setPosition(.9);
    }

    public void camCenter () {

        camPivot.setPosition(.5);
    }

    public void camLiftDown ()
    {
        camLift.setPower(-1.0);
    }

    public void camLiftUp () {

        camLift.setPower(1.0);
    }

    public void camLiftStop () {

        camLift.setPower(0.0);
    }


    public void lazySusanLeft (double power) {
        lazy_Susan.setPower(Math.abs(power));
    }

    public void lazySusanRight (double power) {
        lazy_Susan.setPower(-Math.abs(power));
    }

    public void lazySusanStop(){
        lazy_Susan.setPower(0);
    }



}
