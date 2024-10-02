package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(80, 60, Math.toRadians(180), Math.toRadians(180), 17)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(39.5, -63.5, Math.toRadians(0)))
                        .setTangent(Math.toRadians(170))
                        .splineToSplineHeading(new Pose2d(-50,-63.5, Math.toRadians(0)), Math.toRadians(190))
                        .waitSeconds(2)
                        .setTangent(Math.toRadians(10))
                        .splineToSplineHeading(new Pose2d(38, -35, Math.toRadians(45)), Math.toRadians(45))
                        .waitSeconds(1)
                        .setTangent(Math.toRadians(220))
                        .splineToSplineHeading(new Pose2d(-50, -63.5, Math.toRadians(0)), Math.toRadians(190))
                        .waitSeconds(2)
                        .setTangent(Math.toRadians(10))
                        .splineToSplineHeading(new Pose2d(48, -35, Math.toRadians(45)), Math.toRadians(45))
                        .waitSeconds(1)
                        .setTangent(Math.toRadians(210))
                        .splineToSplineHeading(new Pose2d(-50, -63.5, Math.toRadians(0)), Math.toRadians(190))
                        .waitSeconds(2)
                        .setTangent(Math.toRadians(10))
                        .splineToSplineHeading(new Pose2d(58, -35, Math.toRadians(45)), Math.toRadians(45))
                        .waitSeconds(1)
                        .setTangent(Math.toRadians(210))
                        .splineToSplineHeading(new Pose2d(-50, -63.5, Math.toRadians(0)), Math.toRadians(190))
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