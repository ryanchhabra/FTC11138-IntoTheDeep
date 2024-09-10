package org.firstinspires.ftc.teamcode.hardware.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.Constants;
import org.firstinspires.ftc.teamcode.util.Globals;

public class DepositSubsystem extends SubsystemBase {

    private final DcMotorEx lift1, lift2;
    private final Servo wrist, claw;

    private ClawState clawState;

    private boolean useLiftPower = true;
    private boolean liftModeUpdate = false;
    private boolean liftUseEnc = true;

    private int targetLiftPosition = 0;
    private int currentLiftPosition1 = 0;
    private int currentLiftPosition2 = 0;
    private double liftPower1 = 0.0;
    private double liftPower2 = 0.0;

    private double maxPower = 1;


    public enum ClawState {
        OPEN,
        CLOSED,
    }


    public DepositSubsystem(HardwareMap hardwareMap, String lift1, String lift2, String wrist, String claw) {
        this.lift1 = hardwareMap.get(DcMotorEx.class, lift1);
        this.lift2 = hardwareMap.get(DcMotorEx.class, lift2);
        this.wrist = hardwareMap.get(Servo.class, wrist);
        this.claw = hardwareMap.get(Servo.class, claw);
        this.clawState = ClawState.OPEN;
    }

    public void setWristServo(double pos) {
        this.wrist.setPosition(pos);
    }

    public double getWristPosition() {
        return this.wrist.getPosition();
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
        this.liftPower1 = power;
        this.liftPower2 = power;
    }

    public void setTargetLiftPosition(int target) {
        this.targetLiftPosition = target;
        useLiftPower = false;
        liftUseEnc = true;
    }

    public void setLiftPosition(double power, int target) {
        lift1.setPower(power);
        lift2.setPower(power);
        lift1.setTargetPosition(target);
        lift2.setTargetPosition(target);
        lift1.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        lift2.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
    }

    public void teleop_periodic() {
        currentLiftPosition1 = lift1.getCurrentPosition();
        currentLiftPosition2 = lift2.getCurrentPosition();

        if (useLiftPower && liftPower1 == 0 && liftPower2 == 0) {
            useLiftPower = false;
            targetLiftPosition = currentLiftPosition1;
            liftUseEnc = true;
        }

        if (liftPower1 > 0.05) {
            liftUseEnc = true;
            // user trying to lift up
            if (currentLiftPosition1 < Constants.liftMax1 || !Globals.LIMITS) {
                useLiftPower = true;
                liftPower1 *= Constants.liftUpRatio;
            } else {
                liftPower1 = 0;
            }
        } else if (liftPower1 < -0.05) {
            liftUseEnc = true;
            // user trying to lift down
            if (currentLiftPosition1 > Constants.liftMin1 || !Globals.LIMITS) {
                useLiftPower = true;
                liftPower1 *= Constants.liftDownRatio;
                if (currentLiftPosition1 > Constants.liftSlow) {
                    currentLiftPosition1 *= Constants.liftSlowRatio;
                }
            } else {
                liftPower1 = 0;
            }
        } else if (useLiftPower) {
            liftPower1 = 0;
        }

        if (liftPower2 > 0.05) {
            liftUseEnc = true;
            // user trying to lift up
            if (currentLiftPosition2 < Constants.liftMax2 || !Globals.LIMITS) {
                useLiftPower = true;
                liftPower2 *= Constants.liftUpRatio;
            } else {
                liftPower2 = 0;
            }
        } else if (liftPower2 < -0.05) {
            liftUseEnc = true;
            // user trying to lift down
            if (currentLiftPosition2 > Constants.liftMin2 || !Globals.LIMITS) {
                useLiftPower = true;
                liftPower2 *= Constants.liftDownRatio;
                if (currentLiftPosition2 > Constants.liftSlow) {
                    currentLiftPosition2 *= Constants.liftSlowRatio;
                }
            } else {
                liftPower2 = 0;
            }
        } else if (useLiftPower) {
            liftPower2 = 0;
        }


        if (liftModeUpdate && liftUseEnc) {
            lift1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            lift2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            liftModeUpdate = false;
        }

        if (useLiftPower) {
            lift1.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
            lift2.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
            lift1.setPower(this.liftPower1);
            lift2.setPower(this.liftPower2);
        } else {
            setLiftPosition(this.maxPower, this.targetLiftPosition);
            liftUseEnc = false;
            liftModeUpdate = true;
        }

    }



}
