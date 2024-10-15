package org.firstinspires.ftc.teamcode.commands.teleop;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.subsystem.LiftPositionCommand;
import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.util.Constants;

public class LiftUpCommand extends SequentialCommandGroup {
    public LiftUpCommand() {
        super(
                new LiftPositionCommand(Constants.liftMax1),
                new InstantCommand(Robot.getInstance().data::startScoring)
        );
    }
}
