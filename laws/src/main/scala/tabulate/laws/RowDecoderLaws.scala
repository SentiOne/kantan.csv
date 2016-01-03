package tabulate.laws

import org.scalacheck.Prop._
import tabulate.RowDecoder

trait RowDecoderLaws[A] extends SafeRowDecoderLaws[A] {
  def safeRowDecodeFail(row: IllegalRow[A]): Boolean = rowDecoder.decode(row.value).isFailure

  def unsafeRowDecodeFail(row: IllegalRow[A]): Boolean =
    throws(classOf[java.lang.Exception])(rowDecoder.unsafeDecode(row.value))
}

object RowDecoderLaws {
  def apply[A](implicit c: RowDecoder[A]): RowDecoderLaws[A] = new RowDecoderLaws[A] {
    override implicit val rowDecoder = c
  }
}
