package org.projet_iwa.alert.api.model;

public class Response<TYPE, PAYLOAD> {

    private TYPE type;
    private PAYLOAD payload;

    public Response(TYPE type, PAYLOAD payload) {
        this.type = type;
        this.payload = payload;
    }

    public TYPE getType() {
        return type;
    }

    public PAYLOAD getPayload() {
        return payload;
    }
}
