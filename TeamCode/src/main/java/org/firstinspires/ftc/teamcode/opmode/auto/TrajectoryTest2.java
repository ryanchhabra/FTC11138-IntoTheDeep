package org.firstinspires.ftc.teamcode.opmode.auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.commands.drivecommand.LinePositionCommand;
import org.firstinspires.ftc.teamcode.commands.drivecommand.SplinePositionCommand;
import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.util.Globals;
import org.firstinspires.ftc.teamcode.util.PoseConstants;

@Autonomous(name = "TrajectoryTest2")
public class TrajectoryTest2 extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {
        Robot robot = Robot.getInstance();

        Globals.IS_AUTO = true;
        Globals.ALLIANCE = Globals.Alliance.BLUE;

        robot.initialize(hardwareMap, telemetry);
        CommandScheduler.getInstance().reset();

        waitForStart();

        robot.setPoseEstimate(PoseConstants.Start.redObsv);

        CommandScheduler.getInstance().schedule(
                new SequentialCommandGroup(
                        new SplinePositionCommand(PoseConstants.Score.redBasketWall, Math.toRadians(170), Math.toRadians(190)),
                        new WaitCommand(2000),
                        new SplinePositionCommand(new Pose2d(38, -35, Math.toRadians(45)), Math.toRadians(10), Math.toRadians(45)),
                        new WaitCommand(1000),
                        new SplinePositionCommand(PoseConstants.Score.redBasketWall, Math.toRadians(220), Math.toRadians(190)),
                        new WaitCommand(2000),
                        new SplinePositionCommand(new Pose2d(48, -35, Math.toRadians(45)), Math.toRadians(10), Math.toRadians(45)),
                        new WaitCommand(1000),
                        new SplinePositionCommand(PoseConstants.Score.redBasketWall, Math.toRadians(210), Math.toRadians(190)),
                        new WaitCommand(2000),
                        new SplinePositionCommand(new Pose2d(58, -35, Math.toRadians(45)), Math.toRadians(10), Math.toRadians(45)),
                        new WaitCommand(1000),
                        new SplinePositionCommand(PoseConstants.Score.redBasketWall, Math.toRadians(210), Math.toRadians(190)),
                        new WaitCommand(2000)
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
