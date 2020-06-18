package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.disnodeteam.dogecommander.Subsystem;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Lift implements Subsystem {
    private HardwareMap hardwareMap;

    private DcMotor pulleyLeft;
    private DcMotor pulleyRight;

    private int targetCounts = 0;

    private State state = State.MANUAL;

    private double power;

    public enum State {
        MANUAL (DcMotor.RunMode.RUN_WITHOUT_ENCODER),
        RESET (DcMotor.RunMode.RUN_TO_POSITION);

        private final DcMotor.RunMode runMode;

        State(DcMotor.RunMode runMode) {
            this.runMode = runMode;
        }
    }

    public Lift(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
    }

    public void initHardware() {
        pulleyLeft = hardwareMap.get(DcMotor.class, "pulley_left");
        pulleyRight = hardwareMap.get(DcMotor.class, "pulley_right");

        pulleyLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        pulleyLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        pulleyRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        pulleyLeft.setTargetPosition(targetCounts);
        pulleyRight.setTargetPosition(targetCounts);


    }

    public void periodic() {
        pulleyLeft.setMode(state.runMode);
        pulleyRight.setMode(state.runMode);

        if(state.equals(State.MANUAL)){
            pulleyLeft.setPower(power);
            pulleyRight.setPower(power);
        } else if(state.equals(State.RESET)){
            pulleyLeft.setPower(1.0);
            pulleyRight.setPower(1.0);
        }

    }

    public void setPower(double power) {
        this.power = power;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setTargetCounts(int target) {
        targetCounts = target;
    }

    public int getTargetCounts() {
        return targetCounts;
    }

    public boolean isBusy() {
        return pulleyLeft.isBusy() && pulleyRight.isBusy();
    }
}
