package org.firstinspires.ftc.teamcode.iLab.Bot_Connor.Wall_E;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.iLab.Bot_Connor.Drivetrains.Tank_FourMotorDrive_Connor;


public class WalleBot extends Tank_FourMotorDrive_Connor {
    //define Mechinism Varibles
    //Set Lazy Susan movement values
    public DcMotor sidewaysLinearMotor;
    public DcMotor upAndDownLinearMotor;
    public DcMotor lazy_Susan;
    public Servo leftClaw = null;
    public Servo rightClaw = null;
    //Hardware Mapping Variable used by robot controller
    public HardwareMap hwBot = null;

    //Robot Physical Constructor used in TeleOp and Autonomous
    public WalleBot() {
    }

    public void setLinearOp(LinearOpMode LinearOp) {this.LinearOp = LinearOp;}

    // Custom Method that will initilize the robot hardware in TeleOp and Autonomous
    public void initRobot(HardwareMap hwMap) {
        hwBot = hwMap;

        //Define the name of the motors used in the control hub configuation
        frontLeftMotor = hwBot.dcMotor.get("frontLeftMotor"); //Port 0
        frontRightMotor = hwBot.dcMotor.get("frontRightMotor");// Port 2
        rearLeftMotor = hwBot.dcMotor.get("rearLeftMotor");// Port 1
        rearRightMotor = hwBot.dcMotor.get("rearRightMotor");// Port 3

        //Sets the direction of the robot's motors based on physical placement
        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotor.Direction.FORWARD);
        rearLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        rearRightMotor.setDirection(DcMotor.Direction.FORWARD);


        //Define this robot run modes
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //Define this robot's braking modes
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rearLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rearRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);


        /**  ********  Tankbot_Connor Mechanisms ************     **/

        //Expansion Hub Port 2

        lazy_Susan = hwBot.dcMotor.get("lazySusan");
        lazy_Susan.setDirection(DcMotor.Direction.FORWARD);
        lazy_Susan.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        lazy_Susan.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lazy_Susan.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        /** Linear Actuatiors*********    **/

        sidewaysLinearMotor = hwBot.dcMotor.get("sidewaysLinearMotor"); //Expantion Hub Port 0
        upAndDownLinearMotor = hwBot.dcMotor.get("upAndDownLinearMotor"); //Expantion Hub Port 1

        sidewaysLinearMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        upAndDownLinearMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        sidewaysLinearMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        upAndDownLinearMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        sidewaysLinearMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sidewaysLinearMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        upAndDownLinearMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        upAndDownLinearMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //Control Hub Port 0
        leftClaw = hwBot.get(Servo.class,"leftClaw");
        leftClaw.setDirection(Servo.Direction.REVERSE);

        rightClaw = hwBot.get(Servo.class, "rightClaw");
        rightClaw.setDirection(Servo.Direction.REVERSE);
        /** Linear Actuatiors*********    **/


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

    public void lazySusanLeft (double power, double rotations) {
        double ticks = rotations  * TICKS_PER_ROTATION;
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while ((Math.abs(lazy_Susan.getCurrentPosition() ) < ticks && LinearOp.opModeIsActive()) ) {
            lazySusanLeft(power);
        }
        stopMotors();
    }

    public void lazySusanRight (double power, double rotations) {
        double ticks = rotations  * TICKS_PER_ROTATION;
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while ((Math.abs(lazy_Susan.getCurrentPosition() ) < ticks && LinearOp.opModeIsActive()) ) {
            lazySusanRight(power);
        }
        stopMotors();
    }

    public void sidewaysLinearMotorForward (double power) {
        sidewaysLinearMotor.setPower(-Math.abs(power));
    }

    public void sidewaysLinearMotorBack (double power) {
        sidewaysLinearMotor.setPower(Math.abs(power));
    }

    public void sidewaysLinearMotorStop(){
        sidewaysLinearMotor.setPower(0);
    }

    public void sidewaysLinearMotorForward (double power, double rotations) {
        double ticks = rotations  * TICKS_PER_ROTATION;
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while ((-Math.abs(sidewaysLinearMotor.getCurrentPosition() ) < ticks && LinearOp.opModeIsActive()) ) {
            sidewaysLinearMotorForward(power);
        }
        stopMotors();
    }

    public void sidewaysLinearMotorBack(double power, double rotations) {
        double ticks = rotations  * TICKS_PER_ROTATION;
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while ((Math.abs(frontLeftMotor.getCurrentPosition() ) < ticks && LinearOp.opModeIsActive()) ) {
            sidewaysLinearMotorBack(power);
        }
        stopMotors();
    }

    public void upAndDownLinearMotorForward(double power, double rotations) {
        double ticks = rotations  * TICKS_PER_ROTATION;
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while ((-Math.abs(upAndDownLinearMotor.getCurrentPosition() ) < ticks && LinearOp.opModeIsActive()) ) {
            upAndDownLinearMotorForward(power);
        }
        stopMotors();
    }

    public void upAndDownLinearMotorBack(double power, double rotations) {
        double ticks = rotations  * TICKS_PER_ROTATION;
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while ((Math.abs(upAndDownLinearMotor.getCurrentPosition() ) < ticks && LinearOp.opModeIsActive()) ) {
            upAndDownLinearMotorBack(power);
        }
        stopMotors();
    }

    public void upAndDownLinearMotorForward(double power){
        upAndDownLinearMotor.setPower(-Math.abs(power));
    }

    public void upAndDownLinearMotorBack(double power) {
        upAndDownLinearMotor.setPower(Math.abs(power));
    }

    public void upAndDownLinearMotorStop (){
        upAndDownLinearMotor.setPower(0);
    }

    public void leftClawOpen() {leftClaw.setPosition(1);}
    public void leftClawClose() {leftClaw.setPosition(0);}
    public void rightClawOpen() {rightClaw.setPosition(1);}
    public void rightClawClose() {rightClaw.setPosition(0);}


}
