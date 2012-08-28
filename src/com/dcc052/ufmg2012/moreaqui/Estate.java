package com.dcc052.ufmg2012.moreaqui;

/**
 * This class describes a real estate.
 *
 * @author fernando
 *
 */
public class Estate {
  /** The serial version of this class. */
  private static final long serialVersionUID = 1734003038366261208L;

  /** The type of the state is not known. */
  public static final int UNKNOWN_TYPE = 0;

  /** The estate is a house, i.e., is not in a building with other aps. */
  public static final int HOUSE = 1;

  /** The estate is an apartment. */
  public static final int APARTMENT = 2;

  /** The estate is a commercial room. */
  public static final int SHOP = 3;

  /** The size of the state is not known. */
  public static final int UNKNOWN_SIZE = 0;

  /** The estate is small, i.e., it has one or two rooms. */
  public static final int SMALL = 1;

  /** The estate is medium sized, having three to four rooms. */
  public static final int MEDIUM = 2;

  /** The estate is large, i.e., it has more than four rooms. */
  public static final int LARGE = 3;

  /** The telephone of the seller. */
  public final int PHONE;

  /** The type of the estate: house, apartment or shop. */
  public final String TYPE;

  /** The size of the estate: small, medium, large. */
  public final String SIZE;

  /** True if the estate is under construction. */
  public final String STATUS;

  /**
   * Class constructor.
   *
   * @param type
   *        either house, apartment or shop.
   * @param size
   *        either small, large or medium.
   * @param phone
   *        the phone of the seller.
   * @param inConstruction
   *        true if the estate is still under construction.
   */
  public Estate(final String type, final String size, final int phone,
      final String inConstruction) {
    this.TYPE = type;
    this.SIZE = size;
    this.PHONE = phone;
    this.STATUS = inConstruction;
  }

  @Override
  public final String toString() {
    String ans = "Imovel: " + TYPE + ", Tamanho: " + SIZE
        + ", Contato: " + this.PHONE + ", (" + this.STATUS + ")";
    return ans;
  }

}
