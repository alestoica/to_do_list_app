package entities;

import jakarta.persistence.*;

@jakarta.persistence.Entity
@Table(name = "tasks")
public class Task implements Entity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "description")
    private String description;

    public Task() {
        this.id = 0;
        this.description = "";
    }

    public Task(String description) {
        this.id = 0;
        this.description = description;
    }

    public Task(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
