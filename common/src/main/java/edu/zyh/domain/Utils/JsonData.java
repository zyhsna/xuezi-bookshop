package edu.zyh.domain.Utils;

public class JsonData {
    private  int stateCode;
    private Object data;
    private String message;

    public JsonData(){

    }
    public JsonData(int stateCode, Object data){
        this.stateCode = stateCode;
        this.data = data;
    }

    public JsonData(int stateCode, Object data, String message) {
        this.stateCode = stateCode;
        this.data = data;
        this.message = message;
    }

    public int getStateCode() {
        return stateCode;
    }

    public void setStateCode(int stateCode) {
        this.stateCode = stateCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static JsonData buildSuccess(Object data){
        return new JsonData(0, data);
    }

    public static JsonData buildError(int stateCode, Object data){
        return new JsonData(stateCode, data);
    }

}
