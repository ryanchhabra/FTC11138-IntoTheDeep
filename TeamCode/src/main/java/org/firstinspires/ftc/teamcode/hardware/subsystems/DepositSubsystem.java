package org.firstinspires.ftc.teamcode.hardware.subsystems;

import com.arcrobotics.ftclib.hardware.ServoEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Const;
import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.util.MotorParams;
import org.firstinspires.ftc.teamcode.util.wrappers.RE_DcMotorEx;
import org.firstinspires.ftc.teamcode.util.wrappers.RE_SubsystemBase;
import org.firstinspires.ftc.teamcode.util.Constants;
import org.firstinspires.ftc.teamcode.util.Globals;

public class DepositSubsystem extends RE_SubsystemBase {

    private final RE_DcMotorEx lift;
    private final MotorParams liftParams = new MotorParams(
            Constants.liftMin1, Constants.liftMax1, Constants.liftSlow,
            1, 0.1, Constants.liftUpRatio, Constants.liftDownRatio, Constants.liftSlowRatio
    );

    private final ServoEx wrist, claw;

    private ClawState clawState;

    public enum ClawState {
        OPEN,
        CLOSED,
        NONE
    }


    public DepositSubsystem(HardwareMap hardwareMap, String lift1, String wrist, String claw) {
        this.lift = new RE_DcMotorEx(hardwareMap.get(DcMotorEx.class, lift1), liftParams);
        this.wrist = hardwareMap.get(ServoEx.class, wrist);
        this.claw = hardwareMap.get(ServoEx.class, claw);
        this.clawState = ClawState.OPEN;

        Robot.getInstance().subsystems.add(this);
    }

    @Override
    public void updateData() {
        Robot.getInstance().data.liftPosition = this.lift.getPosition();
        Robot.getInstance().data.depositClawState = clawState;
        Robot.getInstance().data.wristPosition = wrist.getPosition();
    }

    public void setWristServo(double pos) {
        this.wrist.setPosition(pos);
    }

    public void updateClawState(ClawState state) {
        this.clawState = state;
        claw.setPosition(getClawStatePosition(state));
    }

    private double getClawStatePosition(ClawState state) {
        switch (state) {
            case OPEN:
                return Constants.clawOpen;
            case CLOSED:
                return Constants.clawClose;
            default:
                return 0;
        }
    }

    public ClawState getClawState() {
        return this.clawState;
    }

    public void setLiftPower(double power) {
        this.lift.setPower(power);
    }

    public void setTargetLiftPosition(int target) {
        this.lift.setTargetPosition(target);
    }

    public void setLiftPosition(double power, int target) {
        this.lift.setPosition(power, target);
    }

    @Override
    public void periodic() {
        this.lift.periodic();
    }

}
