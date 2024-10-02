package org.firstinspires.ftc.teamcode.opmode.auto;

import android.util.Log;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.commands.drivecommand.LinePositionCommand;
import org.firstinspires.ftc.teamcode.commands.drivecommand.SplinePositionCommand;
import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.util.Globals;
import org.firstinspires.ftc.teamcode.util.PoseConstants;

@Autonomous(name = "TrajectoryTest")
public class TrajectoryTest extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {
        Robot robot = Robot.getInstance();

        Globals.IS_AUTO = true;
        Globals.ALLIANCE = Globals.Alliance.BLUE;

        robot.initialize(hardwareMap, telemetry);
        CommandScheduler.getInstance().reset();

        waitForStart();

        robot.setPoseEstimate(PoseConstants.Start.redBasket);

//        robot.followTrajectorySequence(
//                robot.trajectorySequenceBuilder(robot.getPoseEstimate())
//                        .lineToLinearHeading(new Pose2d(-54, -54, Math.toRadians(45)))
//                        .waitSeconds(1)
//                        .lineToLinearHeading(new Pose2d(-61, -40, Math.toRadians(135)))
//                        .waitSeconds(1)
//                        .lineToLinearHeading(new Pose2d(-54, -54, Math.toRadians(45)))
//                        .waitSeconds(1)
//                        .lineToLinearHeading(new Pose2d(-60, -36, Math.toRadians(90)))
//                        .waitSeconds(1)
//                        .lineToLinearHeading(new Pose2d(-54, -54, Math.toRadians(45)))
//                        .waitSeconds(1)
//                        .lineToLinearHeading(new Pose2d(-54, -39, Math.toRadians(75)))
//                        .waitSeconds(1)
//                        .build()
//        );

        CommandScheduler.getInstance().schedule(
                new SequentialCommandGroup(
                        new LinePositionCommand(new Pose2d(-54, -54, Math.toRadians(45))),
                        new LinePositionCommand(new Pose2d(-54, -39, Math.toRadians(75))),
                        new LinePositionCommand(new Pose2d(-54, -54, Math.toRadians(45))),
                        new LinePositionCommand(new Pose2d(-60, -36, Math.toRadians(80))),
                        new LinePositionCommand(new Pose2d(-54, -54, Math.toRadians(45))),
                        new LinePositionCommand(new Pose2d(-61, -40, Math.toRadians(120))),
                        new LinePositionCommand(new Pose2d(-54, -54, Math.toRadians(45))),
                        new SplinePositionCommand(new Pose2d(-24, -12, Math.toRadians(180)), Math.toRadians(90), Math.toRadians(-30))
                )
        );

        while (opModeIsActive() && !isStopRequested()) {
            CommandScheduler.getInstance().run();
            robot.updateData();
            robot.periodic();
            robot.write();
        }

    }

}
