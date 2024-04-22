package org.firstinspires.ftc.teamcode.Base.Robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Base.Drivetrains.Carnival_Drive;

public class CarnivalBot extends Carnival_Drive {

    public HardwareMap hwBot = null;

    // Motors
    public DcMotor wormGear;
    public DcMotor linearActuator;
    public DcMotor flyWheel1;
    public DcMotor flyWheel2;


    // Servos
    public Servo discPusher = null;
    public Servo balloonPopper;
    public Servo servoTwo;

    public CarnivalBot() {}

    // **** Initialize Drivetrain Hardware ****
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

    // **** Initialize Fly Wheel Hardware ****
    public void initFlyWheels(HardwareMap hwMap) {
        hwBot = hwMap;
        flyWheel1 = hwBot.dcMotor.get("fly_wheel_one");
        flyWheel1.setDirection(DcMotorSimple.Direction.FORWARD);
        flyWheel1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        flyWheel2 = hwBot.dcMotor.get("fly_wheel_two");
        flyWheel2.setDirection(DcMotorSimple.Direction.FORWARD);
        flyWheel2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }


    // **** Initialize Worm Gear Mechanism ****
    public void initWormGear(HardwareMap hwMap) {
        hwBot = hwMap;
        wormGear = hwBot.dcMotor.get("worm_gear");
        wormGear.setDirection(DcMotorSimple.Direction.FORWARD);
        wormGear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    // **** Initialize Linear Actuator  ****
    public void initLinearActuator(HardwareMap hwMap) {
        hwBot = hwMap;
        linearActuator = hwBot.dcMotor.get("linear_actuator");
        linearActuator.setDirection(DcMotorSimple.Direction.FORWARD);
        linearActuator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearActuator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        linearActuator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    // **** Initialize Balloon Popper Hardware ****
    public void initBalloonPopper(HardwareMap hwMap) {
        hwBot = hwMap;
        balloonPopper = hwBot.get(Servo.class, "balloon_popper");
        balloonPopper.setDirection(Servo.Direction.FORWARD);
    }

    // **** Initialize Disc Pusher Hardware ****
    public void initDiscPusher(HardwareMap hwMap) {
        hwBot = hwMap;
        discPusher = hwBot.get(Servo.class, "disc_pusher");
        discPusher.setDirection(Servo.Direction.FORWARD);
    }

    // **** Initialize Additional Servo Hardware ****
    public void initServoTwo(HardwareMap hwMap) {
        hwBot = hwMap;
        servoTwo = hwBot.get(Servo.class, "servo_two");
        servoTwo.setDirection(Servo.Direction.FORWARD);
    }


    // **** Movement Methods for Fly Wheels ****
    public void rotateFlyWheel1 (double power) {
        flyWheel1.setPower(power);
    }

    public void rotateFlyWheel2 (double power) {
        flyWheel2.setPower(power);
    }

    public void stopFlyWheel1 () {
        flyWheel1.setPower(0);
    }

    public void stopFlyWheel2 () {
        flyWheel2.setPower(0);
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
        linearActuator.setPower(Math.abs(power));
    }

    public void retractLinear (double power) {
        linearActuator.setPower(-Math.abs(power));
    }

    public void stopLinear () {
        linearActuator.setPower(0);
    }

    // **** Movement Methods for Disc Pusher ****
    public void extendFully() {
        discPusher.setPosition(0.8);
    }
    public void extendPartially() {
        discPusher.setPosition(0.5);
    }
    public void retractFully() {
        discPusher.setPosition(0.2);
    }


    // **** Movement Methods for Balloon Popper ****
    public void extendBalloonPopper() {
        balloonPopper.setPosition(0.8);
    }
    public void retractBalloonPopper() {
        balloonPopper.setPosition(0.1);
    }


    // **** Movement Methods for Servo Two ****
    public void extendServoTwo() {servoTwo.setPosition(0.8);}
    public void retractServoTwo() {
        servoTwo.setPosition(0.1);
    }



}