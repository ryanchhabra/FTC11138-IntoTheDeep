package org.firstinspires.ftc.teamcode.opmode.auto;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.commands.drivecommand.LinePositionCommand;
import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.util.Globals;
import org.firstinspires.ftc.teamcode.util.PoseConstants;

@Autonomous (name="ReturnToStart_RedObsv")
public class ReturnToStart_RedObsv extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Robot robot = Robot.getInstance();

        Globals.IS_AUTO = true;
        Globals.ALLIANCE = Globals.Alliance.BLUE;

        robot.initialize(hardwareMap, telemetry);
        CommandScheduler.getInstance().reset();

        waitForStart();

        robot.setPoseEstimate(robot.data.currentPose);

        CommandScheduler.getInstance().schedule(
                new SequentialCommandGroup(
                        new LinePositionCommand(PoseConstants.Start.redObsv)
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
