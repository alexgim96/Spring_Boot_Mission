package mission3.mission3.dto;

import java.util.ArrayList;
import java.util.List;

public class UserDto {
    private int id;
    private String name;
    private int age;
    private List postDtoList = new ArrayList<>();

    public UserDto() {
    }

    public UserDto(int id, String name, int age, List postDtoList) {
        this.id = id;
        this.name = name;
        this.age = age;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<PostDto> getPostDtoList() {
        return postDtoList;
    }

    public void setPostDtoList(List<PostDto> postDtoList) {
        this.postDtoList = postDtoList;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", postDtoList=" + postDtoList +
                '}';
    }
}
