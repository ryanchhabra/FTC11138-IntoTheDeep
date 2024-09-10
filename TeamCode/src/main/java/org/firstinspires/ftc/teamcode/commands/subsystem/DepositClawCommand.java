package org.firstinspires.ftc.teamcode.commands.subsystem;

import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.hardware.subsystems.DepositSubsystem;

public class DepositClawCommand extends InstantCommand {
    public DepositClawCommand(DepositSubsystem.ClawState state) {
        super(
                () -> Robot.getInstance().depositSubsystem.updateClawState(state)
        );
    }
}
