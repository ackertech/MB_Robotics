//package org.firstinspires.ftc.teamcode.BNI_Team.Connor.BNI_Testing;
//
//
//import com.acmerobotics.dashboard.FtcDashboard;
//import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//
//import org.firstinspires.ftc.teamcode.BNI_Team.Connor.CompetitionRobot_7th.Robots.CompetitionBot;
//import org.firstinspires.ftc.teamcode.BNI_Team.Connor.CompetitionRobot_7th.Robots.RoadRunnerCompBot;
//import org.firstinspires.ftc.teamcode.BNI_Team.Connor.Drivetrains.MecanumDrive_Connor;
//import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
//
//import java.util.List;
//
//@Autonomous (name = "BasicAprilResponce_CWR")
//public class BasicAprilAuto_TST extends Test_AUTOMAINCenterStage_Connor {
//
//
//
//    @Override
//    public void runOpMode() {
//
//        initAprilTag();
//        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
//        CompetitionBot CompBot = new CompetitionBot();
//
//        CompBot.initRobot(hardwareMap);
//        CompBot.setLinearOp(this);
//
//
//
//        waitForStart();
//
//        while (opModeIsActive()) {
//            List<AprilTagDetection> currentDetections = aprilTag.getDetections();
//
//            telemetryAprilTag();
//
//            detectTags();
//            for (org.firstinspires.ftc.vision.apriltag.AprilTagDetection detection : currentDetections) {
//                if (detection.metadata != null) {
//                    if (detection.id == 1) {
//                        CompBot.driveForward(.5,2);
//
//                    }
//
//                }
//            }
//        }
//    }
//}
