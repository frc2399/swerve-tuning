package frc.robot;

import static edu.wpi.first.units.Units.Inches;
import frc.robot.Constants.MotorIdConstants;

import frc.robot.subsystems.drive.DriveSubsystem;
import frc.robot.subsystems.drive.SwerveModule;
import frc.robot.subsystems.drive.SwerveModuleHardwareNEO;
import frc.robot.subsystems.drive.SwerveModuleHardwareVortex;
import frc.robot.subsystems.drive.SwerveModulePlacebo;
import frc.robot.subsystems.gyro.Gyro;
import frc.robot.subsystems.gyro.GyroHardware;
import frc.robot.subsystems.gyro.GyroPlacebo;

public class SubsystemFactory {
    private static final double FRONT_LEFT_CHASSIS_ANGULAR_OFFSET = -Math.PI / 2;
    private static final double FRONT_RIGHT_CHASSIS_ANGULAR_OFFSET = 0;
    private static final double REAR_LEFT_CHASSIS_ANGULAR_OFFSET = Math.PI;
    private static final double REAR_RIGHT_CHASSIS_ANGULAR_OFFSET = Math.PI / 2;

    // we may need more, depending on how many subsystem + rio we have
    private static final String MOZART_SERIAL_NUMBER = "030ee8c8";
    private static final String BUBBLES_SERIAL_NUMBER = "030fc267";
    private static final String BETA_SERIAL_NUMBER = "030589d5";
    private static final String ALPHA_SERIAL_NUMBER = "03260A64";

    public enum RobotType {
        MOZART,
        BUBBLES,
        SIM,
        BETA,
        ALPHA
    }

    private RobotType robotType;

    private String serialNum = System.getenv("serialnum");

    public SubsystemFactory() {
        // if (serialNum.equals(ALPHA_SERIAL_NUMBER)) {
        //     robotType = RobotType.ALPHA;
        // } else
        if (serialNum.equals(BETA_SERIAL_NUMBER)) {
            robotType = RobotType.BETA;
        } else if (serialNum.equals(ALPHA_SERIAL_NUMBER)) {
            robotType  = RobotType.ALPHA;
        } else if (serialNum.equals(BUBBLES_SERIAL_NUMBER)) {
            robotType = RobotType.BUBBLES;
        } else if (serialNum.equals(MOZART_SERIAL_NUMBER)) {
            robotType = RobotType.MOZART;
        } else {
            robotType = RobotType.SIM;
        }
    }

    public RobotType getRobotType() {
        return robotType;
    }

    public DriveSubsystem buildDriveSubsystem(Gyro gyro) {
        SwerveModule frontLeft;
        SwerveModule rearLeft;
        SwerveModule frontRight;
        SwerveModule rearRight;

        if (robotType == RobotType.ALPHA) {
             frontLeft = new SwerveModule(new SwerveModuleHardwareVortex(
                    MotorIdConstants.FRONT_LEFT_DRIVING_CAN_ID,
                    MotorIdConstants.FRONT_LEFT_TURNING_CAN_ID,
                    FRONT_LEFT_CHASSIS_ANGULAR_OFFSET, "front left"));
            frontRight = new SwerveModule(new SwerveModuleHardwareVortex(
                    MotorIdConstants.FRONT_RIGHT_DRIVING_CAN_ID,
                    MotorIdConstants.FRONT_RIGHT_TURNING_CAN_ID,
                    FRONT_RIGHT_CHASSIS_ANGULAR_OFFSET, "front right"));
            rearLeft = new SwerveModule(new SwerveModuleHardwareVortex(
                    MotorIdConstants.REAR_LEFT_DRIVING_CAN_ID,
                    MotorIdConstants.REAR_LEFT_TURNING_CAN_ID,
                    REAR_LEFT_CHASSIS_ANGULAR_OFFSET, "rear left"));
            rearRight = new SwerveModule(new SwerveModuleHardwareVortex(
                    MotorIdConstants.REAR_RIGHT_DRIVING_CAN_ID,
                    MotorIdConstants.REAR_RIGHT_TURNING_CAN_ID,
                    REAR_RIGHT_CHASSIS_ANGULAR_OFFSET, "rear right"));
            return new DriveSubsystem(frontLeft, frontRight, rearLeft, rearRight, gyro,
                    Constants.DriveControlConstants.COMP_TRACK_WIDTH,
                    Constants.DriveControlConstants.COMP_TRACK_LENGTH);
        // } else
        // if (robotType == RobotType.BETA || robotType == RobotType.BUBBLES || robotType == RobotType.MOZART) {
        //     frontLeft = new SwerveModule(new SwerveModuleHardwareVortex(
        //             MotorIdConstants.FRONT_LEFT_DRIVING_CAN_ID,
        //             MotorIdConstants.FRONT_LEFT_TURNING_CAN_ID,
        //             FRONT_LEFT_CHASSIS_ANGULAR_OFFSET, "front left"));
        //     frontRight = new SwerveModule(new SwerveModuleHardwareVortex(
        //             MotorIdConstants.FRONT_RIGHT_DRIVING_CAN_ID,
        //             MotorIdConstants.FRONT_RIGHT_TURNING_CAN_ID,
        //             FRONT_RIGHT_CHASSIS_ANGULAR_OFFSET, "front right"));
        //     rearLeft = new SwerveModule(new SwerveModuleHardwareVortex(
        //             MotorIdConstants.REAR_LEFT_DRIVING_CAN_ID,
        //             MotorIdConstants.REAR_LEFT_TURNING_CAN_ID,
        //             REAR_LEFT_CHASSIS_ANGULAR_OFFSET, "rear left"));
        //     rearRight = new SwerveModule(new SwerveModuleHardwareVortex(
        //             MotorIdConstants.REAR_RIGHT_DRIVING_CAN_ID,
        //             MotorIdConstants.REAR_RIGHT_TURNING_CAN_ID,
        //             REAR_RIGHT_CHASSIS_ANGULAR_OFFSET, "rear right"));
        //     return new DriveSubsystem(frontLeft, frontRight, rearLeft, rearRight, gyro,
        //             Constants.DriveControlConstants.BETA_TRACK_WIDTH,
        //             Constants.DriveControlConstants.BETA_TRACK_LENGTH);
        // } else if (robotType == RobotType.MOZART) {
        //     frontLeft = new SwerveModule(new SwerveModuleHardwareNEO(
        //             MotorIdConstants.FRONT_LEFT_DRIVING_CAN_ID,
        //             MotorIdConstants.FRONT_LEFT_TURNING_CAN_ID,
        //             FRONT_LEFT_CHASSIS_ANGULAR_OFFSET, "front left"));
        //     frontRight = new SwerveModule(new SwerveModuleHardwareNEO(
        //             MotorIdConstants.FRONT_RIGHT_DRIVING_CAN_ID,
        //             MotorIdConstants.FRONT_RIGHT_TURNING_CAN_ID,
        //             FRONT_RIGHT_CHASSIS_ANGULAR_OFFSET, "front right"));
        //     rearLeft = new SwerveModule(new SwerveModuleHardwareNEO(
        //             MotorIdConstants.REAR_LEFT_DRIVING_CAN_ID,
        //             MotorIdConstants.REAR_LEFT_TURNING_CAN_ID,
        //             REAR_LEFT_CHASSIS_ANGULAR_OFFSET, "rear left"));
        //     rearRight = new SwerveModule(new SwerveModuleHardwareNEO(
        //             MotorIdConstants.REAR_RIGHT_DRIVING_CAN_ID,
        //             MotorIdConstants.REAR_RIGHT_TURNING_CAN_ID,
        //             REAR_RIGHT_CHASSIS_ANGULAR_OFFSET, "rear right"));
        //     return new DriveSubsystem(frontLeft, frontRight, rearLeft, rearRight, gyro,
        //             Constants.DriveControlConstants.MOZART_TRACK_WIDTH,
        //             Constants.DriveControlConstants.MOZART_TRACK_WIDTH);
        } else {
            frontLeft = new SwerveModule(new SwerveModulePlacebo());
            frontRight = new SwerveModule(new SwerveModulePlacebo());
            rearLeft = new SwerveModule(new SwerveModulePlacebo());
            rearRight = new SwerveModule(new SwerveModulePlacebo());
            return new DriveSubsystem(frontLeft, frontRight, rearLeft, rearRight, gyro, Inches.of(10), Inches.of(10));
        }

        // 10 is a default value for sim lol
    }

    public Gyro buildGyro() {
        if (robotType == RobotType.ALPHA || robotType == RobotType.BETA || robotType == RobotType.BUBBLES || robotType == RobotType.MOZART) {
            return new Gyro(new GyroHardware());
        } else {
            return new Gyro(new GyroPlacebo());
        }
    }
}