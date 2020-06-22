package org.nodumo.bankapp.application.definitions

/**
 * Credit user account interpreter.
 *
 * @author Nick Odumo (nodumowebdev@gmail.com)
 * @tparam F Effect
 * @tparam Event Event
 * @tparam Response Response
 */
trait UseCaseAlgebra[F[_], Event, Response] {

  /**
   * Credit user account interpreter.
   *
   * @author Nick Odumo (nodumowebdev@gmail.com)
   * @param event Event
   */
  def execute(event: Event): F[Response]

}
