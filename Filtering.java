import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Filtering {
  public static void main(String[] args) {

    // Query 1: Finds CDs whose titles are in the set of popular CD titles.
    Set<String> popularTitles = new HashSet<>(
        Arrays.asList("Java Jive", "Java Jazz", "Java Jam"));

    // Using Stream.filter().
    List<CD> popularCDs1 = CD.cdList
        .stream()
        .filter(cd -> popularTitles.contains(cd.getTitle()))
        .collect(Collectors.toList());
    System.out.println("Query 1: " + popularCDs1);

    // Using Collection.removeIf().
    List<CD> popularCDs2 = new ArrayList<>(CD.cdList);
    popularCDs2.removeIf(cd -> !(popularTitles.contains(cd.getTitle())));
    System.out.println(popularCDs2);

    // Query 2: Create a list of unique CDs with pop music.
    List<CD> miscCDList = Arrays.asList(CD.cd0, CD.cd0, CD.cd1, CD.cd0);
    List<CD> uniquePopCDs1 = miscCDList
        .stream()
        .filter(CD::isPop)
        .distinct()                                // distinct() after filter()
        .collect(Collectors.toList());
    System.out.println("Query 2: " + uniquePopCDs1);

    // Query 3: Create a list of jazz CDs, after skipping the first two CDs.
    List<CD> jazzCDs1 = CD.cdList
        .stream()
        .skip(2)                                   // skip() before filter().
        .filter(CD::isJazz)
        .collect(Collectors.toList());
    System.out.println("Query 3: " + jazzCDs1);

    // Create a list of jazz CDs, but skip the first two jazz CDs.
    List<CD> jazzCDs2 = CD.cdList                  // Not equivalent to Query 3
        .stream()
        .filter(CD::isJazz)
        .skip(2)                                   // skip() after filter().
        .collect(Collectors.toList());
    System.out.println(jazzCDs2);

    // Query 4: Create a list with the first 2 CDs that were issued in 2018.
    List<CD> twoFirstCDs2018 = CD.cdList
        .stream()
        .filter(cd -> cd.getYear().equals(Year.of(2018)))
        .limit(2)
        .collect(Collectors.toList());
    System.out.println("Query 4: " + twoFirstCDs2018);

    // limit(n) and skip(n) are complementary.
    List<CD> resultList = Stream
        .concat(CD.cdList.stream().limit(2), CD.cdList.stream().skip(2))
        .collect(Collectors.toList());
    System.out.println(CD.cdList.equals(resultList));

    // Processing a substream.
    List<CD> substream = CD.cdList
        .stream()
        .skip(1)
        .limit(3)
        .collect(Collectors.toList());
    System.out.println(substream);
  }
}