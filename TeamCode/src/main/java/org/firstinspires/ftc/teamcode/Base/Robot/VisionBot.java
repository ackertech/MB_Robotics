package org.firstinspires.ftc.teamcode.Base.Robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Base.Drivetrains.MecanumDrive;
import org.firstinspires.ftc.teamcode.Base.Sensors.OpenCVPipeline;
import org.opencv.core.Scalar;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;


public class VisionBot extends MecanumDrive {

    // Robot Hardware Constructors
    public HardwareMap hwBot  =  null;

    // Declaration of OpenCV Variables
    public OpenCVPipeline detectionPipeline;
    public OpenCvCamera webcam;
    private static final int CAMERA_WIDTH = 320;
    private static final int CAMERA_HEIGHT = 240;
    public static Scalar scalarLowerYCrCb = new Scalar(0.0, 0.0, 0.0);
    public static Scalar scalarUpperYCrCb = new Scalar(255.0, 150.0, 120.0);

    // FTC SDK Requirement
    public LinearOpMode linearOp = null;
    public void setLinearOp (LinearOpMode Op) {
        linearOp = Op;
    }

    //VisionBot Constructor
    public VisionBot() {}


    // Initializing Hardware for Webcam and Vision Tracking
    public void initWebcam() {

        // Webcam Initialization
        int cameraMonitorViewId = hwBot.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hwBot.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hwBot.get(WebcamName.class, "Webcam1"), cameraMonitorViewId);

        // Configuration of the pipleline
        webcam.setPipeline(detectionPipeline = new OpenCVPipeline());
        detectionPipeline.ConfigurePipeline(30, 30, 30, 30, CAMERA_WIDTH, CAMERA_HEIGHT);
        detectionPipeline.ConfigureScalarLower(scalarLowerYCrCb.val[0], scalarLowerYCrCb.val[1], scalarLowerYCrCb.val[2]);
        detectionPipeline.ConfigureScalarUpper(scalarUpperYCrCb.val[0], scalarUpperYCrCb.val[1], scalarUpperYCrCb.val[2]);

        // Open the Camera Asynchronously so not to block the thread while camera opens

        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {

            @Override
            public void onOpened() {
                webcam.startStreaming(CAMERA_WIDTH, CAMERA_HEIGHT, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {

            }
        });
    }



}
