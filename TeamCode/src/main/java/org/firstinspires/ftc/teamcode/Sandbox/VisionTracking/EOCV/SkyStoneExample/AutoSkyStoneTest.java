package org.firstinspires.ftc.teamcode.Sandbox.VisionTracking.EOCV.SkyStoneExample;

//import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

@Autonomous (name = "EOCV SKyStone", group = "lab")
@Disabled
public class AutoSkyStoneTest extends LinearOpMode {
    // Handle hardware stuff...
    int width = 320;
    int CAMERA_WIDTH = 320;
    int CAMERA_HEIGHT = 240;
    // store as variable here so we can access the location
    SkystoneDetector detector = new SkystoneDetector(width);
    OpenCvCamera webcam;

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        // robot logic...

        // https://github.com/OpenFTC/EasyOpenCV/blob/master/examples/src/main/java/org/openftc/easyopencv/examples/InternalCameraExample.java

        //init the webcam
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);


        webcam.setPipeline(detector);


        // Connect to the camera
        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                webcam.startStreaming(CAMERA_WIDTH, CAMERA_HEIGHT, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode)
            {

            }
        });


//        FtcDashboard dashboard = FtcDashboard.getInstance();
//        telemetry = dashboard.getTelemetry();
//        FtcDashboard.getInstance().startCameraStream(webcam, 10);

        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("Current Time (s): ", runtime.seconds());
            telemetry.update();
            sleep(100);
        }
        requestOpModeStop();
    }
}
