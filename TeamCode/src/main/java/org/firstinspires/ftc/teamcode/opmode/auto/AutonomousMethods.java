package org.firstinspires.ftc.teamcode.opmode.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.hardware.Robot;

public abstract class AutonomousMethods extends LinearOpMode {

    public Robot robot = Robot.getInstance();

    FtcDashboard dashboard = FtcDashboard.getInstance();
    public ElapsedTime runtime = new ElapsedTime();

    public void relocalize() {
        robot.limelight.start();

        LLResult result = null;
        while (result == null) {
            result = robot.limelight.getLatestResult();
        }
        while (!result.isValid()) {
            result = robot.limelight.getLatestResult();
        }

        Pose3D robotPose = result.getBotpose();
        Position robotPosition = robotPose.getPosition().toUnit(DistanceUnit.INCH);
        YawPitchRollAngles robotOrientation = robotPose.getOrientation();

        robot.setPoseEstimate(new Pose2d(robotPosition.x, robotPosition.y, robotOrientation.getYaw(AngleUnit.RADIANS)));

        robot.limelight.stop();
    }

}
