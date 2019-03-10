
package com.rasa.loyality.webApi.usedCode.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rasa.loyality.webApi.sharedModel.Message;

public class ResponseUsedCode implements Serializable
{

    @SerializedName("ok")
    @Expose
    private Boolean ok;
    @SerializedName("messages")
    @Expose
    private List<Message> messages = null;
    @SerializedName("extra")
    @Expose
    private Boolean extra;
    private final static long serialVersionUID = 1252739607586798417L;

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public Boolean getExtra() {
        return extra;
    }

    public void setExtra(Boolean extra) {
        this.extra = extra;
    }

}
