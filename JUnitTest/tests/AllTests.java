//import java.util.List;
//import java.util.ArrayList;
//
//import hearts.util.*;
//import hearts.model.*;

//import org.junit.*;
//import static org.junit.Assert.*;

package JUnitTest.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({CardRankComparatorTest.class, CardSuitComparatorTest.class,
        CardTest.class, ComPlayerTest.class, DeckTest.class, GameTest.class,
        HandTest.class, RuleEngineTest.class, SetTest.class})

public class AllTests {
}