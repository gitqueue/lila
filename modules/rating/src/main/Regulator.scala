package lila.rating

object Regulator {

  def apply(perfType: PerfType, before: Perf, after: Perf) =
    if (before.nb >= after.nb) after
    else {
      val diff = (after.glicko.rating - before.glicko.rating).abs
      val extra = diff / regulationDivider(perfType)
      after.copy(
        glicko = after.glicko.copy(
          rating = after.glicko.rating + extra))
    }

  private def regulationDivider(perfType: PerfType): Int = perfType match {
    case PerfType.Bullet => 70
    case PerfType.Blitz  => 50
    case _               => 40
  }
}
