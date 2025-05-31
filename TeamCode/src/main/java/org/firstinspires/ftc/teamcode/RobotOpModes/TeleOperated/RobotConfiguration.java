package org.firstinspires.ftc.teamcode.RobotOpModes.TeleOperated;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.Core.Utils.EctoMath;

@Config
public class RobotConfiguration {



    public static class Buttons {

        public static GamepadKeys.Button a = GamepadKeys.Button.A;
        public static GamepadKeys.Button b = GamepadKeys.Button.B;
        public static GamepadKeys.Button x = GamepadKeys.Button.X;
        public static GamepadKeys.Button y = GamepadKeys.Button.Y;

        public static GamepadKeys.Button leftBumper = GamepadKeys.Button.LEFT_BUMPER;
        public static GamepadKeys.Button rightBumper = GamepadKeys.Button.RIGHT_BUMPER;

        public static GamepadKeys.Trigger leftTrigger = GamepadKeys.Trigger.LEFT_TRIGGER;
        public static GamepadKeys.Trigger rightTrigger = GamepadKeys.Trigger.RIGHT_TRIGGER;

        public static GamepadKeys.Button leftStickButton = GamepadKeys.Button.LEFT_STICK_BUTTON;
        public static GamepadKeys.Button rightStickButton = GamepadKeys.Button.RIGHT_STICK_BUTTON;

        public static GamepadKeys.Button dPadDown = GamepadKeys.Button.DPAD_DOWN;
        public static GamepadKeys.Button dPadLeft = GamepadKeys.Button.DPAD_LEFT;
        public static GamepadKeys.Button dPadUp = GamepadKeys.Button.DPAD_UP;
        public static GamepadKeys.Button dPadRight = GamepadKeys.Button.DPAD_RIGHT;

        public static GamepadKeys.Button start = GamepadKeys.Button.START;
        public static GamepadKeys.Button back = GamepadKeys.Button.BACK;
    }

}

