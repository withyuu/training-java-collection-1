package net.digitaliccat.training.map;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TreeMapPlay {

    private SortedMap<String, String> singerToSong;
    private SortedMap<PremierLeagueScore, String> teamToManagerName;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void setUp() {
        singerToSong = new TreeMap<>();
        singerToSong.put("Taylor Swift", "Blank Space");
        singerToSong.put("The Weeknd", "I Feel It Coming");
        singerToSong.put("Selena Gomez", "Hand To Myself");
        singerToSong.put("Charlie Puth", "See You Again");
        singerToSong.put("Marian Hill", "Down");
        singerToSong.put("Bruno Mars", "That's What I Like");

        teamToManagerName = new TreeMap<>();
        teamToManagerName.put(new PremierLeagueScore("Arsenal", 75, 77, 44), "Arsène Wenger");
        teamToManagerName.put(new PremierLeagueScore("Bournemouth", 46, 55, 67), "Eddie Howe");
        teamToManagerName.put(new PremierLeagueScore("Burnley FC", 40, 39, 55), "Sean Dyche");
        teamToManagerName.put(new PremierLeagueScore("Chelsea", 93, 85, 33), "Antonio Conte");
        teamToManagerName.put(new PremierLeagueScore("Crystal Palace", 41, 50, 63), null);
        teamToManagerName.put(new PremierLeagueScore("Everton", 61, 62, 44), "Ronald Koeman");
        teamToManagerName.put(new PremierLeagueScore("Hull City", 34, 37, 80), "Leonid Slutsky");
        teamToManagerName.put(new PremierLeagueScore("Leicester City", 44, 48, 63), "Craig Shakespeare");
        teamToManagerName.put(new PremierLeagueScore("Liverpool", 76, 78, 42), "Jürgen Klopp");
        teamToManagerName.put(new PremierLeagueScore("Man. City", 78, 80, 39), "Pep Guardiola");
        teamToManagerName.put(new PremierLeagueScore("Man United", 69, 54, 29), "José Mourinho");
        teamToManagerName.put(new PremierLeagueScore("Middlesbrough", 28, 27, 53), "Garry Monk");
        teamToManagerName.put(new PremierLeagueScore("Tottenham", 86, 86, 26), "Mauricio Pochettino");
        teamToManagerName.put(new PremierLeagueScore("Southampton", 46, 41, 48), "Claude Puel");
        teamToManagerName.put(new PremierLeagueScore("Stoke City", 44, 41, 56), "Mark Hughes");
        teamToManagerName.put(new PremierLeagueScore("Sunderland", 24, 29, 69), null);
        teamToManagerName.put(new PremierLeagueScore("Swansea City", 41, 45, 70), "Paul Clement");
        teamToManagerName.put(new PremierLeagueScore("Watford", 40, 40, 68), "Marco Silva");
        teamToManagerName.put(new PremierLeagueScore("West Brom", 45, 43, 51), "Tony Pulis");
        teamToManagerName.put(new PremierLeagueScore("West Ham", 45, 47, 64), "Slaven Bilić");
    }

    @Test
    public void playWithConstructors() {
        SortedMap<String, String> a = new TreeMap<>();
        SortedMap<String, String> b = new TreeMap<>((t1, t2) -> t1.length() - t2.length()); // We'll explain this later

        // NOT RECOMMENDED!!
        // ONLY use it when you KNOW what you are doing here
        // See: http://wiki.c2.com/?DoubleBraceInitialization
        SortedMap<String, String> c = new TreeMap<String, String>() {
            {
                put("Taylor Swift", "Blank Space");
                put("The Weeknd", "I Feel It Coming");
                put("Selena Gomez", "Hand To Myself");
                put("Charlie Puth", "See You Again");
                put("Marian Hill", "Down");
                put("Bruno Mars", "That's What I Like");
            }
        };

        SortedMap<String, String> d = new TreeMap<>(c); // Equals to putAll()

        assertEquals(0, a.size());
        assertEquals(0, b.size());
        assertEquals(6, c.size());
        assertEquals(6, d.size());
    }

    @Test
    public void treeMapIsSorted() {
        singerToSong.forEach((k,v) -> {System.out.printf("%15s: %s%n", k, v);});
    }

    @Test
    public void byDefaultTreeMapKeyMustBeComparable() {
        // Expect exception and message
        expectedEx.expect(ClassCastException.class);
        expectedEx.expectMessage("java.lang.Object cannot be cast to java.lang.Comparable");

        SortedMap<Object, String> s = new TreeMap<>();
        s.put(new Object(), "haha"); // boom
    }

    @Test
    public void treeMapIsDefaultSortedByComparableKeys() {
        teamToManagerName.forEach((k,v) -> {
            System.out.printf("%18s: GF:%-2d  GA:%-2d  GD:%-3d  PTS:%-2d   Manager: %s%n",
                    k.getName(),
                    k.getGoalsFor(),
                    k.getGoalsAgainst(),
                    k.getGoalsDifference(),
                    k.getPoints(),
                    v
            );
        });
    }

    @Test
    public void treeMapKeyComparationLogicCanBeOverridenWithComparator() {
        SortedMap<PremierLeagueScore, String> teamNameMap = new TreeMap<>((t1, t2) -> {
            return t1.getName().length() - t2.getName().length();
        });
        teamNameMap.putAll(teamToManagerName);

        teamNameMap.forEach((k,v) -> {
            System.out.printf("%18s: GF:%-2d  GA:%-2d  GD:%-3d  PTS:%-2d   Manager: %s%n",
                    k.getName(),
                    k.getGoalsFor(),
                    k.getGoalsAgainst(),
                    k.getGoalsDifference(),
                    k.getPoints(),
                    v
            );
        });

        // Huh!? why the size is shrink
        assertEquals(20, teamToManagerName.size());
        assertNotEquals(20, teamNameMap.size());
    }

    @Test
    public void becarefulWhenYouCreateCustomComparator() {
        // TreeMap treat the item with zero compare result as the same value and thus replace the old one when
        // the new one is put

        // Try using provided compareTo method
        SortedMap<PremierLeagueScore, String> teamNameMap = new TreeMap<>((t1, t2) -> {
            return t1.getName().compareTo(t2.getName());
        });
        teamNameMap.putAll(teamToManagerName);

        teamNameMap.forEach((k,v) -> {
            System.out.printf("%18s: GF:%-2d  GA:%-2d  GD:%-3d  PTS:%-2d   Manager: %s%n",
                    k.getName(),
                    k.getGoalsFor(),
                    k.getGoalsAgainst(),
                    k.getGoalsDifference(),
                    k.getPoints(),
                    v
            );
        });

        // Huh!? why the size is shrink
        assertEquals(20, teamToManagerName.size());
        assertEquals(20, teamNameMap.size());
    }

}

class PremierLeagueScore implements Comparable<PremierLeagueScore> {

    private String name;
    private int points;
    private int goalsFor;
    private int goalsAgainst;

    public PremierLeagueScore(String name, int points, int goalsFor, int goalsAgainst) {
        this.name = name;
        this.points = points;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public int getGoalsDifference() {
        return goalsFor - goalsAgainst;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PremierLeagueScore)) return false;

        PremierLeagueScore that = (PremierLeagueScore) o;

        if (points != that.points) return false;
        if (goalsFor != that.goalsFor) return false;
        if (goalsAgainst != that.goalsAgainst) return false;
        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + points;
        result = 31 * result + goalsFor;
        result = 31 * result + goalsAgainst;
        return result;
    }

    @Override
    public int compareTo(PremierLeagueScore o) {

        int ret = o.points - this.points;
        if (ret != 0)
            return ret;

        ret =  o.getGoalsDifference() - this.getGoalsDifference();
        if (ret != 0)
            return ret;

        return o.goalsFor - this.goalsFor;
    }
}
