package org.firstinspires.ftc.teamcode.Robotics_Class.Bot_BruceWayne.Connor_Class;

import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class FlywheelPIDFController extends SixWheelBot_Connor {
    // Constants for PIDF control
    private static final double Kp = 0.1;
    private static final double Ki = 0.01;
    private static final double Kd = 0.05;
    private static final double Kf = 0.2;

    // Target velocity
    private static final double targetVelocity = 3300; // Adjust as needed - was 1000

    // Variables for PIDF control
    private double integral = 0;
    private double previousError = 0;
    DcMotorEx flywheel;



    // Main control loop
    public void controlLoop() {
        while (true) {

            flywheel = hwBot.get(DcMotorEx.class,"candy_launcher_left");
//
            flywheel.setDirection(DcMotorSimple.Direction.FORWARD);
//
            flywheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            flywheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            flywheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            // Get current velocity from sensor
            double currentVelocity = flywheel.getVelocity();

            // Calculate error
            double error = targetVelocity - currentVelocity;

            // Proportional term
            double proportional = Kp * error;

            // Integral term
            integral += error;
            double integralTerm = Ki * integral;

            // Derivative term
            double derivative = (error - previousError);
            double derivativeTerm = Kd * derivative;

            // Feedforward term
            double feedforward = Kf * targetVelocity;

            // Calculate motor power
            double motorPower = proportional + integralTerm + derivativeTerm + feedforward;

            // Apply motor power with safety checks
            if (motorPower > 1.0) {
                motorPower = 1.0;
            } else if (motorPower < -1.0) {
                motorPower = -1.0;
            }

            // Set motor power
            setMotorPower(motorPower);

            // Update previous error
            previousError = error;

            // Sleep for a short period before next iteration
            try {
                Thread.sleep(10); // Adjust as needed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to get current velocity (replace with your sensor reading)
//    private double getVelocity() {
//
//        return flywheel.getVelocity();
//
//        // Sample implementation (replace with actual sensor reading)
//        //return Math.random() * 2000;
//        // - Random value for demonstration
//    }

    // Method to set motor power (replace with actual motor control)
    private void setMotorPower(double power) {
       flywheel.setPower(power);


        // Sample implementation (replace with actual motor control)
       // System.out.println("Setting motor power: " + power);
    }

    // Main method
    public static void main(String[] args) {
        FlywheelPIDFController controller = new FlywheelPIDFController();
        controller.controlLoop();
    }
}

