package models;


import lombok.Data;

@Data
public class CreateTestCaseBody {


    Long id,createdDate;
    String name, statusName, statusColor;
    Boolean automated,external;


}
