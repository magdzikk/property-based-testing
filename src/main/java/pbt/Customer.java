package pbt;

import java.time.LocalDate;

class Customer {
    private final String name;
    private final LocalDate joinedAt;

    public Customer(String name, LocalDate joinedAt) {
        this.name = name;
        this.joinedAt = joinedAt;
    }

    public LocalDate getJoinedAt() {
        return joinedAt;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Customer{" +
            "name='" + name + '\'' +
            ", joinedAt=" + joinedAt +
            '}';
    }
}
