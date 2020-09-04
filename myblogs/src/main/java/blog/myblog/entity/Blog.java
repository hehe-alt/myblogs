package blog.myblog.entity;

public class Blog {
    private int id;
    private String txt;
    private String title;
    private String createTime;
    private String updateTime;
    private String author;
    private int authorid;
    private  String sort;
    private String free;
    private String tag;

    public Blog(){}

    public Blog(int id, String txt, String title, String createTime, String updateTime, String author, int authorid, String sort, String free, String tag) {
        this.id = id;
        this.txt = txt;
        this.title = title;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.author = author;
        this.authorid = authorid;
        this.sort = sort;
        this.free = free;
        this.tag = tag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getAuthorid() {
        return authorid;
    }

    public void setAuthorid(int authorid) {
        this.authorid = authorid;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getFree() {
        return free;
    }

    public void setFree(String free) {
        this.free = free;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", txt='" + txt + '\'' +
                ", title='" + title + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", author='" + author + '\'' +
                ", authorid=" + authorid +
                ", sort='" + sort + '\'' +
                ", free='" + free + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}
