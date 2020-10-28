//package org.projet_iwa.alert.api.model;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity(name = "users")
//@Access(AccessType.FIELD)
//@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
//public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long user_id;
//
//    @ManyToMany
//    @JoinTable(name="user_locations",
//            joinColumns = @JoinColumn(name="user_id"),
//            inverseJoinColumns = @JoinColumn(name="location_id"))
//    private List<Location> locations;
//
//
//    public void setUser_id(Long user_id) {
//        this.user_id = user_id;
//    }
//
//    @Id
//    public Long getUser_id() {
//        return user_id;
//    }
//
//
//    public List<Location> getLocations() {
//        return locations;
//    }
//
//    public void setLocations(List<Location> locations) {
//        this.locations = locations;
//    }
//}