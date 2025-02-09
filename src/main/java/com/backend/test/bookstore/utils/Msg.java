package com.backend.test.bookstore.utils;

import lombok.Getter;
import lombok.Setter;
import net.sf.json.JSONObject;

@Setter
@Getter
public class Msg {
    private int status;
    private String msg;
    private JSONObject data;



    Msg(int status, String extra){
        this.status = status;
        this.msg = extra;
        this.data = null;
    }

}
