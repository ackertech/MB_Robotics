package org.firstinspires.ftc.teamcode.Robotics_Class.Bot_BruceWayne.Connor_Class;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class SixWheelBot_Connor extends SixWheelDrive_Connor {

    public HardwareMap hwBot = null;
    public DcMotor lazySusan;

    public DcMotor linearSlide;

    boolean isLauncherOn = false;

    public int pullTimer = 350;
    public int rapidPushTimer = 150; //milliseconds
    public int rapidPullTimer = 175;

    public DcMotor intake;

//    public VoltageSensor voltageSensor = null;
//
//    double flywheelVelocityPerVolt = 42900 / voltageSensor.getVoltage();

    public double launchCoefficient;
    public Servo rackgear = null;

    public ElapsedTime launcherServoTimer;



    public DcMotor linearActuator;
//    public DcMotor candyLauncherLeft;
//    public DcMotor candyLauncherRight;

    public void initRobot(HardwareMap hwMap) {
        hwBot = hwMap;


//        voltageSensor = hwBot.voltageSensor.iterator().next();
//        launchCoefficient = 12 / voltageSensor.getVoltage();

                                                            //MAYBE.....:
        frontLeftMotor = hwBot.dcMotor.get("front_left_motor"); //Port 0
        frontRightMotor = hwBot.dcMotor.get("front_right_motor");// Port 2
        rearLeftMotor = hwBot.dcMotor.get("rear_left_motor");// Port 1
        rearRightMotor = hwBot.dcMotor.get("rear_right_motor");// Port 3

        lazySusan = hwBot.dcMotor.get("lazy_susan");

        intake = hwBot.dcMotor.get("intake");

//        candyLauncherLeft = hwBot.dcMotor.get("candy_launcher_left"); //REVERSE
//        candyLauncherRight = hwBot.dcMotor.get("candy_launcher_right"); //FORWARD


        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rearLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rearRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        lazySusan.setDirection(DcMotorSimple.Direction.FORWARD);

        intake.setDirection(DcMotorSimple.Direction.REVERSE);

//        candyLauncherLeft.setDirection(DcMotorSimple.Direction.REVERSE);
//        candyLauncherRight.setDirection(DcMotorSimple.Direction.FORWARD);




        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);




        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rearLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rearRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        lazySusan.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        rackgear = hwBot.get(Servo.class,"rackgear_servo");
        rackgear.setDirection(Servo.Direction.FORWARD);

        linearActuator = hwBot.dcMotor.get("sidewaysLinearMotor"); //Expantion Hub Port 0
        linearActuator.setDirection(DcMotorSimple.Direction.FORWARD);
        linearActuator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        linearActuator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearActuator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        linearSlide = hwBot.dcMotor.get("linearSlide");
        linearSlide.setDirection(DcMotorSimple.Direction.REVERSE);
        linearSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        linearSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//

//        candyLauncherLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//        candyLauncherRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);


    }


    public void lazySusanLeft (double power) {
        lazySusan.setPower(Math.abs(power));
    }

    public void lazySusanRight (double power) {
        lazySusan.setPower(-Math.abs(power));
    }

    public void lazySusanStop(){
        lazySusan.setPower(0);
    }

//    public void launcherOn(){
//        candyLauncherRight.setPower(1);
//        candyLauncherLeft.setPower(1);
//    }
//
//    public void launcherOff(){
//        candyLauncherLeft.setPower(0);
//        candyLauncherRight.setPower(0);



    public void linearSlideUp(){
        linearSlide.setPower(100);
    }
    public void linearSlideDown(){
        linearSlide.setPower(.85);
    }
    public void linearSlideStop(){
        linearSlide.setPower(0);
    }

    public void linearActuatorDown(){
        linearActuator.setPower(-100);
    }

    public void linearActuatorUp() {
        linearActuator.setPower(100);
    }
    public void linearActuatorStop(){
        linearActuator.setPower(0);
    }

    public void linearSlideUp(double power, double ticks) {

        if (Math.abs(linearSlide.getCurrentPosition()) < ticks ){
            linearSlideUp();
        }
        else {
            linearSlideStop();
        }

    }

    public void linearSlideDown(double power, double ticks) {
        if (Math.abs(linearActuator.getCurrentPosition()) > ticks ){
            linearSlideDown();
        }
        else {
            linearSlideStop();
        }

    }


//    }
public void linearActuatorUp(double power, double ticks) {

    if (Math.abs(linearActuator.getCurrentPosition()) < ticks ){
        linearActuatorUp();
    }
    else {
        linearActuatorStop();
    }

}

public void linearActuatorDown(double power, double ticks) {
    if (Math.abs(linearActuator.getCurrentPosition()) > ticks ){
        linearActuatorDown();
    }
    else {
        linearActuatorStop();
    }

}


public void fireLauncher(){

        if (isLauncherOn==true) {
            rackgear.setPosition(1);
            launcherServoTimer.reset();

        }
    if (launcherServoTimer.milliseconds() > pullTimer && isLauncherOn == true) {
        rackgear.setPosition(0.1);

    }
}






    public void intakeFullSpeed(){
        intake.setPower(1);
    }
    public void intakeHalfPower(){
        intake.setPower(0.5);
    }
    public void intakeSlow(){
        intake.setPower(0.3);
    }
    public void outtakeHalfPower(){
        intake.setPower(-0.5);
    }

    public void outtakeFullSpeed(){
        intake.setPower(-1);
    }

    public void intakeStop(){
        intake.setPower(0);
    }
}
