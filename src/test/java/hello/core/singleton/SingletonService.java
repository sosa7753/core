package hello.core.singleton;

public class SingletonService {

  /**
   * static 영역에 객체 instance를 미리 하나 생성해서 올려둔다.
   * 이 객체 인스턴스가 필요하면 오직 getInstance()로만 조회 가능
   * 딱 1개의 객체 인스턴스만 존재해야 하므로, 생성자를 private으로 막아서 혹시라도 외부에서 new 키워드로
   * 객체 인스턴스가 생성되는 것을 막는다.
   */
  private static final SingletonService instance = new SingletonService();

  public static SingletonService getInstance() {

    return instance;
  }

  private SingletonService() {
  }

  public void logic() {
    System.out.println("싱글톤 객체 로직 호출");
  }
}
