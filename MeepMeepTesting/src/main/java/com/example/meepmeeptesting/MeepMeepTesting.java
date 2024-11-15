package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {

    public static Pose2d redBasketAngle = new Pose2d(-56, -58, Math.toRadians(45));
    public static double sample1x = -31;
    public static double sample1y = -36;
    public static double sample1degrees = 150;
    public static int sample1ext = 750;

    public static double sample2x = -37;
    public static double sample2y = -27.5;
    public static double sample2degrees = 180;
    public static int sample2ext = 650;

    public static double sample3x = -47;
    public static double sample3y = -27.5;
    public static double sample3degrees = 180;
    public static int sample3ext = 500;

    public static double sample4x = 31;
    public static double sample4y = -36;
    public static double sample4degrees = 30;
    public static int sample4ext = 500;

    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        Pose2d sample1 = new Pose2d(sample1x, sample1y, Math.toRadians(sample1degrees));
        Pose2d sample2 = new Pose2d(sample2x, sample2y, Math.toRadians(sample2degrees));
        Pose2d sample3 = new Pose2d(sample3x, sample3y, Math.toRadians(sample3degrees));
        Pose2d sample4 = new Pose2d(sample4x, sample4y, Math.toRadians(sample4degrees));

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(80, 60, Math.toRadians(180), Math.toRadians(180), 16)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(-56, -58, Math.toRadians(45)))
//                        .lineToSplineHeading(redBasketAngle)
//                        .waitSeconds(1)
//                        .setTangent(0)
//                        .splineToSplineHeading(sample1, Math.toRadians(90))
//                        .waitSeconds(1)
//                        .lineToLinearHeading(redBasketAngle)
//                        .waitSeconds(1)
//                        .setTangent(0)
//                        .splineToSplineHeading(sample2, Math.toRadians(90))
//                        .waitSeconds(1)
//                        .lineToLinearHeading(redBasketAngle)
//                        .waitSeconds(1)
//                        .setTangent(0)
//                        .splineToSplineHeading(sample3, Math.toRadians(90))
//                        .waitSeconds(1)
//                        .lineToLinearHeading(redBasketAngle)
//                        .waitSeconds(1)
                        .setTangent(Math.toRadians(45))
                        .splineToSplineHeading(new Pose2d(31, -36, Math.toRadians(30)), Math.toRadians(0))
                        .waitSeconds(1)
                        .setTangent(Math.toRadians(180))
                        .splineToSplineHeading(new Pose2d(-56, -58, Math.toRadians(45)), Math.toRadians(225))
                        .build()
                );

        myBot.setDimensions(17, 17);

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}