package org.firstinspires.ftc.teamcode.commands.drivecommand;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.hardware.Robot;

public class DriveForwardCommand extends CommandBase {

    public Pose2d startPose;
    public int targetDistance;

    private Robot robot = Robot.getInstance();

    public DriveForwardCommand(int distance) {
        this.targetDistance = distance;
    }

    @Override
    public void initialize() {
        this.startPose = robot.getPoseEstimate();

        robot.followTrajectoryAsync(
                robot.trajectoryBuilder(startPose)
                        .forward(targetDistance)
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
