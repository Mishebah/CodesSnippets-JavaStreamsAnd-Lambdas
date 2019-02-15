import java.util.List;
import java.util.stream.Collectors;

public final class OrderOfOperationsWithPeek {
  public static void main(String[] args) {

    System.out.println("map() before skip():");
    List<String> cdTitles1 = CD.cdList
        .stream()                                          // (1)
        .map(CD::getTitle)
        .peek(t -> System.out.println("After map: " + t))
        .skip(3)
        .peek(t -> System.out.println("After skip: " + t))
       .collect(Collectors.toList());
    System.out.println(cdTitles1);
    System.out.println();

    System.out.println("skip() before map():");             // Preferable.         
    List<String> cdTitles2 = CD.cdList
        .stream()                                          // (2)
        .skip(3)
        .peek(cd -> System.out.println("After skip: " + cd))
        .map(CD::getTitle)
        .peek(t -> System.out.println("After map: " + t))
        .collect(Collectors.toList());
    System.out.println(cdTitles2);
  }
}