package org.firstinspires.ftc.teamcode.BNI_Team.Connor.BNI_Testing;

import java.util.List;

public abstract class Test_AUTOMAINCenterStage_Connor extends TEST_AprilTagNewCenterStage_CWR {



    public void findTag(){
        initAprilTag();
        List<org.firstinspires.ftc.vision.apriltag.AprilTagDetection> currentDetections = aprilTag.getDetections();
        telemetry.addData("# AprilTags Detected", currentDetections.size());

    }

    public void detectTags() {
        findTag();

        sleep(500);

        findTag();

        sleep(500);

        findTag();

        sleep(500);

        visionPortal.stopStreaming();

        sleep(500);
    }
}
