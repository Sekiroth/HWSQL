package dto;

public class Table {
    private int studentId;
    private int groupId;

    public Table(int studentId, int groupId) {
        this.studentId = studentId;
        this.groupId = groupId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
