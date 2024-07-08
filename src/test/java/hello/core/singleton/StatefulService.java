package hello.core.singleton;

public class StatefulService {

//  private int price; // 상태를 유지하는 필드(공유 필드)


  // 특정 클라이언트가 공유 필드를 바꾸는 로직
  public int order(String name, int price) {
    System.out.println("name = " + name + " price = " + price);
    return price;
  }

//  public void getPrice() {
//    return price;
//  }

}
