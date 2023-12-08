package org.firstinspires.ftc.teamcode.BNI_Team.Connor.Wall_E;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.BNI_Team.Connor.TeleOps.Tank_TeleOp_Connor;
import org.firstinspires.ftc.teamcode.BNI_Team.Connor.Robots.The_Mighty_and_All_Powerful_Hand;

//@Disabled
@TeleOp(name = "WALL-E_TeleOp_CWR",group="iLab")
public class WALL_E_TeleOp extends OpMode {

    public double speedMultiply = 0.50;

    public enum Style {
        ONESTICK, TWOSTICK, TANK
    }

    public enum Person {FIRST, THIRD}

    public enum NumberOfGamepads {ONE, TWO}

    public enum StickControl {FIRSTSTICK, THIRDSTICK}

    public StickControl stickControl = StickControl.THIRDSTICK;

    public NumberOfGamepads numGamepads = NumberOfGamepads.TWO;

    public Person personControl = Person.THIRD;

    public Style driverStyle = Style.ONESTICK;

    public double leftSidePower;
    public double rightSidePower;

    public double linearMotorPower = 0.85;

    public enum LazySusanControl {AUTO, MANUAL}

    boolean clawOpen = false;

    public Tank_TeleOp_Connor.LazySusanControl lazySusanControl = Tank_TeleOp_Connor.LazySusanControl.MANUAL;

    public enum LazySusanEncoder {FORWARD, REVERSE, OFF}

    public Tank_TeleOp_Connor.LazySusanEncoder lazySusanEncoder = Tank_TeleOp_Connor.LazySusanEncoder.OFF;
    public double lazySusanTicks = 5000;
    public double lazySusanPower = 0.90;


    public enum ControlOfUpAndDownLinearMotor {FORWARD, REVERSE}

    public The_Mighty_and_All_Powerful_Hand Hand = new The_Mighty_and_All_Powerful_Hand();
    public Tank_TeleOp_Connor.ControlOfUpAndDownLinearMotor controlOfUpAndDownLinearMotor = Tank_TeleOp_Connor.ControlOfUpAndDownLinearMotor.FORWARD;
    public Tank_TeleOp_Connor.ControlOfSidewaysLinearMotor controlOfSidewaysLinearMotor = Tank_TeleOp_Connor.ControlOfSidewaysLinearMotor.FORWARD;
    public Tank_TeleOp_Connor.ControlOfLeftClaw controlOfLeftClaw = Tank_TeleOp_Connor.ControlOfLeftClaw.CLOSED;
    public Tank_TeleOp_Connor.ControlOfRightClaw controlOfRightClaw = Tank_TeleOp_Connor.ControlOfRightClaw.CLOSED;
    //hi 2
    double leftStickYVal;
    double leftStickXVal;
    double rightStickYVal;
    double rightStickXVal;

    public WalleBot WALL_E = new WalleBot();

    @Override
    public void init() {
        WALL_E.initRobot(hardwareMap);
    }

    public void loop() {
        dpadControl();
        drivingStyle();
        personChanger();
        controllerChanger();
        stickControls();
        clawControl();
        telementryOutput();


    }

    public void dpadControl() {

        switch (personControl) {
            case THIRD:
                if (gamepad1.dpad_right == true) {
                    speedMultiply = 0.50;
                } else if (gamepad1.dpad_down == true) {
                    speedMultiply = 0.75;
                } else if (gamepad1.dpad_left == true) {
                    speedMultiply = 1.00;
                } else if (gamepad1.dpad_up == true) {
                    speedMultiply = 0.25;
                }
                break;

            case FIRST:
                speedMultiply = 0.50;

                if (gamepad1.dpad_left) {
                    stickControl = StickControl.FIRSTSTICK;
                }
                if (gamepad1.dpad_right) {
                    stickControl = StickControl.THIRDSTICK;
                }

                break;
        }


    }

    public void stickControls() {
        switch (stickControl) {
            case THIRDSTICK:


                switch (driverStyle) {
                    case ONESTICK:


                        leftStickYVal = gamepad1.left_stick_y;
                        leftStickYVal = Range.clip(leftStickYVal, -1, 1);

                        leftStickXVal = gamepad1.left_stick_x;
                        leftStickXVal = Range.clip(leftStickXVal, -1, 1);

                        if (leftStickYVal < -0.1) {
                            WALL_E.driveForward(speedMultiply * leftStickYVal);
                        } else if (leftStickYVal > 0.1) {
                            WALL_E.driveBackwards(speedMultiply * leftStickYVal);
                        } else if (leftStickXVal > 0.1) {
                            WALL_E.rotateRight(speedMultiply * leftStickXVal);
                        } else if (leftStickXVal < -0.1) {
                            WALL_E.rotateLeft(speedMultiply * leftStickXVal);
                        } else {
                            WALL_E.stopMotors();

                        }
                        break;

                    case TWOSTICK:
                        leftStickYVal = gamepad1.left_stick_y;
                        leftStickYVal = Range.clip(leftStickYVal, -1, 1);
                        leftStickXVal = gamepad1.left_stick_x;
                        leftStickXVal = Range.clip(leftStickXVal, -1, 1);
                        rightStickYVal = gamepad1.right_stick_y;
                        rightStickYVal = Range.clip(rightStickYVal, -1, 1);
                        rightStickXVal = gamepad1.right_stick_x;
                        rightStickXVal = Range.clip(rightStickXVal, -1, 1);

                        if (leftStickYVal < -0.1) {
                            WALL_E.driveForward(speedMultiply * leftStickYVal);
                        } else if (leftStickYVal > 0.1) {
                            WALL_E.driveBackwards(speedMultiply * leftStickYVal);
                        } else if (rightStickXVal > 0.1) {
                            WALL_E.rotateRight(speedMultiply * rightStickXVal);
                        } else if (rightStickXVal < -0.1) {
                            WALL_E.rotateLeft(speedMultiply * rightStickXVal);
                        } else {
                            WALL_E.stopMotors();
                        }
                        break;


                    case TANK:
                        leftStickYVal = gamepad1.left_stick_y;
                        leftStickYVal = Range.clip(leftStickYVal, -1, 1);

                        rightStickYVal = gamepad1.right_stick_y;
                        rightStickYVal = Range.clip(rightStickYVal, -1, 1);

                        leftSidePower = speedMultiply * leftStickYVal * (-1);
                        rightSidePower = speedMultiply * rightStickYVal * (-1);
                        WALL_E.tankDrive(leftSidePower, rightSidePower);
                        break;

                }
                break;


            case FIRSTSTICK:

                if (gamepad1.right_stick_y < -0.1) {
                    WALL_E.rightLinearActuatorForward(linearMotorPower);
                } else if (gamepad1.right_stick_y > 0.1) {
                    WALL_E.rightLinearActuatorBack(linearMotorPower);
                } else {
                    WALL_E.rightLinearActuatorStop();

                }

                if (gamepad1.left_stick_y < -0.1) {
                    WALL_E.leftLinearActuatorForward(linearMotorPower);
                } else if (gamepad1.left_stick_y > 0.1) {
                    WALL_E.leftLinearActuatorBack(linearMotorPower);
                } else {
                    WALL_E.leftLinearActuatorStop();
                }


                if (gamepad1.right_stick_x < -0.1) {
                    WALL_E.lazySusanLeft(lazySusanPower);
                } else if (gamepad1.right_stick_x > 0.1) {
                    WALL_E.lazySusanRight(lazySusanPower);
                } else {
                    WALL_E.lazySusanStop();
                }


        }

    }


    public void clawControl() {
        switch (numGamepads) {
            case TWO:

                switch (personControl) {
                    case THIRD:
                        if (gamepad2.left_trigger > 0.1) {
                            WALL_E.leftClawOpen();
                        } else if (gamepad2.left_bumper) {
                            WALL_E.leftClawClose();
                        } else if (gamepad2.right_trigger > 0.1) {
                            WALL_E.rightClawOpen();
                        } else if (gamepad2.right_bumper) {
                            WALL_E.rightClawClose();
                        }
                        break;
                    case FIRST:

                        if (gamepad1.left_trigger > 0.1) {
                            WALL_E.leftClawOpen();
                        } else if (gamepad1.left_bumper) {
                            WALL_E.leftClawClose();
                        } else if (gamepad1.right_trigger > 0.1) {
                            WALL_E.rightClawOpen();
                        } else if (gamepad1.right_bumper) {
                            WALL_E.rightClawClose();
                        }


                }

                break;

            case ONE:


                if (gamepad1.right_trigger > 0.2) {
                    WALL_E.rightLinearActuatorForward(linearMotorPower);
                }
                if (gamepad1.right_bumper) {
                    WALL_E.rightLinearActuatorBack(linearMotorPower);


                    if (gamepad1.left_trigger > 0.2) {
                        WALL_E.leftLinearActuatorForward(linearMotorPower);
                    } else if (gamepad1.left_bumper) {
                        WALL_E.leftLinearActuatorBack(linearMotorPower);
                    } else {
                        WALL_E.leftLinearActuatorStop();

                        WALL_E.rightLinearActuatorStop();

                    }

                    if (gamepad2.y) {
                        clawOpen = true;

                    } else {
                        clawOpen = false;
                    }
                    if (clawOpen == true) {
                        WALL_E.leftClawOpen();
                    } else if (clawOpen == false) {
                        WALL_E.leftClawClose();
                    }
                }

                break;

        }
    }






        public void drivingStyle () {

            if (gamepad1.a) {
                driverStyle = WALL_E_TeleOp.Style.ONESTICK;

            }
            if (gamepad1.x) {
                driverStyle = WALL_E_TeleOp.Style.TWOSTICK;
            }
            if (gamepad1.b) {
                driverStyle = WALL_E_TeleOp.Style.TANK;

            }


        }

        public void personChanger () {

//            if (gamepad1.back) {
//                personControl = Person.FIRST;
//            }
//            if (gamepad1.start) {
//                personControl = Person.THIRD;
//            }

        }

        public void controllerChanger() {
        if (gamepad1.back) {
            if (numGamepads == NumberOfGamepads.ONE) {
               numGamepads = NumberOfGamepads.TWO;
            }
            else if (numGamepads == NumberOfGamepads.TWO){
                numGamepads = NumberOfGamepads.ONE;
            }
        }
    }


        public void telementryOutput () {
            telemetry.addLine("Wall-E Control Panel");
            telemetry.addLine("LONG LIVE TACO");
            telemetry.addData("Speed: ", speedMultiply);
            telemetry.addData("Front Left Motor Power", WALL_E.frontLeftMotor.getPower());
            telemetry.addData("Front Right Motor Power", WALL_E.frontRightMotor.getPower());
            telemetry.addData("Rear Left Motor Power", WALL_E.rearLeftMotor.getPower());
            telemetry.addData("Rear Right Motor Power", WALL_E.rearRightMotor.getPower());

            if (driverStyle == WALL_E_TeleOp.Style.ONESTICK) {
                telemetry.addLine("OneStick Drive");
            } else if (driverStyle == WALL_E_TeleOp.Style.TANK) {
                telemetry.addLine("Tank Drive");
            } else if (driverStyle == WALL_E_TeleOp.Style.TWOSTICK) {
                telemetry.addLine(" TwoStick Drive");
            }

            if (personControl == Person.FIRST) {
                telemetry.addLine("Person - FIRST");
            } else if (personControl == Person.THIRD) {
                telemetry.addLine("Person - THIRD");
            }

            if (stickControl == StickControl.FIRSTSTICK) {
                telemetry.addLine("Stick Control - FIRST PERSON");
            } else if (stickControl == StickControl.THIRDSTICK) {
                telemetry.addLine(" Stick Control - THIRD PERSON (DRIVE CONTROL)");
            }
        }

        //Long Live Taco

//C REDD
        //hi

    }


