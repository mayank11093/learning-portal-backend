package learning.portal.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "USERS")
public class User {

    private Long employeeID;
    private String fullName;
    private String emailID;
    private String userName;
    private String password;
    private long contact;
    private String userType;

    public User() {
    }



    public User(long employeeID, String fullName, String emailID, String userName, String password, long contact, String userType) {
        this.employeeID = employeeID;
        this.fullName = fullName;
        this.emailID = emailID;
        this.userName = userName;
        this.password = password;
        this.contact = contact;
        this.userType = userType;
    }
    public User(long employeeID, String fullName, String emailID, String userName, long contact, String userType) {
        this.employeeID = employeeID;
        this.fullName = fullName;
        this.emailID = emailID;
        this.userName = userName;
        this.contact = contact;
        this.userType = userType;
    }


    @DynamoDBHashKey
    public Long getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Long employeeID) {
        this.employeeID = employeeID;
    }

    @DynamoDBAttribute
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @DynamoDBAttribute
    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    @DynamoDBAttribute
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @DynamoDBAttribute
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @DynamoDBAttribute
    public long getContact() {
        return contact;
    }

    public void setContact(long contact) {
        this.contact = contact;
    }

    @DynamoDBAttribute
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
