package hello.core.member;
public class Member {
    private Long id;
    private String name;
    private Grade grade;

    //회원 객체를 생성할때 사용하는 생성자 : 매개변수의 값을 받아 해당 값들을 초기화
    public Member(Long id, String name, Grade grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    //매서드
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Grade getGrade() {
        return grade;
    }
    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}