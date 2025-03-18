package theory;

/**
 * METODE STATICE ȘI REFERINȚA this
 */
class StaticExample {
    static int counter = 0;
    public StaticExample() { counter++; }
    public static int getCounter() { return counter; }
}

/**
 * SINGLETON
 * - Singleton asigură că o clasă are o singură instanță.
 */
class Singleton {
    private static Singleton instance;
    private Singleton() {}
    public static Singleton getInstance() {
        if (instance == null) instance = new Singleton();
        return instance;
    }
}