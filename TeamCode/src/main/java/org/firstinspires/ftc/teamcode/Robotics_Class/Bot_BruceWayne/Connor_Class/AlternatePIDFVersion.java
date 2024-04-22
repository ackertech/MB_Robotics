package org.firstinspires.ftc.teamcode.Robotics_Class.Bot_BruceWayne.Connor_Class;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@Config
@TeleOp(name = "Alternate PIDF")
public class AlternatePIDFVersion extends OpMode {


    DcMotorEx motor;
    private PIDController controller;

    public static double p = 0, i = 0, d = 0;
    public static double f = 0;

    double targetVelo = 0;

    double ticks_in_degree = 700/180.0;


    @Override
    public void init(){
        controller = new PIDController(p,i,d);
        telemetry = new MultipleTelemetry(FtcDashboard.getInstance().getTelemetry());

        motor = hardwareMap.get(DcMotorEx.class, "candy_launcher_left");
    }


    @Override
    public void loop(){
        controller.setPID(p,i,d);
       //int armPos = motor.getCurrentPosition();  use iif you had an arm
        double flywheelVelo = motor.getVelocity();
        double pid = controller.calculate(flywheelVelo, targetVelo);
    }



    //still in progress
    //based on video - see https://www.youtube.com/watch?v=E6H6Nqe6qJo
}
