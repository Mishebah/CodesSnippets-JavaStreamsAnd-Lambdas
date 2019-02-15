import java.time.Year;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public final class FlatMapping {
  public static void main(String[] args) {

List<CD> cdList1 = Arrays.asList(CD.cd0, CD.cd1, CD.cd1);
List<CD> cdList2 = Arrays.asList(CD.cd0, CD.cd1);

{
List<String> cdTitles = CD.cdList
    .stream()                                // Stream<CD>
    .map(cd -> cd.getTitle())                // mapper: CD -> String,
                                             // returns Stream<String>.
    .collect(Collectors.toList());           // List<String>
  System.out.println("CD titles: " + cdTitles);
}

{
List<String> cdTitles = CD.cdList
    .stream()                                // Stream<CD>
    .flatMap(cd -> Stream.of(cd.getTitle())) // mapper: CD -> Stream<String>,
                                             // returns Stream<String>.
    .collect(Collectors.toList());           // List<String>
  System.out.println("CD titles: " + cdTitles);
}
{
// Incorrect Solution using Stream.distinct().
// Query: Create a list of unique CDs from two given lists of CDs.
List<List<CD>> listOfListOfCDs =
    Stream.of(cdList1, cdList2)                        // Stream<List<CD>>
          .distinct()                                  // Stream<List<CD>>
          .collect(Collectors.toList());               // List<List<CD>>
System.out.println(listOfListOfCDs);
}

{
// Incorrect Solution using Stream.map().
// Query: Create a list of unique CDs from two given lists of CDs.
List<Stream<CD>> listOfStreamOfCD =
    Stream.of(cdList1, cdList2)                        // Stream<List<CD>>
          .map(List::stream)                           // Stream<Stream<CD>>
          .distinct()                                  // Stream<Stream<CD>>
          .collect(Collectors.toList());               // List<Stream<CD>>
System.out.println(listOfStreamOfCD);
}

{
// Query: Create a list of unique CDs from two given lists of CDs.
List<CD> listOfCD =
    Stream.of(cdList1, cdList2)                        // Stream<List<CD>>
          .flatMap(List::stream)                       // Stream<CD>
          .distinct()                                  // Stream<CD>
          .collect(Collectors.toList());               // List<CD>
System.out.println(listOfCD);
}

{
//Query: Create a list with the titles of unique CDs from two given lists of CDs.
List<String> listOfCDTitles =
    Stream.of(cdList1, cdList2)                        // Stream<List<CD>>
          .flatMap(List::stream)                       // Stream<CD>
          .distinct()                                  // Stream<CD>
          .map(CD::getTitle)                           // Stream<String>
          .collect(Collectors.toList());               // List<String>
System.out.println(listOfCDTitles);
}

{ // Merge the two lists.
  List<String> listOfCDTitles =
      Stream.concat(cdList1.stream(), cdList2.stream())  // Stream<CD>
            .distinct()                                  // Stream<CD>
            .map(CD::getTitle)                           // Stream<String>
            .collect(Collectors.toList());               // List<String>
  System.out.println(listOfCDTitles);
}

{
  // Flattening a 2-dim array.
int[][] twoDimArray = { {2017, 2018}, {1948, 1949} };
int[] intArray = Arrays
    .stream(twoDimArray)                     // (1) Stream<int[]>
    .flatMapToInt(row -> Arrays.stream(row)) // (2) mapper: int[] -> IntStream,
                                    // flattens Stream<IntStream> to IntStream.
    .toArray();                              // [2017, 2018, 1948, 1949]
System.out.println(Arrays.toString(intArray));
}
  }
}