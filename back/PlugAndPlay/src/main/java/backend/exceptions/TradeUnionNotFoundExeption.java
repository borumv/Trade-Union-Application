package backend.exceptions;

public class TradeUnionNotFoundExeption extends RuntimeException {
    public TradeUnionNotFoundExeption(int id) {
        super("Tradeunion id not found : " + id);
    }
}
