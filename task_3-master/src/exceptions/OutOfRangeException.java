package exceptions;

public class OutOfRangeException extends Exception {
    public OutOfRangeException() {
        super("Вы ввели координату, выходящую за пределы изображения. Повторите попытку.");
    }
}
