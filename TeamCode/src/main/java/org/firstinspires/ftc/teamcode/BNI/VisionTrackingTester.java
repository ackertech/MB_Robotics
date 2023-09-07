package org.firstinspires.ftc.teamcode.BNI;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Base.Sensors.OpenCVSleeveDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

//@Disabled
@Autonomous(name="Vision: OpenCV Tracker Tester")

public class VisionTrackingTester extends LinearOpMode {

    OpenCVSleeveDetection sleeveDetection = new OpenCVSleeveDetection();
    OpenCvCamera webcam;
    
    @Override
    public void runOpMode() throws InterruptedException {

        initializePipeline();

        while (!isStarted() && !isStopRequested()) {
            colorTelemetry();
            sleep(20);
        }

    }

    public void initializePipeline() {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        sleeveDetection = new OpenCVSleeveDetection();
        webcam.setPipeline(sleeveDetection);

        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened() {

                webcam.startStreaming(800,448, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) { }
        });

        while (opModeIsActive()) {

            // Select Parking Position
            colorTelemetry();
            sleep(6000);

            requestOpModeStop();
        }
        idle();

    }

    public void colorTelemetry() {
        telemetry.addData("POSITION: ", sleeveDetection.getPosition());
        telemetry.addData("YELLOW %: ", sleeveDetection.yellowPercent);
        telemetry.addData("GREEN %: ", sleeveDetection.greenPercent);
        telemetry.addData("PURPLE %: ", sleeveDetection.purplePercent);
        telemetry.update();
    }


}

