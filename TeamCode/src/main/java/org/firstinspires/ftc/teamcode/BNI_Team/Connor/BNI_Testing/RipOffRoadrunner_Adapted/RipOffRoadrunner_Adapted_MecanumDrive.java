package org.firstinspires.ftc.teamcode.BNI_Team.Connor.BNI_Testing.RipOffRoadrunner_Adapted;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class RipOffRoadrunner_Adapted_MecanumDrive  {





    public DcMotor frontLeftMotor;
        public DcMotor frontRightMotor;
        public DcMotor rearLeftMotor;
        public DcMotor rearRightMotor;

        public DcMotor leftEncoder;
        public DcMotor rightEncoder;
        public DcMotor centerEncoder;





        public LinearOpMode LinearOp = null;

        public static final double TICKS_PER_ROTATION = 386.3;
        public static final double ODO_TICKS_PER_ROTATION = 2000;

        // Instance Variables for IMU
        public IMU imu = null;
        public double headingTolerance = 0.5;
        public double currentHeading = 0;

        public enum driveDirections {
            STOP,
            DRIVE_FORWARD, DRIVE_BACK, STRAFE_LEFT, STRAFE_RIGHT, DIAGONAL_LEFT_FORWARD, DIAGONAL_RIGHT_FORWARD,DIAGONAL_LEFT_BACK,DIAGONAL_RIGHT_BACK
        }
        driveDirections driveDirection = driveDirections.STOP;


        public RipOffRoadrunner_Adapted_MecanumDrive() {

        }

        //********  Helper Methods for the Class  ************

        // Helper Method for Linear Op
        public void setLinearOp(LinearOpMode LinearOp) {this.LinearOp = LinearOp;}

        // Helper method to set the run modes for all motors at the same
        public void setMotorRunModes(DcMotor.RunMode mode) {

            frontLeftMotor.setMode(mode);
            frontRightMotor.setMode(mode);
            rearLeftMotor.setMode(mode);
            rearRightMotor.setMode(mode);
        }

        //******  Methods using IMU / Gyro  **************

        // Helper Method to Get Heading using IMU
        public double getHeading() {
            YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
            return orientation.getYaw(AngleUnit.DEGREES);


        }

        // Helper Method to reset the IMU Yaw Heading
        public void resetHeading() {
            imu.resetYaw();
        }

        // Method that corrects the robots original heading.
        // Method assumes the heading to correct to has been set outside of this method
    //
        public void gyroTurn(double speed, double targetAngle) {
            currentHeading = getHeading();
            if (currentHeading >= targetAngle + headingTolerance && LinearOp.opModeIsActive()) {
                while (currentHeading >= targetAngle + headingTolerance && LinearOp.opModeIsActive()) {
                    rotateRight(speed);

                    currentHeading = getHeading();
                    LinearOp.telemetry.addData("Current Angle: ", currentHeading);
                    LinearOp.telemetry.addData("Target Angle: ", targetAngle);
                    LinearOp.telemetry.update();
                }
            } else if (currentHeading <= targetAngle - headingTolerance && LinearOp.opModeIsActive()) ;
            {
                while (currentHeading <= targetAngle - headingTolerance && LinearOp.opModeIsActive()) {
                    rotateLeft(speed);

                    currentHeading = getHeading();
                    LinearOp.telemetry.addData("Current Angle: ", currentHeading);
                    LinearOp.telemetry.addData("Target Angle: ", targetAngle);
                    LinearOp.telemetry.update();
                }
            }
            stopMotors();
            currentHeading = getHeading();
        }
        // Method allows robot to rotate using the IMU Yaw Heading
        // Method resets the heading so there is a full rotation based on targetAngle
    /* COMBINED GYRO PATH AND CORRECTION FROM OLD VERSION INTO GYRO TURN - They were literally the same thing*/
        public void rotateByGyro(double speed, double targetAngle) {
            resetHeading();
            currentHeading = getHeading();
            if (currentHeading >= targetAngle + headingTolerance && LinearOp.opModeIsActive()) {
                while (currentHeading >= targetAngle + headingTolerance && LinearOp.opModeIsActive()) {
                    rotateRight(speed);

                    currentHeading = getHeading();
                    LinearOp.telemetry.addData("Current Angle: ", currentHeading);
                    LinearOp.telemetry.addData("Target Angle: ", targetAngle);
                    LinearOp.telemetry.update();
                }
            } else if (currentHeading <= targetAngle - headingTolerance && LinearOp.opModeIsActive()) ;
            {
                while (currentHeading <= targetAngle - headingTolerance && LinearOp.opModeIsActive()) {
                    rotateLeft(speed);

                    currentHeading = getHeading();
                    LinearOp.telemetry.addData("Current Angle: ", currentHeading);
                    LinearOp.telemetry.addData("Target Angle: ", targetAngle);
                    LinearOp.telemetry.update();
                }
            }
            stopMotors();
            currentHeading = getHeading();
        }

        //pretty useless, would not recommend using - outdated
    //resets encoder value (heading) every time, results in less acurate localization

        // ************** Basic Drive Method ***********************

        public void stopMotors() {
            frontLeftMotor.setPower(0);
            frontRightMotor.setPower(0);
            rearRightMotor.setPower(0);
            rearLeftMotor.setPower(0);
        }

        public void driveForward(double speed) {
            frontLeftMotor.setPower(-speed);
            frontRightMotor.setPower(-speed);
            rearLeftMotor.setPower(-speed);
            rearRightMotor.setPower(-speed);
        }

        public void driveBack(double speed) {
            frontLeftMotor.setPower(speed);
            frontRightMotor.setPower(speed);
            rearLeftMotor.setPower(speed);
            rearRightMotor.setPower(speed);
        }

        public void rotateLeft(double speed) {
            frontLeftMotor.setPower(speed);
            frontRightMotor.setPower(-speed);
            rearLeftMotor.setPower(speed);
            rearRightMotor.setPower(-speed);

        }

        public void rotateRight(double speed) {
            frontLeftMotor.setPower(-speed);
            frontRightMotor.setPower(speed);
            rearLeftMotor.setPower(-speed);
            rearRightMotor.setPower(speed);

        }

        public void strafeLeft(double speed) {
            frontLeftMotor.setPower(speed);
            frontRightMotor.setPower(-speed);
            rearLeftMotor.setPower(-speed);
            rearRightMotor.setPower(speed);

        }

        public void strafeRight(double speed) {
            frontLeftMotor.setPower(-speed);
            frontRightMotor.setPower(speed);
            rearLeftMotor.setPower(speed);
            rearRightMotor.setPower(-speed);
        }

        public void diagonalLeftForward(double speed) {
            frontRightMotor.setPower(-speed);
            rearLeftMotor.setPower(-speed);
        }

        public void diagonalRightForward(double speed) {
            frontLeftMotor.setPower(-speed);
            rearRightMotor.setPower(-speed);
        }
        public void diagonalLeftBack(double speed) {
            frontLeftMotor.setPower(speed);
            rearRightMotor.setPower(speed);
        }

        public void diagonalRightBack(double speed) {
            frontRightMotor.setPower(speed);
            rearLeftMotor.setPower(speed);
        }

        // ************** Basic Drive Method ***********************

        public void driveForward(double speed, double rotations) {

            double ticks = rotations  * TICKS_PER_ROTATION;
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            while ((Math.abs(frontLeftMotor.getCurrentPosition() ) < ticks && LinearOp.opModeIsActive()) ) {
                driveForward(speed);
            }
            stopMotors();
        }

        public void driveBack (double speed, double rotations) {
            double ticks = rotations  * TICKS_PER_ROTATION;
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            while ((Math.abs(frontLeftMotor.getCurrentPosition() ) < ticks && LinearOp.opModeIsActive() ) ){
                driveBack(speed);
            }
            stopMotors();

        }

        public void strafeLeft(double speed, double rotations) {
            double ticks = rotations * TICKS_PER_ROTATION;
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            while ((Math.abs(frontLeftMotor.getCurrentPosition() ) < ticks && LinearOp.opModeIsActive()) ){
                strafeLeft(speed);
            }
            stopMotors();
        }

        public void strafeRight(double speed, double rotations) {
            double ticks = rotations * TICKS_PER_ROTATION;
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            while ((Math.abs(frontLeftMotor.getCurrentPosition() ) < ticks && LinearOp.opModeIsActive()) ) {
                strafeRight(speed);
            }
            stopMotors();

        }

        public void rotateRight(double speed, double rotations) {
            double ticks = rotations * TICKS_PER_ROTATION;
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            while ((Math.abs(frontLeftMotor.getCurrentPosition() ) < ticks) && LinearOp.opModeIsActive()) {
                rotateRight(speed);
            }
            stopMotors();
        }

        public void rotateLeft(double speed, double rotations) {
            double ticks = rotations * TICKS_PER_ROTATION;
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            while ((Math.abs(frontLeftMotor.getCurrentPosition() ) < ticks ) && LinearOp.opModeIsActive()) {
                rotateLeft(speed);
            }
            stopMotors();


        }


        public void diagonalLeftForward(double speed, double rotations) {

            double ticks = rotations  * TICKS_PER_ROTATION;
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            while ((Math.abs(frontLeftMotor.getCurrentPosition() ) < ticks && LinearOp.opModeIsActive()) ) {
                diagonalLeftForward(speed);
            }
            stopMotors();
        }

        public void diagonalRightForward(double speed, double rotations) {

            double ticks = rotations  * TICKS_PER_ROTATION;
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            while ((Math.abs(frontLeftMotor.getCurrentPosition() ) < ticks && LinearOp.opModeIsActive()) ) {
                diagonalRightForward(speed);
            }
            stopMotors();
        }

        public void diagonalLeftBack(double speed, double rotations) {

            double ticks = rotations  * TICKS_PER_ROTATION;
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            while ((Math.abs(frontLeftMotor.getCurrentPosition() ) < ticks && LinearOp.opModeIsActive()) ) {
                diagonalLeftBack(speed);
            }
            stopMotors();
        }

        public void diagonalRightBack (double speed, double rotations) {

            double ticks = rotations  * TICKS_PER_ROTATION;
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            while ((Math.abs(frontLeftMotor.getCurrentPosition() ) < ticks && LinearOp.opModeIsActive()) ) {
                diagonalRightBack(speed);
            }
            stopMotors();
        }

        // Speed Acceleration and Deceleration Method
        public void speedAcceleration(double rotations, double maxPower, driveDirections driveDirection) {
            resetEncoders();
            double targetDistance = rotations * ODO_TICKS_PER_ROTATION;
            double accelerationDistance = targetDistance * 0.2;
            double decelerationDistance = targetDistance * 0.7;
            double minPowerStart = 0;
            double minPowerStop = 0;
            if (driveDirection == driveDirections.DRIVE_FORWARD || driveDirection == driveDirections.DRIVE_BACK) {
                minPowerStart = 0.2;
                minPowerStop = 0.2;
            }
            else if (driveDirection == driveDirections.STRAFE_LEFT || driveDirection == driveDirections.STRAFE_RIGHT ){
                minPowerStart = 0.4;
                minPowerStop = 0.4;
            }
            else {
                minPowerStart = 0.65;
                minPowerStop = 0.65;
            }

            double power;
            double currentDistance = 0;

            while(currentDistance < targetDistance && LinearOp.opModeIsActive()){


                // Acceleration
                if (currentDistance < accelerationDistance) {
                    power = maxPower * (currentDistance / accelerationDistance);
                    power = Range.clip(power, minPowerStart,maxPower);
                    LinearOp.telemetry.addData("< 0.2: ", power);
                }

                // Deceleration
                else if (currentDistance > targetDistance - decelerationDistance) {
                    power = maxPower * ((targetDistance - currentDistance) / decelerationDistance);
                    power = Range.clip(power, minPowerStop, maxPower);
                    LinearOp.telemetry.addData("> 0.2: ", power);
                }

                // Constant Power
                else {

                    power = maxPower;
                    power = Range.clip(power, minPowerStart,maxPower);
                    LinearOp.telemetry.addData("Main Drive: ", power);
                }
                LinearOp.telemetry.update();

                // Incremental Power Assigned to Motors
                switch (driveDirection) {
                    case STOP:
                        stopMotors();
                        break;
                    case DRIVE_FORWARD:
                        frontLeftMotor.setPower(-power);
                        frontRightMotor.setPower(-power);
                        rearLeftMotor.setPower(-power);
                        rearRightMotor.setPower(-power);
                        break;
                    case DRIVE_BACK:
                        frontLeftMotor.setPower(power);
                        frontRightMotor.setPower(power);
                        rearLeftMotor.setPower(power);
                        rearRightMotor.setPower(power);
                        break;
                    case STRAFE_LEFT:
                        frontLeftMotor.setPower(power);
                        frontRightMotor.setPower(-power);
                        rearLeftMotor.setPower(-power);
                        rearRightMotor.setPower(power);
                        break;
                    case STRAFE_RIGHT:
                        frontLeftMotor.setPower(-power);
                        frontRightMotor.setPower(power);
                        rearLeftMotor.setPower(power);
                        rearRightMotor.setPower(-power);
                        break;
                    case DIAGONAL_LEFT_BACK:
                        frontLeftMotor.setPower(power);
                        rearRightMotor.setPower(power);
                        break;
                    case DIAGONAL_RIGHT_BACK:
                        frontRightMotor.setPower(power);
                        rearLeftMotor.setPower(power);
                        break;
                    case DIAGONAL_LEFT_FORWARD:
                        frontRightMotor.setPower(-power);
                        rearLeftMotor.setPower(-power);
                        break;
                    case DIAGONAL_RIGHT_FORWARD:
                        frontLeftMotor.setPower(-power);
                        rearRightMotor.setPower(-power);
                        break;
                    default:
                        stopMotors();
                        break;
                }


                try {
                    Thread.sleep(10);
                }
                catch (InterruptedException e)
                {
                    Thread.currentThread().interrupt();//re-interrupt the thread
                }
                if (driveDirection == driveDirections.DRIVE_FORWARD || driveDirection == driveDirections.DRIVE_BACK) {
                    currentDistance = getEncoderAvgDistanceX();
                }
                else if  (driveDirection == driveDirections.STRAFE_LEFT || driveDirection == driveDirections.STRAFE_RIGHT ){
                    currentDistance = getEncoderAvgDistanceY();
                }
                else {
                    currentDistance = getEncoderAvgDistanceX() + getEncoderAvgDistanceY();
                }
//            currentDistance = getEncoderAvgDistanceX();
            }

            stopMotors();

        }



        // *********  Helper methods for Encoders******************
        // Helper Method to reset encoders
        public void resetEncoders() {
            leftEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            centerEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            leftEncoder.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rightEncoder.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            centerEncoder.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        }

        // Helper Method that averages all the encoder counts using getPosition
        public double getEncoderAvgDistanceX() {
            double average = (
                    Math.abs(leftEncoder.getCurrentPosition()) +
                            Math.abs(rightEncoder.getCurrentPosition())
            ) / 2.0;
            return Math.abs(average);
        }

        public double getEncoderAvgDistanceY() {
            return Math.abs(centerEncoder.getCurrentPosition());
        }




        /* USAGE EXAMPLE:

    double GYRO_PATH_SPD = .5;
    double GYRO_CORRECT_SPD = .21;
    double MAX_SPD = 1.0;
    double FAST_SPD = .7;
    double MED_SPD = .5;
    double STRAFE_SPD = .8;
    double LONG_STRAFE_SPD = 1;
    int SLEEP_GYRO = 150;
    int SLEEP_TIME = 100;


      Bot.speedAcceleration(3, FAST_SPD, MecanumDrive.driveDirections.DRIVE_FORWARD);
            sleep(SLEEP_TIME);
            Bot.gyroCorrection(GYRO_PATH_SPD, 45);
            sleep(SLEEP_GYRO);
            Bot.leftPixelClawOpen();
            Bot.speedAcceleration(.5, MED_SPD, MecanumDrive.driveDirections.DRIVE_BACK);
            sleep(100);
            Bot.speedAcceleration(.5, STRAFE_SPD, MecanumDrive.driveDirections.STRAFE_RIGHT);
            sleep(100);
            Bot.speedAcceleration(.2, MED_SPD, MecanumDrive.driveDirections.DRIVE_FORWARD);
            sleep(100);
            Bot.gyroPath(GYRO_CORRECT_SPD, 25);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 25);
            sleep(100);
            Bot.speedAcceleration(.1, MED_SPD, MecanumDrive.driveDirections.DRIVE_FORWARD);
            sleep(100);
            Bot.leftPixelClawClose();
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 0);
            sleep(100);
            Bot.speedAcceleration(0.13 ,    STRAFE_SPD, MecanumDrive.driveDirections.STRAFE_LEFT); //.11
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 0);
            sleep(100);
            Bot.speedAcceleration(4.0, FAST_SPD, MecanumDrive.driveDirections.DRIVE_FORWARD);
            sleep(100);
            Bot.gyroPath(GYRO_PATH_SPD, 90);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 90);
            sleep(100);
            //            DRIVE TO BACKDROP
            Bot.speedAcceleration(13.1, FAST_SPD, MecanumDrive.driveDirections.DRIVE_BACK);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 90);
            sleep(100);
            Bot.speedAcceleration(2.5, STRAFE_SPD, MecanumDrive.driveDirections.STRAFE_LEFT);
            sleep(100);
            Bot.gyroPath(GYRO_PATH_SPD, -90);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, -90);
            sleep(100);

            dropPixelBackdrop();

//            Bot.gyroCorrection(GYRO_CORRECT_SPD, -90);
            sleep(100);

            Bot.speedAcceleration(1.0, STRAFE_SPD, MecanumDrive.driveDirections.STRAFE_LEFT);





            public void dropPixelBackdrop() {
//        Need full power or motor stalls.
        Bot.rightWormgearUp(1, 430);
        sleep(100);
        Bot.driveForward(.3);
        sleep(1000);
        Bot.stopMotors();
        Bot.linearSlideExtend(.8,390);
        sleep(500);
        Bot.rightPixelClawClose();
        sleep(1500);
        Bot.linearSlideRetract(.8,200);
        sleep(500);
        Bot.speedAcceleration(0.8, MAX_SPD, MecanumDrive.driveDirections.DRIVE_BACK);
        sleep(1000);
        Bot.stopMotors();
    }

    */










    }





