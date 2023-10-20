package org.firstinspires.ftc.teamcode.Sandbox.Vision;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;


@TeleOp(name = "Vision:Auto with Detection", group="iLab")
public class BNIVisionAuto extends LinearOpMode {

    OpenCvWebcam webcam;
    BNIVision.TeamPropPositionPipeline pipeline;
    BNIVision.TeamPropPositionPipeline.TeamPropPosition snapshotAnalysis = BNIVision.TeamPropPositionPipeline.TeamPropPosition.ONE;

    @Override
    public void runOpMode() {

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        pipeline = new BNIVision.TeamPropPositionPipeline();
        webcam.setPipeline(pipeline);

        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                webcam.startStreaming(320,240, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {}
        });

        while (!isStarted() && !isStopRequested())
        {
            telemetry.addData("Realtime analysis", pipeline.getAnalysis());
            telemetry.update();

            // Don't burn CPU cycles busy-looping in this sample
            sleep(50);
        }

        // Take a snapshot
        snapshotAnalysis = pipeline.getAnalysis();

        telemetry.addData("Snapshot post-START analysis", snapshotAnalysis);
        telemetry.update();

        switch (snapshotAnalysis)
        {
            case ONE:
            {
                telemetry.addData("GO TO POSITION:", BNIVision.TeamPropPositionPipeline.TeamPropPosition.ONE);
                telemetry.update();
                break;
            }

            case TWO:
            {
                telemetry.addData("GO TO POSITION:", BNIVision.TeamPropPositionPipeline.TeamPropPosition.TWO);
                telemetry.update();
                break;
            }

            case THREE:
            {
                telemetry.addData("GO TO POSITION:", BNIVision.TeamPropPositionPipeline.TeamPropPosition.THREE);
                telemetry.update();
                break;
            }
        }

        /* Do not include in real Autonmous. */
        while (opModeIsActive())
        {
            // Don't burn CPU cycles busy-looping in this sample
            sleep(50);
        }

    }

}
