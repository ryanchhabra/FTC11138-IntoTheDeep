package org.firstinspires.ftc.teamcode.commands.teleop;

import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.hardware.Robot;

public class LiftSetCommand extends InstantCommand {
    public LiftSetCommand(int target) {
        super(
                () -> Robot.getInstance().depositSubsystem.setTargetLiftPosition(target)
        );
    }
}
