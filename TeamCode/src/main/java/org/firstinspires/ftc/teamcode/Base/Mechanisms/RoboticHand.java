package org.firstinspires.ftc.teamcode.Base.Mechanisms;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class RoboticHand {

    //Defines Hand Variable
    public Servo pinkyFinger = null;
    public Servo ringFinger = null;
    public Servo middleFinger = null;
    public Servo indexFinger = null;
    public Servo thumb = null;

    public DcMotor lazy_Susan;

    //Define Arm Variables
    public Servo elbow = null;
    public Servo wrist = null;

    //Set Positioning for Arm or Hand
    public double elbowMaxPos = 0.55;
    public double elbowMinPos = 0.8;
    public double elbowHalfPos = 0.7;
    public double   elbowCurrPos = 0.18;
    public double elbowIncrements = 0.0005;

    //Hardware Constructors
    public HardwareMap hwBot = null;

    public RoboticHand() {}

    //Allows us to leverage telementry and sleep
    public LinearOpMode linearOp = null;
    public void setLinearOp (LinearOpMode linearOpModeOp) {

        this.linearOp = linearOp;
    }

    public void initRoboticHand(HardwareMap hwMap) {

        hwBot = hwMap;

        //Control Hub Port 4
        pinkyFinger = hwBot.get(Servo.class, "pinkyFinger");
        pinkyFinger.setDirection(Servo.Direction.FORWARD);

        //Control Hub Port 3
        ringFinger = hwBot.get(Servo.class, "ringFinger");
        ringFinger.setDirection(Servo.Direction.FORWARD);

        //Control Hub Port 2
        middleFinger = hwBot.get(Servo.class, "middleFinger");
        middleFinger.setDirection(Servo.Direction.FORWARD);

        //Control Hub Port 1
        indexFinger = hwBot.get(Servo.class, "indexFinger");
        indexFinger.setDirection(Servo.Direction.FORWARD);

        //Control hub Port 0
        thumb = hwBot.get(Servo.class, "thumb");
        thumb.setDirection(Servo.Direction.REVERSE);

        //Expansion hub Port 0
        wrist = hwBot.get(Servo.class, "wrist");
        wrist.setDirection(Servo.Direction.REVERSE);

        //Expansion Hub Port 1
        elbow = hwBot.get(Servo.class, "elbow");
        elbow.setDirection(Servo.Direction.REVERSE);

        lazy_Susan = hwBot.dcMotor.get("lazySusan");
        lazy_Susan.setDirection(DcMotor.Direction.FORWARD);
        lazy_Susan.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        lazy_Susan.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lazy_Susan.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


    }

    /**  ********  WRIST MOVEMENT METHODS ************     **/

    public void wristLeft() {
        wrist.setPosition(0);
    }

    public void wristRight() {
        wrist.setPosition(1);
    }

    public void wristMiddle() {
        wrist.setPosition(0.5);
    }

    /** *******  ARM MOVEMENT METHODS *************      **/

    public void raiseArm() {
        closePartial();
        elbow.setPosition(elbowMaxPos); }

   public void halfArm() {elbow.setPosition(elbowHalfPos);}

    public void lowerArm() {
       closePartial();
       elbow.setPosition(elbowMinPos);
    }

    /**  ******* HAND GESTURES ************     **/

    public void point() {
        thumb.setPosition(0);
        indexFinger.setPosition(1);
        middleFinger.setPosition(0);
        ringFinger.setPosition(0);
        pinkyFinger.setPosition(0);
    }

    public void peaceSign() {
        thumb.setPosition(0);
        indexFinger.setPosition(1);
        middleFinger.setPosition(1);
        ringFinger.setPosition(0);
        pinkyFinger.setPosition(0);
    }

    public void openHand() {
        thumb.setPosition(1);
        indexFinger.setPosition(1);
        middleFinger.setPosition(1);
        ringFinger.setPosition(1);
        pinkyFinger.setPosition(1);
    }

    public void closeHand() {
        thumb.setPosition(0);
        indexFinger.setPosition(0);
        middleFinger.setPosition(0);
        ringFinger.setPosition(0);
        pinkyFinger.setPosition(0);
    }
    public void closePartial() {
        thumb.setPosition(.25);
        indexFinger.setPosition(.25);
        middleFinger.setPosition(.25);
        ringFinger.setPosition(.25);
        pinkyFinger.setPosition(.25);
    }

    public void thumbsUp() {
        halfArm();
        wristLeft();
        thumb.setPosition(1);
        indexFinger.setPosition(0);
        middleFinger.setPosition(0);
        ringFinger.setPosition(0);
        pinkyFinger.setPosition(0);
    }

    public void thumbsDown() {
        halfArm();
        wristRight();
        thumb.setPosition(1);
        indexFinger.setPosition(0);
        middleFinger.setPosition(0);
        ringFinger.setPosition(0);
        pinkyFinger.setPosition(0);
    }

    public void wave() {
        openHand();
        raiseArm();
        halfArm();
        raiseArm();
        halfArm();
        lowerArm();
    }

    public void smack() {
        halfArm();
        openHand();
    }

    public void resetHand() {
        closeHand();
        wristMiddle();
        lowerArm();
    }


public void lazySusanLeft (double power) {lazy_Susan.setPower(Math.abs(power));}

public void lazySusanRight (double power) {
        lazy_Susan.setPower(-Math.abs(power));
    }

public void lazySusanStop(){
        lazy_Susan.setPower(0);
    }

}


