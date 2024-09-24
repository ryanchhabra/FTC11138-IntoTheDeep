package org.firstinspires.ftc.teamcode.util;

public class MotorParams {

    public int minPosition, maxPosition, slow;
    public double maxPower, minPower, upRatio, downRatio, slowRatio;

    public MotorParams(int minPos, int maxPos, int slow, double maxPow, double minPow, double upRatio, double downRatio, double slowRatio) {
        this.minPosition = minPos;
        this.maxPosition = maxPos;
        this.maxPower = maxPow;
        this.minPower = minPow;
        this.upRatio = upRatio;
        this.downRatio = downRatio;
        this.slow = slow;
        this.slowRatio = slowRatio;
    }

}
