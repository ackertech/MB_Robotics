package org.firstinspires.ftc.teamcode.Robotics_Class.Bot_Barbenheimer;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Barbenheimer_Olivia extends DriveTrain_Olivia{

    //Variable for hardware mapping
    public HardwareMap hwBot = null;

    //Constructor
    public Barbenheimer_Olivia() {}

    //Method used to init robot
    public void initRobot (HardwareMap hwMap) {
        hwBot = hwMap;

        //mapping drivetrain variables to the values in driver hub
        frontLeftMotor = hwBot.dcMotor.get("front_left_motor");
        frontRightMotor = hwBot.dcMotor.get("front_right_motor");
        rearLeftMotor = hwBot.dcMotor.get("rear_left_motor");
        rearRightMotor = hwBot.dcMotor.get("rear_right_motor");


        //setting direction of the motor
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rearLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rearRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);



        //motor behavior(w/ or w/o encoders)
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //how motors act with zero power
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        penguinSlide = hwBot.dcMotor.get("penguin_slide");
        penguinSlide.setDirection(DcMotorSimple.Direction.FORWARD);
        penguinSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        iglooTurn = hwBot.dcMotor.get("igloo_turn");
        iglooTurn.setDirection(DcMotorSimple.Direction.FORWARD);
        iglooTurn.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        bearLift = hwBot.servo.get("bear_lift");
        bearLift.setDirection(Servo.Direction.FORWARD);

    }
    public void penguinOut(double power) {
        penguinSlide.setPower(Math.abs(power));
    }
    public void penguinIn(double power){
        penguinSlide.setPower(-Math.abs(power));
    }
    public void stopPenguin(){
        penguinSlide.setPower(0);
    }

    public void iglooLeft(double power){iglooTurn.setPower(-Math.abs(power));}
    public void iglooRight(double power){iglooTurn.setPower(Math.abs(power));}
    public void stopIgloo(){iglooTurn.setPower(0);}

    public void bearUp(double position){bearLift.setPosition(1);}
    public void bearDown(double position){bearLift.setPosition(.5);}


}
