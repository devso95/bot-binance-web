package deso.future_bot.futurebotweb.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JWTToken {

    private String idToken;

    @JsonProperty("id_token")
    public String getIdToken() {
        return idToken;
    }

    void setIdToken(String idToken) {
        this.idToken = idToken;
    }
}
