package com.nico.educarjava.ajax;

import java.util.ArrayList;

public class ResponseJSON {
    
    private String status;
    private String message;
    private ArrayList data;
    

    public ResponseJSON(String status, String message, ArrayList data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList getData() {
        return data;
    }

    public void setData(ArrayList data) {
        this.data = data;
    }
    
    
    
}
