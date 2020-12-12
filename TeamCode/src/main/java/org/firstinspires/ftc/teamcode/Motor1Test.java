package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Motor1Test extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor motor1 = null;

    public void runOpMode() {

        motor1 = hardwareMap.get(DcMotor.class, "motor1");

        motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motor1.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();
        runtime.reset();

        while (!isStopRequested()) {
            double power1 = gamepad1.left_stick_y * 1.5;
            motor1.setPower(power1);
            telemetry.addData("Power", power1);
            telemetry.update();
        }
    }
}
