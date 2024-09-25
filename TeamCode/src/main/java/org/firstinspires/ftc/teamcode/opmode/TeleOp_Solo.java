package org.firstinspires.ftc.teamcode.opmode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.subsystem.LiftPositionCommand;
import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.hardware.Localizer;
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

        Globals.stopIntaking();
        Globals.stopScoring();

        switch (Globals.ALLIANCE) {
            case BLUE:
                fieldCentricOffset = Math.toRadians(-90);
                break;
            case RED:
                fieldCentricOffset = Math.toRadians(90);
                break;
        }

        g1.getGamepadButton(GamepadKeys.Button.A)
                .whenPressed(() -> cs.schedule(new LiftPositionCommand(Constants.liftMax1)));

        g1.getGamepadButton(GamepadKeys.Button.B)
                .whenPressed(() -> cs.schedule(new LiftPositionCommand(Constants.liftMin1)));

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
