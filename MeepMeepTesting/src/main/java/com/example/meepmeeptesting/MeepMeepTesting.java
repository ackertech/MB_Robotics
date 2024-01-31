package com.example.meepmeeptesting;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {

        public static void main(String[] args) {
            MeepMeep meepMeep = new MeepMeep(800);

            RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                    // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                    .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            //Blue Backstage
//                            drive.trajectorySequenceBuilder(new Pose2d(13, 58, 4.7))
//                                    .forward(23)
//                                    .turn(Math.toRadians(45))
//                                    .lineToLinearHeading(new Pose2d(13,57,Math.toRadians(1)))
//                                    .forward(35)
//                                    .strafeRight(Math.toRadians(800))
//                                    .splineToSplineHeading(new Pose2d(13,13,Math.toRadians(180)),Math.toRadians(90))
////                                    .lineToLinearHeading(new Pose2d(13,13,Math.toRadians(-181)))
//                                    .lineTo(new Vector2d(-55,13) )
//                                    .lineToLinearHeading(new Pose2d(-32,13,219.9))
//                                    .lineTo(new Vector2d(45,13))
//                                    .strafeLeft(25)
////                                    .lineToLinearHeading(new Pose2d(13,13,Math.toRadians(-181)))
//                                    .splineToSplineHeading(new Pose2d(13,13,Math.toRadians(180)),Math.toRadians(150))
//                                    .lineTo(new Vector2d(-55,13))
//                                    .lineToLinearHeading(new Pose2d(-32,13,219.9))
//                                    .lineTo(new Vector2d(45,13))
//                                    .strafeLeft(25)

                            //Blue Audience
                                    drive.trajectorySequenceBuilder(new Pose2d(-35, 58, 4.7))
                                            .forward(23)
                                    .turn(Math.toRadians(45))
                                    .lineToLinearHeading(new Pose2d(-35,57,Math.toRadians(1)))
                                    .forward(83)
                                    .strafeRight(Math.toRadians(800))
                                            .splineToSplineHeading(new Pose2d(13,13,Math.toRadians(180)),Math.toRadians(90))
                                    .lineTo(new Vector2d(-55,13) )
                                    .lineToLinearHeading(new Pose2d(-32,13,219.9))
                                    .lineTo(new Vector2d(45,13))
                                    .strafeLeft(25)
                                    .splineToSplineHeading(new Pose2d(13,13,Math.toRadians(180)),Math.toRadians(150))
                                    .lineTo(new Vector2d(-55,13))
                                    .lineToLinearHeading(new Pose2d(-32,13,219.9))
                                    .lineTo(new Vector2d(45,13))
                                    .strafeLeft(25)
                                            .lineToLinearHeading(new Pose2d(54,13,Math.toRadians(45)))

                                    .build()
                    );

            meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                    .setDarkMode(true)
                    .setBackgroundAlpha(0.95f)
                    .addEntity(myBot)
                    .start();
        }
    }