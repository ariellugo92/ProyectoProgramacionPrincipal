package random;
public class RandomConector {
    public static RandomCreador buildRandom(String prestamo){
        return new RandomCreador(prestamo);
    }
}
