
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;


@TeleOp(name="Example Manual Opmode")

public class ManualExample extends LinearOpMode {

    SampleMecanumDrive drive = null;

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftBack = null;
    private DcMotor rightBack = null;
    private DcMotor leftFront = null;
    private DcMotor rightFront = null;

    @Override
    public void runOpMode() {
        drive = new SampleMecanumDrive(hardwareMap);

        leftBack = hardwareMap.get(DcMotor.class, "motor2");
        rightBack = hardwareMap.get(DcMotor.class, "motor3");
        leftFront = hardwareMap.get(DcMotor.class, "motor0");
        rightFront = hardwareMap.get(DcMotor.class, "motor1");

        leftBack.setDirection(DcMotor.Direction.REVERSE);
        rightBack.setDirection(DcMotor.Direction.FORWARD);
        leftFront.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

            //to assist with finding the deadzone
            telemetry.addData("Left stick X", gamepad1.left_stick_x);
            telemetry.addData("Left stick Y", gamepad1.left_stick_y);
            telemetry.addData("Right stick X", gamepad1.right_stick_x);
            telemetry.addData("Right stick Y", gamepad1.right_stick_y);
            drive.update();

            double forwardPower = gamepad1.left_stick_y * 1.5;
            double turnPower = gamepad1.right_stick_x * 1.5;
            double strafePower = gamepad1.left_stick_x * 1.5;

            double lbPower = forwardPower - turnPower - strafePower;
            double rbPower = forwardPower + turnPower + strafePower;
            double lfPower = forwardPower - turnPower + strafePower;
            double rfPower = forwardPower + turnPower - strafePower;

            leftBack.setPower(lbPower);
            rightBack.setPower(rbPower);
            leftFront.setPower(lfPower);
            rightFront.setPower(rfPower);

        }
    }
}
