package org.firstinspires.ftc.teamcode.commands.advancedcommand;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.subsystem.SpecimenClawStateCommand;
import org.firstinspires.ftc.teamcode.commands.subsystem.SpecimenLiftPositionCommand;
import org.firstinspires.ftc.teamcode.hardware.subsystems.SpecimenSubsystem;
import org.firstinspires.ftc.teamcode.util.Constants;

public class SpecimenGrabCommand extends SequentialCommandGroup {
    public SpecimenGrabCommand() {
        super(
                new SpecimenClawStateCommand(SpecimenSubsystem.SpecimenClawState.CLOSED),
                new WaitCommand(100),
                new SpecimenLiftPositionCommand(Constants.specimenLiftHigh)
        );
    }
}
