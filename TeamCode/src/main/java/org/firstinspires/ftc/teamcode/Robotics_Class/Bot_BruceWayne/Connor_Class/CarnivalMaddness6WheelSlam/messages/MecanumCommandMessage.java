package org.firstinspires.ftc.teamcode.Robotics_Class.Bot_BruceWayne.Connor_Class.CarnivalMaddness6WheelSlam.messages;

public final class MecanumCommandMessage {
    public long timestamp;
    public double voltage;
    public double leftFrontPower;
    public double leftBackPower;
    public double rightBackPower;
    public double rightFrontPower;

    public MecanumCommandMessage(double voltage, double leftFrontPower, double leftBackPower, double rightBackPower, double rightFrontPower) {
        this.timestamp = System.nanoTime();
        this.voltage = voltage;
        this.leftFrontPower = leftFrontPower;
        this.leftBackPower = leftBackPower;
        this.rightBackPower = rightBackPower;
        this.rightFrontPower = rightFrontPower;
    }
}
