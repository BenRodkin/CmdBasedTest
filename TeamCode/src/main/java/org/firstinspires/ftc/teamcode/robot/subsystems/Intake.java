package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.disnodeteam.dogecommander.Subsystem;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake implements Subsystem {
    private HardwareMap hardwareMap;

    private CRServo intakeLeft;
    private CRServo intakeRight;

    private State state = State.STOP;



    public Intake(HardwareMap hardwareMap){
        this.hardwareMap = hardwareMap;
    }

    public enum State {
        INTAKE(1.0),
        SPIT_OUT(-1.0),
        STOP(0.0);

        private final double power;

        State(double power){
            this.power = power;
        }
    }

    public void setState(State state){
        this.state = state;
    }


    @Override
    public void initHardware(){
        intakeLeft = hardwareMap.get(CRServo.class,"intake_left");
        intakeRight= hardwareMap.get(CRServo.class, "intake_right");

        // Reverse left side
        intakeLeft.setDirection(CRServo.Direction.REVERSE);

    }

    @Override
    public void periodic(){
        intakeLeft.setPower(state.power);
        intakeRight.setPower(state.power);
    }

}
