package org.firstinspires.ftc.teamcode.Base.Drivetrains;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class MecanumDrive {

    // Declare Variables for the Motors
    public DcMotor frontLeftMotor;
    public DcMotor frontRightMotor;
    public DcMotor rearLeftMotor;
    public DcMotor rearRightMotor;

    // These are motor variables from running with encoders (not power)
    public static final double TICKS_PER_ROTATION = 537.7;  //

    public IMU imu = null;
    public double headingTolerance = 2;
    public double currentHeading = 0;


    double gearRatio = 0.5;
    double wheelRadius = 1.9685;  // inches
    double powerPID;
    double powerNormPID;

    static final double COUNTS_PER_MOTOR_REV = 537.7 ;
    static final double DRIVE_GEAR_REDUCTION = 1.0 ;
    static final double WHEEL_DIAMETER_INCHES = 4.0 ;
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);

    // This is just required as part of the FIRST SDK.  Memorize it!!!
    public LinearOpMode linearOp = null;

    public void setLinearOp(LinearOpMode linearOp) {

        this.linearOp = linearOp;
    }



    // Default Constructors

    public MecanumDrive() {

    }

    // Constructor with parameters.

    public MecanumDrive(DcMotor fl, DcMotor fr, DcMotor rl, DcMotor rr) {

        frontLeftMotor = fl;
        frontRightMotor = fr;
        rearLeftMotor = rl;
        rearRightMotor = rr;

        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);     //Forward and reverse depends on builder and manufacture
        rearLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotor.Direction.FORWARD);
        rearRightMotor.setDirection(DcMotor.Direction.FORWARD);

        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);   //memorize
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // MUST HAVE RUN MODE

        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


    }


    // Reusable Method to Set the Motor Behavior or Run Modes from the Robot Class
    public void setMotorRunModes(DcMotor.RunMode mode) {

        frontLeftMotor.setMode(mode);
        frontRightMotor.setMode(mode);
        rearLeftMotor.setMode(mode);
        rearRightMotor.setMode(mode);
    }

    public void stopMotors() {
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        rearLeftMotor.setPower(0);
        rearRightMotor.setPower(0);
    }

    public void drivePID(double speed) {
        frontLeftMotor.setPower(speed);
        frontRightMotor.setPower(speed);
        rearLeftMotor.setPower(speed);
        rearRightMotor.setPower(speed);
    }

    public void driveForward(double speed) {
        frontLeftMotor.setPower(Math.abs(speed));
        frontRightMotor.setPower(Math.abs(speed));
        rearLeftMotor.setPower(Math.abs(speed));
        rearRightMotor.setPower(Math.abs(speed));
    }

    public void driveBackward(double speed) {
        frontLeftMotor.setPower(-Math.abs(speed));
        frontRightMotor.setPower(-Math.abs(speed));
        rearLeftMotor.setPower(-Math.abs(speed));
        rearRightMotor.setPower(-Math.abs(speed));

    }

    public void rotateLeft(double speed) {
        frontLeftMotor.setPower(-Math.abs(speed));
        frontRightMotor.setPower(Math.abs(speed));
        rearLeftMotor.setPower(-Math.abs(speed));
        rearRightMotor.setPower(Math.abs(speed));

    }

    public void rotateRight(double speed) {
        frontLeftMotor.setPower(Math.abs(speed));
        frontRightMotor.setPower(-Math.abs(speed));
        rearLeftMotor.setPower(Math.abs(speed));
        rearRightMotor.setPower(-Math.abs(speed));

    }

    public void strafeLeft(double speed) {
        frontLeftMotor.setPower(-Math.abs(speed));
        frontRightMotor.setPower(Math.abs(speed));
        rearLeftMotor.setPower(Math.abs(speed));
        rearRightMotor.setPower(-Math.abs(speed));

    }

    public void strafeRight(double speed) {
        frontLeftMotor.setPower(Math.abs(speed));
        frontRightMotor.setPower(-Math.abs(speed));
        rearLeftMotor.setPower(-Math.abs(speed));
        rearRightMotor.setPower(Math.abs(speed));
    }


    // ****** Overloaded methods for driving distance with gyro *****

    public void gyroCorrection(double speed, double targetAngle) {
        imu.resetYaw();
        currentHeading = getHeading();
        if (currentHeading >= targetAngle + headingTolerance && linearOp.opModeIsActive()) {
            while (currentHeading >= targetAngle + headingTolerance && linearOp.opModeIsActive()) {
                rotateRight(speed);

                currentHeading = getHeading();
                linearOp.telemetry.addData("Current Angle: ", currentHeading);
                linearOp.telemetry.addData("Target Angle: ", targetAngle);
                linearOp.telemetry.update();
            }
        } else if (currentHeading <= targetAngle - headingTolerance && linearOp.opModeIsActive()) ;
        {
            while (currentHeading <= targetAngle - headingTolerance && linearOp.opModeIsActive()) {
                rotateLeft(speed);

                currentHeading = getHeading();
                linearOp.telemetry.addData("Current Angle: ", currentHeading);
                linearOp.telemetry.addData("Target Angle: ", targetAngle);
                linearOp.telemetry.update();
            }
        }

        stopMotors();
        currentHeading = getHeading();
    }


    public double getHeading() {
        YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
        return orientation.getYaw(AngleUnit.DEGREES);
    }


    // Consolidated Method (in Beta Testing) for combining all mecanum movements
    public void driveDirection(double speed, double rotations, String direction) {

        double ticks = rotations * TICKS_PER_ROTATION;
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        if (direction.equals("FWD")) {
            while ((Math.abs(frontLeftMotor.getCurrentPosition()) < ticks) && linearOp.opModeIsActive()) {
                driveForward(speed);
            }
            stopMotors();
        } else if (direction.equals("RWD")) {
            while ((Math.abs(frontLeftMotor.getCurrentPosition()) < ticks) && linearOp.opModeIsActive()) {
                driveBackward(speed);
            }
            stopMotors();
        } else if (direction.equals("STR")) {
            while ((Math.abs(frontLeftMotor.getCurrentPosition()) < ticks) && linearOp.opModeIsActive()) {
                strafeRight(speed);
            }
            stopMotors();
        } else if (direction.equals("STL")) {
            while ((Math.abs(frontLeftMotor.getCurrentPosition()) < ticks) && linearOp.opModeIsActive()) {
                strafeLeft(speed);
            }
            stopMotors();
        } else if (direction.equals("RR")) {
            while ((Math.abs(frontLeftMotor.getCurrentPosition()) < ticks) && linearOp.opModeIsActive()) {
                rotateRight(speed);
            }
            stopMotors();
        } else if (direction.equals("RL")) {
            while ((Math.abs(frontLeftMotor.getCurrentPosition()) < ticks) && linearOp.opModeIsActive()) {
                rotateLeft(speed);
            }
            stopMotors();
        }
    }

    public void accelerate(double minspeed, double maxspeed, double rotations) {

        double ticks = rotations * TICKS_PER_ROTATION;
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        double currentPower = minspeed;
        double minPower = 0.20;
        double tolerance = 0.97;

        while ((Math.abs(frontLeftMotor.getCurrentPosition()) < ticks) && linearOp.opModeIsActive()) {
            if (currentPower < (maxspeed * tolerance)) {
                driveForward(minspeed+currentPower);
                currentPower += .001;
                linearOp.telemetry.addData("Front Lef Motor: ", frontLeftMotor.getPower());
                linearOp.telemetry.addData("Current Power Var: ", currentPower);
                linearOp.telemetry.addData("Encoder Counts: ", frontLeftMotor.getCurrentPosition());
                linearOp.telemetry.update();
            } else {
                driveForward(maxspeed);
            }
        }
        stopMotors();

    }

    public double normalizePower(double value, double orig_max ) {
        double orig_min = 0;
        double new_min = 0.20;
        double new_max = 0.99;
        return powerNormPID =((powerPID -orig_min)/(orig_max -orig_min)) * (new_max -new_min) + new_min;
    }

    public double convDistancetoTicks(double dist) {
        double rotations, ticks;
              rotations  =  dist / (2 * Math.PI * gearRatio * wheelRadius );
              ticks = rotations * TICKS_PER_ROTATION;
     return ticks;
    }

    public void drivePID(double rotations, double Kp, double Ki, double Kd) {

        //Set Motor Run Modes
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Timer Variables
        ElapsedTime timer = new ElapsedTime();
        timer.reset();

        // Declare and Initialize variables
        double currentPosition = Math.abs(frontLeftMotor.getCurrentPosition());
        double targetPosition = rotations * TICKS_PER_ROTATION;
        double integralSum = 0;
        double error;
        double previousPosition = 0;
        double lastError = 0;
        double derivative;

        while ( currentPosition < targetPosition && linearOp.opModeIsActive() ) {

            currentPosition = Math.abs(frontLeftMotor.getCurrentPosition());
            error = targetPosition - currentPosition;

            // Calculate the Derivative
            derivative = (error - lastError) / timer.seconds();

            // Calculate the Integral Sum
            integralSum =  integralSum + (error * timer.seconds());

            //Increasing Kp makes robot approach target faster and lead to overshooting target
            //Increasing kd makes the approach approach the target slower
            powerPID = ( Kp * error) + (Ki * integralSum) + (Kd * derivative) ;
            powerNormPID =  normalizePower(powerPID, targetPosition);

            //Drive Forward
            drivePID(powerNormPID);
            lastError = error;

            linearOp.telemetry.addData("PID Power:", powerPID);
            linearOp.telemetry.addData("PID Normalized Power:", powerNormPID);
            linearOp.telemetry.addData("FL Power:", frontLeftMotor.getPower());
            linearOp.telemetry.addData("Current Position:", currentPosition);
            linearOp.telemetry.addData("Target Positionr:", targetPosition);
            linearOp.telemetry.addData("Previous Position:", previousPosition);
            linearOp.telemetry.update();

        }
        stopMotors();
    }

    public void driveForwardDistancePID(double inches, double Kp, double Ki, double Kd) {

        //Set Motor Run Modes
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Timer Variables
        ElapsedTime timer = new ElapsedTime();
        timer.reset();

        // Declare and Initialize variables
        double currentPosition = Math.abs(frontLeftMotor.getCurrentPosition());
        double targetPosition = convDistancetoTicks(inches);
        double integralSum = 0;
        double error;
        double previousPosition = 0;
        double lastError = 0;
        double derivative;

        while ( currentPosition < targetPosition && linearOp.opModeIsActive() ) {

            currentPosition = Math.abs(frontLeftMotor.getCurrentPosition());
            error = targetPosition - currentPosition;

            // Calculate the Derivative
            derivative = (error - lastError) / timer.seconds();

            // Calculate the Integral Sum
            integralSum =  integralSum + (error * timer.seconds());

            //Increasing Kp makes robot approach target faster and lead to overshooting target
            //Increasing kd makes the approach approach the target slower
            powerPID = ( Kp * error) + (Ki * integralSum) + (Kd * derivative) ;
            powerNormPID =  normalizePower(powerPID, targetPosition);

            //Drive Forward
            driveForward(powerNormPID);
            lastError = error;

            linearOp.telemetry.addData("PID Power:", powerPID);
            linearOp.telemetry.addData("PID Normalized Power:", powerNormPID);
            linearOp.telemetry.addData("FL Power:", frontLeftMotor.getPower());
            linearOp.telemetry.addData("Current Position:", currentPosition);
            linearOp.telemetry.addData("Target Positionr:", targetPosition);
            linearOp.telemetry.addData("Previous Position:", previousPosition);
            linearOp.telemetry.update();

        }
        stopMotors();
    }



}