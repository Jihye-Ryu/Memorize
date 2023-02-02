package project.java.memorize.model;

public class Group {
    
    public interface Entity {
        String TBL_GROUPNAMES = "GROUPNAMES";
        String COL_GROUP_ID = "GROUP_ID";
    }
    
    
    private Integer groupId;
    private String group;
    private String userID;
    
    public Group(Integer groupId, String group, String userID) {
        this.groupId = groupId;
        this.group = group;
        this.userID = userID;
    }

    public String getGroup() {
        return group;
    }

    public Integer getId() {
        return groupId;
    }
    
    public String getUserId() {
        return userID;
    }

    

}
