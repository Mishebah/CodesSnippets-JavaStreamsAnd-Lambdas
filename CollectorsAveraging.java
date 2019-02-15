import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class CollectorsAveraging {
  public static void main(String[] args) {

Double avgNoOfTracks1 = CD.cdList.stream()
    .collect(Collectors
        .averagingInt(CD::getNoOfTracks));          // (1) Standalone collector
System.out.println(avgNoOfTracks1);                 // 8.4

// Group CDs by genre, and average number of tracks on the CDs in each group.
Map<Genre, Double> grpByGenre = CD.cdList.stream()
    .collect(Collectors.groupingBy(
       CD::getGenre,
       Collectors.averagingInt(CD::getNoOfTracks)// (2) Downstream collector
       ));
System.out.println(grpByGenre);                     // {POP=9.0, JAZZ=8.0}
System.out.println(grpByGenre.get(Genre.JAZZ));     // 8.0

OptionalDouble avgNoOfTracks2 = CD.cdList.stream()  // Stream<CD>
    .mapToInt(CD::getNoOfTracks)                    // IntStream
    .average();
System.out.println(avgNoOfTracks2.orElse(0.0));     // 8.4
  }
}
