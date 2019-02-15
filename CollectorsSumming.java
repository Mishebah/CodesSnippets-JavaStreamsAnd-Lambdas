import java.util.Map;
import java.util.stream.Collectors;

public final class CollectorsSumming {
  public static void main(String[] args) {

Integer sumTracks = CD.cdList.stream()
    .collect(Collectors.summingInt(CD::getNoOfTracks)); // (1)
System.out.println(sumTracks);                          // 42

// Group CDs by genre, and count the number of tracks on the CDs in each group.
Map<Genre, Integer> grpByGenre = CD.cdList.stream()
    .collect(Collectors.groupingBy(
         CD::getGenre,
         Collectors.summingInt(CD::getNoOfTracks))); // (2) Downstream collector
System.out.println(grpByGenre);                      // {POP=18, JAZZ=24}
System.out.println(grpByGenre.get(Genre.JAZZ));      // 24

int sumTracks2 = CD.cdList.stream()                  // (3)
    .mapToInt(CD::getNoOfTracks)
    .sum();
System.out.println(sumTracks2);                      // 42
  }
}
