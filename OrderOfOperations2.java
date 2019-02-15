import java.util.List;
import java.util.stream.Collectors;

public final class OrderOfOperations2 {
  public static void main(String[] args) {

    List<CD> cdList = CD.cdList;

    // Filter before skip.
    List<CD> jazzCDs1 = cdList.stream()       // (1)
        .filter(CD::isJazz)
        .peek(cd -> System.out.println("After filter: " + cd.getTitle()))
        .skip(2)
        .peek(cd -> System.out.println("After skip: " + cd.getTitle()))
        .collect(Collectors.toList());
    System.out.println(jazzCDs1);

    // Skip before filter preferable.
    List<CD> jazzCDs2 = cdList.stream()       // (2)
        .skip(2)
        .peek(cd -> System.out.println("After skip: " + cd.getTitle()))
        .filter(CD::isJazz)
        .peek(cd -> System.out.println("After filter: " + cd.getTitle()))
        .collect(Collectors.toList());
    System.out.println(jazzCDs2);
  }
}