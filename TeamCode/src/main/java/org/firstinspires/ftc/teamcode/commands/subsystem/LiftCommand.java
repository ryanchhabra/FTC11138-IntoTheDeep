package org.firstinspires.ftc.teamcode.commands.subsystem;

import com.arcrobotics.ftclib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.hardware.Robot;

public class LiftCommand extends InstantCommand {
    public LiftCommand(double power, int target) {
        super(
                () -> Robot.getInstance().depositSubsystem.setLiftPosition(power, target)
        );
    }
}
