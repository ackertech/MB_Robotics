package org.firstinspires.ftc.teamcode.Base.Controls.Vision;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Base.Mechanisms.ArmHand;
import org.firstinspires.ftc.teamcode.Base.Robot.AckerBot;
import org.firstinspires.ftc.teamcode.Base.Robot.TankBot;
import org.firstinspires.ftc.teamcode.Base.Robot.VisionBot;

@Disabled
@Autonomous(name = "Vision: OpenCV Tracking")

public class VisionTrackingAuto extends LinearOpMode {

    VisionBot Cam = new VisionBot();

    @Override
    public void runOpMode() throws InterruptedException {

        Cam.initWebcam();
        Cam.setLinearOp(this);

        telemetry.addLine("Awaiting Start");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()){

            telemetry.addLine("Waiting for Camera init");
            telemetry.update();

            requestOpModeStop();

        }

        idle();

    }


}
