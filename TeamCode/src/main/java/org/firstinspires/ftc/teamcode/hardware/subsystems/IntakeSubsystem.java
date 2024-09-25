package org.firstinspires.ftc.teamcode.hardware.subsystems;

import com.arcrobotics.ftclib.hardware.ServoEx;
import com.qualcomm.robotcore.hardware.CRServoImplEx;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.util.Constants;
import org.firstinspires.ftc.teamcode.util.wrappers.MotorParams;
import org.firstinspires.ftc.teamcode.util.wrappers.RE_DcMotorEx;
import org.firstinspires.ftc.teamcode.util.wrappers.RE_SubsystemBase;

public class IntakeSubsystem extends RE_SubsystemBase {

    private final RE_DcMotorEx extension;
    private final MotorParams extensionParams = new MotorParams(
            Constants.extMin, Constants.extMax, Constants.extSlow,
            1, 1, Constants.extUpRatio, Constants.extDownRatio, Constants.extSlowRatio
    );

    private final ServoEx arm;
    private final CRServoImplEx intake;

    public IntakeState intakeState;
    public ArmState armState;

    public enum IntakeState {
        IN,
        STOP,
        OUT
    }

    public enum ArmState {
        TRANSFER,
        INTAKE,
        NONE
    }

    public IntakeSubsystem(HardwareMap hardwareMap, String ext, String arm, String intake) {
        this.extension = new RE_DcMotorEx(hardwareMap.get(DcMotorEx.class, ext), extensionParams);
        this.arm = hardwareMap.get(ServoEx.class, arm);
        this.intake = hardwareMap.get(CRServoImplEx.class, intake);
        intakeState = IntakeState.STOP;
        armState = ArmState.TRANSFER;

        Robot.getInstance().subsystems.add(this);
    }

    @Override
    public void updateData() {
        Robot.getInstance().data.liftPosition = this.extension.getPosition();
        Robot.getInstance().data.armState = armState;
        Robot.getInstance().data.armPosition = arm.getPosition();
        Robot.getInstance().data.intakeState = intakeState;
    }

    public void setArmPosition(double pos) {
        this.arm.setPosition(pos);
    }

    public void updateArmState(ArmState state) {
        this.armState = state;
        this.arm.setPosition(getArmStatePosition(state));
    }

    private double getArmStatePosition(ArmState state) {
        switch (state) {
            case TRANSFER:
                return Constants.armTransfer;
            case INTAKE:
                return Constants.armIntake;
            default:
                return 0;
        }
    }

    public void setExtensionPower(double power) {
        this.extension.setPower(power);
    }

    public void setTargetExtensionPosition(int target) {
        this.extension.setTargetPosition(target);
    }

    public void setExtensionPosition(double power, int target) {
        this.extension.setPosition(power, target);
    }

    @Override
    public void periodic() {
        this.extension.periodic();

        switch (this.intakeState) {
            case IN:
                intake.setPower(-1);
                break;
            case OUT:
                intake.setPower(1);
                break;
            case STOP:
                intake.setPower(0);
                break;
        }
    }

    public void updateIntakeState(IntakeState state) {
        this.intakeState = state;
    }



}
