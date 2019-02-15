import java.util.List;
import java.util.stream.Collectors;

public final class OrderOfOperations {
  public static void main(String[] args) {

    List<CD> cdList = CD.cdList;

    // Map before skip.
    List<String> cdTitles1 = cdList
        .stream()                    // (1)
        .map(cd -> {                 // Map applied to all elements.
           System.out.println("Mapping: " + cd.getTitle());
           return cd.getTitle();
         })
        .skip(3)                     // Skip afterwards.
        .collect(Collectors.toList());
    System.out.println(cdTitles1);
    System.out.println();

    // Skip before map preferable.
    List<String> cdTitles2 = cdList
        .stream()                    // (2)
        .skip(3)                     // Skip first.
        .map(cd -> {                 // Map not applied to the first 3 elements.
           System.out.println("Mapping: " + cd.getTitle());
           return cd.getTitle();
         })
        .collect(Collectors.toList());
    System.out.println(cdTitles2);
  }
}