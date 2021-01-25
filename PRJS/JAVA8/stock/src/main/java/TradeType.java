
/**
 * Abstraction of a Single Trade  Type
 */
public enum TradeType {

    BUY("B","BUY","OUTGOING"),
    SELL("S","SELL","INCOMING");

    private final String code;
    private final String action;
    private final String type;

    public String code() {
        return code;
    }

    public String action() {
        return action;
    }

    public String type() {
        return type;
    }

    TradeType( String code, String action, String type){
        this.code = code;
        this.action = action;
        this.type = type;
    }

    @Override
    public String toString() {
        return  action + " - " +  type ;
    }


}
