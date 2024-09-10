package org.firstinspires.ftc.teamcode.commands.subsystem;

import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.hardware.Robot;

public class WristCommand extends InstantCommand {
    public WristCommand(double pos) {
        Robot.getInstance().depositSubsystem.setWristServo(pos);
    }
}
