class Army {


    public enum Order {
        UNITS(0, 5),
        KNIGHTS(1, 3),
        GENERALS(2, 1),
        DOCTORS(3, 1);
        public final int quantity;
        public final int index;

        Order(int i, int num) {
            quantity = num;
            index = i;
        }
    }

    public static void createArmy() {
        // Create all objects here
        int[] create = {Order.UNITS.quantity, Order.KNIGHTS.quantity,
            Order.GENERALS.quantity, Order.DOCTORS.quantity};
        int greatestFactor = 0;
        for (int i : create) {
            greatestFactor = Math.max(greatestFactor, i);
        }
        Unit[] units = new Unit[create[0]];
        Knight[] knights = new Knight[create[1]];
        General[] generals = new General[create[2]];
        Doctor[] doctors = new Doctor[create[Order.DOCTORS.index]];

        for (int i = 0; i < greatestFactor; i++) {

            if (i < create[Order.UNITS.index]) {
                units[i] = new Unit("IDU%d".formatted(i));
            }
            if (i < create[Order.KNIGHTS.index]) {
                knights[i] = new Knight("IDK%d".formatted(i));
            }
            if (i < create[Order.GENERALS.index]) {
                generals[i] = new General("IDG%d".formatted(i));
            }
            if (i < create[Order.DOCTORS.index]) {
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