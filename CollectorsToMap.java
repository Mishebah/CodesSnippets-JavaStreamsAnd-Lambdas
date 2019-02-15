import java.time.Year;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class CollectorsToMap {
  public static void main(String[] args) {

{
//Query: Create a map of cd titles and their release year.
Map<String, Year> mapTitleToYear = CD.cdList.stream()
    .collect(Collectors.toMap(CD::getTitle, CD::getYear));
System.out.println(mapTitleToYear);
}

{
//Query: Create a map of cds and their release year.
Map<CD, Year> mapCDToYear = CD.cdList.stream()
    .collect(Collectors.toMap(Function.identity(), CD::getYear));
System.out.println(mapCDToYear);
}

{
//Query: Create a map of CD titles and their release year (collisions).
List<CD> dupList = Arrays.asList(CD.cd0, CD.cd1, CD.cd2, CD.cd0, CD.cd1);
//Map<String, Year> mapTitleToYear1 = dupList.stream()
//    .collect(Collectors.toMap(CD::getTitle, CD::getYear));
//System.out.println(mapTitleToYear1);
// java.lang.IllegalStateException: Duplicate key 2017

Map<String, Year> mapTitleToYear2 = dupList.stream()
    .collect(Collectors.toMap(CD::getTitle, CD::getYear, (y1, y2) -> y1));
System.out.println(mapTitleToYear2);
}

{
//Query: Create a map of release year and titles released each year.
Map<Year, String> mapTitleToYear3 = CD.cdList.stream()
    .collect(Collectors.toMap(CD::getYear, CD::getTitle,
                              (tt, t) -> tt + ":" + t));
System.out.println(mapTitleToYear3);
System.out.println(mapTitleToYear3 instanceof HashMap);
}

{
//Query: Create a TreeMap of release year and longest title released each year.
TreeMap<Year, String> mapTitleToYear4 = CD.cdList.stream()
    .collect(Collectors.toMap(CD::getYear, CD::getTitle,
//                              BinaryOperator.maxBy(Comparator.naturalOrder()),
                              (str1, str2) -> str1.compareTo(str2) > 0
                                ? str1 : str2,
                              TreeMap::new));
System.out.println(mapTitleToYear4);
}

{
//Query: Create a concurrent map of book titles released each year.
ConcurrentMap<Year, String> mapTitleToYear = CD.cdList
    .parallelStream()
    .collect(Collectors.toConcurrentMap(CD::getYear, CD::getTitle,
                                        (tt, t) -> tt + ":" + t));
System.out.println(mapTitleToYear);
//{2017=Java Jam:Java Jive, 2018=Lambda Dancing:Hot Generics:Keep on Erasing}
}
  }
}