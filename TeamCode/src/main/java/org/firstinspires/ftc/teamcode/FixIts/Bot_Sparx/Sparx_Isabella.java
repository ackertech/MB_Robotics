package org.firstinspires.ftc.teamcode.FixIts.Bot_Sparx;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Sparx_Isabella extends FourMotorDrive_Isabella{

    //Declare Robot Variables//
    public Servo flag = null;
    public DcMotor launchMotor = null;
    public DcMotor launchMotor2 = null;
    public Servo discLaunch = null;
    public Servo catapult = null;

    public HardwareMap hwBot = null;
    //Led Variables//
    public RevBlinkinLedDriver ledLights;
    public RevBlinkinLedDriver.BlinkinPattern ledPattern;
    public void setLedPattern (RevBlinkinLedDriver.BlinkinPattern patternName) {
        ledLights.setPattern(patternName);
    }
    //default constructor for the robot//
    public Sparx_Isabella () {}
    //Method to initialize the robot hardware when user init button//
    public void initSparx (HardwareMap hwMap) {
        hwBot = hwMap;

        //Initialize Launcher Motor//

        launchMotor = hwBot.dcMotor.get("launch_motor");
        launchMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        launchMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        launchMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        launchMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        launchMotor2 = hwBot.dcMotor.get("launch_motor_2");
        launchMotor2.setDirection(DcMotorSimple.Direction.FORWARD);
        launchMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        launchMotor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        launchMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        //hardware mapping between Name on robot and variable name in code//
        frontLeftMotor = hwBot.dcMotor.get("front_left_motor");
        frontRightMotor = hwBot.dcMotor.get("front_right_motor");
        rearLeftMotor = hwBot.dcMotor.get("rear_left_motor");
        rearRightMotor = hwBot.dcMotor.get("rear_right_motor");

        //direction of motors as placed on the robot//
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rearLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rearRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        //Using method from drivetrain class to set the motor run modes//
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //Motor behavior when no power is applied//
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rearRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rearLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        //initializing servos that is used for any robot mechanisms//
        flag = hwBot.get(Servo.class, "flag");
        flag.setDirection((Servo.Direction.FORWARD));

        //Define and initialize LEDTester lights//
        ledLights = hwBot.get(RevBlinkinLedDriver.class, "LedLights");
        ledPattern = RevBlinkinLedDriver.BlinkinPattern.STROBE_RED;
        ledLights.setPattern(ledPattern);
    }
    //Robot methods for raising, lowering, and waving the flag//
    public void lowerFlag () {
        flag.setPosition(0);
    }
    public void raiseFlag() {
        flag.setPosition(0.675);
    }   // was 0.475
    public void waveFlagLeft() {
        flag.setPosition(0.38);
    }
    public void waveFlagRight() {flag.setPosition(0.55);}
    public void initFlag () {
        flag.setPosition(.8);
    }


}
