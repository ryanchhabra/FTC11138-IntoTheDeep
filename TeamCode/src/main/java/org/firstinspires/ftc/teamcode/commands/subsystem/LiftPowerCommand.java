package org.firstinspires.ftc.teamcode.commands.subsystem;

import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.util.Constants;

public class LiftPowerCommand extends InstantCommand {
    public LiftPowerCommand(double power) {
        super(
                () -> Robot.getInstance().depositSubsystem.setLiftPower(power)
        );
    }
}
