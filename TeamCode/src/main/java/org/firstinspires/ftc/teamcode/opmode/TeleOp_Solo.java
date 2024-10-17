package org.firstinspires.ftc.teamcode.opmode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.subsystem.ArmStateCommand;
import org.firstinspires.ftc.teamcode.commands.subsystem.LiftPositionCommand;
import org.firstinspires.ftc.teamcode.commands.teleop.DropSampleCommand;
import org.firstinspires.ftc.teamcode.commands.teleop.IntakePullBackCommand;
import org.firstinspires.ftc.teamcode.commands.teleop.IntakePushOutCommand;
import org.firstinspires.ftc.teamcode.commands.teleop.LiftDownCommand;
import org.firstinspires.ftc.teamcode.commands.teleop.LiftUpCommand;
import org.firstinspires.ftc.teamcode.commands.teleop.SampleTransferCommand;
import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.hardware.Localizer;
import org.firstinspires.ftc.teamcode.hardware.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.util.Constants;
import org.firstinspires.ftc.teamcode.util.Globals;

@TeleOp (name = "Solo")
public class TeleOp_Solo extends CommandOpMode {

    private final Robot robot = Robot.getInstance();
    private final CommandScheduler cs = CommandScheduler.getInstance();
    private GamepadEx g1;

    Localizer localizer;
    Pose2d currentPose;
    double heading;
    double fieldCentricOffset;


    @Override
    public void initialize() {

        g1 = new GamepadEx(gamepad1);

        Globals.IS_AUTO = false;

        robot.initialize(hardwareMap, telemetry);
        robot.setPoseEstimate(robot.data.currentPose);

        Robot.getInstance().data.stopIntaking();
        Robot.getInstance().data.stopScoring();

        switch (Globals.ALLIANCE) {
            case BLUE:
                fieldCentricOffset = Math.toRadians(-90);
                break;
            case RED:
                fieldCentricOffset = Math.toRadians(90);
                break;
        }

        g1.getGamepadButton(GamepadKeys.Button.A)
                .whenPressed(() -> cs.schedule(new LiftDownCommand()));

        g1.getGamepadButton(GamepadKeys.Button.B)
                .whenPressed(() -> cs.schedule(new LiftUpCommand()));

        g1.getGamepadButton(GamepadKeys.Button.X)
                .whenPressed(() -> cs.schedule(new IntakePullBackCommand()));

        g1.getGamepadButton(GamepadKeys.Button.Y)
                .whenPressed(() -> cs.schedule(new IntakePushOutCommand()));

        g1.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .whenPressed(() -> cs.schedule(new DropSampleCommand()));

        g1.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .whenPressed(() -> cs.schedule(new SampleTransferCommand()));

    }

    @Override
    public void run() {

        cs.run();
        robot.periodic();

        robot.update();
        currentPose = robot.getPoseEstimate();
        heading = -currentPose.getHeading();

        Vector2d input = new Vector2d(
                -gamepad1.left_stick_y,
                -gamepad1.left_stick_x
        ).rotated(heading + fieldCentricOffset);


        robot.setWeightedDrivePower(
                new Pose2d(
                        input.getX(),
                        input.getY(),
                        -gamepad1.right_stick_x
                )
        );

        robot.updateData();
        robot.write();

    }
}
