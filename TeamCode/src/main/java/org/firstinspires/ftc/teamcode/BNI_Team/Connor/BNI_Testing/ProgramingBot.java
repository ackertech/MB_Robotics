package org.firstinspires.ftc.teamcode.BNI_Team.Connor.BNI_Testing;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.BNI_Team.Connor.BNI_Testing.RipOffRoadrunner_Adapted.RipOffRoadrunner_Adapted_MecanumDrive;


public class ProgramingBot extends RipOffRoadrunner_Adapted_MecanumDrive {

    public HardwareMap hwBot = null;

    public ElapsedTime currentTime = new ElapsedTime();

    public ElapsedTime timer = new ElapsedTime();

    RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.RIGHT;
    RevHubOrientationOnRobot.UsbFacingDirection  usbDirection  = RevHubOrientationOnRobot.UsbFacingDirection.UP;
    RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);


//
//    public BNO055IMU imu;
//    public Orientation angles;
//    public Acceleration gravity;
//    public final double SPEED = .3;
//    public final double TOLERANCE = .4;

    public ProgramingBot(){}

    public void initRobot(HardwareMap hwMap) {
        hwBot = hwMap;

        frontLeftMotor = hwBot.dcMotor.get("front_left_motor");
        frontRightMotor = hwBot.dcMotor.get("front_right_motor");
        rearLeftMotor = hwBot.dcMotor.get("rear_left_motor");
        rearRightMotor = hwBot.dcMotor.get("rear_right_motor");

        leftEncoder = frontRightMotor;
        rightEncoder = rearRightMotor;
        centerEncoder = rearLeftMotor;

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


        currentTime.reset();

      imu = hwBot.get(IMU.class,"imu");
      imu.initialize(new IMU.Parameters(orientationOnRobot));


//        BNO055IMU.Parameters parametersimu = new BNO055IMU.Parameters();
//        parametersimu.angleUnit = BNO055IMU.AngleUnit.DEGREES;
//        parametersimu.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
//        parametersimu.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
//
//        parametersimu.loggingEnabled = true;
//        parametersimu.loggingTag = "IMU";
//        parametersimu.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
//
//        imu = hwBot.get(BNO055IMU.class, "imu");
//        imu.initialize(parametersimu);


    }

    public void stopMotors(){
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        rearLeftMotor.setPower(0);
        rearRightMotor.setPower(0);
    }

//    public void gyroCorrection (double speed, double angle) {
//
//        angles = imu.getAngularOrientation(
//                AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
//
//        if (angles.firstAngle >= angle + TOLERANCE && LinearOp.opModeIsActive()) {
//            while (angles.firstAngle >=  angle + TOLERANCE && LinearOp.opModeIsActive()) {
//                rotateRight(speed);
//                angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
//
//                LinearOp.telemetry.addData("Current Angle Est: ", angles.firstAngle);
//            }
//        }
//        else if (angles.firstAngle <= angle - TOLERANCE && LinearOp.opModeIsActive()) {
//            while (angles.firstAngle <= angle - TOLERANCE && LinearOp.opModeIsActive()) {
//                rotateLeft(speed);
//                angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
//
//                LinearOp.telemetry.addData("Current Angle Est:" , angles.firstAngle);
//            }
//        }
//        stopMotors();
//
//        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
//    }
//
//
//    public void gyroReset () {
//        BNO055IMU.Parameters parametersimu = new BNO055IMU.Parameters();
//        imu.initialize(parametersimu);
//    }
//



}
