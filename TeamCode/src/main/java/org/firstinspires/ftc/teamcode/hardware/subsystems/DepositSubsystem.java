package org.firstinspires.ftc.teamcode.hardware.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.util.wrappers.MotorParams;
import org.firstinspires.ftc.teamcode.util.wrappers.RE_DcMotorEx;
import org.firstinspires.ftc.teamcode.util.wrappers.RE_SubsystemBase;
import org.firstinspires.ftc.teamcode.util.Constants;

public class DepositSubsystem extends RE_SubsystemBase {

    private final RE_DcMotorEx lift1, lift2;
    private final MotorParams liftParams = new MotorParams(
            Constants.liftMin1, Constants.liftMax1, Constants.liftSlow,
            1, 0.1, Constants.liftUpRatio, Constants.liftDownRatio, Constants.liftSlowRatio
    );

    private final MotorParams lift2Params = new MotorParams(
            Constants.liftMin1, Constants.liftMax1, Constants.liftSlow,
            1, 0.1, Constants.liftUpRatio, Constants.liftDownRatio, Constants.liftSlowRatio
    );

    private final Servo bucket;

    private BucketState bucketState;

    public enum BucketState {
        DROP,
        INTAKE,
        NONE
    }


    public DepositSubsystem(HardwareMap hardwareMap, String lift1, String lift2, String wrist) {
        this.lift1 = new RE_DcMotorEx(hardwareMap.get(DcMotorEx.class, lift1), liftParams);
        this.lift2 = new RE_DcMotorEx(hardwareMap.get(DcMotorEx.class, lift2), lift2Params);
        this.bucket = hardwareMap.get(Servo.class, wrist);
        this.bucketState = BucketState.INTAKE;

        Robot.getInstance().subsystems.add(this);
    }

    @Override
    public void updateData() {
        Robot.getInstance().data.liftPosition1 = this.lift1.getPosition();
        Robot.getInstance().data.liftPosition2 = this.lift2.getPosition();
        Robot.getInstance().data.depositClawState = bucketState;
        Robot.getInstance().data.bucketPosition = bucket.getPosition();
    }

    public void updateBucketState(BucketState state) {
        this.bucketState = state;
        bucket.setPosition(getClawStatePosition(state));
    }

    private double getClawStatePosition(BucketState state) {
        switch (state) {
            case DROP:
                return Constants.bucketDrop;
            case INTAKE:
                return Constants.bucketIntake;
            default:
                return 0;
        }
    }

    public BucketState getBucketState() {
        return this.bucketState;
    }

    public void setLiftPower(double power) {
        this.lift1.setPower(power);
        this.lift2.setPower(power);
    }

    public void setTargetLiftPosition(int target) {
        this.lift1.setTargetPosition(target);
        this.lift2.setTargetPosition(target);
    }

    public void setLiftPosition(double power, int target) {
        this.lift1.setPosition(power, target);
        this.lift2.setPosition(power, target);
    }

    @Override
    public void periodic() {
        this.lift1.periodic();
        this.lift2.periodic();
    }

}
