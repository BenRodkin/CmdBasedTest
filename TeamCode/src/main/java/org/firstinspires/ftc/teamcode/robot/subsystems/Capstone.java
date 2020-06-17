package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.disnodeteam.dogecommander.Subsystem;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Capstone implements Subsystem {
    private HardwareMap hardwareMap;

    private Servo capstone;

    private State state = State.STOW;

    public enum State{
        STOW(1.0),
        CAP(0.0);

        private final double pos;

        State(double pos){
            this.pos = pos;
        }
    }

    public Capstone(HardwareMap hardwareMap){
        this.hardwareMap = hardwareMap;
    }

    public void setState(State state){
        this.state = state;
    }

    @Override
    public void initHardware(){
        capstone = hardwareMap.get(Servo.class, "capstone");
    }

    @Override
    public void periodic(){
        capstone.setPosition(state.pos);
    }

}
