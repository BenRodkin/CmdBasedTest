package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.disnodeteam.dogecommander.Subsystem;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake implements Subsystem {
    private HardwareMap hardwareMap;

    private CRServo intakeLeft;
    private CRServo intakeRight;

    private double leftIntakePower = 0;
    private double rightIntakePower = 0;

    public Intake(HardwareMap hardwareMap){
        this.hardwareMap = hardwareMap;
    }

    public void setPower(double power) {
        leftIntakePower = power;
        rightIntakePower = power;
    }

    @Override
    public void initHardware(){
        intakeLeft = hardwareMap.get(CRServo.class,"intake_left");
        intakeRight= hardwareMap.get(CRServo.class, "intake_right");

        intakeRight.setDirection(CRServo.Direction.REVERSE);

    }

    @Override
    public void periodic(){
        intakeRight.setPower(rightIntakePower);
        intakeLeft.setPower(leftIntakePower);


    }
}
