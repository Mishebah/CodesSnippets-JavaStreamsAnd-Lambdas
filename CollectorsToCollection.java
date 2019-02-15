import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public final class CollectorsToCollection {
  public static void main(String[] args) {

{
// Collection
ArrayList<String> cdTitles1 = CD.cdList.stream()
    .map(CD::getTitle)
    .collect(Collectors.toCollection(ArrayList::new));
System.out.println("ArrayList:" + cdTitles1);
}

{
//List
List<String> cdTitles3 = CD.cdList.stream()
   .map(CD::getTitle)
   .collect(Collectors.toList());
System.out.println("List:" + cdTitles3);
}

{
// Set
Set<String> cdTitles2 = CD.cdList.stream()
    .map(CD::getTitle)
    .collect(Collectors.toSet());
System.out.println("Set:" + cdTitles2);
}



{
  // BETTER WAYS OF DOING THIS: list.toArray().
List<CD> cdList = Arrays.stream(CD.cdArray)   // Array to List
    .collect(Collectors.toList());
System.out.println("List:" + cdList);
}

  }
}