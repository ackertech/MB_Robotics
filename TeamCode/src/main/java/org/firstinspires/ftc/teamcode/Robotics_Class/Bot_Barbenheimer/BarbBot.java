package org.firstinspires.ftc.teamcode.Robotics_Class.Bot_Barbenheimer;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class BarbBot extends DriveTrain_Olivia{
    public HardwareMap hwBot = null;

    // Motors
    public DcMotor outtakeOne;
    public DcMotor outtakeTwo;
    public DcMotor launcher;

    // Servos
    public Servo catapult;
    public Servo maceFlipper;
    public Servo discPusher;


    public BarbBot() {}

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

        outtakeOne = hwBot.dcMotor.get("outtake_one");
        outtakeOne.setDirection(DcMotorSimple.Direction.FORWARD);
        outtakeOne.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        outtakeTwo = hwBot.dcMotor.get("outtake_two");
        outtakeTwo.setDirection(DcMotorSimple.Direction.REVERSE);
        outtakeTwo.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        launcher = hwBot.dcMotor.get("launcher_up");
        launcher.setDirection(DcMotor.Direction.REVERSE);
        launcher.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        discPusher = hwBot.servo.get("disc_pusher");
        discPusher.setDirection(Servo.Direction.FORWARD);

        catapult = hwBot.servo.get("catapult");
        catapult.setDirection(Servo.Direction.FORWARD);

        maceFlipper = hwBot.servo.get("mace");
        maceFlipper.setDirection(Servo.Direction.FORWARD);

    }


    // **** Movement Methods for Motors ****
    public void launchDisc(double power) {
        outtakeOne.setPower(Math.abs(power));
        outtakeTwo.setPower(Math.abs(power));
    }

    public void launcherStop() {
        outtakeOne.setPower(0);
        outtakeTwo.setPower(0);
    }

    public void stopLauncher(){
        launcher.setPower(0);
    }

    public void raiseLauncher(double power){
        launcher.setPower(Math.abs(power));
    }

    public void lowerLauncher(double power){
        launcher.setPower(-Math.abs(power));
    }

    public void raiseLauncher(double power, double ticks){
        launcher.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        launcher.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while((Math.abs(launcher.getCurrentPosition()) < ticks && linearOp.opModeIsActive())) {
            raiseLauncher(power);
        }
        stopLauncher();
    }

    public void lowerLauncher(double power, double ticks){
        launcher.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        launcher.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while(Math.abs(launcher.getCurrentPosition()) > ticks && linearOp.opModeIsActive()) {
            lowerLauncher(power);
        }
        stopLauncher();
    }

    // **** Movement Methods for Mace Flipper ****

    public void flipMace() {
        maceFlipper.setPosition(0.1);
    }

    public void resetFlipMace() {
        maceFlipper.setPosition(0.5);
    }

    // **** Movement Methods for Catapult ****

    public void launchCatapult() {catapult.setPosition(0.6);}

    public void resetLaunchCatapult() {
        catapult.setPosition(0.2);
    }

}
