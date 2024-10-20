package org.firstinspires.ftc.teamcode.hardware.subsystems;

import com.qualcomm.robotcore.hardware.CRServoImplEx;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

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

    private final Servo arm1, arm2;
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
        UP,
        NONE
    }

    public IntakeSubsystem(HardwareMap hardwareMap, String ext, String arm1, String arm2, String intake) {
        this.extension = new RE_DcMotorEx(hardwareMap.get(DcMotorEx.class, ext), extensionParams);

        this.arm1 = hardwareMap.get(Servo.class, arm1);
        this.arm2 = hardwareMap.get(Servo.class, arm2);
        this.arm2.setDirection(Servo.Direction.REVERSE);

        this.intake = hardwareMap.get(CRServoImplEx.class, intake);

        intakeState = IntakeState.STOP;
        armState = ArmState.UP;

        Robot.getInstance().subsystems.add(this);
    }

    @Override
    public void updateData() {
        Robot.getInstance().data.extensionPosition = this.extension.getPosition();
        Robot.getInstance().data.armState = armState;
        Robot.getInstance().data.armPosition1 = arm1.getPosition();
        Robot.getInstance().data.armPosition2 = arm2.getPosition();
        Robot.getInstance().data.intakeState = intakeState;
    }

    public void setArmPosition(double pos) {
        this.arm1.setPosition(pos);
        this.arm2.setPosition(pos);
    }

    public void updateArmState(ArmState state) {
        this.armState = state;
    }

    private double getArmStatePosition(ArmState state) {
        switch (state) {
            case TRANSFER:
                return Constants.armTransfer;
            case INTAKE:
                return Constants.armIntake;
            case UP:
                return Constants.armUp;
            default:
                return 0;
        }
    }

    public void setExtensionPower(double power) {
        this.extension.setPower(power);
    }

    public void setTargetExtensionPosition(int target) {
        this.extension.setTargetPosition(target, this.extensionParams.maxPower);
    }

    public void setExtensionPosition(double power, int target) {
        this.extension.setPosition(power, target);
    }

    @Override
    public void periodic() {
        this.extension.periodic();

        this.arm1.setPosition(getArmStatePosition(armState) - Constants.armServoOffset);
        this.arm2.setPosition(getArmStatePosition(armState));

        switch (this.intakeState) {
            case IN:
                intake.setPower(1);
                break;
            case OUT:
                intake.setPower(-1);
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
