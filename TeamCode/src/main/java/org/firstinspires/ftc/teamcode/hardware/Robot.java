package org.firstinspires.ftc.teamcode.hardware;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.hardware.subsystems.DepositSubsystem;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.VisionProcessor;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

public class Robot extends MecanumDrive {

    public Telemetry telemetry;
    private ElapsedTime runtime = new ElapsedTime();

    public WebcamName webcam;
    public VisionProcessor visionProcessor;
    public VisionPortal visionPortal;
    public AprilTagProcessor aprilTagProcessor;

    public DepositSubsystem depositSubsystem;

    public Robot(HardwareMap hardwareMap, Telemetry telemetry_) {
        super(hardwareMap, new Pose2d(0, 0, 0));

        this.depositSubsystem = new DepositSubsystem(hardwareMap, names.lift1, names.lift2, names.wrist);

    }

}
