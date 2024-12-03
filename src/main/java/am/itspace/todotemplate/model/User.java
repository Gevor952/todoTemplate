package am.itspace.myfriend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.cert.CertPathBuilder;
import java.time.chrono.Chronology;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String image_name;



}
