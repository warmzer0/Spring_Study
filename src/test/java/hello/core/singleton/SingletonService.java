package hello.core.singleton;

public class SingletonService {

    //테스트 케이스가 아니고 그냥 순수한 애플리케이션에 영향을 안주고 그냥 싱글톤만 간단하게 만들어서 테스트 !!

    //싱글톤 서비스라고 자기 자신을 선언
    //자기 자신 내부에 하나를 스태틱으로 가지고 있음
    //클래스 레벨이 올라가기 때문에 딱 하나만존재하게 된다
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    }

    //딱 1개의 객체 인스턴스만 존재해야 하므로,
    // 생성자를 private으로 막아서 혹시라도 외부에서 new 키워드로 객체 인스턴스가 생성되는 것을 막는다
    private SingletonService(){

    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }

}
