package hr.java.restaurant.model;

abstract class Entity {
    private  Long Id;

    public Entity(Long id) {
        Id = id;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}
