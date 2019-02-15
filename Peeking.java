import java.util.List;
import java.util.stream.Collectors;

public final class Peeking {
  public static void main(String[] args) {

    List<CD> cdList = CD.cdList;
    List<CD> jazzCDs = cdList.stream()           // (1)
        .peek(cd -> System.out.println("Before filter: " + cd.getTitle()))
        .filter(CD::isJazz)                      // (2)
        .peek(cd -> System.out.println("After filter: " + cd.getTitle()))
        .collect(Collectors.toList());
    System.out.println(jazzCDs);
  }
}