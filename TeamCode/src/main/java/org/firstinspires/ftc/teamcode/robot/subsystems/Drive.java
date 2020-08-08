package org.firstinspires.ftc.teamcode.robot.subsystems;

import android.os.Environment;

import com.disnodeteam.dogecommander.Subsystem;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.robot.commands.auto.SavePID;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    private BNO055IMU imu;

    // State and interface variables
    private double flPower = 0;
    private double frPower = 0;
    private double rlPower = 0;
    private double rrPower = 0;
    private double goSlow = FAST;
    private boolean initIMU;

    // Heading offset
    private float headingOffset;
    private boolean hasHeadingOffset;
    private Exception exception;


    // Constructor
    public Drive(HardwareMap hardwareMap, boolean initIMU) {
        this.hardwareMap = hardwareMap;
        this.initIMU = initIMU;
    }

    public Drive(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
        this.initIMU = false;
    }

    public Drive(HardwareMap hardwareMap, boolean initIMU, boolean hasHeadingOffset) {
        this.hardwareMap = hardwareMap;
        this.initIMU = initIMU;
        this.hasHeadingOffset = hasHeadingOffset;
    }


    @Override
    public void initHardware() {
        flDrive = hardwareMap.get(DcMotor.class, "fl_drive");
        frDrive = hardwareMap.get(DcMotor.class, "fr_drive");
        rlDrive = hardwareMap.get(DcMotor.class, "rl_drive");
        rrDrive = hardwareMap.get(DcMotor.class, "rr_drive");

        if(initIMU){
            BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
            parameters.angleUnit            = BNO055IMU.AngleUnit.DEGREES;
            parameters.accelUnit            = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
            parameters.calibrationDataFile  = "BNO055IMUCalibration.json";
            parameters.loggingEnabled       = true;
            parameters.loggingTag           = "IMU";
            parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

            imu = hardwareMap.get(BNO055IMU.class, "imu");
            imu.initialize(parameters);
        }

        headingOffset = 0f;
        if(hasHeadingOffset){
            try {
                headingOffset = new SavePID(this).getHeading();
            } catch (Exception e){
                headingOffset= 1234;
                exception = e;
            }
        } else {
            headingOffset = 4321f;
        }

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

    public void setTargets(int flTarget, int frTarget, int rlTarget, int rrTarget) {
        flDrive.setTargetPosition(flTarget);
        frDrive.setTargetPosition(frTarget);
        rlDrive.setTargetPosition(rlTarget);
        rrDrive.setTargetPosition(rrTarget);
    }

    public void setRunMode(DcMotor.RunMode runMode){
        flDrive.setMode(runMode);
        frDrive.setMode(runMode);
        rlDrive.setMode(runMode);
        rrDrive.setMode(runMode);
    }

    public int[] getCurrentPositions(){
        return new int[]{flDrive.getCurrentPosition(), frDrive.getCurrentPosition(), rlDrive.getCurrentPosition(), rrDrive.getCurrentPosition()};
    }

    public DcMotor.RunMode getRunMode(){
        return flDrive.getMode();
    }

    public boolean driveIsBusy(){
        return flDrive.isBusy() || frDrive.isBusy() || rlDrive.isBusy() || rrDrive.isBusy();
    }

    public float heading(){
        return imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
    }

    public float getHeadingOffset(){
        return headingOffset;
    }

    public Exception getException(){
        return exception;
    }










}
