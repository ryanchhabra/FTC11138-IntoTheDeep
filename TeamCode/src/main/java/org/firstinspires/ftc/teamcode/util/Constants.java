package org.firstinspires.ftc.teamcode.util;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;

@Config
public class Constants {

    public static RevBlinkinLedDriver.BlinkinPattern closePattern = RevBlinkinLedDriver.BlinkinPattern.WHITE;
    public static RevBlinkinLedDriver.BlinkinPattern openPattern = RevBlinkinLedDriver.BlinkinPattern.RED;

    public static RevBlinkinLedDriver.BlinkinPattern redPattern = RevBlinkinLedDriver.BlinkinPattern.RED;
    public static RevBlinkinLedDriver.BlinkinPattern whitePattern = RevBlinkinLedDriver.BlinkinPattern.WHITE;
    public static RevBlinkinLedDriver.BlinkinPattern bluePattern = RevBlinkinLedDriver.BlinkinPattern.BLUE;
    public static RevBlinkinLedDriver.BlinkinPattern greenPattern = RevBlinkinLedDriver.BlinkinPattern.GREEN;

    /* -------------------------------------------- TELE OP CONSTANTS -------------------------------------------- */



    /* -------------------------------------------- AUTO CONSTANTS -------------------------------------------- */



    /* -------------------------------------------- DRIVE CONSTANTS -------------------------------------------- */




    /* -------------------------------------------- SERVO CONSTANTS -------------------------------------------- */

    public static double bucketDrop = 0;
    public static double bucketIntake = 0.43;
    public static double bucketAscent = 0;

    public static double armTransfer = 1;
    public static double armIntake = 0.35;
    public static double armUp = 0.73;

    public static double armServoOffset = 0;



    /* -------------------------------------------- MOTOR CONSTANTS -------------------------------------------- */

    // LIFT
    public static int liftMin1 = 0;
    public static int liftMin2 = 0;
    public static int liftMax1 = 1200;
    public static int liftMid1 = 700;
    public static int liftMax2 = 100;
    public static int liftSlow = 500;
    public static int liftAscent = 470;

    public static double liftUpRatio = 1;
    public static double liftDownRatio = 1;
    public static double liftSlowRatio = .4;

    // Extension
    public static int extMin = 0;
    public static int extIntake = 1000;
    public static int extMax = 1300;
    public static int extSlow = 50;

    public static int extJump = 400;

    public static double extUpRatio = 1;
    public static double extDownRatio = 1;
    public static double extSlowRatio = 1;



    /* -------------------------------------------- VISION RECTANGLE CONSTANTS -------------------------------------------- */



    /* --------------------------------------------  APRILTAG CONSTANTS ------------------------------------------ */


}
