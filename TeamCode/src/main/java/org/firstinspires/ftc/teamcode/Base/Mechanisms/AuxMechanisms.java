package org.firstinspires.ftc.teamcode.Base.Mechanisms;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class AuxMechanisms {

    // Declare Variables for Motors that you have added
    public DcMotor wornGearMotor;
    public DcMotor horizontalMotor;
    public DcMotor verticalMotor;

    // Declare Variables for Servos that you have added
    public Servo normalServo;
    public Servo rackGearServo;

    // ***** This section is already in your robot class
    public HardwareMap hwBot = null;
    public LinearOpMode linearOp = null;
    public void setLinearOp (LinearOpMode linearOpModeOp) {

        this.linearOp = linearOp;
    }

    public AuxMechanisms() {};

    // **********


    // **** Initialize Worm Gear Mechanism ****
    public void initWormGear(HardwareMap hwMap) {

        hwBot = hwMap;


        wornGearMotor = hwBot.dcMotor.get("worm_gear_motor");
        wornGearMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        wornGearMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        wornGearMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        wornGearMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    // **** Initialize Linear Actuator with Vertical Placement Mechanism ****
    public void initLinearVertical(HardwareMap hwMap) {

        hwBot = hwMap;

        verticalMotor = hwBot.dcMotor.get("vertical_motor");
        verticalMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        verticalMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        verticalMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        verticalMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    // **** Initialize Linear Actuator with Horizontal Placement Mechanism ****
    public void initLinearHorizontal(HardwareMap hwMap) {

        hwBot = hwMap;

        horizontalMotor = hwBot.dcMotor.get("horizontal_motor");
        horizontalMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        horizontalMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        horizontalMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        horizontalMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    // **** Initialize Linear Actuator with Horizontal Placement Mechanism ****
    public void initServos(HardwareMap hwMap) {

        hwBot = hwMap;

        normalServo = hwBot.get(Servo.class, "normal_servo");
        normalServo.setDirection(Servo.Direction.FORWARD);

        rackGearServo = hwBot.get(Servo.class, "rack_gear_servo");
        rackGearServo.setDirection(Servo.Direction.FORWARD);

    }



    // **** Movement Methods for Worm Gear ****
    public void wormGearRotateForward (double power) {

        wornGearMotor.setPower(Math.abs(power));
    }

    public void wormGearRotateReverse (double power) {

        wornGearMotor.setPower(-Math.abs(power));
    }

    // **** Movement Methods for Linear Actuator in Horizontal Placement ****
    public void moveLinearForward (double power) {

        horizontalMotor.setPower(Math.abs(power));
    }

    public void moveLinearReverse (double power) {

        horizontalMotor.setPower(-Math.abs(power));
    }

    // **** Movement Methods for Linear Actuator in Vertical Placement ****
    public void moveLinearUp (double power) {

        verticalMotor.setPower(Math.abs(power));
    }

    public void moveLinearDown (double power) {

        verticalMotor.setPower(-Math.abs(power));
    }


    // **** Movement Methods for Normal Servo ****

    public void rotateRight() {
        normalServo.setPosition(0.9);
    }

    public void rotateLeft() {
        normalServo.setPosition(0.1);
    }

    public void rotateMid() {
        normalServo.setPosition(0.5);
    }

    // **** Movement Methods for Rack Gear Servo ****

    public void extendFully() {
        rackGearServo.setPosition(0.9);
    }

    public void extendPartially() {
        rackGearServo.setPosition(0.5);
    }

    public void retract() {
        rackGearServo.setPosition(0.1);
    }
}
