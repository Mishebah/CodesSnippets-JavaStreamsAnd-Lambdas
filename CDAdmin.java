import java.util.List;

public final class CDAdmin {
  public static void main(String[] args) {
    List<CD> cdList = CD.cdList;
    System.out.println("     Artist    Title           No. Year Genre");
    for(int i = 0; i < cdList.size(); ++i) {
      CD cd = cdList.get(i);
      String cdToString = String.format("%-10s%-16s%-4d%-5s%-5s",
          cd.getArtist(), cd.getTitle(), cd.getNoOfTracks(),
          cd.getYear(), cd.getGenre());
      System.out.printf("cd%d: %s%n", i, cdToString);
    }
  }
}