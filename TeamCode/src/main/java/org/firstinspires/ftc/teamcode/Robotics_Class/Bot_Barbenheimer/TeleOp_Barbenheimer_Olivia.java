package org.firstinspires.ftc.teamcode.Robotics_Class.Bot_Barbenheimer;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import kotlin.math.UMathKt;

@TeleOp(name = "TeleOp_Olivia", group = "iLab")
public class TeleOp_Barbenheimer_Olivia extends OpMode {

    //variable to throttle speed
    public double speedMultiply = 0.50;

    //calling constructor
    public Barbenheimer_Olivia Barb = new Barbenheimer_Olivia();

    //ignore parent's init method and replace
    @Override
    public void init() {
        Barb.initRobot(hardwareMap);
    }

    //ignore parents loop method and replace
    @Override
    public void loop () {
        drive();
        speedControl();
    }

    //define method to drive robot with gamepad and left stick
    //for each direction of the y stick, we will call our drivetrain methods
    public void drive(){
        if (gamepad1.left_stick_y< -0.1) {
            Barb.driveForward(speedMultiply * Math.abs(gamepad1.left_stick_y));
        }else if (gamepad1.left_stick_y > 0.1) {
            Barb.driveBackward(speedMultiply * Math.abs(gamepad1.left_stick_y));
        }else if (gamepad1.left_stick_x > 0.1) {
            Barb.rotateRight(speedMultiply * Math.abs(gamepad1.left_stick_x));
        }else if (gamepad1.left_stick_x < -0.1) {
            Barb.rotateLeft(speedMultiply * Math.abs(gamepad1.left_stick_x));
        }else {
            Barb.stopMotors();
        }
    }

    //define method to control speed
    public void speedControl() {
        if (gamepad1.dpad_right){
            speedMultiply = 0.25;
        }
        else if (gamepad1.dpad_down){
            speedMultiply = 0.50;
        }
        else if (gamepad1.dpad_left){
            speedMultiply = 0.75;
        }
        else if (gamepad1.dpad_up){
            speedMultiply = 1.00;
        }
    }

}
