package org.firstinspires.ftc.teamcode.Robotics_Class.Bot_BruceWayne.Connor_Class;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class SixWheelBot_Connor extends SixWheelDrive_Connor {

    public HardwareMap hwBot = null;

    public void initRobot(HardwareMap hwMap) {
        hwBot = hwMap;


        frontLeftMotor = hwBot.dcMotor.get("frontLeftMotor"); //Port 0
        frontRightMotor = hwBot.dcMotor.get("frontRightMotor");// Port 2
        rearLeftMotor = hwBot.dcMotor.get("rearLeftMotor");// Port 1
        rearRightMotor = hwBot.dcMotor.get("rearRightMotor");// Port 3


        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotor.Direction.FORWARD);
        rearLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        rearRightMotor.setDirection(DcMotor.Direction.FORWARD);



        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


    }
}
