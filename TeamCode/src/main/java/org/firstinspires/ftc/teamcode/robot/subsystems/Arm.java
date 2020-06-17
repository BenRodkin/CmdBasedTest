package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.disnodeteam.dogecommander.Subsystem;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Arm implements Subsystem {
    // Hardware Map
    private HardwareMap hardwareMap;

    // Motor
    private DcMotor arm;

    // Variables for runToPosition
    private int targetPos;
    private int pos;

    // Variables for joystick control
    private double power;

    // Where the arm started
    private int stowedPos;


    public Arm(HardwareMap hardwareMap){
       this.hardwareMap = hardwareMap;
    }

    @Override
    public void initHardware(){
        arm = hardwareMap.get(DcMotor.class, "arm");

        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        pos = arm.getCurrentPosition();
        stowedPos = pos;
    }

    public void periodic(){
        if(arm.getMode().equals(DcMotor.RunMode.RUN_TO_POSITION)) {
            arm.setTargetPosition(targetPos);
        } else if(arm.getMode().equals(DcMotor.RunMode.RUN_WITHOUT_ENCODER)) {
            arm.setPower(power);
        }
    }

    public void setRunMode(DcMotor.RunMode runMode){
        arm.setMode(runMode);
    }

    public void setTargetPos(int targetPos){
        this.targetPos = targetPos;
    }

    public void setPower(double power){
        this.power = power;
    }

    public DcMotor.RunMode getRunMode(){
        return arm.getMode();
    }

}
