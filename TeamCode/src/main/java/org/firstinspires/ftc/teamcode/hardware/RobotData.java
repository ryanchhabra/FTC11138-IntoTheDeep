package org.firstinspires.ftc.teamcode.hardware;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.subsystems.DepositSubsystem;
import org.firstinspires.ftc.teamcode.util.Globals;

public class RobotData {

    public double liftPosition = 0;
    public double wristPosition = 0;
    public DepositSubsystem.ClawState depositClawState = DepositSubsystem.ClawState.NONE;

    public double extensionPosition = 0;
    public double armPosition = 0;

    public boolean scoring = Globals.IS_SCORING;
    public boolean intaking = Globals.IS_INTAKING;

    public void write(Telemetry telemetry) {
        telemetry.addData("SCORING", Globals.IS_SCORING);
        telemetry.addData("INTAKING", Globals.IS_INTAKING);

        telemetry.addLine();
    }

}
