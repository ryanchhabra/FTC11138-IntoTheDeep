package org.firstinspires.ftc.teamcode.opmode.auto;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.commands.advancedcommand.Ascent1Command;
import org.firstinspires.ftc.teamcode.commands.advancedcommand.DropSampleCommand;
import org.firstinspires.ftc.teamcode.commands.advancedcommand.ExtensionJumpCommand;
import org.firstinspires.ftc.teamcode.commands.advancedcommand.IntakePullBackCommand;
import org.firstinspires.ftc.teamcode.commands.advancedcommand.IntakePushOutCommand;
import org.firstinspires.ftc.teamcode.commands.advancedcommand.LiftDownCommand;
import org.firstinspires.ftc.teamcode.commands.advancedcommand.LiftUpCommand;
import org.firstinspires.ftc.teamcode.commands.advancedcommand.SampleTransferCommand;
import org.firstinspires.ftc.teamcode.commands.drivecommand.LinePositionCommand;
import org.firstinspires.ftc.teamcode.commands.drivecommand.SplinePositionCommand;
import org.firstinspires.ftc.teamcode.commands.subsystem.ArmStateCommand;
import org.firstinspires.ftc.teamcode.commands.subsystem.BucketStateCommand;
import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.hardware.subsystems.DepositSubsystem;
import org.firstinspires.ftc.teamcode.hardware.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.util.Globals;
import org.firstinspires.ftc.teamcode.util.PoseConstants;

@Config
@Autonomous(name = "Blue Basket 1+2", preselectTeleOp = "Solo")
public class BlueBasket_1Plus2 extends LinearOpMode {

    public static Pose2d bluebasketAngle = PoseConstants.Score.blueBasketAngle;
    public static double sample1x = 36;
    public static double sample1y = 26;
    public static double sample1degrees = 0;
    public static int sample1ext = 600;

    public static double sample2x = 47;
    public static double sample2y = 26.5;
    public static double sample2degrees = 0;
    public static int sample2ext = 500;

    @Override
    public void runOpMode() throws InterruptedException {
        Robot robot = Robot.getInstance();

        Globals.IS_AUTO = true;
        Globals.ALLIANCE = Globals.Alliance.BLUE;

        robot.initialize(hardwareMap, telemetry);
        CommandScheduler.getInstance().reset();

        while (!isStarted()) {
            CommandScheduler.getInstance().run();
            robot.updateData();
            robot.periodic();
            robot.write();
        }

        robot.setPoseEstimate(PoseConstants.Start.blueBasket);
        robot.data.stopIntaking();
        robot.data.stopScoring();
        robot.data.setSampleLoaded();

        Pose2d sample1 = new Pose2d(sample1x, sample1y, Math.toRadians(sample1degrees));
        Pose2d sample2 = new Pose2d(sample2x, sample2y, Math.toRadians(sample2degrees));

        CommandScheduler.getInstance().schedule(
                new SequentialCommandGroup(
                        new ArmStateCommand(IntakeSubsystem.ArmState.UP),
                        new BucketStateCommand(DepositSubsystem.BucketState.INTAKE),
                        new LinePositionCommand(bluebasketAngle)
                                .alongWith(new LiftUpCommand()),
                        new WaitCommand(500),
                        new DropSampleCommand(),
                        new LiftDownCommand(),
                        new IntakePushOutCommand(0),

                        new SplinePositionCommand(sample1, 180, Math.toRadians(-90)),
                        new ExtensionJumpCommand(1, sample1ext),
                        new WaitCommand(1000),
                        new LinePositionCommand(bluebasketAngle)
                                .alongWith(
                                        new SequentialCommandGroup(
                                                new IntakePullBackCommand(),
                                                new SampleTransferCommand(),
                                                new WaitCommand(200),
                                                new LiftUpCommand(),
                                                new WaitCommand(700)
                                        )
                                ),
                        new WaitCommand(1000),
                        new DropSampleCommand(),
                        new LiftDownCommand(),
                        new IntakePushOutCommand(0),

                        new SplinePositionCommand(sample2, 180, Math.toRadians(-90)),
                        new ExtensionJumpCommand(1, sample2ext),
                        new WaitCommand(1000),
                        new LinePositionCommand(bluebasketAngle)
                                .alongWith(
                                        new SequentialCommandGroup(
                                                new IntakePullBackCommand(),
                                                new SampleTransferCommand(),
                                                new WaitCommand(200),
                                                new LiftUpCommand(),
                                                new WaitCommand(700)
                                        )
                                ),
                        new WaitCommand(1000),
                        new DropSampleCommand(),
                        new LiftDownCommand(),

                        new SplinePositionCommand(new Pose2d(18, 12, Math.toRadians(0)), Math.toRadians(-90), Math.toRadians(150))
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
