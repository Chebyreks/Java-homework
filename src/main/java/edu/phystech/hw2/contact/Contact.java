package edu.phystech.hw2.contact;
import java.util.regex.Pattern;

record Contact(String username, String email) implements Comparable<Contact>{
    public static final String UNKNOWN_EMAIL = "unknown";
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@gmail\\.com$");

    Contact {
        if (username.isBlank()) {
            throw new InvalidContactFieldException("username");
        }
        if (email == null || email.equals(UNKNOWN_EMAIL)) {
            email = UNKNOWN_EMAIL;
        } else if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new InvalidContactFieldException("email");
        }
    }

    Contact(String username) { this(username, null); }


    public int compareTo(Contact o) {
        if (o == null) {
            throw new InvalidContactFieldException("contact");
        }
        return Integer.compare(this.username.length(), o.username.length());
    }
}