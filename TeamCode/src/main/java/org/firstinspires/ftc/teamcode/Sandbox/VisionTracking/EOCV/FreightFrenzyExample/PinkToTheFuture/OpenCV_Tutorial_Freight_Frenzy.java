package org.firstinspires.ftc.teamcode.Sandbox.VisionTracking.EOCV.FreightFrenzyExample.PinkToTheFuture;

//import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Scalar;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

//@Config
@TeleOp(name="OpenCV_FF", group="Tutorials")
//@Disabled

public class OpenCV_Tutorial_Freight_Frenzy extends LinearOpMode {

    private OpenCvCamera webcam;

    private static final int CAMERA_WIDTH  = 320; // width  of wanted camera resolution / originial: 320
    private static final int CAMERA_HEIGHT = 240; // height of wanted camera resolution /original: 240

    double CrLowerUpdate = 200;
    double CbLowerUpdate = 200;
    double CrUpperUpdate = 255;
    double CbUpperUpdate = 255;

    double lowerruntime = 0;
    double upperruntime = 0;

    //ORIGINAL
    // Pink Range                                      Y      Cr     Cb
//    public static Scalar scalarLowerYCrCb = new Scalar(  0, 150, 120);
//    public static Scalar scalarUpperYCrCb = new Scalar(255, 255.0, 255.0);
//    NOTHING
//    public static Scalar scalarLowerYCrCb = new Scalar(  0.0, 255.0, 0.0);
//    NOTHING
//    public static Scalar scalarLowerYCrCb = new Scalar(  0.0, 0.0, 255.0);

// THESE ARE THE KEY VALUES!!!!!!!!!

    //public static Scalar scalarLowerYCrCb = new Scalar(  0.0, 0.0, 0.0);  // duVals values
    public static Scalar scalarLowerYCrCb = new Scalar(  62, 46, 71); // emmas values

    //public static Scalar scalarUpperYCrCb = new Scalar(255.0, 150.0, 120.0);    // duVals values
    public static Scalar scalarUpperYCrCb = new Scalar(159, 88, 129); //emmas values
//    TESTING    ****
//    Just no.
    //                                      Y      Cr     Cb
//    public static Scalar scalarLowerYCrCb = new Scalar(  150.0, 0.0, 120.0);
//    public static Scalar scalarUpperYCrCb = new Scalar(255.0, 255.0, 255.0);

//    public static Scalar scalarLowerYCrCb = new Scalar( 0.0, 200.0, 200.0);
//    public static Scalar scalarUpperYCrCb = new Scalar(255.0, 255.0, 255.0);





    @Override
    public void runOpMode()
    {
        // OpenCV webcam
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        //OpenCV Pipeline
        ContourPipeline myPipeline;
        webcam.setPipeline(myPipeline = new ContourPipeline());
        // Configuration of Pipeline
        myPipeline.ConfigurePipeline(30, 30,30,30,  CAMERA_WIDTH, CAMERA_HEIGHT);
        myPipeline.ConfigureScalarLower(scalarLowerYCrCb.val[0],scalarLowerYCrCb.val[1],scalarLowerYCrCb.val[2]);
        myPipeline.ConfigureScalarUpper(scalarUpperYCrCb.val[0],scalarUpperYCrCb.val[1],scalarUpperYCrCb.val[2]);
        // Webcam Streaming

//        webcam.openCameraDeviceAsync(() -> webcam.startStreaming(CAMERA_WIDTH, CAMERA_HEIGHT, OpenCvCameraRotation.UPRIGHT));


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


                // Only if you are using ftcdashboard
//        FtcDashboard dashboard = FtcDashboard.getInstance();
//        telemetry = dashboard.getTelemetry();
//        FtcDashboard.getInstance().startCameraStream(webcam, 10);

        telemetry.update();
        waitForStart();

        while (opModeIsActive())
        {
            if(myPipeline.error){
                telemetry.addData("Exception: ", myPipeline.debug);
            }
            // Only use this line of the code when you want to find the lower and upper values
            // testing(myPipeline);

            // Watch our YouTube Tutorial for the better explanation

            telemetry.addData("RectArea: ", myPipeline.getRectArea());
            //jd
            telemetry.addData("Rect Midpoint X",myPipeline.getRectMidpointX());
            telemetry.addData("Rect Midpoint Y",myPipeline.getRectMidpointY());
            telemetry.update();

            if(myPipeline.getRectArea() > 2000){
                if(myPipeline.getRectMidpointX() > 400){
                    AUTONOMOUS_C();
                }
                else if(myPipeline.getRectMidpointX() > 200){
                    AUTONOMOUS_B();
                }
                else {
                    AUTONOMOUS_A();
                }
            }
        }
    }
    public void testing(ContourPipeline myPipeline){
        if(lowerruntime + 0.05 < getRuntime()){
            CrLowerUpdate += -gamepad1.left_stick_y;
            CbLowerUpdate += gamepad1.left_stick_x;
            lowerruntime = getRuntime();
        }
        if(upperruntime + 0.05 < getRuntime()){
            CrUpperUpdate += -gamepad1.right_stick_y;
            CbUpperUpdate += gamepad1.right_stick_x;
            upperruntime = getRuntime();
        }

        CrLowerUpdate = inValues(CrLowerUpdate, 0, 255);
        CrUpperUpdate = inValues(CrUpperUpdate, 0, 255);
        CbLowerUpdate = inValues(CbLowerUpdate, 0, 255);
        CbUpperUpdate = inValues(CbUpperUpdate, 0, 255);

        myPipeline.ConfigureScalarLower(255, CrLowerUpdate, CbLowerUpdate);
        myPipeline.ConfigureScalarUpper(0, CrUpperUpdate, CbUpperUpdate);

        telemetry.addData("lowerCr ", (int)CrLowerUpdate);
        telemetry.addData("lowerCb ", (int)CbLowerUpdate);
        telemetry.addData("UpperCr ", (int)CrUpperUpdate);
        telemetry.addData("UpperCb ", (int)CbUpperUpdate);
    }
    public Double inValues(double value, double min, double max){
        if(value < min){ value = min; }
        if(value > max){ value = max; }
        return value;
    }
    public void AUTONOMOUS_A(){
        telemetry.addLine("Autonomous A");
    }
    public void AUTONOMOUS_B(){
        telemetry.addLine("Autonomous B");
    }
    public void AUTONOMOUS_C(){
        telemetry.addLine("Autonomous C");
    }
}
