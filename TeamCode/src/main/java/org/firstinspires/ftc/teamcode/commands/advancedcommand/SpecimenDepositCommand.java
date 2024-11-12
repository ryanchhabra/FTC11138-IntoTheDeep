package org.firstinspires.ftc.teamcode.commands.advancedcommand;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.subsystem.SpecimenClawStateCommand;
import org.firstinspires.ftc.teamcode.commands.subsystem.SpecimenLiftPositionCommand;
import org.firstinspires.ftc.teamcode.commands.subsystem.SpecimenLiftPowerCommand;
import org.firstinspires.ftc.teamcode.hardware.subsystems.SpecimenSubsystem;
import org.firstinspires.ftc.teamcode.util.Constants;

public class SpecimenDepositCommand extends SequentialCommandGroup {
    public SpecimenDepositCommand() {
        super(
                new SpecimenLiftPowerCommand(-1),
                new WaitCommand(500),
                new SpecimenClawStateCommand(SpecimenSubsystem.SpecimenClawState.OPEN),
                new SpecimenLiftPowerCommand(0),
                new WaitCommand(1000),
                new SpecimenLiftPositionCommand(Constants.specimenLiftMin)
        );
    }
}
