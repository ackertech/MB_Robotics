package org.firstinspires.ftc.teamcode.iLab.Bot_Connor.CompetitionRobot.Robots;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.iLab.Bot_Connor.Drivetrains.MecanumDrive_Connor;

public class CompetitionBot extends MecanumDrive_Connor {



    public HardwareMap hwBot = null;

    public ElapsedTime currentTime = new ElapsedTime();

    public ElapsedTime timer = new ElapsedTime();


    public BNO055IMU imu;
    public Orientation angles;
    public Acceleration gravity;
    public final double SPEED = .3;
    public final double TOLERANCE = .4;

    public DcMotor linearSlide;
    public DcMotor lazy_Susan;
    public Servo claw = null;

    public CompetitionBot() {}


    public void initRobot(HardwareMap hwMap) {

        hwBot = hwMap;

        frontLeftMotor = hwBot.dcMotor.get("front_left_motor");
        frontRightMotor = hwBot.dcMotor.get("front_right_motor");
        rearLeftMotor = hwBot.dcMotor.get("rear_left_motor");
        rearRightMotor = hwBot.dcMotor.get("rear_right_motor");

        frontLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        rearLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
        rearRightMotor.setDirection(DcMotor.Direction.REVERSE);

        //Initialize Motor Run Mode for Robot
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        linearSlide = hwBot.dcMotor.get("linearSlide");
        linearSlide.setDirection(DcMotorSimple.Direction.REVERSE);
        linearSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        linearSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        lazy_Susan = hwBot.dcMotor.get("lazySusan");
        lazy_Susan.setDirection(DcMotor.Direction.FORWARD);
        lazy_Susan.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        lazy_Susan.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lazy_Susan.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        claw = hwBot.get(Servo.class, "claw");
        claw.setDirection(Servo.Direction.FORWARD);

        //45 degrees

        //Timer Reset
        currentTime.reset();


        BNO055IMU.Parameters parametersimu = new BNO055IMU.Parameters();
        parametersimu.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parametersimu.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parametersimu.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode

        parametersimu.loggingEnabled = true;
        parametersimu.loggingTag = "IMU";
        parametersimu.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        imu = hwBot.get(BNO055IMU.class, "imu");
        imu.initialize(parametersimu);

    }

    public void gyroCorrection (double speed, double angle) {

        angles = imu.getAngularOrientation(
                AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        if (angles.firstAngle >= angle + TOLERANCE && LinearOp.opModeIsActive()) {
            while (angles.firstAngle >=  angle + TOLERANCE && LinearOp.opModeIsActive()) {
                rotateRight(speed);
                angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

               LinearOp.telemetry.addData("Current Angle Est: ", angles.firstAngle);
            }
        }
        else if (angles.firstAngle <= angle - TOLERANCE && LinearOp.opModeIsActive()) {
            while (angles.firstAngle <= angle - TOLERANCE && LinearOp.opModeIsActive()) {
                rotateLeft(speed);
                angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

                LinearOp.telemetry.addData("Current Angle Est:" , angles.firstAngle);
            }
        }
        stopMotors();

        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
    }


    public void gyroReset () {
        BNO055IMU.Parameters parametersimu = new BNO055IMU.Parameters();
        imu.initialize(parametersimu);
    }


    public void lazySusanLeft (double power) {
        lazy_Susan.setPower(Math.abs(power));
    }

    public void lazySusanRight (double power) {
        lazy_Susan.setPower(-Math.abs(power));
    }

    public void autoSusanLeft (double power, double rotations) {lazy_Susan.setPower(Math.abs(power));}

    public void autoSusanRight (double power, double rotations) {lazy_Susan.setPower(-Math.abs(power));}

    public void lazySusanStop(){
        lazy_Susan.setPower(0);
    }

    public void linearSlideUp (double power) {
        linearSlide.setPower(-Math.abs(power));
    }

    public void linearSlideDown (double power) {linearSlide.setPower(Math.abs(power));
    }



    public void linearSlideUp (double power, double rotations)  {
        double ticks = rotations * (1) * TICKS_PER_ROTATION;
        linearSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        while (Math.abs(linearSlide.getCurrentPosition()) < ticks && LinearOp.opModeIsActive()) {
            linearSlideUp(power);
        }
        linearSlideStop();
    }

    public void linearSlideDown (double power, double rotations) {
        double ticks = rotations * TICKS_PER_ROTATION;
        linearSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        while (Math.abs(linearSlide.getCurrentPosition())< ticks && LinearOp.opModeIsActive()) {
            linearSlideDown(power);
        }
        linearSlideStop();
    }
//hi

    public void linearSlideStop() {
        linearSlide.setPower(0);
    }

    public void clawOpen () {claw.setPosition(0);}

    public void clawClose () {claw.setPosition(.25);}


}
