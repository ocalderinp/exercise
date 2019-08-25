package models;

public class Provider {
    private String content_module;
    private String auth_module;

    public Provider(String content_module, String auth_module) {
        this.content_module = content_module;
        this.auth_module = auth_module;
    }

    public String getContent_module() {
        return content_module;
    }

    public void setContent_module(String content_module) {
        this.content_module = content_module;
    }

    public String getAuth_module() {
        return auth_module;
    }

    public void setAuth_module(String auth_module) {
        this.auth_module = auth_module;
    }
}
