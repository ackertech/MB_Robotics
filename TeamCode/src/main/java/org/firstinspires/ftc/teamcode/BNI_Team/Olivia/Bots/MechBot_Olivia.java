package org.firstinspires.ftc.teamcode.BNI_Team.Olivia.Bots;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.BNI_Team.Olivia.DriveTrains.MecanumDrive2_Olivia;


public class MechBot_Olivia extends MecanumDrive2_Olivia {

    // Robot Hardware Constructors
    public HardwareMap hwBot  =  null;

    // Declaration of Timer Variables
    public ElapsedTime currentTime = new ElapsedTime();

    public ElapsedTime timer = new ElapsedTime();
    double waitTime = 2.0;

    // Declaration of Gyro Variables
    public BNO055IMU imu;
    public Orientation angles;
    public Acceleration gravity;
    public final double SPEED = .3;
    public final double TOLERANCE = .4;

    //MechBot Constructor
    public MechBot_Olivia() {}

    // Initializing Hardware for Robot
    public void initRobot(HardwareMap hwMap) {

        hwBot = hwMap;

        // Define Motors for Robot
        frontLeftMotor = hwBot.dcMotor.get("front_left_motor");
        frontRightMotor = hwBot.dcMotor.get("front_right_motor");
        rearLeftMotor = hwBot.dcMotor.get("rear_left_motor");
        rearRightMotor = hwBot.dcMotor.get("rear_right_motor");

        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        rearLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotor.Direction.FORWARD);
        rearRightMotor.setDirection(DcMotor.Direction.FORWARD);


        //Initialize Motor Run Mode for Robot
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //Timer Reset
        currentTime.reset();

    }









}
