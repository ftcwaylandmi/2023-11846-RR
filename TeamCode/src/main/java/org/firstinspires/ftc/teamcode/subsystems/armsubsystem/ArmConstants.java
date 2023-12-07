package org.firstinspires.ftc.teamcode.subsystems.armsubsystem;

public final class ArmConstants {

    public static final class PMC {
         public static int HOME = 644;
         public static int PICKUP_IN = 0;
         public static int PICKUP_OUT = -3000;
         public static int SCORE = 2670;

         public static int HANG_UP = 1277;
         public static int HANG_DOWN = 100;
    }

    public static final class EMC {
        public static int HOME = 0;
        public static int PICKUP_IN = 0;
        public static int PICKUP_OUT = -5200;
        public static int SCORE = -11050;

        public static int HANG_UP = -8798;
        public static int HANG_DOWN = 100;
    }

    public static final class PSC {
        public static double HOME = 0.4; // NOT BEING USED
        public static double PICKUP_IN = 0.48; // 0.33
        public static double PICKUP_OUT = 0.6; // NOT BEING USED
        public static double SCORE = 1.0;
    }

    public static final class GSC {
        public static double GRAB = 0.2;
        public static double RELEASE = 0.05;

    }

}