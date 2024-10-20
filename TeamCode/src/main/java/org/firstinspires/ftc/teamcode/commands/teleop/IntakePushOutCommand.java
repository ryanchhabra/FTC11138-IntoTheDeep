package org.firstinspires.ftc.teamcode.commands.teleop;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.subsystem.ArmStateCommand;
import org.firstinspires.ftc.teamcode.commands.subsystem.ExtensionPositionCommand;
import org.firstinspires.ftc.teamcode.commands.subsystem.IntakeStateCommand;
import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.hardware.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.util.Constants;

public class IntakePushOutCommand extends SequentialCommandGroup {
    public IntakePushOutCommand() {
        super(
                new InstantCommand(Robot.getInstance().data::startIntaking),
                new ExtensionPositionCommand(Constants.extIntake),
                new ArmStateCommand(IntakeSubsystem.ArmState.INTAKE),
                new IntakeStateCommand(IntakeSubsystem.IntakeState.IN)
        );
    }
}
