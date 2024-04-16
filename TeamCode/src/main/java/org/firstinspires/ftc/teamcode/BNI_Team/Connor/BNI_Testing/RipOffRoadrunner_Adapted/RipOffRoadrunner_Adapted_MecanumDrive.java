package org.firstinspires.ftc.teamcode.BNI_Team.Connor.BNI_Testing.RipOffRoadrunner_Adapted;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;





public class RipOffRoadrunner_Adapted_MecanumDrive {



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
            DRIVE_FORWARD, DRIVE_BACK, STRAFE_LEFT, STRAFE_RIGHT
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
        public void gyroCorrection(double speed, double targetAngle) {
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

        public void gyroPath (double speed, double targetAngle) {
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
            else {
                minPowerStart = 0.4;
                minPowerStop = 0.4;
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
                else {
                    currentDistance = getEncoderAvgDistanceY();
                }
//            currentDistance = getEncoderAvgDistanceX();
            }

            stopMotors();

        }

        public void speedAccelerationStrafe (double rotations, double maxPower, driveDirections driveDirection) {
            double targetDistance = rotations * ODO_TICKS_PER_ROTATION;

            resetEncoders();
            double accelerationDistance = targetDistance * 0.2;
            double decelerationDistance = targetDistance * 0.7;
            double minPowerStart = .2;
            double minPowerStop = 0.2;
            double power;
            double currentDistance = getEncoderAvgDistanceY();

            while(getEncoderAvgDistanceY() < targetDistance && LinearOp.opModeIsActive()){


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
                        frontLeftMotor.setPower(power);
                        frontRightMotor.setPower(power);
                        rearLeftMotor.setPower(power);
                        rearRightMotor.setPower(power);
                        break;
                    case DRIVE_BACK:
                        frontLeftMotor.setPower(-power);
                        frontRightMotor.setPower(-power);
                        rearLeftMotor.setPower(-power);
                        rearRightMotor.setPower(-power);
                        break;
                    case STRAFE_LEFT:
                        frontLeftMotor.setPower(-power);
                        frontRightMotor.setPower(power);
                        rearLeftMotor.setPower(-power);
                        rearRightMotor.setPower(power);
                        break;
                    case STRAFE_RIGHT:
                        frontLeftMotor.setPower(power);
                        frontRightMotor.setPower(-power);
                        rearLeftMotor.setPower(power);
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

                currentDistance = getEncoderAvgDistanceY();
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








    }





