package org.firstinspires.ftc.teamcode.util;

import com.acmerobotics.dashboard.config.Config;

@Config
public class Globals {

    public static boolean LIMITS = true;

    public static boolean IS_AUTO = false;

    public static Alliance ALLIANCE;


    public static boolean IS_SCORING = false;
    public static boolean IS_INTAKING = false;

    public static void startScoring() {
        IS_SCORING = true;
        IS_INTAKING = false;
    }

    public static void stopScoring() {
        IS_SCORING = false;
        IS_INTAKING = false;
    }

    public static void startIntaking() {
        IS_SCORING = false;
        IS_INTAKING = true;
    }

    public static void stopIntaking() {
        IS_SCORING = false;
        IS_INTAKING = false;
    }


    public enum Alliance {
        RED,
        BLUE
    }

}
