package pbt;

import java.time.LocalDate;

class Customer {
    private final String name;
    private final LocalDate joinedAt;
    private final LocalDate dateOfBirth;

    public Customer(String name, LocalDate joinedAt, LocalDate dateOfBirth) {
        this.name = name;
        this.joinedAt = joinedAt;
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getJoinedAt() {
        return joinedAt;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public String toString() {
        return "Customer{" +
            "name='" + name + '\'' +
            ", joinedAt=" + joinedAt +
            ", dateOfBirth=" + dateOfBirth +
            '}';
    }
}
