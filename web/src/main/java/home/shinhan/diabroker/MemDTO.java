package home.shinhan.diabroker;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter@Getter@ToString
public class MemDTO {
    private String mem_name;
    private String phone_num;
    private String email;
    private String id;
    private String pw;
    private Date signed_date;
    private String card_id;
    private int credit;
}
