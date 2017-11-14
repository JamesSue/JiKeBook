package jike.book.pojo;

public class Author {
    private int id;
    private String realName;
    private String idCard;
    private JiKeUser jiKeUser;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public JiKeUser getJiKeUser() {
        return jiKeUser;
    }

    public void setJiKeUser(JiKeUser jiKeUser) {
        this.jiKeUser = jiKeUser;
    }
}
