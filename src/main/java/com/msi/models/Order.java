//package com.msi.models;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//
//import javax.persistence.*;
//import java.time.LocalDate;
//
//@Entity
//@Table(name = "orders")
//public class Order {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @JsonFormat(pattern = "dd/MM/yyyy")
//    private LocalDate dateCreated;
//
//    private String status;
//
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Long getId() {
//        return id;
//    }
//}
