package hearts.model;

import java.util.Arrays;
import java.util.List;

/**
 *  Specification of the rank values for a standard deck of cards.
 *  Client has ability to set either the ace or the king to be the
 *  highest ranking card; default is king high.  Ranks are
 *  established in the following ascending order:
 *  <p>Ace high: 2, 3, 4, 5, 6, 7, 8, 9, 10, jack, queen, king, ace.
 */
public class Rank implements Comparable {
   private String name;
   private String symbol;
    
   /** 
    * The rank ace. 
    */
   public final static Rank ACE = new Rank( "Ace", "A" );
   /** 
    * The rank two. 
    */
   public final static Rank TWO = new Rank( "Two", "2" );
   /** 
    * The rank three. 
    */
   public final static Rank THREE = new Rank( "Three", "3" );
   /** 
    * The rank four. 
    */
   public final static Rank FOUR = new Rank( "Four", "4" );
   /**
    * The rank five.
    */
   public final static Rank FIVE = new Rank( "Five", "5" );
   /**
    * The rank six.
    */
   public final static Rank SIX = new Rank( "Six", "6" );
   /** 
    * The rank seven.
    */
   public final static Rank SEVEN = new Rank( "Seven", "7" );
   /**
    * The rank eight.
    */
   public final static Rank EIGHT = new Rank( "Eight", "8" );
   /**
    * The rank nine.
    */
   public final static Rank NINE = new Rank( "Nine", "9" );
   /**
    * The rank ten.
    */
   public final static Rank TEN = new Rank( "Ten", "10" );
   /**
    * The rank jack.
    */
   public final static Rank JACK = new Rank( "Jack", "J" );
   /**
    * The rank queen.
    */
   public final static Rank QUEEN = new Rank( "Queen", "Q" );
   /**
    * The rank king.
    */
   public final static Rank KING = new Rank( "King", "K" );

    /**
     * Established rank order with smallest index being the smallest rank and largest index being the largest rank.
     */
   public final static List<Rank> VALUES = Arrays.asList( new Rank[] { TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE } );

   // Constructor - declared private as only the predefined values should
   // be used by the client.
   private Rank(String nameValue, String symbolValue ) {
      name = nameValue;
      symbol = symbolValue;
   }    

  /**
   *  Returns a description of this rank.
   *  @return the name of this rank.
   */
   public String getName() {
      return name;
   }
    
    
  /**
   *  Returns a description of this rank.
   *  @return the name of this rank.
   */
   public String toString() { return symbol; }
    
    
  /**
   *  The symbol associated with this rank.  Returns the symbol, which
   *  usually constitutes a single character, in the form of a string.
   *  Symbol is used for the construction of the filenames of the card images.
   *  @return string containing the symbol for the rank.
   */
   public String getSymbol() {
      return symbol;
   }

  /** 
   *  Compares the ranks.  Result is dependent on the whether the ace
   *  or the king is considered to be the high rank.
   *  @param otherRankObject the other rank.
   *  @return the arithmetic difference between the compared ranks
   *  based on their ordering in the listing of values.  This result
   *  may differ depending on whether the king or the ace is considered
   *  the high card.  Result will be less than 0 if this rank is lower than
   *  the other rank, 0 if the ranks are the same, or more than 0 if this
   *  rank is higher than the other rank.
   */
   public int compareTo( Object otherRankObject ) {
      Rank otherRank = (Rank) otherRankObject;
      return VALUES.indexOf( this ) - VALUES.indexOf( otherRank );
   }
}                                                               