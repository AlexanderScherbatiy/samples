package datatype.currency

/**
  * Created by alexsch on 3/19/2017.
  */
abstract class CurrencyZone {

  type Currency <: AbstractCurrency

  val CurrencyUnit: Currency

  def make(amount: Long): Currency

  abstract class AbstractCurrency {

    def designation: String

    def amount: Long

    def +(that: Currency): Currency = make(this.amount + that.amount)

    def *(number: Double): Currency = make((number * this.amount).toLong)

    override def toString: String =
      (amount.toDouble / CurrencyUnit.amount.toDouble)
        .formatted("%.2f")
  }

}

object Europe extends CurrencyZone {

  val Cent = make(1)
  val Euro = make(100)
  val CurrencyUnit = Euro

  type Currency = Euro

  override def make(cents: Long) = new Euro {
    override def amount = cents
  }

  abstract class Euro extends AbstractCurrency {
    override def designation = "EUR"
  }

}

object US extends CurrencyZone {

  val Cent = make(1)
  val Dollar = make(100)
  val CurrencyUnit = Dollar

  type Currency = Dollar

  def make(cents: Long) = new Dollar {
    override val amount = cents
  }

  abstract class Dollar extends AbstractCurrency {
    override def designation = "USD"
  }

}
