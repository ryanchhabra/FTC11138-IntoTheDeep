package org.firstinspires.ftc.teamcode.hardware.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.util.Constants;
import org.firstinspires.ftc.teamcode.util.wrappers.RE_DcMotorExParams;
import org.firstinspires.ftc.teamcode.util.wrappers.RE_DcMotorEx;
import org.firstinspires.ftc.teamcode.util.wrappers.RE_SubsystemBase;

public class SpecimenSubsystem extends RE_SubsystemBase {

    private final RE_DcMotorEx specimenLift;
    private final RE_DcMotorExParams specimenLiftParams = new RE_DcMotorExParams(
            Constants.specimenLiftMin, Constants.specimenLiftMax, Constants.specimenLiftSlow,
            1, 1, Constants.specimenLiftUpRatio, Constants.specimenLiftDownRatio, Constants.specimenLiftSlowRatio
    );
    private final Servo specimenClaw;
    private SpecimenClawState specimenClawState;

    public enum SpecimenClawState {
        CLOSED,
        OPEN,
        NONE
    }

    public SpecimenSubsystem(HardwareMap hardwareMap, String specimenClaw, String specimenLift) {
        this.specimenLift = new RE_DcMotorEx(hardwareMap.get(DcMotorEx.class, specimenLift), specimenLiftParams);
        this.specimenClaw = hardwareMap.get(Servo.class, specimenClaw);
        this.specimenClawState = SpecimenClawState.OPEN;
    }

    @Override
    public void updateData() {
        Robot.getInstance().data.specimenLiftPosition = this.specimenLift.getPosition();
        Robot.getInstance().data.specimenClawState = this.specimenClawState;
    }

    public void setSpecimenLiftPower(double power) {
        this.specimenLift.setPower(power);
    }

    public void setTargetSpecimenLiftPosition(int target) {
        this.specimenLift.setTargetPosition(target, this.specimenLiftParams.maxPower);
    }

    public void setSpecimenLiftPosition(double power, int target) {
        this.specimenLift.setPosition(power, target);
    }

    public void updateSpecimenClawState(SpecimenClawState state) {
        this.specimenClawState = state;
        this.specimenClaw.setPosition(getSpecimenClawStatePosition(state));
    }

    private double getSpecimenClawStatePosition(SpecimenClawState state) {
        switch (state) {
            case OPEN:
                return Constants.specimenClawOpen;
            case CLOSED:
                return Constants.specimenClawClose;
            default:
                return 0;
        }
    }

    public SpecimenClawState getSpecimenClawState() {
        return this.specimenClawState;
    }

    @Override
    public void periodic() {
        this.specimenLift.periodic();

        this.specimenClaw.setPosition(getSpecimenClawStatePosition(this.specimenClawState));
    }

}
