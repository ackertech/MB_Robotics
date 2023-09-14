package org.firstinspires.ftc.teamcode.BNI_Team.Connor.BNI_Testing;

import org.openftc.apriltag.AprilTagDetection;

import java.util.ArrayList;
import java.util.List;

public abstract class Test_AUTOMAINCenterStage_Connor extends TEST_AprilTagNewCenterStage_CWR {
int ID_TAG_LIST [] = {0,1,2,3,4,5,6,7};
public AprilTagDetection tagOfInterest = null;



    public void findTag() {
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

        visionPortal.close();

        sleep(500);
    }
}
