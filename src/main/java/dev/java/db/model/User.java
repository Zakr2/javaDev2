package dev.java.db.model;

public class User extends Entity {
    private String email="";
    private String password="";
    private String surname="";
    private String name="";
    private State state=State.ACTIVE;

    public User(){ }
    public User(long id,String email,String password,String name,String surname,State isBlocked){
        super(id);
        this.email=email;
        this.surname=surname;
        this.name=name;
        this.state=isBlocked;
        this.password=password;
    }

    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getSurname() {
        return surname;
    }
    public String getName() {
        return name;
    }
    public State getState() {
        return state;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setState(State state) {
        this.state = state;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "User:\n" +
                "E-mail: "+email+"\n" +
                "Password: "+password +
                "Surname: "+surname+"\n" +
                "Name: "+name+"\n" +
                "State: "+state+"\n";
    }

    public enum State{BLOCKED,ACTIVE}
}
