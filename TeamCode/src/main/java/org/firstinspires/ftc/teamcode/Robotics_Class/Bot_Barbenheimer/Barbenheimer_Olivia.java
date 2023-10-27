package org.firstinspires.ftc.teamcode.Robotics_Class.Bot_Barbenheimer;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Barbenheimer_Olivia extends DriveTrain_Olivia{

    //Variable for hardware mapping
    public HardwareMap hwBot = null;

    //Constructor
    public Barbenheimer_Olivia() {}

    //Method used to init robot
    public void initRobot (HardwareMap hwMap) {
        hwBot = hwMap;

        //mapping drivetrain variables to the values in driver hub
        frontLeftMotor = hwBot.dcMotor.get("front_Left_Motor");
        frontRightMotor = hwBot.dcMotor.get("front_Right_Motor");
        rearLeftMotor = hwBot.dcMotor.get("rear_Left_Motor");
        rearRightMotor = hwBot.dcMotor.get("rear_Right_Motor");

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

    }

}
