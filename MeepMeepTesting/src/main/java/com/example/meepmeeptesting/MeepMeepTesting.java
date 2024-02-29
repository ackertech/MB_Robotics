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
//                                    .splineToSplineHeading(new Pose2d(13,13,Math.toRadians(180)),Math.toRadians(160))
//                                    .lineTo(new Vector2d(-55,13) )
//                                    .lineToLinearHeading(new Pose2d(-32,11,219.9))
//                                    .lineTo(new Vector2d(45,13))
//                                    .strafeLeft(25)
//                                            .strafeRight(10)
//                                    .splineToSplineHeading(new Pose2d(13,13,Math.toRadians(180)),Math.toRadians(155))
//                                    .lineTo(new Vector2d(-55,13))
//                                    .lineToLinearHeading(new Pose2d(-32,11,219.9))
//                                    .lineTo(new Vector2d(45,13))
//                                    .strafeLeft(25)
//                                    .strafeLeft(20)

                            //Blue Audience
//                                    drive.trajectorySequenceBuilder(new Pose2d(-35, 58, 4.7))
//                                            .forward(23)
//                                    .turn(Math.toRadians(45))
//                                    .lineToLinearHeading(new Pose2d(-35,57,Math.toRadians(1)))
//                                    .forward(83)
//                                    .strafeRight(Math.toRadians(800))
//                                            .splineToSplineHeading(new Pose2d(13,13,Math.toRadians(180)),Math.toRadians(160))
//                                    .lineTo(new Vector2d(-55,13) )
//                                    .lineToLinearHeading(new Pose2d(-32,11,219.9))
//                                    .lineTo(new Vector2d(45,13))
//                                    .strafeLeft(25)
//                                            .strafeRight(10)
//                                    .splineToSplineHeading(new Pose2d(13,13,Math.toRadians(180)),Math.toRadians(155))
//                                    .lineTo(new Vector2d(-55,13))
//                                    .lineToLinearHeading(new Pose2d(-32,11,219.9))
//                                    .lineTo(new Vector2d(45,13))
//                                    .strafeLeft(25)
//                                            .back(9)
//                                            .lineToLinearHeading(new Pose2d(54,13,Math.toRadians(45)))

//                                            Red Backstage
//                            drive.trajectorySequenceBuilder(new Pose2d(13, -58, -4.7))//-4.7
//                                    .forward(23)
//                                    .turn(Math.toRadians(45))
//                                    .lineToLinearHeading(new Pose2d(13,-57,Math.toRadians(1))) //1
//                                    .forward(35)
//                                    .strafeRight(Math.toRadians(800))
//                                    .splineToSplineHeading(new Pose2d(45,-31,Math.toRadians(0)),Math.toRadians(90))
//                                    .splineToSplineHeading(new Pose2d(13,-12,Math.toRadians(180)),Math.toRadians(190))
//                                            .lineTo(new Vector2d(-55,-12) )
//                                            .lineToLinearHeading(new Pose2d(-32,-10,219.9))
//                                            .lineTo(new Vector2d(45,-12))
//                                            .strafeRight(25)
//                                            .strafeLeft(Math.toRadians(600))
//                                            .splineToSplineHeading(new Pose2d(13,-12,Math.toRadians(180)),Math.toRadians(190))
//                                            .lineTo(new Vector2d(-55,-12))
//                                            .lineToLinearHeading(new Pose2d(-32,-10,219.9))
//                                            .lineTo(new Vector2d(45,-13))
//                                    .strafeRight(25)
//                                    .strafeRight(17)


//                               //     Red Backstage NEW
//                            drive.trajectorySequenceBuilder(new Pose2d(13, -58, -4.7))//-4.7
//                                    .lineToLinearHeading(new Pose2d(13,-31,Math.toRadians(182)))
//                                             .lineToLinearHeading(new Pose2d(45,-31,Math.toRadians(1)))
//                                             .lineToLinearHeading(new Pose2d(13,-59,Math.toRadians(182)))
//                                    .lineTo(new Vector2d(-52,-59))
//                                    .lineTo(new Vector2d(-52,-36))
//                                    .lineTo(new Vector2d(-52,-59))
//                                    .lineTo(new Vector2d(13,-59))
//                                    .lineToLinearHeading(new Pose2d(45,-31,Math.toRadians(1)))
//                                    .lineTo(new Vector2d(45,-59))
//                                   .forward(5)
////

                                    //red audience new
//                                    drive.trajectorySequenceBuilder(new Pose2d(-36, -58, -4.7))
//                                            .lineToLinearHeading(new Pose2d(-36,-31,Math.toRadians(182)))
//                                            .lineTo(new Vector2d(-36,-20))
//                                            .splineToConstantHeading(new Vector2d(-52,-11),Math.toRadians(-180))
//                                            .lineTo(new Vector2d(37,-11))
//                                            .lineToLinearHeading(new Pose2d(45,-31,Math.toRadians(1)))
//                                            .lineToLinearHeading(new Pose2d(37,-11,Math.toRadians(180 )))
//                                            .lineTo(new Vector2d(-52,-11))
//                                            .lineTo(new Vector2d(37,-11))
//                                            .lineToLinearHeading(new Pose2d(45,-31,Math.toRadians(1)))
//                                            .lineToLinearHeading(new Pose2d(45,-10,Math.toRadians(-45)))
//                                            .forward(5)



                                    //blue backstage new
//                            drive.trajectorySequenceBuilder(new Pose2d(13, 58, 4.7))
//                                    .lineToLinearHeading(new Pose2d(13,31,Math.toRadians(182)))
//                                    .lineToLinearHeading(new Pose2d(45,31,Math.toRadians(1)))
//                                    .lineToLinearHeading(new Pose2d(13,59,Math.toRadians(182)))
//                                    .lineTo(new Vector2d(-52,59))
//                                    .lineTo(new Vector2d(-52,36))
//                                    .lineTo(new Vector2d(-52,59))
//                                    .lineTo(new Vector2d(13,59))
//                                    .lineToLinearHeading(new Pose2d(45,31,Math.toRadians(1)))
//                                    .lineTo(new Vector2d(45,59))
//                                   .forward(5)


                                    //blue audience new
//                            drive.trajectorySequenceBuilder(new Pose2d(-35, 58, 4.7))
//                                    .lineToLinearHeading(new Pose2d(-36,31,Math.toRadians(182)))
//                                    .lineTo(new Vector2d(-36,20))
//                                    .splineToConstantHeading(new Vector2d(-52,11),Math.toRadians(-180))
//                                    .lineTo(new Vector2d(37,11))
//                                    .lineToLinearHeading(new Pose2d(45,31,Math.toRadians(1)))
//                                    .lineToLinearHeading(new Pose2d(37,11,Math.toRadians(180 )))
//                                    .lineTo(new Vector2d(-52,11))
//                                    .lineTo(new Vector2d(37,11))
//                                    .lineToLinearHeading(new Pose2d(45,31,Math.toRadians(1)))
//                                    .lineToLinearHeading(new Pose2d(45,5,Math.toRadians(45)))
//                                    .forward(5)
//




//                            //RED BACKSTAGE SIMPLE (For Acker)
//                                    drive.trajectorySequenceBuilder(new Pose2d(13, -60, -4.7))
//                                            .forward(27)
//                                            .turn(Math.toRadians(90))
//                                            .back(30)
//                                            .turn(Math.toRadians(-180))
//                                            .strafeLeft(22)
//                                            .back(90)
//                                            .turn(Math.toRadians(180))
//                                            .back(90)
//                                            .turn(Math.toRadians(180))
//                                            .strafeRight(22)
//                                            .strafeRight(27)
//                                            .forward(17)
//
//                                            .forward(15)

                                            //blue backstage simple
//                            drive.trajectorySequenceBuilder(new Pose2d(13, 58, 4.7))
//                                    .forward(27)
//                                    .strafeLeft(2)
//                                    .turn(Math.toRadians(-90))
//                                    .forward(1)
//                                    .back(29)
//                                    .turn(Math.toRadians(180))
//                                    .strafeLeft(7)
//                                    .strafeRight(27)
//                                    .back(90)
//                                    .turn(Math.toRadians(-180))
//                                    .back(90)
//                                    .turn(Math.toRadians(-180))
//                                    .strafeLeft(22)
//                                    .strafeLeft(27)
//                                    .forward(17)


                                    //red audience simple
                            drive.trajectorySequenceBuilder(new Pose2d(-35, -57, -4.7))
                                    .forward(24)
                                    .turn(Math.toRadians(-90))
                                    .turn(Math.toRadians(90))
                                    .forward(24)
                                    .turn(Math.toRadians(-90))
                                    .forward(80)
                                    .strafeRight(30)



                                            //Red Audience
//                                    drive.trajectorySequenceBuilder(new Pose2d(-35, -57, -4.7))
//                                            .forward(21)
//                                            .turn(Math.toRadians(45))
//                                            .lineToLinearHeading(new Pose2d(-35,-59,Math.toRadians(1)))
//                                            .forward(83)
//                                            .strafeLeft(Math.toRadians(800))
//                                            .splineToSplineHeading(new Pose2d(13,-12,Math.toRadians(180)),Math.toRadians(190))
//                                            .lineTo(new Vector2d(-55,-12) )
//                                            .lineToLinearHeading(new Pose2d(-32,-10,219.9))
//                                            .lineTo(new Vector2d(45,-12))
//                                            .strafeRight(25)
//                                            .strafeLeft(Math.toRadians(600))
//                                            .splineToSplineHeading(new Pose2d(13,-12,Math.toRadians(180)),Math.toRadians(190))
//                                            .lineTo(new Vector2d(-55,-12))
//                                            .lineToLinearHeading(new Pose2d(-32,-10,219.9))
//                                            .lineTo(new Vector2d(45,-12))
//                                            .strafeRight(25)
//                                            .back(4)
//                                            .lineToSplineHeading(new Pose2d(51,-13,Math.toRadians(-45)))
////                                            .splineToSplineHeading(new Pose2d(54,-13),Math.toRadians(-45))
////                                            .strafeLeft(Math.toRadians(900))
////                                            .lineToLinearHeading(new Pose2d(54,-13,Math.toRadians(-45)))



                                    .build()

                    );

            meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_OFFICIAL)
                    .setDarkMode(true)
                    .setBackgroundAlpha(0.95f)
                    .addEntity(myBot)
                    .start();
        }
    }