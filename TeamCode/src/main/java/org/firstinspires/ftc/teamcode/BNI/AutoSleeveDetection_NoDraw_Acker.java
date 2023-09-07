package org.firstinspires.ftc.teamcode.BNI;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

// April Tag Detection. No drawing of bounding boxes or cubes

@Autonomous(name="Vision: Sleeve Detect No Draw")
public class AutoSleeveDetection_NoDraw_Acker extends AutoMain_NoDraw_Acker {

    @Override
    public void runOpMode() throws InterruptedException {

        // Initialize WebCam and Create Image Processing Pipeline
        initializePipeline();

        // Find Tags During the Init Loop
        while (!isStarted() && !isStopRequested()) {
            findTag();
            sleep(20);
        }

        // Start Button Processed
        while (opModeIsActive()) {

            // Select Parking Position
            parkingTelemetry();
            sleep(6000);

            requestOpModeStop();
        }
    idle();
    }


}
