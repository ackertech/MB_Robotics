package org.firstinspires.ftc.teamcode.Sandbox.BNI;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Vision: Sleeve Detect Draw")
public class AutoSleeveDetection extends AutoMain_Acker {

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
