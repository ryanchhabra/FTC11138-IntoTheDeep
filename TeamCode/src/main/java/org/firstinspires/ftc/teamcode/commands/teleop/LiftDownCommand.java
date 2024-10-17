package org.firstinspires.ftc.teamcode.commands.teleop;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.subsystem.LiftPositionCommand;
import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.util.Constants;

public class LiftDownCommand extends SequentialCommandGroup {
    public LiftDownCommand() {
        super(
                new LiftPositionCommand(Constants.liftMin1),
                new InstantCommand(Robot.getInstance().data::stopScoring)
        );
    }
}