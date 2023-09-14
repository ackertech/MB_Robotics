package org.firstinspires.ftc.teamcode.BNI_Team.Olivia;

import android.graphics.drawable.GradientDrawable;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.tensorflow.lite.task.core.vision.ImageProcessingOptions;

public class ProgrammingBot_Olivia extends MecanumDrive_Olivia{

    public HardwareMap hwBot = null;

    public BNO055IMU imu;

    public Orientation angles;

    public Acceleration gravity;

    public final double speed = .3;

    public final double tolerance = .4;

    public ProgrammingBot_Olivia(){}
    public void initRobot (HardwareMap hwMap){
        hwBot = hwMap;
        frontLeftMotor = hwBot.dcMotor.get("frontLeftMotor");
        frontRightMotor = hwBot.dcMotor.get("frontRightMotor");
        rearLeftMotor = hwBot.dcMotor.get("rearLeftMotor");
        rearRightMotor = hwBot.dcMotor.get("rearRightMotor");

        frontLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        rearLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
        rearRightMotor.setDirection(DcMotor.Direction.REVERSE);

        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


    }

    public void stopMotors (){
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        rearLeftMotor.setPower(0);
        rearRightMotor.setPower(0);

    }

}
