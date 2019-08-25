package models;

public class User {
    private String name;
    private Provider provider;

    public User(String name, Provider provider) {
        this.name = name;
        this.provider = provider;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public String fileUse(){
        String userNumber = this.name.replace("User ", "");
        return "./u" + userNumber + ".json";
    }
}
