package mission3.mission3.dto;

import java.util.ArrayList;
import java.util.List;

public class BoardDto {
    private int id;
    private String name;
    private List postDtoList = new ArrayList<>();

    public BoardDto() {
    }

    public BoardDto(int id, String name, List postDtoList) {
        this.id = id;
        this.name = name;
        this.postDtoList = postDtoList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PostDto> getPostDtoList() {
        return postDtoList;
    }

    public void setPostDtoList(List<PostDto> postDtoList) {
        this.postDtoList = postDtoList;
    }

    @Override
    public String toString() {
        return "BoardDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
