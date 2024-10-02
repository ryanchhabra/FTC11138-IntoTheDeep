package org.firstinspires.ftc.teamcode.commands.drivecommand;

import android.util.Log;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.hardware.Robot;

public class LinePositionCommand extends CommandBase {

    public Pose2d startPose;
    public Pose2d targetPose;

    private Robot robot = Robot.getInstance();

    public LinePositionCommand(Pose2d target) {
        this.targetPose = target;
    }

    @Override
    public void initialize() {
        this.startPose = robot.getPoseEstimate();

        robot.followTrajectoryAsync(
                robot.trajectoryBuilder(startPose)
                        .lineToSplineHeading(targetPose)
                        .build()
        );
    }

    @Override
    public void execute() {
        robot.update();
    }

    @Override
    public boolean isFinished() {
        return !robot.isBusy();
    }

}
