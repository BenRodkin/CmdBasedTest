package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.disnodeteam.dogecommander.Subsystem;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class TapeDrive implements Subsystem {
    private HardwareMap hardwareMap;

    private DcMotor tapeDrive;
    private double tapeTower;

    private final double TAPE_SPEED_MOD = 1;

    public TapeDrive(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
    }

    @Override
    public void initHardware(){
        tapeDrive = hardwareMap.get(DcMotor.class, "tape_measure");

        tapeDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void periodic(){
        tapeDrive.setPower(tapeTower * TAPE_SPEED_MOD);
    }

    public void setPower(double power){
       tapeTower = power;
    }





}
