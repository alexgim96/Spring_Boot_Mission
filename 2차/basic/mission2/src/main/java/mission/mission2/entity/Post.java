package mission.mission2.entity;

public class Post {
    private static int count = 0;

    private int id;
    private int boardId;
    private String title;
    private String content;
    private String author;
    private String pwd;

    public Post() {
    }

    public Post(int boardId, String title, String content,String author, String pwd) {
        count++;
        this.id = count;
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.author = author;
        this.pwd = pwd;
    }

    public String getAuthor(){
        return this.author;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Post.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
