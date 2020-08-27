package home.demos.reactiveprogramingdemo.entitymodel;

import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
//@Document
public class Reservation {
    
    @Id
    private Integer id;
    //private String id;
    private String name;
    
}