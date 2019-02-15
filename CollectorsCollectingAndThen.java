import java.time.Year;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class CollectorsCollectingAndThen {
  public static void main(String[] args) {


Map<Year, Integer> maxTracksByYear = CD.cdList.stream()
    .collect(Collectors.groupingBy(
         CD::getYear,
         Collectors.collectingAndThen(                                  // (1)
             Collectors.maxBy(Comparator.comparing(CD::getNoOfTracks)), // (2)
             optCD -> optCD.map(CD::getNoOfTracks).orElse(0)))          // (3)
     );
System.out.println(maxTracksByYear);                      // {2017=8, 2018=10}

Map<Genre, String> avgTracksByGenre = CD.cdList.stream()
    .collect(Collectors.groupingBy(
         CD::getGenre,
         Collectors.collectingAndThen(                                  // (4)
             Collectors.averagingDouble(CD::getNoOfTracks),             // (5)
             d -> String.format("%.1f", d)))                            // (6)
     );
System.out.println(avgTracksByGenre);                    // {JAZZ=8.0, POP=9.0}
  }
}
