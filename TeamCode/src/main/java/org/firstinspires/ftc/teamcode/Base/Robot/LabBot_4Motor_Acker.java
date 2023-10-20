package org.firstinspires.ftc.teamcode.Base.Robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.Base.Drivetrains.LabBot_FourMotorDrive_Basic_Acker;

public class LabBot_4Motor_Acker extends LabBot_FourMotorDrive_Basic_Acker {

    // Instance Variable for Hardware Mapping
    public HardwareMap hwBot = null;

    // Constructor that we will call to instantiate or build actual robot
    public LabBot_4Motor_Acker() {}

    // Method we will call to Initiatlize the Robot Hardware Mapping
    public void initRobot (HardwareMap hwMap) {

        // Assigning the hardware parameter to our instance variable above
        hwBot = hwMap;

        // Mapping our drivetrain instance variables to the values we will add to our robot controller
        frontLeftMotor = hwBot.dcMotor.get("front_left_motor");
        frontRightMotor = hwBot.dcMotor.get("front_right_motor");
        rearLeftMotor = hwBot.dcMotor.get("rear_left_motor");
        rearRightMotor = hwBot.dcMotor.get("rear_right_motor");

        // Motr Behavior: Setting the direction of the motor spin with positive power
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rearLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rearRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        // Motor Behavior: Setting how we want our motor to run (dumb vs intelligent)...
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Motor Behavior: Setting how we want our motor to behave with zero power
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // End our initiatization method
    }

    // End of our class
}
