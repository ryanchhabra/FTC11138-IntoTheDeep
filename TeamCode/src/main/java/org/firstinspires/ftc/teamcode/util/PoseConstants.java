package org.firstinspires.ftc.teamcode.util;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;

@Config
public class PoseConstants {

    public static class Start {
        public static Pose2d redBasket = new Pose2d(-39.5, -63.5, Math.toRadians(0));
        public static Pose2d redObsv = new Pose2d(39.5, -63.5, Math.toRadians(0));

        public static Pose2d blueBasket = new Pose2d(39.5, 63.5, Math.toRadians(180));
        public static Pose2d blueObsv = new Pose2d(-39.5, -63.5, Math.toRadians(180));
    }

    public static class Score {
        public static Pose2d redBasket = new Pose2d(-54, -54, Math.toRadians(45));
        public static Pose2d redBasketWall = new Pose2d(-50, -63.5, Math.toRadians(0));
        public static Pose2d blueBasket = new Pose2d(54, 54, Math.toRadians(225));
    }

}
