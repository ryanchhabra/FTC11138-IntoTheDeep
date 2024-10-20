package org.firstinspires.ftc.teamcode.opmode.auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.commands.drivecommand.LinePositionCommand;
import org.firstinspires.ftc.teamcode.commands.drivecommand.SplinePositionCommand;
import org.firstinspires.ftc.teamcode.commands.subsystem.ArmStateCommand;
import org.firstinspires.ftc.teamcode.commands.subsystem.BucketStateCommand;
import org.firstinspires.ftc.teamcode.commands.teleop.DropSampleCommand;
import org.firstinspires.ftc.teamcode.commands.teleop.IntakePullBackCommand;
import org.firstinspires.ftc.teamcode.commands.teleop.IntakePushOutCommand;
import org.firstinspires.ftc.teamcode.commands.teleop.LiftDownCommand;
import org.firstinspires.ftc.teamcode.commands.teleop.LiftUpCommand;
import org.firstinspires.ftc.teamcode.commands.teleop.SampleTransferCommand;
import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.hardware.subsystems.DepositSubsystem;
import org.firstinspires.ftc.teamcode.hardware.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.util.Globals;
import org.firstinspires.ftc.teamcode.util.PoseConstants;

@Autonomous(name = "Red Basket 1+3")
public class RedBasket_1Plus3 extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {
        Robot robot = Robot.getInstance();

        Globals.IS_AUTO = true;
        Globals.ALLIANCE = Globals.Alliance.BLUE;

        robot.initialize(hardwareMap, telemetry);
        CommandScheduler.getInstance().reset();

        Pose2d redBasketAngle = PoseConstants.Score.redBasketAngle;
        Pose2d sample1 = new Pose2d(-54, -39, Math.toRadians(75));
        Pose2d sample2 = new Pose2d(-61, -40, Math.toRadians(120));
        Pose2d sample3 = new Pose2d(-61, -40, Math.toRadians(120));

        while (!isStarted()) {
            CommandScheduler.getInstance().run();
            robot.updateData();
            robot.periodic();
            robot.write();
        }

        robot.setPoseEstimate(PoseConstants.Start.redBasket);
        robot.data.stopIntaking();
        robot.data.stopScoring();
        robot.data.setSampleLoaded();

        CommandScheduler.getInstance().schedule(
                new SequentialCommandGroup(
                        new ArmStateCommand(IntakeSubsystem.ArmState.UP),
                        new BucketStateCommand(DepositSubsystem.BucketState.INTAKE),
                        new LinePositionCommand(redBasketAngle)
                                .alongWith(new LiftUpCommand()),
                        new DropSampleCommand(),
                        new LiftDownCommand(),
                        new IntakePushOutCommand(),
                        new LinePositionCommand(sample1),
                        new IntakePullBackCommand(),
                        new SampleTransferCommand(),
                        new LinePositionCommand(redBasketAngle)
                                .alongWith(new LiftUpCommand()),
                        new DropSampleCommand(),
                        new LiftDownCommand(),
                        new IntakePushOutCommand(),
                        new LinePositionCommand(sample2),
                        new IntakePullBackCommand(),
                        new SampleTransferCommand(),
                        new LinePositionCommand(redBasketAngle)
                                .alongWith(new LiftUpCommand()),
                        new DropSampleCommand(),
                        new LiftDownCommand(),
                        new IntakePushOutCommand(),
                        new LinePositionCommand(sample3),
                        new IntakePullBackCommand(),
                        new SampleTransferCommand(),
                        new LinePositionCommand(redBasketAngle)
                                .alongWith(new LiftUpCommand()),
                        new DropSampleCommand(),
                        new SplinePositionCommand(new Pose2d(-24, -12, Math.toRadians(180)), Math.toRadians(90), Math.toRadians(-30))
                                .alongWith(new LiftDownCommand())
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
