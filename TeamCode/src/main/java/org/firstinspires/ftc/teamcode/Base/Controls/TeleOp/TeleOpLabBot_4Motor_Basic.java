package org.firstinspires.ftc.teamcode.Base.Controls.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Base.Robot.LabBot_4Motor;

//@Disabled
// This tells the drive station to show this program under the TeleOp section with name we define
@TeleOp(name = "LabBot 4Motor Basic")

public class TeleOpLabBot_4Motor_Basic extends OpMode {

    // Variable to throttle speed
    public double speedMultiply = 0.50;

    // call our constructor to create the physical robot that we can call whatever
    // You can replace "Bot" with the name of your robot... Barbenheimer, Twig, Bob, GOAT, etc.
    public LabBot_4Motor Bot = new LabBot_4Motor();

    // Ignore the parent's init method and replace with the one we defined in the Robot class
    @Override
    public void init() {
        Bot.initRobot(hardwareMap);
    }

    // Ignore the parent's loop method and replace with the one we define here
    @Override
    public void loop () {
        drive();                // Call the Method to drive our robot
        speedControl();         // Call then Method to control speed of our robot
    }

    // Define Method to drive our robot using one gamepad controller and only the left stick
    // For each direction of the y stick, we will call our drivetrain methoods (forward, backward)
    public void drive() {
            if (gamepad1.left_stick_y < -0.1) {

                Bot.driveForward(speedMultiply * Math.abs(gamepad1.left_stick_y));
            } else if (gamepad1.left_stick_y > 0.1) {
                Bot.driveBackward(speedMultiply * Math.abs(gamepad1.left_stick_y));
            } else if (gamepad1.left_stick_x > 0.1) {
                Bot.rotateRight(speedMultiply * Math.abs(gamepad1.left_stick_x));
            } else if (gamepad1.left_stick_x < -0.1) {
                Bot.rotateLeft(speedMultiply * Math.abs(gamepad1.left_stick_x));
            } else {
                Bot.stopMotors();
            }
    }

    // Define Method to control the speed of our robot using the gamepad's d-pad.
    public void speedControl() {

        if (gamepad1.dpad_right) {
            speedMultiply = 0.25;           // 25% Power
        }
        else if (gamepad1.dpad_down) {
            speedMultiply = 0.50;           // 50% Power
        }
        else if (gamepad1.dpad_left) {
            speedMultiply = 0.75;           // 75% Power
        }
        else if (gamepad1.dpad_up) {
            speedMultiply = 1.00;           // 100% Power
        }
    }
}
