package com.example.meepmeeptesting;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepRoadRunnerAcker {

        public static void main(String[] args) {

            MeepMeep meepMeep = new MeepMeep(800);
            Pose2d redBackstageStartPose = new Pose2d(13, -60, -4.7);
            Pose2d redAudienceStartPose = new Pose2d(-35, -60, -4.7);

            RoadRunnerBotEntity myBot1 = new DefaultBotBuilder(meepMeep)
                    .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(redAudienceStartPose)

//                                    //Red Audience Left
//                                    .forward(24)
//                                    .addDisplacementMarker(() -> {
//                                        //Bot.collectorPosition();
//                                        // if (Bot.pixelDistanceSensor1.getDistance(DistanceUnit.INCH) > 1.05 && Bot.pixelDistanceSensor1.getDistance(DistanceUnit.INCH) < 1.5 ))
//                                        //Bot.rightWormgearDown(.6, 800);
//                                    })
//                                    .turn(Math.toRadians(90))
//                                    .forward(8)
//                                    .back(.5)
//                                    .addDisplacementMarker(() -> {
////                                        //Bot.leftPixelClawOpen();
//                                    })
//                                    .back(7.5)
//                                    .waitSeconds(1)
//                                    .turn(Math.toRadians(-90))
//                                    .forward(24)
//                                    .turn(Math.toRadians(-90))
//                                    .forward(80)
//                                    .strafeRight(30)
//                                    .build()


                                    //Red Audience Right
//                                    .forward(24)
//                                    .addDisplacementMarker(() -> {
//                                        //Bot.collectorPosition();
//                                        // if (Bot.pixelDistanceSensor1.getDistance(DistanceUnit.INCH) > 1.05 && Bot.pixelDistanceSensor1.getDistance(DistanceUnit.INCH) < 1.5 ))
//                                        //Bot.rightWormgearDown(.6, 800);
//                                    })
//                                    .turn(Math.toRadians(-90))
//                                    .forward(8)
//                                    .back(.5)
//                                    .addDisplacementMarker(() -> {
////                                        //Bot.leftPixelClawOpen();
//                                    })
//                                    .back(7.5)
//                                    .waitSeconds(1)
//                                    .turn(Math.toRadians(90))
//                                    .forward(24)
//                                    .turn(Math.toRadians(-90))
//                                    .forward(80)
//                                    .strafeRight(30)
//                                    .build()

                                    // Red Audience Middle
                                    .forward(24)
                                    .addDisplacementMarker(() -> {
                                        //Bot.collectorPosition();
                                        // if (Bot.pixelDistanceSensor1.getDistance(DistanceUnit.INCH) > 1.05 && Bot.pixelDistanceSensor1.getDistance(DistanceUnit.INCH) < 1.5 ))
                                        //Bot.rightWormgearDown(.6, 800);
                                    })
                                    .forward(8)
                                    .back(.5)
                                    .addDisplacementMarker(() -> {
//                                        //Bot.leftPixelClawOpen();
                                    })
                                    .back(7.5)
                                    .strafeLeft(20)
                                    .waitSeconds(1)
                                    .forward(24)
                                    .turn(Math.toRadians(-90))
                                    .forward(100)
                                    .strafeRight(30)
                                    .build()



                            // Red Backstage Left
//                                    .forward(25)
//                                    .addDisplacementMarker(() -> {
//                                        //Bot.collectorPosition();
//                                        // if (Bot.pixelDistanceSensor1.getDistance(DistanceUnit.INCH) > 1.05 && Bot.pixelDistanceSensor1.getDistance(DistanceUnit.INCH) < 1.5 ))
//                                        //Bot.rightWormgearDown(.6, 800);
//                                    })
//                                    .turn(Math.toRadians(90))
//                                    .forward(8)
//                                    .back(0.5)
//                                    .addDisplacementMarker(() -> {
//                                        //Bot.leftPixelClawOpen();
//                                    })
//                                    .waitSeconds(1)
//                                    .back(24)
//                                    .addDisplacementMarker(() -> {
//                                        //Bot.leftPixelClawClose();
//                                        //Bot.drivePosition();
//                                    })
//                                    .turn(Math.toRadians(-180))
//                                    .addDisplacementMarker(()->{
//                                        //Bot.rightWormgearUp(1,751);
//                                        //Bot.autoPlacePosition();
//                                    })
//                                    .strafeLeft(23)
//                                    .forward(15)
//                                    .build()

                    );



            meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                    .setDarkMode(true)
                    .setBackgroundAlpha(0.95f)
                    .addEntity(myBot1)
                    .start();
        }
    }