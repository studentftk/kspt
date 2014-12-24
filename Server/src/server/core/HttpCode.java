package server.core;

public enum HttpCode {
    
    OK(200,"Succes!"),
    ERROR(500, "Internal server error!");
    
    public final int code;
    public final String message;
    
    private HttpCode(int code, String message){
       this.code = code;
       this.message = message;
    }
    
    
}
