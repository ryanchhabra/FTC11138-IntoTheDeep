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



    /* -------------------------------------------- MOTOR CONSTANTS -------------------------------------------- */

    // LIFT
    public static double liftMin1 = 0;
    public static double liftMin2 = 0;
    public static double liftMax1 = 100;
    public static double liftMax2 = 100;
    public static double liftSlow = 50;

    public static double liftUpRatio = 1;
    public static double liftDownRatio = 1;
    public static double liftSlowRatio = 1;



    /* -------------------------------------------- VISION RECTANGLE CONSTANTS -------------------------------------------- */



    /* --------------------------------------------  APRILTAG CONSTANTS ------------------------------------------ */


}
