package org.bbelovic.codekatas.beantostring;

class ConfidentialData {

    private final String username;
    @LoggingIgnored
    private final String password;
    @LoggingIgnored
    private final String pin;
    private final String email;

    ConfidentialData(String username, String password, String pin, String email) {
        this.username = username;
        this.password = password;
        this.pin = pin;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    @LoggingIgnored
    public String getPassword() {
        return password;
    }

    @LoggingIgnored
    public String getPin() {
        return pin;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return new BeanToStringBuilder(this).toString();
    }
}
