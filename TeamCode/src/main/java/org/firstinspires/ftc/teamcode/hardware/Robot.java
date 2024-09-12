package org.firstinspires.ftc.teamcode.hardware;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.hardware.subsystems.DepositSubsystem;
import org.firstinspires.ftc.teamcode.hardware.subsystems.RE_SubsystemBase;
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
    public ArrayList<RE_SubsystemBase> subsystems;


    private static Robot instance = null;


    public void initialize(HardwareMap hardwareMap, Telemetry telemetry_) {
        initializeRoadrunner(hardwareMap);

        subsystems = new ArrayList<>();

        this.depositSubsystem = new DepositSubsystem(hardwareMap, names.lift, names.wrist, names.depositClaw);
        subsystems.add(depositSubsystem);

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
    }

    public static Robot getInstance() {
        if (instance == null) {
            instance = new Robot();
        }
        return instance;
    }

}
