package org.firstinspires.ftc.teamcode.Robotics_Class.Bot_BruceWayne.Connor_Class;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

@TeleOp(name = "Max Velocity Test - Flywheel")
public class MotorMaxVeloTest extends LinearOpMode {
    DcMotorEx motor;
    double currentVelocity;
    double maxVelocity = 0.0;


    @Override
    public void runOpMode() {
        motor = hardwareMap.get(DcMotorEx.class, "candy_launcher_left");
       motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        waitForStart();


        while (opModeIsActive()) {
           motor.setPower(1);
            currentVelocity = motor.getVelocity();

           motor.getCurrent(CurrentUnit.MILLIAMPS);
//           if (motor.getCurrent(CurrentUnit.MILLIAMPS) > 6000) {
//               motor.setPower(0);
//           }

            if (currentVelocity > maxVelocity) {
                maxVelocity = currentVelocity;
            }

            telemetry.addData("current velocity", currentVelocity);
            telemetry.addData("maximum velocity", maxVelocity);
            telemetry.addData("Motor Power Milliamps",motor.getCurrent(CurrentUnit.MILLIAMPS));
            telemetry.update();
        }
    }
}

