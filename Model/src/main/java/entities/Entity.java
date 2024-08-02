package entities;

public interface Entity<Id> {
    Id getId();
    void setId(Id id);
}
