package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.disnodeteam.dogecommander.Subsystem;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Drive implements Subsystem {
    // Hardware map
    private HardwareMap hardwareMap;

    private final double SLOW = 0.6;
    private final double FAST = 1.0;

    // Components
    private DcMotor flDrive;
    private DcMotor frDrive;
    private DcMotor rlDrive;
    private DcMotor rrDrive;

    // State and interface variables
    private double flPower = 0;
    private double frPower = 0;
    private double rlPower = 0;
    private double rrPower = 0;
    private double goSlow = FAST;




    // Constructor
    public Drive(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
    }

    // Interface methods
    public void setPower(double leftPower, double rightPower) {
        this.flPower = leftPower;
        this.frPower = rightPower;
        this.rlPower = leftPower;
        this.rrPower = rightPower;
    }

    public void setMecanumPower(double drive, double strafe, double twist, boolean goSlow) {
        this.goSlow = goSlow ? SLOW : FAST;
        flPower = (drive + strafe + twist) * this.goSlow;
        frPower = (drive - strafe - twist) * this.goSlow;
        rlPower = (drive - strafe + twist) * this.goSlow;
        rrPower = (drive + strafe - twist) * this.goSlow;
    }

    @Override
    public void initHardware() {
        flDrive = hardwareMap.get(DcMotor.class, "fl_drive");
        frDrive = hardwareMap.get(DcMotor.class, "fr_drive");
        rlDrive = hardwareMap.get(DcMotor.class, "rl_drive");
        rrDrive = hardwareMap.get(DcMotor.class, "rr_drive");

        // Reverse left side
        flDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        rlDrive.setDirection(DcMotorSimple.Direction.REVERSE);

        flDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rlDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rrDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


    }

    @Override
    public void periodic() {
        flDrive.setPower(flPower);
        frDrive.setPower(frPower);
        rlDrive.setPower(rlPower);
        rrDrive.setPower(rrPower);

}






}
