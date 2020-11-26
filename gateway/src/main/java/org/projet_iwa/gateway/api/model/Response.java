package org.projet_iwa.gateway.api.model;

public class Response<TYPE, PAYLOAD> {
    private TYPE type;
    private PAYLOAD payload;

    public Response(TYPE type, PAYLOAD payload){
        this.type = type;
        this.payload = payload;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public PAYLOAD getPayload() {
        return payload;
    }

    public void setPayload(PAYLOAD payload) {
        this.payload = payload;
    }
}
