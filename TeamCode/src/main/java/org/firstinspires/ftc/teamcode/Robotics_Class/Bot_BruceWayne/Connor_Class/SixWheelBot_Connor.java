package org.firstinspires.ftc.teamcode.Robotics_Class.Bot_BruceWayne.Connor_Class;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class SixWheelBot_Connor extends SixWheelDrive_Connor {

    public HardwareMap hwBot = null;
    public DcMotor lazySusan;
//    public DcMotor candyLauncherLeft;
//    public DcMotor candyLauncherRight;

    public void initRobot(HardwareMap hwMap) {
        hwBot = hwMap;

                                                            //MAYBE.....:
        frontLeftMotor = hwBot.dcMotor.get("front_left_motor"); //Port 0
        frontRightMotor = hwBot.dcMotor.get("front_right_motor");// Port 2
        rearLeftMotor = hwBot.dcMotor.get("rear_left_motor");// Port 1
        rearRightMotor = hwBot.dcMotor.get("rear_right_motor");// Port 3

        lazySusan = hwBot.dcMotor.get("lazy_susan");

//        candyLauncherLeft = hwBot.dcMotor.get("candy_launcher_left"); //REVERSE
//        candyLauncherRight = hwBot.dcMotor.get("candy_launcher_right"); //FORWARD


        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rearLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rearRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        lazySusan.setDirection(DcMotorSimple.Direction.FORWARD);

//        candyLauncherLeft.setDirection(DcMotorSimple.Direction.REVERSE);
//        candyLauncherRight.setDirection(DcMotorSimple.Direction.FORWARD);




        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);




        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rearLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rearRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        lazySusan.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

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
//    }
}
