import java.util.List;
import java.util.stream.Collectors;

public class StreamOps {
  public static void main(String[] args) {

    // Query: Create a list with titles of pop CDs.

    // (1) Stream Mapping:
    List<CD> cdList1 = CD.cdList;
    List<String> popCDs1 = cdList1
        .stream()                      // Initial stream:         Stream<CD>
        .filter(CD::isPop)             // Intermediate operation: Stream<CD>
        .map(CD::getTitle)             // Intermediate operation: Stream<String>
        .collect(Collectors.toList()); // Terminal operation: List<CD>
    System.out.println(popCDs1);
    System.out.println();

    // (2) Lazy Evaluation:
    List<CD> cdList2 = CD.cdList;
    List<String> popCDs2 = cdList2
        .stream()                      // Initial stream:          Stream<CD>
        .filter(cd -> {                // Intermediate operation:  Stream<CD>
           System.out.println("Filtering: " + cd                     // (3)
              + (cd.isPop() ? " is pop CD." : " is not pop CD."));
           return cd.isPop();
         })
        .map(cd -> {                   // Intermediate operation: Stream<String>
           System.out.println("Mapping: " + cd.getTitle());          // (4)
           return cd.getTitle();
         })
        .collect(Collectors.toList()); // Terminal operation: List<CD>
    System.out.println(popCDs2);
  }
}