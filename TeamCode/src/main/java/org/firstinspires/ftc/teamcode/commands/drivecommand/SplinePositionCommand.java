package org.firstinspires.ftc.teamcode.commands.drivecommand;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.hardware.Robot;

public class SplinePositionCommand extends CommandBase {

    public Pose2d startPose;
    public Pose2d targetPose;
    public double st;
    public double et;

    private Robot robot = Robot.getInstance();

    public SplinePositionCommand(Pose2d target, double st, double et) {
        this.st = st;
        this.et = et;
        this.targetPose = target;
    }

    @Override
    public void initialize() {
        this.startPose = robot.getPoseEstimate();

        robot.followTrajectoryAsync(
                robot.trajectoryBuilder(startPose, st)
                        .splineToSplineHeading(targetPose, et)
                        .build()
        );
    }

    @Override
    public void execute() {
        robot.update();
    }

    @Override
    public boolean isFinished() {
        return Thread.currentThread().isInterrupted() || !robot.isBusy();
    }
}
