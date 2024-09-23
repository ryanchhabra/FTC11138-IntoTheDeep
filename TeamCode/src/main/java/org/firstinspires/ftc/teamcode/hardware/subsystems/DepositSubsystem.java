package org.firstinspires.ftc.teamcode.hardware.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.util.Constants;
import org.firstinspires.ftc.teamcode.util.Globals;

public class DepositSubsystem extends RE_SubsystemBase {

    private final DcMotorEx lift;
    private final Servo wrist, claw;

    private ClawState clawState;

    private boolean useLiftPower = true;
    private boolean liftModeUpdate = false;
    private boolean liftUseEnc = true;

    private int targetLiftPosition = 0;
    private int currentLiftPosition = 0;
    private double liftPower = 0.0;

    private double maxPower = 1;
    private double minPower = 0.1;


    public enum ClawState {
        OPEN,
        CLOSED,
        NONE
    }


    public DepositSubsystem(HardwareMap hardwareMap, String lift1, String wrist, String claw) {
        this.lift = hardwareMap.get(DcMotorEx.class, lift1);
        this.wrist = hardwareMap.get(Servo.class, wrist);
        this.claw = hardwareMap.get(Servo.class, claw);
        this.clawState = ClawState.OPEN;
    }

    @Override
    public void updateData() {
        Robot.getInstance().data.liftPosition = currentLiftPosition;
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

    public void setPower(double power) {
        this.liftPower = power;
    }

    public void setTargetLiftPosition(int target) {
        this.targetLiftPosition = target;
        useLiftPower = false;
        liftUseEnc = true;
    }

    public void setLiftPosition(double power, int target) {
        lift.setPower(power);
        lift.setTargetPosition(target);
        lift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
    }

    @Override
    public void periodic() {
        currentLiftPosition = lift.getCurrentPosition();

        if (useLiftPower && liftPower == 0) {
            useLiftPower = false;
            targetLiftPosition = currentLiftPosition;
            liftUseEnc = true;
        }

        if (liftPower > 0.05) {
            liftUseEnc = true;
            // user trying to lift up
            if (currentLiftPosition < Constants.liftMax1 || !Globals.LIMITS) {
                useLiftPower = true;
                liftPower *= Constants.liftUpRatio;
            } else {
                liftPower = 0;
            }
        } else if (liftPower < -0.05) {
            liftUseEnc = true;
            // user trying to lift down
            if (currentLiftPosition > Constants.liftMin1 || !Globals.LIMITS) {
                useLiftPower = true;
                liftPower *= Constants.liftDownRatio;
                if (currentLiftPosition > Constants.liftSlow) {
                    currentLiftPosition *= Constants.liftSlowRatio;
                }
            } else {
                liftPower = 0;
            }
        } else if (useLiftPower) {
            liftPower = 0;
        }


        if (liftModeUpdate && liftUseEnc) {
            lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            liftModeUpdate = false;
        }

        if (useLiftPower) {
            lift.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
            lift.setPower(this.liftPower);
        } else {
            if (currentLiftPosition > targetLiftPosition) {
                setLiftPosition(this.minPower, this.targetLiftPosition);
            } else {
                setLiftPosition(this.maxPower, this.targetLiftPosition);
            }
            liftUseEnc = false;
            liftModeUpdate = true;
        }

    }

}
