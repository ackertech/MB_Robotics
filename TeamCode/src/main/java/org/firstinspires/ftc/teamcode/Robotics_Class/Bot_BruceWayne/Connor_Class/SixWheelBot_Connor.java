package org.firstinspires.ftc.teamcode.Robotics_Class.Bot_BruceWayne.Connor_Class;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class SixWheelBot_Connor extends SixWheelDrive_Connor {

    public HardwareMap hwBot = null;
 //  public DcMotor lazySusan;

    public Servo platformRotatorLeft = null;
    public Servo platformRotatorRight = null;

    public Servo ballonPopper = null;

    public Servo discPusherArm = null;

    public Servo platformStopperArm = null;
    public DcMotorEx linearSlideMotorA;
    public DcMotorEx linearSLideMotorB;

    boolean isLauncherOn = false;

    public int pullTimer = 350;
    public int rapidPushTimer = 150; //milliseconds
    public int rapidPullTimer = 175;

    public DcMotorEx intake;

//    public VoltageSensor voltageSensor = null;
//
//    double flywheelVelocityPerVolt = 42900 / voltageSensor.getVoltage();

    public double launchCoefficient;
    public Servo rackgear = null;

    public Servo catapult = null;

    public ElapsedTime launcherServoTimer;

    DcMotorEx flywheel;



  //  public DcMotor linearActuator;
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


        flywheel = hwBot.get(DcMotorEx.class,"candy_launcher_left");
        flywheel.setDirection(DcMotorSimple.Direction.REVERSE);
//
        flywheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        flywheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        flywheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

       // lazySusan = hwBot.dcMotor.get("lazy_susan");

        intake = hwBot.get(DcMotorEx.class,"intake");

//        candyLauncherLeft = hwBot.dcMotor.get("candy_launcher_left"); //REVERSE
//        candyLauncherRight = hwBot.dcMotor.get("candy_launcher_right"); //FORWARD


        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rearLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rearRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);

       // lazySusan.setDirection(DcMotorSimple.Direction.FORWARD);


//        candyLauncherLeft.setDirection(DcMotorSimple.Direction.REVERSE);
//        candyLauncherRight.setDirection(DcMotorSimple.Direction.FORWARD);




        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);




        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rearLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rearRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

   //     lazySusan.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        intake.setDirection(DcMotorSimple.Direction.REVERSE);
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        ballonPopper = hwBot.get(Servo.class,"rackgear_servo");
        ballonPopper.setDirection(Servo.Direction.FORWARD);

       catapult = hwBot.get(Servo.class,"catapult");
        catapult.setDirection(Servo.Direction.FORWARD);


        discPusherArm = hwBot.get(Servo.class,"discPusher");
       discPusherArm.setDirection(Servo.Direction.FORWARD);

        platformRotatorLeft = hwBot.get(Servo.class,"platformRotator");
        platformRotatorLeft.setDirection(Servo.Direction.FORWARD);
        platformRotatorRight = hwBot.get(Servo.class, "platformRotatorRight");
        platformRotatorRight.setDirection(Servo.Direction.REVERSE);




//
//        linearActuator = hwBot.dcMotor.get("sidewaysLinearMotor"); //Expantion Hub Port 0
//        linearActuator.setDirection(DcMotorSimple.Direction.FORWARD);
//        linearActuator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        linearActuator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        linearActuator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        linearSlideMotorA = hwBot.get(DcMotorEx.class,"linearSlideA");
        linearSlideMotorA.setDirection(DcMotorSimple.Direction.FORWARD);
        linearSlideMotorA.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        linearSlideMotorA.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearSlideMotorA.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        linearSLideMotorB = hwBot.get(DcMotorEx.class,"linearSlideB");
        linearSLideMotorB.setDirection(DcMotorSimple.Direction.REVERSE);
        linearSLideMotorB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        linearSLideMotorB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearSLideMotorB.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//

//        candyLauncherLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//        candyLauncherRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);


    }


//    public void lazySusanLeft (double power) {
//        lazySusan.setPower(Math.abs(power));
//    }c
//
//    public void lazySusanRight (double power) {
//        lazySusan.setPower(-Math.abs(power));
//    }
//
//    public void lazySusanStop(){
//        lazySusan.setPower(0);
//    }

//    public void launcherOn(){
//        candyLauncherRight.setPower(1);
//        candyLauncherLeft.setPower(1);
//    }
//
//    public void launcherOff(){
//        candyLauncherLeft.setPower(0);
//        candyLauncherRight.setPower(0);



    public void linearSlideUp(double power){
        linearSlideMotorA.setPower(power);
        linearSLideMotorB.setPower(power);
    }
    public void linearSlideDown(double power){
        linearSlideMotorA.setPower(power);
        linearSLideMotorB.setPower(power);
    }
    public void linearSlideStop(){
        linearSlideMotorA.setPower(0);
        linearSLideMotorB.setPower(0);
    }

    public void platformLaunchPosition(){
        platformRotatorLeft.setPosition(0.35);
        platformRotatorRight.setPosition(0.35);
    }

    public void platformIntakePosition(){
        platformRotatorLeft.setPosition(0);
        platformRotatorRight.setPosition(0);
    }

//    public void linearActuatorDown(){
//        linearActuator.setPower(-100);
//    }
//
//    public void linearActuatorUp() {
//        linearActuator.setPower(100);
//    }
//    public void linearActuatorStop(){
//        linearActuator.setPower(0);
//    }

    public void linearSlideUp(double power, double ticks) {

        if (Math.abs(linearSlideMotorA.getCurrentPosition()) < ticks ){
            linearSlideUp(power);
        }
        else {
            linearSlideStop();
        }

    }

    public void linearSlideDown(double power, double ticks) {
        if (Math.abs(linearSlideMotorA.getCurrentPosition()) > ticks ){
            linearSlideDown(power);
        }
        else {
            linearSlideStop();
        }

    }

//
////    }
//public void linearActuatorUp(double power, double ticks) {
//
//    if (Math.abs(linearActuator.getCurrentPosition()) < ticks ){
//        linearActuatorUp();
//    }
//    else {
//        linearActuatorStop();
//    }
//
//}
//
//public void linearActuatorDown(double power, double ticks) {
//    if (Math.abs(linearActuator.getCurrentPosition()) > ticks ){
//        linearActuatorDown();
//    }
//    else {
//        linearActuatorStop();
//    }

//}


public void fireLauncher(){

        if (isLauncherOn==true) {
            discPusherArm.setPosition(1);
            launcherServoTimer.reset();

        }
    if (launcherServoTimer.milliseconds() > pullTimer && isLauncherOn == true) {
        discPusherArm.setPosition(0.1);

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
