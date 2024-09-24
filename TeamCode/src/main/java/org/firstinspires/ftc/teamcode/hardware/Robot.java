package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.hardware.subsystems.DepositSubsystem;
import org.firstinspires.ftc.teamcode.hardware.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.util.wrappers.RE_SubsystemBase;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.VisionProcessor;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.ArrayList;

public class Robot extends MecanumDrive {

    public Telemetry telemetry;
    private ElapsedTime runtime = new ElapsedTime();

    public WebcamName webcam;
    public VisionProcessor visionProcessor;
    public VisionPortal visionPortal;
    public AprilTagProcessor aprilTagProcessor;

    public RobotData data = new RobotData();

    public DepositSubsystem depositSubsystem;
    public IntakeSubsystem intakeSubsystem;
    public ArrayList<RE_SubsystemBase> subsystems;


    private static Robot instance = null;


    public void initialize(HardwareMap hardwareMap, Telemetry telemetry_) {
        initializeRoadrunner(hardwareMap);

        telemetry = telemetry_;

        subsystems = new ArrayList<>();

        this.depositSubsystem = new DepositSubsystem(hardwareMap, names.lift, names.wrist, names.depositClaw);
        this.intakeSubsystem = new IntakeSubsystem(hardwareMap, names.extension, names.arm, names.intake);

    }

    public void write() {
        this.data.write(telemetry);
    }

    public void periodic() {
        for (RE_SubsystemBase subsystem : subsystems) {
            subsystem.periodic();
        }
    }

    public void updateData() {
        for (RE_SubsystemBase subsystem : subsystems) {
            subsystem.updateData();
        }
        this.data.currentPose = this.getPoseEstimate();
    }

    public static Robot getInstance() {
        if (instance == null) {
            instance = new Robot();
        }
        return instance;
    }

}
