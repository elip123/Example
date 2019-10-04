package com.e.example;

public class Contact {
    public  Integer id;
    private String name;
    private String phoneNumber;
    private String No;
    private String idnumber;
    public static final String TABLE_NAME = "contacts";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NO = "no";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONENUMBER = "phonenumber";
    public static final String COLUMN_IDNUMBER= "idnumber";
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NO + " TEXT,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_PHONENUMBER + " TEXT,"
                    + COLUMN_IDNUMBER + " TEXT"
                    + ")";

    public Contact() {}

    public Contact(Integer id, String name, String phoneNumber, String no, String idnumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        No = no;
        this.idnumber = idnumber;
    }

    public Contact(String name, String phoneNumber, String no, String idnumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        No = no;
        this.idnumber = idnumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNo() {
        return No;
    }

    public void setNo(String no) {
        No = no;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }
}
