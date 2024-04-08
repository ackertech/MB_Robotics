package org.firstinspires.ftc.teamcode.Robotics_Class.Bot_BruceWayne.Connor_Class;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;


@Config
@TeleOp(name = "Max Velocity Test - Flywheel")
public class MotorMaxVeloTest extends LinearOpMode {
    DcMotorEx motor;

    public static double p = 0, i = 0, d = 0, f = 0;

    double currentVelocity;
    double maxVelocity = 0.0;

    double targetVelo = 0;




    @Override
    public void runOpMode() {

        telemetry = new MultipleTelemetry(FtcDashboard.getInstance().getTelemetry());
        motor = hardwareMap.get(DcMotorEx.class, "candy_launcher_left");
       motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        waitForStart();


        while (opModeIsActive()) {
        //  motor.setVelocityPIDFCoefficients(p,i,d,f);


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
            telemetry.addData("Motor Current Milliamps",motor.getCurrent(CurrentUnit.MILLIAMPS));
            telemetry.update();
        }
    }
}

