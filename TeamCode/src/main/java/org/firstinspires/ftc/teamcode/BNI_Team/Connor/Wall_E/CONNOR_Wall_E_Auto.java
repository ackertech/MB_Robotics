package org.firstinspires.ftc.teamcode.BNI_Team.Connor.Wall_E;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Disabled
@Autonomous (name = "Wall -E Auto_Connor", group="iLab")
public class CONNOR_Wall_E_Auto extends LinearOpMode {


    WalleBot Wall_E = new WalleBot();

    @Override
    public void runOpMode() throws InterruptedException {

        Wall_E.initRobot(hardwareMap);
        Wall_E.setLinearOp(this);


        telemetry.addLine("Wall_E Awaiting Start");
        telemetry.update();


                waitForStart();

        while (opModeIsActive()) {
            telemetryUpdate("Testing Wall_E Motors & Encoders");
            Wall_E.driveForward(0.5, 106);
            //1 rotation = 9 in
            //1 intersection = 96in
            sleep(100);
//            Wall_E.rotateLeft(0.5,1);
//            sleep(100);
//            Wall_E.rotateRight(0.5,1.7);
//            sleep(100);
//            Wall_E.rotateLeft(0.5,1);
//            sleep(100);
//            Wall_E.driveBackwards(0.5,1);
//            sleep(100);
//       Wall_E.upAndDownLinearMotor.setPower(-1);
//       sleep(5000);
//       Wall_E.upAndDownLinearMotor.setPower(0);
//       sleep(200);
//            Wall_E.lazySusanLeft(0.5,0.5);
//            sleep(5000);
//            Wall_E.lazySusanRight(0.5,0.1);
//            sleep(5000);

            requestOpModeStop();



//hi



        }
        //hi
        idle();
    }
    public void telemetryUpdate(String comment) {
        telemetry.addLine("LONG LIVE TACO");
        telemetry.addLine(comment);
        telemetry.addData("Front Lef Motor:", Wall_E.frontLeftMotor.getPower());
        telemetry.addData("Front Rig Motor:", Wall_E.frontRightMotor.getPower());
        telemetry.addData("Rear Lef Motor:", Wall_E.rearLeftMotor.getPower());
        telemetry.addData("Rear Rig Motor:", Wall_E.rearRightMotor.getPower());
        telemetry.addData("Encoder Count: ", Wall_E.frontLeftMotor.getCurrentPosition());
        telemetry.update();
    }






}
