package org.firstinspires.ftc.teamcode.hardware;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.subsystems.DepositSubsystem;
import org.firstinspires.ftc.teamcode.util.Globals;

public class RobotData {

    public int liftPosition = 0;
    public double wristPosition = 0;
    public DepositSubsystem.ClawState depositClawState = DepositSubsystem.ClawState.NONE;

    public int extensionPosition = 0;
    public double armPosition = 0;

    public Pose2d currentPose = new Pose2d(0,0, Math.toRadians(0));

    public boolean scoring = false;
    public boolean intaking = false;

    public void write(Telemetry telemetry) {

        telemetry.addData("POSE", this.currentPose);

        telemetry.addLine();

        telemetry.addData("SCORING", this.scoring);
        telemetry.addData("INTAKING", this.intaking);

        telemetry.addLine();

        telemetry.addData("Lift Position", this.liftPosition);
        telemetry.addData("Wrist Position", this.wristPosition);

        telemetry.addLine();

        telemetry.addData("Extension Position", this.extensionPosition);
        telemetry.addData("Arm Position", this.armPosition);

        telemetry.update();
    }

    public void startScoring() {
        scoring = true;
        intaking = false;
    }

    public void stopScoring() {
        scoring = false;
        intaking = false;
    }

    public void startIntaking() {
        scoring = false;
        intaking = true;
    }

    public void stopIntaking() {
        scoring = false;
        intaking = false;
    }

}
