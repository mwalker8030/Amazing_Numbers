class Army {

    private static Knight[] knights;
    private static Unit[] units;
    private static General[] generals;
    private static Doctor[] doctors;
    public enum Order{
        private int quantity;
        UNITS;

        Order(int num){
           quantity = num;
        }
    }
    public static void createArmy() {
        // Create all objects here
        int[] create = {5, 3, 1, 1};
        int lcd = 0;
        for(int i : create) {
            lcd = Math.max(lcd, i);
        }
        units = new Unit[create[0]];
        knights = new Knight[create[1]];
        generals = new General[create[2]];
        doctors = new Doctor[create[3]];
        for(int i = 0; i < lcd ; i++) {
            if (i < create[0]) {
                units[i] = new Unit("IDU%d".formatted(i));
            }
            if (i < create[1]) {
                knights[i] = new Knight("IDK%d".formatted(i));
            }
            if (i < create[2]) {
                generals[i] = new General("IDG%d".formatted(i));
            }
            if (i < create[3]) {
                doctors[i] = new Doctor("IDD%d".formatted(i));
            }
        }
    }


    // Don't change the code below
    static class Unit {
        static String nameUnit;
        static int countUnit;

        public Unit(String name) {
            countUnit++;
            nameUnit = name;

        }
    }

    static class Knight {
        static String nameKnight;
        static int countKnight;

        public Knight(String name) {
            countKnight++;
            nameKnight = name;

        }
    }

    static class General {
        static String nameGeneral;
        static int countGeneral;

        public General(String name) {
            countGeneral++;
            nameGeneral = name;

        }
    }

    static class Doctor {
        static String nameDoctor;
        static int countDoctor;

        public Doctor(String name) {
            countDoctor++;
            nameDoctor = name;

        }
    }

    public static void main(String[] args) {
        createArmy();
        System.out.println(Unit.countUnit);
        System.out.println(Knight.countKnight);
        System.out.println(General.countGeneral);
        System.out.println(Doctor.countDoctor);
    }

}